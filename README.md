# Taste Buddies


## ⚙️ Project setup

### Angular
1. Navigate to the \TasteBuddiesClient folder and run `npm install`

### Spring Boot
- ❗ Open intelliJ to the `team-kat/TasteBuddiesServer`/ directory. This is required for IntelliJ to recognize the Gradle project and run it at all.
- Follow steps below for DB setup.


### MySQL
1. Create schema "taste-buddies".  
2. Create user and assign it CRUD permissions on taste-buddies schema.  
3. In your Java IDE, configure environment variables of your `TasteBuddiesServer[bootrun]` configuration to match your DB user. See [this launchcode textbook page](https://education.launchcode.org/gis-devops/configurations/02-environment-variables-intellij/index.html) for how to configure those environment variables.  

## 🔒 Authentication

### Overview

Authenticaion is handled via JWTs (json web tokens). They have some advantages over cookies - [see this article](https://dzone.com/articles/cookies-vs-tokens-the-definitive-guide) for some of the tradeoffs, but one large benefit is they allow the server to have no session state.  

Related: [Tutorial from Kat on Angular/Spring communication with JWT authentication](https://www.bezkoder.com/angular-15-spring-boot-jwt-auth/)

Tokens are valid for 24 hours from the time they are issued. 

### Implementation

Authentication is achieved by making a POST request to `/api/auth/login`  
The body should contain a JSON object containing fields for email and password:  
```
{
    "email": "user@user.com"
    "password": "userPassword"
}
```
### Registration

New user accounts can be registered by making a POST request to `/api/auth/register`
The body of the request should contain a JSON object with fields for displayName, email, and password:
```
{
    "displayName": "User's Name"
    "email": "user@user.com"
    "password": "userPassword"
}
```

### Testing
Once there is a registered user in the DB, you should be able to make a GET request to `/api/auth/authenticated` and recieve a 200 response. 

See the registration branch for a demo of registration, authentication, and accessing the api. Follow the project setup steps above, then make sure your java backend is running, your angular frontend is running, then navigate to localhost:4200 for the demo.