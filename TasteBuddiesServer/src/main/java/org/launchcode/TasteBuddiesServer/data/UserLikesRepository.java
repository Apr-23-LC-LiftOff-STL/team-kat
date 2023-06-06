package org.launchcode.TasteBuddiesServer.data;

import org.launchcode.TasteBuddiesServer.models.Event;
import org.launchcode.TasteBuddiesServer.models.User;
import org.launchcode.TasteBuddiesServer.models.UserLikes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserLikesRepository extends CrudRepository<UserLikes, Integer> {

    Optional<UserLikes> findByUserAndEvent(User user, Event event);


}
