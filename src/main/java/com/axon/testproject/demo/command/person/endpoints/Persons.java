package com.axon.testproject.demo.command.person.endpoints;

import com.axon.testproject.demo.command.person.PersonCommander;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController("commandPersonsEndpoint")
@RequestMapping(path = "persons")
@Slf4j
public class Persons {
    private final PersonCommander commander;

    @Autowired
    private final CommandGateway gateway;

    public Persons(CommandGateway gateway) {
        this.gateway = gateway;
        this.commander = new PersonCommander(this.gateway);
    }

    @PostMapping
    public UUID createPerson(@RequestBody PersonData data) {
        return commander.createPerson(data);
    }
}
