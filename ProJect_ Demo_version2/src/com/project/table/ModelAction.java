package com.project.table;

import com.project.model.ModelListFilm;

public class ModelAction {

    public ModelListFilm getStudent() {
        return student;
    }

    public void setStudent(ModelListFilm student) {
        this.student = student;
    }

    public EventAction getEvent() {
        return event;
    }

    public void setEvent(EventAction event) {
        this.event = event;
    }

    public ModelAction(ModelListFilm student, EventAction event) {
        this.student = student;
        this.event = event;
    }

    public ModelAction() {
    }

    private ModelListFilm student;
    private EventAction event;
}
