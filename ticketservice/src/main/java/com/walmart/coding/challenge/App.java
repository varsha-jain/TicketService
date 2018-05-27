package com.walmart.coding.challenge;

import com.walmart.coding.challenge.model.SeatHold;
import com.walmart.coding.challenge.model.Venue;
import com.walmart.coding.challenge.service.TicketService;
import com.walmart.coding.challenge.service.TicketServiceImpl;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        System.out.println( "*********Ticket Service***********" );
        int rows = 5;
        int col = 5;
        Venue venue = new Venue(rows,col);
        TicketService ticketService = new TicketServiceImpl(venue);
        int seatsAvailable = ticketService.numSeatsAvailable();
        System.out.println(" total seats available are: " + seatsAvailable);
        SeatHold seatHold = ticketService.findAndHoldSeats(5,"vashvj@gmail.com");
        System.out.println(" total seats available now are: " + ticketService.numSeatsAvailable());
        System.out.println(ticketService.reserveSeats(seatHold.getSeatHoldId(), "vashvj@gmail.com"));
        System.out.println(" total seats available now are: " + ticketService.numSeatsAvailable());
    }
}

/*
* to-do
* 1. create menu and interaction in separate class
* 2. validations
* 3. while booking/reserving ask for row from user and scenarios while booking
* 4. documentation
* 5. test
* 6. update readme
* 7. frequent commits
* */
