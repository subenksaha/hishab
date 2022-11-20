## Readme

The test application for Senior Software Engineer @ Hishab.co

Requirements:
1) Java **17** installed and in the path
2) Maven installed

How To Run:
1) Got to root project directory
2) Open terminal
3) Run `./mvnw spring-boot:run` terminal

Nice to know:
1) Default web server port: 8080
2) Swagger url: http://{HOST}:8080/swagger-ui/index.html
3) To change winning score go to application.yaml file and change app.win.score to anything you wish

Run in Docker:
1) Run `./mvnw package && java -jar target/test-0.0.1-SNAPSHOT.jar` in terminal
2) Build the image using the command `docker build -t hishab/test .`
3) Run it using `docker run -p 8080:8080 hishab/test`