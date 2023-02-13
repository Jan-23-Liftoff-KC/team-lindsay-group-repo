package org.launchcode.medicalapp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notesId;

    public Long getNotesId() {
        return notesId;
    }

    private String noteContent;

    public void setNotesId(Long notesId) {
        this.notesId = notesId;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }
}
