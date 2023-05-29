package org.launchcode.TasteBuddiesServer.data;

import org.launchcode.TasteBuddiesServer.models.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {
}
