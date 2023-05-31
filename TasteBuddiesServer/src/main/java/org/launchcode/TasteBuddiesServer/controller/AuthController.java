package org.launchcode.TasteBuddiesServer.controller;

import org.launchcode.TasteBuddiesServer.config.JwtUtil;
import org.launchcode.TasteBuddiesServer.data.UserRepository;
import org.launchcode.TasteBuddiesServer.models.User;
import org.launchcode.TasteBuddiesServer.models.dto.LoginFormDTO;
import org.launchcode.TasteBuddiesServer.models.dto.RegistrationFormDTO;
import org.launchcode.TasteBuddiesServer.models.dto.TokenDTO;
import org.launchcode.TasteBuddiesServer.models.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(
        origins = "http://localhost:4200",
        allowCredentials = "true")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public AuthController(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtUtil jwtUtil, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(
            @RequestBody LoginFormDTO loginDetails
    ) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDetails.getEmail(), loginDetails.getPassword())
        );
        final UserDetails user = userDetailsService.loadUserByUsername(loginDetails.getEmail());
        if (user != null) {
            return ResponseEntity.status(200).body(new TokenDTO(jwtUtil.generateToken(user)));
        }
        return ResponseEntity.status(400).body("Some error has occurred.");
    }

    @GetMapping("/authenticated")
    public ResponseEntity<?> authenticationTest() {
        return ResponseEntity.status(200).body(new UserDTO(-1, "This is an email", "email@gmail.com"));
    }

    @PostMapping("/register")
    public ResponseEntity<?> processRegistrationForm(@RequestBody RegistrationFormDTO registrationFormDTO,
                                                     HttpServletResponse response,
                                                     Errors errors
                                                     ) throws IOException {

        if (errors.hasErrors()) {
            // TODO figure out how to return a redirect page.
            response.sendRedirect("/register");
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        Optional<User> existingUser = userRepository.findByEmail(registrationFormDTO.getEmail());

        if (existingUser.isPresent()) {
            errors.rejectValue("email", "email.alreadyExists", "A account with that email already exists.");
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        User newUser = new User(registrationFormDTO.getDisplayName(), registrationFormDTO.getEmail(), registrationFormDTO.getPassword());
        userRepository.save(newUser);
        return ResponseEntity.status(200).build();
    }

}
