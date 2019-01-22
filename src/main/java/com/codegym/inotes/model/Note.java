package com.codegym.inotes.model;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "note")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotEmpty(message = "Not be Empty")
    private String title;

    @NotEmpty(message = "Not be Empty")
    private String content;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;


    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Note() {
    }

    public Note(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
