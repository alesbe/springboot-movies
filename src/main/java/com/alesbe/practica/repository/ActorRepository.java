package com.alesbe.practica.repository;

import java.util.List;

import com.alesbe.practica.business.entity.Actor;

public interface ActorRepository {
    public List<Actor> getAll();
    public List<Actor> getActoresByMovieId(int movieId);
}
