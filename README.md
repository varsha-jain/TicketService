# TicketService

## Overview
This application allows users to check if seats are available or not, then allows them to hold tickets for a specified amount of time. USers can then reserve those tickets. However, if the time to reserve crosses the expiration time then tickets cannot be reserved.

## Steps to run the application
clone the repository<br>
In the directory with pom.xml run the following maven commands<br>
Step 1: mvn clean install<br>
Step 2: mvn clean install package assembly:assembly -DskipTests (To skip tests)/ mvn clean install package assembly:assembly<br>
Step 3: java -jar target\ticketservice-1.0-SNAPSHOT-jar-with-dependencies.jar com.walmart.coding.challenge.App

### Assumptions:
1. Service asks user only for the number of seats he wants to book and doesn't ask for the specific row<br>
2. Various cases such as invalid id and email address combination, expiration time check, number of seats to be reserved are more than the actual seats available are handled by the program<br>
3. Service is written in Java and maven is used as build tool<br>
4. Expiration time is taken to be 60 seconds

## Implementation Details:
1. Models: Venue,Customer, Seat, SeatHold
2. SeatHold is a collection of Seats
3. Every Seat has a location in terms of row and columne and a status (Available, Hold, Reserved)
4. Statuses are stated in enum
5. Venue is a collection of seats
6. Every Seat hold is associated with a customer
7. Customer pojo stores information about customer
8. Service implements 3 methods: numOfSeatsAvailable(), reserveSeats() and findAndHoldGOodSeats()
9. Test case has been written for all the three methods in the interface implementation
9. Vaidation has been performed
20. Interactive menu has been programmed 

Scenarios considered-<br>
1. Number of seats requested to book by the user are more than the no.of seats available 
2. User tries to reserve seats with the same booking id which has already been reserved
3. User tries to reserve seats with incorrect email id or booking id
