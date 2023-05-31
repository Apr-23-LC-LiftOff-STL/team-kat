package org.launchcode.TasteBuddiesServer.services;

import org.launchcode.TasteBuddiesServer.models.dto.CreateEventDTO;
import org.launchcode.TasteBuddiesServer.data.EventRepository;
import org.launchcode.TasteBuddiesServer.data.UserRepository;
import org.launchcode.TasteBuddiesServer.models.Event;
import org.launchcode.TasteBuddiesServer.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    public static final char[] UPPERCASE_LETTERS = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    public static final int ENTRY_CODE_LENGTH = 6;

    public Event createEvent(CreateEventDTO request, String userEmail) {
        Event event = new Event();
        event.setLocation(request.getLocation());
        event.setSearchRadius(request.getSearchRadius());

        String entryCode = generateUniqueEntryCode();
        event.setEntryCode(entryCode);

        Optional<User> optionalUser = userRepository.findByEmail(userEmail);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            event.getUsers().add(user);

            Event savedEvent = eventRepository.save(event);
            user.getEvents().add(savedEvent);
            userRepository.save(user);

            return savedEvent;
        } else {
            // handel the case when user is not found, throw exception or return default value
            return event;
        }

    }

    public String generateUniqueEntryCode() {
        //Implement unique entry

        String roomCode;
        StringBuilder codeBuilder = new StringBuilder();

        while (true) {

            /*
            This is a pretty simple approach to code generation, but there are approximately
            300 million possible combinations. So for now this is a fine way to generate codes.
            I think the design would eventually need to change so these codes expire.
             */
            for (int i = 0; i < ENTRY_CODE_LENGTH; i++) {
                int random = (int) Math.floor(Math.random() * 26);
                char letter = UPPERCASE_LETTERS[random];
                codeBuilder.append(letter);
            }

            roomCode = codeBuilder.toString();

            Optional possibleEvent = eventRepository.findByEntryCode(roomCode);

            if (possibleEvent.isEmpty()) {
                break;
            }

        }
        return roomCode;
    }

}
