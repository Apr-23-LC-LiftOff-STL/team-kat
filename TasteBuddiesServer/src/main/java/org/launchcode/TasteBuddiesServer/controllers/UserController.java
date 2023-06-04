package org.launchcode.TasteBuddiesServer.controllers;

import org.launchcode.TasteBuddiesServer.config.JwtUtil;
import org.launchcode.TasteBuddiesServer.data.EventRepository;
import org.launchcode.TasteBuddiesServer.data.UserRepository;
import org.launchcode.TasteBuddiesServer.models.User;
import org.launchcode.TasteBuddiesServer.models.dto.CurrentUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(
        origins = "http://localhost:4200",
        allowCredentials = "true")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("all")
    public ResponseEntity<?> getAllUsers() {
        List<User> possibleUsers = (List<User>) userRepository.findAll();

        if (possibleUsers.size() == 0) {
            return ResponseEntity.status(204).body(null);
        }

        List<CurrentUserDTO> users = possibleUsers
                .stream()
                .map(CurrentUserDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.status(200).body(users);
    }

    @GetMapping("")
    public ResponseEntity<?> getLoggedInUser(
            HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION).substring(7);
        String email = jwtUtil.extractUsername(token);

        Optional<User> possibleUser = userRepository.findByEmail(email);
        if (possibleUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        CurrentUserDTO userDTO = new CurrentUserDTO(possibleUser.get());

        return ResponseEntity.status(200).body(userDTO);
    }
}
