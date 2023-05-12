package com.alesbe.practica.business.service.impl;

import java.util.List;

import com.alesbe.practica.business.entity.Director;
import com.alesbe.practica.business.service.DirectorService;
import com.alesbe.practica.repository.DirectorRepository;
import com.alesbe.practica.repository.impl.DirectorRepositoryImpl;

public class DirectorServiceImpl implements DirectorService{

    DirectorRepository repository = new DirectorRepositoryImpl();

    @Override
    public List<Director> getAll() {
        return this.repository.getAll();
    }

    @Override
    public Director getById(int directorId) {
        return this.repository.getById(directorId);
    }
    
    @Override
    public Director getDirectorByMovieId(int movieId) {
        return this.repository.getDirectorByMovieId(movieId);
    }
}
