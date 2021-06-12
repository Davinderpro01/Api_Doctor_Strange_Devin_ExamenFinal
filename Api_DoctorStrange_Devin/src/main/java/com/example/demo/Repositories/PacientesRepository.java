package com.example.demo.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Entities.Pacientes;

public interface PacientesRepository extends CrudRepository<Pacientes, Long> {

}