package com.alesbe.practica.business.entity;

public class Actor {
    int id;
    String imdbId;
    String name;
    int birthYear;
    int deathYear;
    
    public Actor(int id, String imdbId, String name, int birthYear, int deathYear) {
        this.id = id;
        this.imdbId = imdbId;
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public int getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(int deathYear) {
        this.deathYear = deathYear;
    }

    @Override
    public String toString() {
        return "Actor [id=" + id + ", imdbId=" + imdbId + ", name=" + name + ", birthYear=" + birthYear + ", deathYear="
                + deathYear + "]";
    }
}
