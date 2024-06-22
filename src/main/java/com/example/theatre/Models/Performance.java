package com.example.theatre.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;

@Entity
@Table(name = "performance")
public class Performance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "theatre_id")
    private Theatre theatre;

    @ManyToOne
    @JoinColumn(name = "director_id")
    private Director director;

    @Column(name = "name")
    private String name;

    @Column(name = "time_interval_performance")
    private String timeIntervalPerformance;

    @Column(name = "author")
    private String author;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StringProperty nameProperty() {
        return new SimpleStringProperty(name);
    }

    public String getTimeIntervalPerformance() {
        return timeIntervalPerformance;
    }

    public void setTimeIntervalPerformance(String timeIntervalPerformance) {
        this.timeIntervalPerformance = timeIntervalPerformance;
    }

    public StringProperty timeIntervalPerformanceProperty() {
        return new SimpleStringProperty(timeIntervalPerformance);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public StringProperty authorProperty() {
        return new SimpleStringProperty(author);
    }

    public void setDirectorId(Integer directorId) {
        if (directorId == null) {
            this.director = null;
        } else {
            this.director = new Director();
            this.director.setId(directorId);
        }
    }

    public Integer getDirectorId() {
        return this.director != null ? this.director.getId() : null;
    }

    public void setTheatreId(Integer theatreId) {
        if (theatreId == null) {
            this.theatre = null;
        } else {
            this.theatre = new Theatre();
            this.theatre.setId(theatreId);
        }
    }

    public Integer getTheatreId() {
        return this.theatre != null ? this.theatre.getId() : null;
    }


}
