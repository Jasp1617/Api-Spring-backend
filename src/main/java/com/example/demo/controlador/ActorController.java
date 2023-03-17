package com.example.demo.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.excepciones.ResourceNotFoundException;
import com.example.demo.modelo.Actor;
import com.example.demo.repositorio.ActorRepository;

@RestController
@RequestMapping("/sakila")
@CrossOrigin(origins = "*")
public class ActorController {

    @Autowired
    ActorRepository actorRepository;

    @GetMapping("/hola")
    public String hola() {
        return "hola";
    }

    @GetMapping("/actor")
    public List<Actor> findAll() {
        return actorRepository.findAll();
    }

    /**
     * @GetMapping("/actor/{id}")
     * public Optional<Actor> findById(@PathVariable Integer id){
     * return actorRepository.findById(id);
     * }
     */

    // guardar el actor
    @PostMapping("/actor")
    public Actor findById(@RequestBody Actor actor) {
        return actorRepository.save(actor);
    }

    // este metodo sirve para buscar un empleado
    @GetMapping("/actor/{id}")
    public ResponseEntity<Actor> obtenerEmpleadoPorId(@PathVariable Long id) {
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el empleado con el ID : " + id));
        return ResponseEntity.ok(actor);
    }

    // este metodo sirve para actualizar empleado
    @PutMapping("actor/{id}")
    Actor replaceActor(@RequestBody Actor newActor, @PathVariable Long id) {

        return actorRepository.findById(id)
                .map(actor -> {
                    actor.setFirstName(newActor.getFirstName());
                    actor.setLastName(newActor.getLastName());
                    return actorRepository.save(actor);
                })
                .orElseGet(() -> {
                    newActor.setId(id);
                    return actorRepository.save(newActor);
                });
    }

    // este metodo sirve para eliminar un empleado
    @DeleteMapping("/actor/{id}")
    void deleteActor(@PathVariable Long id) {
        actorRepository.deleteById(id);
    }
}
