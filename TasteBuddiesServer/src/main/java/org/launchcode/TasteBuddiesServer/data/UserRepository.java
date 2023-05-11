package org.launchcode.TasteBuddiesServer.data;

import org.launchcode.TasteBuddiesServer.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    public Optional<User> findByEmail(String email);


    // this is a very simple implementation of a search, but that's all I need right now
    // https://stackoverflow.com/questions/55707052/implement-search-functionality-for-jpa-and-spring-boot
    @Query("SELECT n FROM user n WHERE " +
            "(?1 upper(n.display_name) like concat('%', upper(?1), '%')) LIMIT 10")
    public List<User> findDisplayNameFromFilter(String name);
}
