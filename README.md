# Taste Buddies

## ⚙️ Project setup


### Angular
1. Navigate to the \TasteBuddiesClient folder and run `npm install`

### Spring Boot
- Follow steps below for DB setup.

### MySQL
1. Create schema "taste-buddies".  
2. Create user and assign it CRUD permissions on taste-buddies schema.  
3. In your Java IDE, configure your environment variables to match your DB user. See [this article](https://education.launchcode.org/gis-devops/configurations/02-environment-variables-intellij/index.html).  

## Authentication

I'm looking at implementing authenticaion via JWTs (json web tokens). They have some advantages over cookies. [See this article](https://dzone.com/articles/cookies-vs-tokens-the-definitive-guide) for some of the tradeoffs. Related: [Tutorial from Kat on Angular/Spring communication with JWT authentication](https://www.bezkoder.com/angular-15-spring-boot-jwt-auth/)