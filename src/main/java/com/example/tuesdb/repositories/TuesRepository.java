package com.example.tuesdb.repositories;

import com.example.tuesdb.dtos.GroupDto.GroupOutDto;
import com.example.tuesdb.dtos.PermissionDto.PermissionOutDto;
import com.example.tuesdb.dtos.PersonDto.PersonInputDto;
import com.example.tuesdb.dtos.PersonDto.PersonOutputDto;
import com.example.tuesdb.models.Person;
import com.example.tuesdb.models.Groups;
import com.example.tuesdb.models.Permission;
import java.util.stream.Collectors;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;


import java.util.HashSet;
import java.util.List;

@org.springframework.stereotype.Repository
public class TuesRepository {
    private final EntityManager entityManager;

    public TuesRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<PersonOutputDto> getAllUsers() {
        return this.entityManager.createQuery("select p from Person p", Person.class).getResultList().stream().map(PersonOutputDto::new).toList();
    }

    @Transactional
    public PersonOutputDto createUser(PersonInputDto personInputDto) {
        Person newPerson = new Person();

        newPerson.setName(personInputDto.getUsername());
        newPerson.setPassword(personInputDto.getPassword());
        newPerson.setName(personInputDto.getName());
        newPerson.setPermission(new HashSet<>());
        newPerson.setGroups(new HashSet<>());

        for(String currentPermissionName : personInputDto.getPermissions()) {
            Permission currentPermission = this.entityManager.createQuery("select p from Permission p where label = :currentPermissionName", Permission.class).setParameter("currentPermissionName", currentPermissionName).getSingleResult();

            newPerson.getPermissions().add(currentPermission);
        }

        for (String currentGroupName : personInputDto.getGroups()) {
            Groups currentGroup = this.entityManager.createQuery("select g from Groups g where name = :currentGroupName", Groups.class)
                    .setParameter("currentGroupName", currentGroupName)
                    .getSingleResult();

            newPerson.getGroups().add(currentGroup);
        }


        this.entityManager.persist(newPerson);

        return new PersonOutputDto(newPerson);
    }

    public List<GroupOutDto> getAllGroups() {
        return this.entityManager.createQuery("select g from Groups g", Groups.class)
                .getResultList().stream().map(GroupOutDto::new).toList();
    }


    public List<PermissionOutDto> getAllPermissions() {
        return this.entityManager.createQuery("select p from Permission p", Permission.class).getResultList().stream().map(PermissionOutDto::new).toList();
    }

    public List<GroupOutDto> findGroupByName(String groupName) {
        return this.entityManager.createQuery("select g from Groups g where g.name = :groupName", Groups.class)
                .setParameter("groupName", groupName)
                .getResultList().stream().map(GroupOutDto::new).toList();
    }

    @Transactional
    public List<PersonOutputDto> getAllPeopleFromWantedGroup(String groupName) {
        List<PersonOutputDto> people;

        List<GroupOutDto> wantedGroups = this.entityManager.createQuery(
                        "select new com.example.tuesdb.dtos.GroupDto.GroupOutDto(g) from Groups g where g.name = :groupName", GroupOutDto.class)
                .setParameter("groupName", groupName)
                .getResultList();

        List<Groups> groups = wantedGroups.stream().map(GroupOutDto::toEntity).collect(Collectors.toList());

        people = this.entityManager.createQuery(
                        "select new com.example.tuesdb.dtos.PersonDto.PersonOutputDto(p) from Person p where p.groups in :groups", PersonOutputDto.class)
                .setParameter("groups", groups)
                .getResultList();

        return people;
    }
}
