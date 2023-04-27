//package org.launchcode.TasteBuddiesServer.controller;
//
//import org.launchcode.TasteBuddiesServer.data.UserRepository;
//import org.launchcode.TasteBuddiesServer.models.dto.LoginFormDTO;
//import org.launchcode.TasteBuddiesServer.models.dto.RegistrationFormDTO;
//import org.launchcode.TasteBuddiesServer.models.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.Errors;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import javax.validation.Valid;
//import java.io.IOException;
//import java.util.Optional;
//
//@Controller
//public class AuthenticationController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    private static final String userSessionKey = "user";
//
//    private static void setUserInSession(HttpSession session, User user) {
//        session.setAttribute(userSessionKey, user.getId());
//    }
//
//    public User getUserFromSession(HttpSession session) {
//
//        Integer userId = (Integer) session.getAttribute(userSessionKey);
//        if (userId == null) {
//            return null;
//        }
//
//        Optional<User> userOpt = userRepository.findById(userId);
//
//        if (userOpt.isEmpty()) {
//            return null;
//        }
//
//        return userOpt.get();
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<?> processRegistrationForm(@ModelAttribute @Valid RegistrationFormDTO registrationFormDTO,
//                                                  Errors errors,
//                                                  HttpServletRequest request,
//                                                  HttpServletResponse response) throws IOException {
//
//        if (errors.hasErrors()) {
//            // TODO figure out how to return a redirect page.
//            response.sendRedirect("/register");
//            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//        }
//
//        User existingUser  = userRepository.findByEmail(registrationFormDTO.getEmail());
//
//        if (existingUser != null) {
//            errors.rejectValue("email", "email.alreadyExists", "A account with that email already exists.");
//            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//        }
//
//        String password = registrationFormDTO.getPassword();
//        String verifyPassword = registrationFormDTO.getVerifyPassword();
//
//        if (password.equals(verifyPassword)) {
//            errors.rejectValue("password", "password.mismatch", "The entered passwords do not match.");
//            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//        }
//
//        User newUser = new User(registrationFormDTO.getUsername(), registrationFormDTO.getEmail(), registrationFormDTO.getPassword());
//        userRepository.save(newUser);
//        setUserInSession(request.getSession(), newUser);
//        response.sendRedirect("/login");
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<?> processLoginForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO,
//                                              Errors errors,
//                                              HttpServletRequest request,
//                                              HttpServletResponse response) throws IOException {
//
//        if (errors.hasErrors()) {
//            response.sendRedirect("/login");
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        User theUser = userRepository.findByEmail(loginFormDTO.getEmail());
//
//        String password = loginFormDTO.getPassword();
//
//        if (theUser == null || !theUser.isMatchingPassword(password)) {
//            errors.rejectValue(
//                    "password",
//                    "login.invalid",
//                    "Invalid credentials.");
//            response.sendRedirect("/login");
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        setUserInSession(request.getSession(), theUser);
//        response.sendRedirect("/");
//        return new ResponseEntity<>(HttpStatus.OK);
//
//    }
//
//}
