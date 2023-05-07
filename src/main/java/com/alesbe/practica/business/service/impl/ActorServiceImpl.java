package com.alesbe.practica.business.service.impl;

import java.util.List;

import com.alesbe.practica.business.entity.Actor;
import com.alesbe.practica.business.service.ActorService;
import com.alesbe.practica.repository.ActorRepository;
import com.alesbe.practica.repository.impl.ActorRepositoryImpl;

public class ActorServiceImpl implements ActorService{

    ActorRepository repository = new ActorRepositoryImpl();

    @Override
    public List<Actor> getAll() {
        return this.repository.getAll();
    }

    @Override
    public List<Actor> getActoresByMovieId(int movieId) {
        return this.repository.getAll();
    }
    
}
