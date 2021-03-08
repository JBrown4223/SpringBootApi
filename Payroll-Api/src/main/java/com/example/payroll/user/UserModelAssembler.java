package com.example.payroll.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


import com.example.payroll.user.UserController;
import com.example.payroll.user.User;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class UserModelAssembler implements RepresentationModelAssembler<User,EntityModel<User>> {

    @Override
    public EntityModel<User> toModel(User user) {
        return EntityModel.of(user,
                linkTo(methodOn(UserController.class).one(user.getUsername(), user.getPassword())).withSelfRel(),
                linkTo(methodOn(UserController.class).all()).withRel("users"));
    }



}
