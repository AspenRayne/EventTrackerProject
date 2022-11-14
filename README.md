# EventTrackerProject -- Concert Event Tracker

## Description

Using the SeatGeek API, I created an event tracker for any event that seat geek
provides tickets for. I then decided that you can only use this app for concerts.
Users can lookup concerts based on performer, state, and city.

This project includes full CRUD, allowing users to:

  - Save concerts
  - View upcoming concerts
  - View saved concerts
  - Add or update reviews
  - Delete saved concerts

## Lessons Learned

* Serializing and Deserializing JSON objects in Java is challenging because it is strongly typed.
On a previous project I implemented an API wrapper library, which stripped a lot of the learning
of implementing API's. Knowing that I won't always have that luxury, I expanded my understanding by using simple-json.
I tried other libraries, for example GSON, but found that it was overly complex.
  - This lightweight utility provides freedom in how data is unpacked from JSON. I can  
iterate through lists, access the JSON values and populate them into Java variables.
A huge benefit to this strategy is that it avoids defining multiple classes just to deserialize JSON.

  - This means that I can pass JSON data through the Request Body into a Java REST endpoint
without needing to define a class!


* SpringBoot annotations are extremely helpful in streamlining CRUD functionality.
Autowiring services and repositories gives a lot of freedom in how I develop applications.
I was able to create an API service class to access SeatGeek data and then call it from
a REST controller.


* Response handling, when using API's over network connections, many things can go wrong.
This project forced me to consider different ways in which process chains can fail.
I handled many issues, for example:
  - SeatGeek's API may return an array or a map, by checking the class instance of the
  JSONValue, I adjusted the process to handle both possibilities.
  - SeatGeek's API may return no results, in which a null response and a different status code
  should be returned in the HTTP response.
  - CRUD functionality in the database can fail at certain points, where many different exceptions
  should be handled, all resulting in unique status codes.

## Technologies Used

  - Java 8
  - SpringBoot
  - Hibernate
  - MySQL
  - Gradle
  - Git
  - JPA
  - Spring Tool Suite
  - SeatGeek API

## Access Front End Website On AWS 
`http://52.40.152.6:8080/ConcertTracker/`

## Endpoints

### Search Concerts (GET)

#### types : "state" (Abbreviation), "city", "performer"

`http://52.40.152.6:8080/ConcertTracker/api/searchSG/concerts`

```
[
    {
        "type": "state",
        "query": "CO"
    },
    {
      "type": "city",
      "query": "Aspen"
    },
    {
        "type": "performer",
        "query": "Flume"
    }
]
```


### List Concerts (GET)

`http://52.40.152.6:8080/ConcertTracker/api/concerts`


### View Saved Concert by ID (GET)

`http://52.40.152.6:8080/ConcertTracker/api/concerts/{concertId}`


### Create Concert by SeatGeek ID (POST)

#### This ID is found from the "Search Concerts" Endpoint

`http://52.40.152.6:8080/ConcertTracker/api/concerts/{seatGeekId}`


### Update Concert by ID (PUT)

`http://52.40.152.6:8080/ConcertTracker/api/concerts/{concertId}`


### Delete Concert by ID (DEL)

`http://52.40.152.6:8080/ConcertTracker/api/concerts/{concertId}`


### EER Diagram

![image](https://user-images.githubusercontent.com/90653438/200232125-a374eec8-84ac-4b0c-a483-e75c8a2627ed.png)
