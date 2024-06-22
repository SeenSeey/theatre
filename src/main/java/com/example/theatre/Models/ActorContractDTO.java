package com.example.theatre.Models;

public class ActorContractDTO {
    private Actor actor;
    private Contract contract;

    public ActorContractDTO(Actor actor, Contract contract) {
        this.actor = actor;
        this.contract = contract;
    }

    public Actor getActor() {
        return actor;
    }

    public Contract getContract() {
        return contract;
    }

    public String getName() {
        return actor.getName();
    }

    public String getSurname() {
        return actor.getSurname();
    }

    public String getEducation() {
        return actor.getEducation();
    }

    public String getAward() {
        return actor.getAward();
    }

    public double getSalaryForPerformance() {
        return contract.getSalaryForPerformance();
    }

    public String getRole() {
        return contract.getRole();
    }
}
