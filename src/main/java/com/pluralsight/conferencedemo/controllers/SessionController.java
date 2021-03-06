package com.pluralsight.conferencedemo.controllers;


import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.repositories.SessionRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/sessions")
@Api(value="sessions Controller", description="Permet de gérer les sessions")
public class SessionController {

    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping
    @ApiOperation(value = "RENVOI LA LISTE DE TOUTES LES SESSIONS")
    public List<Session> list() {
        return sessionRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    @ApiOperation(value = "RENVOI UN SESSION EN FONCTION DE SON ID")
    public Session get(@PathVariable Long id) {
        return sessionRepository.getOne(id);
    }

    @PostMapping
    @ApiOperation(value = "PERMET DE CREER UNE SESSION")
    /*@ResponseStatus(HttpStatus.CREATED)*/
    public Session create(@RequestBody final Session session) {
        return sessionRepository.saveAndFlush(session);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "PERMET DE SUPPRIMER UNE SESSION")
    public void delete(@PathVariable final Long id) {
        sessionRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "PERMET DE METTRE A JOUR UNE SESSION")
    public Session update(@PathVariable final Long id, @RequestBody final Session session) {

        final Session existingSession = sessionRepository.getOne(id);
        // le session_id est null lors d'une mise à jour, on va donc l'ignorer
        BeanUtils.copyProperties(session, existingSession, "session_id");
        return sessionRepository.saveAndFlush(existingSession);
    }

}
