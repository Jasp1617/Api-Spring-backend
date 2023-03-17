package com.example.demo;

import com.example.demo.repositorio.ActorRepository;
import com.example.demo.modelo.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service 
public class ActorService{

    @Autowired
    ActorRepository actorRepository;

    public Actor addActor(Actor actor){
        return actorRepository.save(actor);
    }

}