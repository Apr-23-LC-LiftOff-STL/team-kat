package org.launchcode.TasteBuddiesServer.controller;


import org.launchcode.TasteBuddiesServer.controller.Services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @PostMapping("/create")
    public ResponseEntity<?> createEvent(@Valid @RequestBody EventCreateRequest request, Principal principal) {
        //Validate user input, generate an entry code, and store event to DB.
        //Connect user to event and update the user-events
    }


}
