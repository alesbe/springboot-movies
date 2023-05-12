package com.alesbe.practica.repository;

import java.util.List;

import com.alesbe.practica.business.entity.Director;

public interface DirectorRepository {
    public List<Director> getAll();
    public Director getById(int directorId);
    public Director getDirectorByMovieId(int movieId);
}
