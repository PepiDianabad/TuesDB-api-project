package com.example.tuesdb.repositories;

import com.example.tuesdb.dtos.BikeDto;
import com.example.tuesdb.dtos.PersonDto;
import com.example.tuesdb.models.Bike;
import com.example.tuesdb.models.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@org.springframework.stereotype.Repository
public class TuesRepository {
    private final EntityManager entityManager;

    public TuesRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public Person createPerson(PersonDto personDto) {
        Person newPerson = new Person();
        newPerson.setEmail(personDto.getEmail());
        newPerson.setFirstName(personDto.getFirstName());
        newPerson.setLastName(personDto.getLastName());

        if (!CollectionUtils.isEmpty(personDto.getBikes())) {

            Set<Long> bikeIds = personDto.getBikes().stream().map(BikeDto::getId).collect(Collectors.toSet());

            TypedQuery<Bike> q = entityManager.createQuery("select b from Bike b where id in (:in)", Bike.class);
            q.setParameter("in", bikeIds);

            List<Bike> bikes = q.getResultList();

            if (CollectionUtils.isEmpty(bikes)) {
                throw new RuntimeException("Bikes not found");
            }

            newPerson.setBikes(new HashSet<>(bikes));
        }

        entityManager.persist(newPerson);

        return newPerson;
    }

    @Transactional
    public Bike createBike(BikeDto bikeDto) {
        Bike newBike = new Bike();
        newBike.setMake(bikeDto.getMake());
        newBike.setModel(bikeDto.getModel());

        if (bikeDto.getPersonId() != null) {
            Person owner = entityManager.find(Person.class, bikeDto.getPersonId());
            if (owner == null) {
                throw new RuntimeException("Person not found");
            }
            newBike.setOwner(owner);
        }

        entityManager.persist(newBike);

        return newBike;
    }

    public List<PersonDto> getAll() {
        return entityManager.createQuery("select p from Person p", Person.class)
                .getResultList().stream().map(PersonDto::new).toList();
    }
}
