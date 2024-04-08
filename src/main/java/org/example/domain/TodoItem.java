package org.example.domain;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name = "todoitem")
public class TodoItem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Assuming you want auto-generated IDs
    @Column(name="id",updatable = false, nullable = false)
    private int id=-1; // Add an ID field for Hibernate to use

    @Column(name = "description")
    private String description;



    public TodoItem(int i, String d) {
        this.id = i;
        this.description = d;

    }


    public TodoItem(String d) {
        this.description = d;
    }

    public TodoItem() {
        this.description = "Default";

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

