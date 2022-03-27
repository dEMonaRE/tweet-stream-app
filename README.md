
+ Connect to [Twitter Streaming API 1.1](https://developer.twitter.com/en/docs/twitter-api/v1/tweets/filter-realtime/overview)
+ Filter messages that track on "bieber"
+ Retrieve the incoming messages for 30 seconds or up to 100 messages, whichever comes first
+ Your application should return the messages grouped by user (users sorted chronologically, ascending)
+ The messages per user should also be sorted chronologically, ascending
+ For each message:
    * The message ID
    * The creation date of the message as epoch value
    * The text of the message
    * The author of the message
+ For each author:
    * The user ID
    * The creation date of the user as epoch value
    * The name of the user
    * The screen name of the user
+ All the above information is provided in either Standard output, or a log file
+ You are free to choose the output format, provided that it makes it easy to parse and process by a machine

TECH STACK
-Java 11
-Spring Boot 2.6.4
-Twitter4j https://github.com/Twitter4J/Twitter4J
-JUnit 5
-Lombok+Mapper
-MongoDB
-Springdoc  ➡️ http://localhost:8080/swagger-ui/index.html#/
-Docker ➡️ https://hub.docker.com/r/aemrezorlu/tweet-stream-app

  -	docker pull aemrezorlu/tweet-stream-app:latest

  -	docker run -p 8080:8080 aemrezorlu/tweet-stream-app
   
   -  start with swagger-> startStream and listen or getTweets instantly
   -  get stats with getSummary
                     
-Postman 2.1 ➡️ /docs/tweet-stream-app.postman_collection.json
