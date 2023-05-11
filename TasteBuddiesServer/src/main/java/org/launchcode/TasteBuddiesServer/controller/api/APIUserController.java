package org.launchcode.TasteBuddiesServer.controller.api;

import org.launchcode.TasteBuddiesServer.config.JwtUtil;
import org.launchcode.TasteBuddiesServer.data.UserRepository;
import org.launchcode.TasteBuddiesServer.models.User;
import org.launchcode.TasteBuddiesServer.models.dto.SearchDTO;
import org.launchcode.TasteBuddiesServer.models.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
public class APIUserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("all")
    public ResponseEntity<?> getAllUsers() {
        List<User> possibleUsers = (List<User>) userRepository.findAll();

        if (possibleUsers.size() == 0) {
            return ResponseEntity.status(204).body(null);
        }

        List<UserDTO> users = possibleUsers
                .stream()
                .map(UserDTO::new)
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

        UserDTO user = new UserDTO(possibleUser.get());

        return ResponseEntity.status(200).body(user);
    }

    @PostMapping("search")
    public ResponseEntity<?> getUsersFromSearch(@RequestBody SearchDTO term) {
        List<User> users = this.userRepository.findDisplayNameFromFilter(term.getTerm());
        return ResponseEntity.status(200).body(users);
    }

}
