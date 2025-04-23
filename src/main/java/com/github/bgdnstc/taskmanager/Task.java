package com.github.bgdnstc.taskmanager;

import java.util.Date;
import java.util.List;

public class Task {
    int id;
    String title;
    String description;
    Date dueDate;
    String personAssignedTo;
//    List<Task> subtasks;
    String comment;

    public Task(int id, String title, String description, Date dueDate, String personAssignedTo, String comment) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.personAssignedTo = personAssignedTo;
        this.comment = comment;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getPersonAssignedTo() {
        return personAssignedTo;
    }

    public void setPersonAssignedTo(String personAssignedTo) {
        this.personAssignedTo = personAssignedTo;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

//    public List<Task> getSubtasks() {
//        return subtasks;
//    }
//
//    public void setSubtasks(List<Task> subtasks) {
//        this.subtasks = subtasks;
//    }
}
