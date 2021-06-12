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

import com.example.demo.Entities.Citas;
import com.example.demo.Repositories.CitasRepository;

@RestController
@RequestMapping(value = "/citas")
public class CitasControl {
	
	@Autowired
	CitasRepository repository;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public Collection<Citas> getListaCitas(){
		 Iterable<Citas> listaCitas = repository.findAll();
		return (Collection<Citas>) listaCitas;
	}
	
	@GetMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Citas getCitas(@PathVariable(name = "id") Long id) {
		Optional<Citas> cita = repository.findById(id);
		Citas resultados = null;
		if(cita.isPresent()) {
			resultados = cita.get();
		}
		return resultados;
	}
	
	@PutMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public Citas updateCitas(@PathVariable(name = "id")Long id, 
			@RequestBody Citas cita) {
		Optional<Citas> Cita = repository.findById(id);
		if(Cita.isPresent()) {
			Citas moderno = Cita.get();
			moderno.setId(id);
			moderno.setPrimerNombre(cita.getPrimerNombre());
			moderno.setSegundoNombre(cita.getSegundoNombre());
			moderno.setPrimerApellido(cita.getPrimerApellido());
			moderno.setSegundoApellido(cita.getSegundoApellido());	
			moderno.setEdad(cita.getEdad());
			Citas cargarCitas = repository.save(moderno);
			return cargarCitas;
		}
		return null;
	}
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void deleteCitas(@PathVariable(name = "id") Long id) {
		repository.deleteById(id);
	}
	
}
