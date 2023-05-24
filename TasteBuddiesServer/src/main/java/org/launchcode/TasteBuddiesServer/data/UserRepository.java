package org.launchcode.TasteBuddiesServer.data;

import org.launchcode.TasteBuddiesServer.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    public Optional<User> findByEmail(String email);

}
