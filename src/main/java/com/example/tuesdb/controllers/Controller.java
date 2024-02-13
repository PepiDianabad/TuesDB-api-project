package com.example.tuesdb.controllers;

import com.example.tuesdb.dtos.BikeDto;
import com.example.tuesdb.dtos.PersonDto;
import com.example.tuesdb.repositories.TuesRepository;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final TuesRepository tuesRepository;

    public Controller(TuesRepository tuesRepository) {
        this.tuesRepository = tuesRepository;
    }

    @GetMapping(value = "/person")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(tuesRepository.getAll());
    }

    @PostMapping(value = "/person", consumes = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<?> createPerson(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            examples = {
                                    @ExampleObject(name = "Create-1", value = """
                                            {
                                              "firstName": "Nathan",
                                              "lastName": "Lisochovich",
                                              "email": "nat@abv.bg"
                                            }""")
                            }
                    )
            )
            @RequestBody PersonDto personDto
    ) {
        tuesRepository.createPerson(personDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/bike", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createBike(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            examples = {
                                    @ExampleObject(name = "Create-1", value = """
                                                                                     
                                            {
                                              "make": "Kawasaki",
                                              "model": "Ninja 636",
                                              "personId": 1
                                            }""")
                            }
                    )
            )
            @RequestBody BikeDto bikeDto
    ) {
        tuesRepository.createBike(bikeDto);
        return ResponseEntity.ok().build();
    }
}
