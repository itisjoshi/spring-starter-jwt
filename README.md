# spring-starter-jwt

This is a starter app for spring mvc with jwt token based Authentication project.

## Installation 

1. mvn clean
2. mvn install
3. mvn deploy

use mvn tomcat7:run to run the app ease it runs in port 9000.

## API Calls:

|Return Type|Request Method|URL Mapping  |
|-----------|--------------|------------ |
|Home       |GET           |/api/        |
|void       |POST          |/api/auth/login |
|void       |GET           |/api/dummy   |


```javascript
authModel:
{
	String username;
	String password;
}
```
Use


```javascript
authModel:
{
	"username": "username";
	"password": "password";
}
```

GET  http://localhost:9000/api/

you will not be authenticated.

Login using

POST http://localhost:9000/api/auth/login

to get the auth token in response header

Then, use 

GET  http://localhost:9000/api/

By keeping 

auth-token in header with the key in value

you will be authenticated & authorized.

you can hit GET  http://localhost:9000/api/dummy

you will be authenticated but not authorized.
