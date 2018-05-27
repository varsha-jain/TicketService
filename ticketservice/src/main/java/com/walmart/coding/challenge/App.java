package com.walmart.coding.challenge;

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
        ticketService.findAndHoldSeats(5,"vashvj@gmail.com");
        System.out.println(" total seats available now are: " + ticketService.numSeatsAvailable());

    }
}
