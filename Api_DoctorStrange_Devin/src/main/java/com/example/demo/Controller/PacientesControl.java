package com.example.demo.Controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entities.Pacientes;
import com.example.demo.Repositories.PacientesRepository;

@RestController
@RequestMapping(value = "/pacientes")
public class PacientesControl {
	
	@Autowired
	PacientesRepository repository;	
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public Collection<Pacientes> getListaPacientes(){
		 Iterable<Pacientes> listaPacientes = repository.findAll();
		return (Collection<Pacientes>) listaPacientes;
	}
	
	@GetMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Pacientes getPacientes(@PathVariable(name = "id") Long id) {
		Optional<Pacientes> paciente = repository.findById(id);
		Pacientes resultados = null;
		if(paciente.isPresent()) {
			resultados = paciente.get();
		}
		return resultados;
	}
	
	@PutMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public Pacientes updatePacientes(@PathVariable(name = "id")Long id, 
			@RequestBody Pacientes paciente) {
		Optional<Pacientes> Paciente = repository.findById(id);
		if(Paciente.isPresent()) {
			Pacientes moderno = Paciente.get();
			moderno.setId(id);
			moderno.setFecha(paciente.getFecha());
			moderno.setHora(paciente.getHora());
			moderno.setPaciente(paciente.getPaciente());
			moderno.setEstado(paciente.getEstado());	
			moderno.setObservaciones(paciente.getObservaciones());
			Pacientes cargarPacientes = repository.save(moderno);
			return cargarPacientes;
		}
		return null;
	}
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void deletePacientes(@PathVariable(name = "id") Long id) {
		repository.deleteById(id);
	}
	
}
