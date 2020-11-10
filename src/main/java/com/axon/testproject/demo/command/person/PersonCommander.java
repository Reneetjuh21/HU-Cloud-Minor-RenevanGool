package com.axon.testproject.demo.command.person;

import com.axon.testproject.demo.command.person.commands.CreatePerson;
import com.axon.testproject.demo.command.person.endpoints.PersonData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
@Slf4j
public class PersonCommander {
    private final CommandGateway gateway;

    public UUID createPerson(PersonData personData) {
        UUID randomId = UUID.randomUUID();
        CreatePerson createPerson = new CreatePerson(randomId, personData.getDateOfBirth());
        gateway.send(createPerson);
        log.info("Entered createPerson function: data={}", createPerson);
        return randomId;
    }
}
