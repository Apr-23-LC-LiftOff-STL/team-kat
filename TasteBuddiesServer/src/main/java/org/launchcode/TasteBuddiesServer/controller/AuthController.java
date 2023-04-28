package org.launchcode.TasteBuddiesServer.controller;

import lombok.RequiredArgsConstructor;
import org.launchcode.TasteBuddiesServer.config.JwtUtil;
import org.launchcode.TasteBuddiesServer.data.UserRepository;
import org.launchcode.TasteBuddiesServer.models.User;
import org.launchcode.TasteBuddiesServer.models.dto.LoginFormDTO;
import org.launchcode.TasteBuddiesServer.models.dto.RegistrationFormDTO;
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
import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(
            @RequestBody LoginFormDTO loginDetails
    ) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDetails.getEmail(), loginDetails.getPassword())
        );
        final UserDetails user = userDetailsService.loadUserByUsername(loginDetails.getEmail());
        if (user != null) {
            return ResponseEntity.ok(jwtUtil.generateToken(user));
        }
        return ResponseEntity.status(400).body("Some error has occurred.");
    }

    @PostMapping("/register")
    public ResponseEntity<?> processRegistrationForm(@RequestBody @Valid RegistrationFormDTO registrationFormDTO,
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

//        Unneeded check of passwords. Functionality moved to frontend.
//        String password = registrationFormDTO.getPassword();
//        String verifyPassword = registrationFormDTO.getVerifyPassword();

//        if (password.equals(verifyPassword)) {
//            errors.rejectValue("password", "password.mismatch", "The entered passwords do not match.");
//            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//        }

        User newUser = new User(registrationFormDTO.getDisplayName(), registrationFormDTO.getEmail(), registrationFormDTO.getPassword());
        userRepository.save(newUser);
        response.sendRedirect("/login");
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
