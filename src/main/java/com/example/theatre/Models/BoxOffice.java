package com.example.theatre.Models;

import javax.persistence.*;

@Entity
@Table(name = "box_office")
public class BoxOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theatre_id", nullable = false)
    private Theatre theatre;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "time_interval_of_work", nullable = false)
    private String timeIntervalOfWork;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    public BoxOffice() {}

    public BoxOffice(Theatre theatre, String location, String timeIntervalOfWork, String phoneNumber) {
        this.theatre = theatre;
        this.location = location;
        this.timeIntervalOfWork = timeIntervalOfWork;
        this.phoneNumber = phoneNumber;
    }

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTimeIntervalOfWork() {
        return timeIntervalOfWork;
    }

    public void setTimeIntervalOfWork(String timeIntervalOfWork) {
        this.timeIntervalOfWork = timeIntervalOfWork;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Transient
    public String getTheatreName() {
        return theatre != null ? theatre.getName() : null;
    }
}
