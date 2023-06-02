# Java Entities 
(Will be persistent (saved in the db))

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

## Naming Scheme
### Entity classes 
Entities use the standard java class name.  
eg
```
User.java

------

@Entity
public class User {
	...
}
```

### DTOs
Since entities should not be passed to the frontend, we should use DTOs to pass data back and forth. I've broken DTOs into two catagories: Outgoing and incoming.

#### Outgoing
Outgoing models use the ClassNameDTO form and will contain some of, but not all, the data from the entity. They are used essentially to control exactly what data goes to the client.  
One (mostly arbitrary) choice I made was to not embed data more than one level. So for isntance I have User returning a list of eventIDs, rather than the full EventDTO. I'm definitely open to changing this if it becomes a pain.  
I have the User and Event DTO set up so they can be populated by just passing an Entity class to the constructor and that should make mapping the details for tranfer clean and simple.

#### Incoming 

Incoming DTOs are named after the form they represent on the frontend in VerbFormDTO format. For instance: LoginFormDTO describes clearly where it is being populated. Hopefully this avoids confusion!

## Detailed Models

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
- List \<UserLikes\> userLikedRestaurants
- Date createdDate  
- Date mealTime   // allows for checking if a restaurant is open for the event  

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
