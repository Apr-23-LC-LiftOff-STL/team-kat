package org.launchcode.TasteBuddiesServer.data;

import org.launchcode.TasteBuddiesServer.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByEmail(String email);

}
