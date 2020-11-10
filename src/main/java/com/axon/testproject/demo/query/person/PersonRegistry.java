package com.axon.testproject.demo.query.person;

import com.axon.testproject.demo.query.person.endpoints.PersonData;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PersonRegistry extends CrudRepository<PersonData, UUID> {
}
