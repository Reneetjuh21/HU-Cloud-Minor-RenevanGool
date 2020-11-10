package com.axon.testproject.demo.command.person.commands;

import lombok.Data;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDate;
import java.util.UUID;

@Value
public class CreatePerson {
    @TargetAggregateIdentifier
    public UUID id;
    public LocalDate dateOfBirth;
}
