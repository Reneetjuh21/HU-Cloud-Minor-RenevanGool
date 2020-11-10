package com.axon.testproject.demo.query.person;

import com.axon.testproject.demo.query.person.endpoints.PersonData;
import com.axon.testproject.demo.query.person.queries.FindPerson;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonInvestigator {
    private final PersonRegistry personRegistry;

    @Autowired
    public PersonInvestigator(PersonRegistry personRegistry) {
        this.personRegistry = personRegistry;
    }

    @QueryHandler
    public PersonData findPerson(FindPerson findPerson) {
        PersonData personData = this.personRegistry.findById(findPerson.id).orElseThrow();
        return personData;
    }
}
