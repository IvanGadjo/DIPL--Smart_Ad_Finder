# Smart_Ad_Finder

The backend application of a web app used for scheduling and finding desired adverts. Pazar3.mk and reklama5.mk are one of the biggest ad listing websites in Macedonia. Therefore, the core idea is to have an all-in-one web app that simplifies and automates finding ads on pazar3.mk and reklama5.mk. The user can set keywords, other criteria and a time limit, by which the search will be conducted. When a matching ad is found, it is listed so the user can preview the link. He/She is also able to see all of the collected ads for the current search criterias, but also past searches with the respective found ads.

## Tech stack

This project is built with Java Spring Boot. It is following the DDD (Domain Driven Design) principles for the most extent (aplication & domain logic, value objects, factory class...).

- Services: CRUD services for model classes, rest services for scheduling reklama5 and pazar3 requests
- Other tools:
  - Apache Kafka for message scheduling (found advert messages). Implemented by a timed task, which sends the found adverts to Kafka.
  - WebSocket for a reliable connection with the frontend. Every message stored in the Apache Kafka server is then sent through the WebSocket to the frontend.
  - JSoup maven dependency for parsing the resulting HTML from reklama5 and pazar3 in order to extract the found adverts.
  - Implementation of a rest service scheduler, which executes regular HTTP requests to reklama5 and pazar3, as required from the respective user interests.
  - Database: PostgreSQL

Frontend code: https://github.com/IvanGadjo/DIPL--Smart_Ad_Finder_Front
