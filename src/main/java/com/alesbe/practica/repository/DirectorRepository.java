package com.alesbe.practica.repository;

import java.util.List;

import com.alesbe.practica.business.entity.Director;

public interface DirectorRepository {
    public List<Director> getAll();
}