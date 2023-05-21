package org.launchcode.TasteBuddiesServer.data;



import org.launchcode.TasteBuddiesServer.models.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventRepository extends CrudRepository <Event, Long> {
    List<Event> findByLocation(String location);

}
