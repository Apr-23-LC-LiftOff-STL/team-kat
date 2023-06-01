## Java Entities (Will be persistent (saved in the db))

Entities should NOT be returned directly to the frontend as they can contain sensitive details (like the password in the user class). Make an entityDTO that only has the needed fields in it!

eg
```
UserDTO {
	String displayName
	String email
	UserLikes eventUserLike
	Set <AuthorityEntity> authorities
}
```

### User
extends AbstractEntity implements UserDetails    UserDetails is for spring security  

- String displayName    // does not need to be unique  
- String email    // must be unique  
- String password    // bcrypt  
- List \<Event\> events    // many to many  
- List \<UserLikes\> userLikes    // one to many  
- Set \<AuthorityEntity\> authorities    // many to many  
- boolean accountNonExpired    // part of UserDetails  
- boolean accountNonLocked    // part of UserDetails  
- boolean credentialsNonExpired    // part of UserDetails  
- boolean enabled    // part of UserDetails  

### Event
extends AbstractEntity  

- String entryCode  
- String location  
- String searchRadius  
- List \<User\> users  
- List \<Restaurant\> availableRestaurants  
- Date createdDate  
- Date timeForMeal   // allows for checking if a restaurant is open for the event  

### UserLikes
extends AbstractEntity  

- User user  
- Event event  
- List \<Restaurant\> likedRestaurants  
- List \<Restaurant\> dislikedRestaurants  

### Restaurant
DOES NOT extend AbstractEntity  

- String id    // this is the places API ID
- List \<Event\> events    // This is for a many-to-many, but restaurant doesn't need to know about events so eliminate if possible.  

### AuthorityEntity
- String authority  
- List \<User\> users  
