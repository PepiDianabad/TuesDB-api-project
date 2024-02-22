package com.example.tuesdb.controllers;

import com.example.tuesdb.dtos.GroupDto.GroupOutDto;
import com.example.tuesdb.dtos.PersonDto.PersonInputDto;
import com.example.tuesdb.dtos.PersonDto.PersonOutputDto;
import com.example.tuesdb.repositories.TuesRepository;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    private final TuesRepository tuesRepository;

    public Controller(TuesRepository tuesRepository) {
        this.tuesRepository = tuesRepository;
    }

    @GetMapping(value = "/person")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(this.tuesRepository.getAllUsers());
    }

    @PostMapping(value = "/person", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            examples = {
                                    @ExampleObject(name = "Create-1", value = """
                                            {
                                              "username": "Pepkata",
                                              "password": "1357",
                                              "name": "Pepkata Petrov",
                                              "permissions": ["perm1", "perm2"],
                                              "groups": ["group2", "group2"]
                                            }""")
                            }
                    )
            )
            @RequestBody PersonInputDto personInputDto) {
        return ResponseEntity.ok(this.tuesRepository.createUser(personInputDto));
    }

    @GetMapping(value = "/groups")
    public ResponseEntity<?> getAllGroups() {
        return ResponseEntity.ok(this.tuesRepository.getAllGroups());
    }

    @GetMapping(value = "/permissions")
    public ResponseEntity<?> getAllPermissions() {
        return ResponseEntity.ok(this.tuesRepository.getAllPermissions());
    }

    @GetMapping(value = "/groups")
    public ResponseEntity<?> findGroupByName(String Name){
        List<GroupOutDto> group = this.tuesRepository.getGroupByName(Name);
        return ResponseEntity.ok(group);
    }
    @GetMapping(value = "/peopleFromWantedGroup/{groupName}")
    public ResponseEntity<?> getAllPeopleFromWantedGroup(@PathVariable String groupName) {
        List<PersonOutputDto> people = this.tuesRepository.getAllPeopleFromWantedGroup(groupName);
        return ResponseEntity.ok(people);
    }
}