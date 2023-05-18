package org.launchcode.TasteBuddiesServer.data;

import org.launchcode.TasteBuddiesServer.models.Restaurant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, String> {
}
