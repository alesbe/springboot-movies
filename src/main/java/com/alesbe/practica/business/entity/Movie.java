package com.alesbe.practica.business.entity;

public class Movie {
    int id;
    String imdbId;
    String title;
    int year;
    String image;
    int runtime;
    String description;
    Director director;
    
    public Movie(int id, String imdbId, String title, int year, String image, int runtime, String description) {
        this.id = id;
        this.imdbId = imdbId;
        this.title = title;
        this.year = year;
        this.image = image;
        this.runtime = runtime;
        this.description = description;
    }

    public Movie(int id, String imdbId, String title, int year, int runtime) {
        this.id = id;
        this.imdbId = imdbId;
        this.title = title;
        this.year = year;
        this.image = null;
        this.runtime = runtime;
        this.description = null;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "Movie [id=" + id + ", imdbId=" + imdbId + ", title=" + title + ", year=" + year + ", image=" + image
                + ", runtime=" + runtime + ", description=" + description + ", director=" + director + "]";
    }
}
