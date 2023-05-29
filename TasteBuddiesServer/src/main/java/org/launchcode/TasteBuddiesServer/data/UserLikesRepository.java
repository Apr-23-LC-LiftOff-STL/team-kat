package org.launchcode.TasteBuddiesServer.data;

import org.launchcode.TasteBuddiesServer.models.User;
import org.launchcode.TasteBuddiesServer.models.UserLikes;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserLikesRepository extends CrudRepository<UserLikes, Integer> {

    public Optional<UserLikes> findById(Integer id);
}
