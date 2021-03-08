package com.example.payroll.user;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
class UserController {
    private final UserRepository repository;
    private final UserModelAssembler assembler;

    UserController(UserRepository repository, UserModelAssembler assembler){
        this.repository = repository;
        this.assembler = assembler;
    }

    //Get All
    @GetMapping("/api/users")
    CollectionModel<EntityModel<User>> all(){
        List<EntityModel<User>> user = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());
        return CollectionModel.of(user,linkTo(methodOn(UserController.class).all()).withSelfRel());
    }
    //Create
    @PostMapping("/api/users")
    ResponseEntity<?> newUser(@RequestBody User newUser) {

        EntityModel<User> entityModel = assembler.toModel(repository.save(newUser));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    //Login (Get one and validate)
    @GetMapping("/api/users/{username}")
    EntityModel<User> one(@PathVariable String username, @RequestBody String password) {
        User user = repository.findById(username)
                .orElseThrow(() -> new UserNotFoundException(username));

         if(user.checkPassword(password));
            return assembler.toModel(user);


    }
    @DeleteMapping("api/users/{username}")
    ResponseEntity<?> deleteUser(@PathVariable String username) {

        repository.deleteById(username);

        return ResponseEntity.noContent().build();
    }


}
