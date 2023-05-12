package com.alesbe.practica.business.service;

import java.util.List;

import com.alesbe.practica.business.entity.Actor;

public interface ActorService {
    public List<Actor> getAll();
    public List<Actor> getActoresByMovieId(int movieId);
    public Actor getActorById(int actorId);
}