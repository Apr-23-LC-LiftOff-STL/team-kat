package org.launchcode.TasteBuddiesServer.data;



import org.launchcode.TasteBuddiesServer.models.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends CrudRepository <Event, Long> {
    List<Event> findByLocation(String location);

    Optional<Event> findByEntryCode(String entryCode);

}
