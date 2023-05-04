package org.launchcode.TasteBuddiesServer.controller.api;

import org.launchcode.TasteBuddiesServer.data.UserRepository;
import org.launchcode.TasteBuddiesServer.models.User;
import org.launchcode.TasteBuddiesServer.models.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("api/user")
public class APIUserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public ResponseEntity<?> getAllUsers() {
        List<User> possibleUsers = (List<User>) userRepository.findAll();

        if (possibleUsers.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<UserDTO> users = possibleUsers
                .stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
