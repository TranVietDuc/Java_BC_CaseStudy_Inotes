package com.codegym.inotes.controller;

import com.codegym.inotes.model.Note;
import com.codegym.inotes.model.Type;
import com.codegym.inotes.service.NoteService;
import com.codegym.inotes.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private TypeService typeService;

    @GetMapping("/")
    public ModelAndView noteList(@RequestParam("title") Optional<String> title,@RequestParam("type") Optional<Integer> typeId, @PageableDefault(size = 5) Pageable pageable){

        Page<Note> notes;
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("types",typeService.findAll());
        //Phuc vu tim kiem

        if(typeId.isPresent()||title.isPresent())                             // Phan trang tim kiem neu User an search
        {
            if(typeId.get()==-1)                                             // Nguoi dung chi muon tim kiem theo Title
            {
                notes = noteService.findAllByTitleContaining(title.get(),pageable);
            }
            else                                                           // Nguoi dung tim kiem Title&Type
                {
                Type type = typeService.findById(typeId.get());
                notes = noteService.findAllByTypeAndTitleContaining(type, title.get(), pageable);
            }
            modelAndView.addObject("searchedType",typeId.get());
            modelAndView.addObject("searchedTitle",title.get());
        }
        else                                                               // khi User chi yeu cau xem toan bo Notes
            { notes= noteService.findAll(pageable);
                modelAndView.addObject("searchedType",-1);
                modelAndView.addObject("searchedTitle","");
            }


        // Gan 1 type Unknown cho nhung Note ko dc dinh nghia type
        for (Note note:notes) {
          if(note.getType()==null)
          {
              note.setType(new Type("Unknown","Unknown"));
          }
        }

        //Phuc vu hien thi Note list

        modelAndView.addObject("notes",notes);
        return modelAndView;
    }

    @GetMapping("/create-note")
    public  ModelAndView showCreatNoteForm(){
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("note", new Note());
        modelAndView.addObject("types",typeService.findAll());
        return  modelAndView;
    }

    @PostMapping("/create-note")
    public ModelAndView createNote(@Validated @ModelAttribute("note") Note note, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("types",typeService.findAll());
        if(bindingResult.hasFieldErrors())  {

          return modelAndView;
      }
        noteService.save(note);
        modelAndView.addObject("note",new Note());
        modelAndView.addObject("message","New Note was created");
        return modelAndView;
    }

    @GetMapping("/view-note/{id}")
        public ModelAndView showDetail(@PathVariable("id") int id){
        Note note = noteService.findById(id);
        ModelAndView modelAndView = new ModelAndView("detail");
        modelAndView.addObject("note", note);
        return modelAndView;
        }


    @GetMapping("/edit-note/{id}")
    public ModelAndView showEditNoteForm(@PathVariable("id") int id){
        Note note = noteService.findById(id);

        if (note != null){

            ModelAndView modelAndView = new ModelAndView("edit");
            modelAndView.addObject("types",typeService.findAll());
            modelAndView.addObject("note",note);
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("error404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-note")
    public ModelAndView editNote(@Validated @ModelAttribute("note") Note note,BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("types",typeService.findAll());
        if(bindingResult.hasFieldErrors())  {
            modelAndView.addObject("note",note);
            return modelAndView;
        }
        noteService.save(note);
        modelAndView.addObject("note",new Note());
        modelAndView.addObject("message","Note was updated");
        return modelAndView;
    }

    @GetMapping("/delete-note/{id}")
    public ModelAndView showDeleteForm(@PathVariable int id) {
        Note note = noteService.findById(id);

        if (note != null) {
            ModelAndView modelAndView = new ModelAndView("delete");
            modelAndView.addObject("note", note);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-note")
    public String deleteNote(@ModelAttribute("note") Note note) {
        noteService.remove(note.getId());
        return "redirect:/";

    }
}
