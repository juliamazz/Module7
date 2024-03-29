package org.example.domain;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name = "todoitem")
public class TodoItem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Assuming you want auto-generated IDs
    private int id; // Add an ID field for Hibernate to use

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private boolean status;

    public TodoItem(int i, String d, boolean b) {
        this.id = i;
        this.description = d;
        this.status = b;
    }

    public TodoItem(int i, String d) {
        this.id = i;
        this.description = d;
        this.status = false;
    }
    public TodoItem(String d, boolean b) {
        this.description = d;
        this.status = b;
    }

    public TodoItem() {
        this.description = "Default";
        this.status = false;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

