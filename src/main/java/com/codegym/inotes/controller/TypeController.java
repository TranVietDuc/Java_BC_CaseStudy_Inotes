package com.codegym.inotes.controller;

import com.codegym.inotes.model.Type;
import com.codegym.inotes.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public ModelAndView listTypes(){
        Iterable<Type> types = typeService.findAll();
        ModelAndView modelAndView = new ModelAndView("type/list");
        modelAndView.addObject("types", types);
        return modelAndView;
    }

    @GetMapping("/create-type")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("type/create");
        modelAndView.addObject("type", new Type());
        return modelAndView;
    }

    @PostMapping("/create-type")
    public ModelAndView saveType(@ModelAttribute("type") Type type){
        typeService.save(type);

        ModelAndView modelAndView = new ModelAndView("type/create");
        modelAndView.addObject("type", new Type());
        modelAndView.addObject("message", "New type created successfully");
        return modelAndView;
    }

    @GetMapping("/edit-type/{id}")
    public ModelAndView showEditForm(@PathVariable int id){
        Type type = typeService.findById(id);
        if(type != null) {
            ModelAndView modelAndView = new ModelAndView("type/edit");
            modelAndView.addObject("type", type);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("error404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-type")
    public ModelAndView updateType(@ModelAttribute("type") Type type){
        typeService.save(type);
        ModelAndView modelAndView = new ModelAndView("type/edit");
        modelAndView.addObject("type", type);
        modelAndView.addObject("message", "Type updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-type/{id}")
    public ModelAndView showDeleteForm(@PathVariable int id){
        Type type = typeService.findById(id);
        if(type != null) {
            ModelAndView modelAndView = new ModelAndView("type/delete");
            modelAndView.addObject("type", type);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("error404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-type")
    public String deleteType(@ModelAttribute("type") Type type){
        typeService.remove(type.getId());
        return "redirect:types";
    }
}