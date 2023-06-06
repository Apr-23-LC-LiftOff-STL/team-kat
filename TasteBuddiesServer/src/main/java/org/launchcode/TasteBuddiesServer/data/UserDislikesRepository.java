package org.launchcode.TasteBuddiesServer.data;

import org.launchcode.TasteBuddiesServer.models.UserLikes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDislikesRepository extends CrudRepository<UserLikes, Integer> {
}
