package com.sayonara.Library_SpringBoot.util;

import com.sayonara.Library_SpringBoot.models.User;
import com.sayonara.Library_SpringBoot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class UserValidator implements Validator {

    private final UserService userService;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        Optional<User> userFromDB = userService.findByUsername(user);

        if (userFromDB.isPresent())
            errors.rejectValue("username", "", "Аккаунт с таким именем уже существует");
    }
}
