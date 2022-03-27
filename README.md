![example workflow](https://github.com/dEMonaRE/tweet-stream-app/actions/workflows/maven-publish.yml/badge.svg)


**TECH STACK**
-  **Java 11**
-  **Spring Boot 2.6.4**
-  **Twitter4j** https://github.com/Twitter4J/Twitter4J
-  **JUnit 5**
-  **Lombok+Mapper**
-  **MongoDB**
-  **Postman 2.1** ➡️ /docs/tweet-stream-app.postman_collection.json
-  **Springdoc**  ➡️ http://localhost:8080/swagger-ui/index.html#/
-  **Docker** ➡️ https://hub.docker.com/r/aemrezorlu/tweet-stream-app

    -	docker pull aemrezorlu/tweet-stream-app:latest

   -	docker run -p 8080:8080 aemrezorlu/tweet-stream-app
   
        -  start with swagger-> **startStream** with default keyword(bieber) or can stream any keywords to listen or/and **getTweets** instantly 
        -  tweets will appear in console log
        -   get stats with **getSummary** 
        -   get stats by interval for each stream/execution of twitter api via **getStatisticsByInterval**   example :  from : 2022-03-20T01:00:00.000-01:00   to :  2022-03-23T01:00:00.000-01:00
                     


+ Connect to [Twitter Streaming API 1.1](https://developer.twitter.com/en/docs/twitter-api/v1/tweets/filter-realtime/overview)
+ Filter messages that track on "bieber" or whatever keyword entered
+ Retrieve the incoming messages for 30 seconds or up to 100 messages, whichever comes first
+ the messages grouped by user (users sorted chronologically, ascending)
+ The messages per user sorted chronologically, ascending
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
+ All the above information is provided in Standard output.
