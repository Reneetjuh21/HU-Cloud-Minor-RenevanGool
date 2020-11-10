package com.axon.testproject.demo.query.person.endpoints;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class PersonData {
    @Id
    UUID id;
    @Column(nullable = false)
    LocalDate dateOfBirth;
}
