package com.alesbe.practica.business.service;

import java.util.List;

import com.alesbe.practica.business.entity.Director;

public interface DirectorService {
    public List<Director> getAll();
    public Director getById(int directorId);
}
