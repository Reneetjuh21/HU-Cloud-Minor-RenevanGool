package com.axon.testproject.demo.query.person;

import com.axon.testproject.demo.command.person.events.PersonCreated;
import com.axon.testproject.demo.query.person.endpoints.PersonData;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonProjector {

    private final PersonRegistry personRegistry;

    @Autowired
    public PersonProjector(PersonRegistry personRegistry) {
        this.personRegistry = personRegistry;
    }

    @EventHandler
    public void handleEventCreated(PersonCreated personCreated) {
        PersonData personData = new PersonData(personCreated.getId(), personCreated.getDateOfBirth());
        personRegistry.save(personData);
    }
}
