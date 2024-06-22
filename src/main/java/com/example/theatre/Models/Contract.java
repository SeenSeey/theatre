package com.example.theatre.Models;

import javax.persistence.*;

@Entity
@Table(name = "contract")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "performance_id")
    private Performance performance;

    @ManyToOne
    @JoinColumn(name = "actor_id")
    private Actor actor;

    @Column(name = "role")
    private String role;

    @Column(name = "salary_for_performance")
    private double salaryForPerformance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Performance getPerformance() {
        return performance;
    }

    public void setPerformance(Performance performance) {
        this.performance = performance;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getSalaryForPerformance() {
        return salaryForPerformance;
    }

    public void setSalaryForPerformance(double salaryForPerformance) {
        this.salaryForPerformance = salaryForPerformance;
    }

    public void setPerformanceId(int performanceId) {
        this.performance = new Performance();
        this.performance.setId(performanceId);
    }

    public void setActorId(int actorId) {
        this.actor = new Actor();
        this.actor.setId(actorId);
    }
}
