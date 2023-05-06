package com.alesbe.practica.business.entity;

public class Director {
    int id;
    String imdb_id;
    String name;
    int birthYear;
    int deathYear;
    public Director(int id, String imdb_id, String name, int birthYear, int deathYear) {
        this.id = id;
        this.imdb_id = imdb_id;
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
    public String getImdb_id() {
        return imdb_id;
    }
    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
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
        return "Director [id=" + id + ", imdb_id=" + imdb_id + ", name=" + name + ", birthYear=" + birthYear
                + ", deathYear=" + deathYear + "]";
    }
}
