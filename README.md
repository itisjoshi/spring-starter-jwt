# spring-starter-jwt

This is a starter app for spring mvc with jwt token based Authentication project.

use mvn tomcat7:run to run the app in port 9000.

GET  http://localhost:9000/api/

you will not be authenticated.

Login using

POST http://localhost:9000/api/auth/login

username: username,
password: password

to get the auth token in response header

Then, use 

GET  http://localhost:9000/api/

By keeping 

auth-token in header with the key in value

you will be authenticated.

you can hit GET  http://localhost:9000/api/dummy

you will be authenticated but not authorized.
