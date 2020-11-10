package com.axon.testproject.demo.command.person;

import com.axon.testproject.demo.command.person.commands.CreatePerson;
import com.axon.testproject.demo.command.person.events.PersonCreated;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
@NoArgsConstructor
@Slf4j
public class Person {

    @AggregateIdentifier
    UUID id;

    @CommandHandler
    public Person(CreatePerson createPerson){
        log.info("Entered Person constructor: data={}", createPerson);
        PersonCreated personCreated = new PersonCreated(createPerson.id, createPerson.dateOfBirth);
        AggregateLifecycle.apply(personCreated);
    }

    @EventSourcingHandler
    public void handlePersonCreated(PersonCreated personCreated) {
        log.info("Entered handlePersonCreated", personCreated);
        id = personCreated.getId();
    }
}
