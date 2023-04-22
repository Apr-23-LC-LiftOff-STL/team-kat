package org.launchcode.TasteBuddiesServer.controller.api;

import org.launchcode.TasteBuddiesServer.data.UserRepository;
import org.launchcode.TasteBuddiesServer.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/user")
public class APIUserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

}
