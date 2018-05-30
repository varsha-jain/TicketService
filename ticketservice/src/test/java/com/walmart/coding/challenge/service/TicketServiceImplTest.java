package com.walmart.coding.challenge.service;

import com.walmart.coding.challenge.model.SeatHold;
import com.walmart.coding.challenge.model.Venue;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/***
 * Class to test all the three methods defined in the implementation class
 * @author varsha
 */
public class TicketServiceImplTest {

    private TicketServiceImpl ticketService;

    @Before
    public void setUp(){
        //initialise the ticket service and venue
        ticketService = new TicketServiceImpl(new Venue(2,2));
    }

    /***
     * Method to test if number of available seats are returned accurately
     * @throws InterruptedException
     */
    @Test
    public void findTheNumberOfAvailableSeats() throws InterruptedException {
        System.out.println();
        System.out.println("**First Test**");
        System.out.println();
        assert(4 == ticketService.numSeatsAvailable()); //total seats in venue are 2*2
        System.out.println("Booking to be made with 2 seats for customer with email id: v@gmail.com");
        SeatHold seatHold = ticketService.findAndHoldSeats(2,"v@gmail.com");
        int availableSeats = 4 - 2;
        assert(availableSeats == ticketService.numSeatsAvailable());
        if(seatHold != null){
            String result = ticketService.reserveSeats(seatHold.getSeatHoldId(), "v@gmail.com");
            System.out.println("Result from test is: " + result);
            assert(result.contains("Seats reserved"));
        }
        System.out.println("Available Seats now are: " + ticketService.numSeatsAvailable());

        //second venue
        System.out.println();
        System.out.println("Second Venue with 3 rows and 4 seats per row created");
        ticketService = new TicketServiceImpl(new Venue(3,4));
        assert(12 == ticketService.numSeatsAvailable()); //total seats in venue are 3*4
        System.out.println("Booking to be made with 2 seats for customer with email id: v@gmail.com");
        SeatHold seatHoldSecond = ticketService.findAndHoldSeats(2,"v@gmail.com");
        int availableSeatsSecond = 12 - 2;
        assert(availableSeatsSecond == ticketService.numSeatsAvailable());
        System.out.println("Waiting for 61 seconds-- time limit expired");
        Thread.sleep(61000); //as this is greater than the expiration time, user won't be able to make a reservation
        if(seatHoldSecond != null){
            String resultSecond = ticketService.reserveSeats(seatHoldSecond.getSeatHoldId(), "v@gmail.com");
            System.out.println("Result from test is: " + resultSecond);
        }
        System.out.println("Available Seats now are: " + ticketService.numSeatsAvailable());

    }

    /***
     * Method that finds and holds total seats requested by the user
     * @throws InterruptedException
     */
    @Test
    public void findAndHoldSeatsForTest() throws InterruptedException {
        System.out.println();
        System.out.println("**Second Test**");
        System.out.println();
        System.out.println("Booking to be made with 2 seats for customer with email id: v@gmail.com");
        SeatHold seatHoldFirst = ticketService.findAndHoldSeats(2, "v@gmail.com" );
        assertNotNull(seatHoldFirst); //check if seathold object is ull or not
        assert(2 == seatHoldFirst.getSeats().size()); //check if 2 seats are booked or not
        assert(2 == ticketService.numSeatsAvailable()); //check if the no.of available seats have decreased
        System.out.println("waiting for 4 seconds");
        Thread.sleep(4000);
        //after waiting reserve the seats
        if(seatHoldFirst != null){
            System.out.println("result: " + ticketService.reserveSeats(seatHoldFirst.getSeatHoldId(), "v@gmail.com"));
        }
        System.out.println("waiting for 4 seconds");
        Thread.sleep(4000);
        System.out.println("Booking to be made with 3 seats for customer with email id: v@gmail.com");
        SeatHold seatHold2 = ticketService.findAndHoldSeats(3, "v@gmail.com" );

        assertNull(seatHold2);

    }

    /***
     * Method that tests if seats asked by the user can be reserved within expiry time or not
     * @throws InterruptedException
     */
    @Test
    public void reserveSeatsOnlyIfExpirationTimeHasNotPassedTest() throws InterruptedException{
        System.out.println();
        System.out.println("**Third Test**");
        System.out.println();
        System.out.println("Booking to be made with 2 seats for customer with email id: v@gmail.com");
        SeatHold seatHoldFirst = ticketService.findAndHoldSeats(2, "v@gmail.com" );
        assert(2 == seatHoldFirst.getSeats().size()); //check if 2 seats are booked or not
        assert(2 == ticketService.numSeatsAvailable()); //check if the no.of available seats have decreased
        System.out.println("waiting for 2 seconds");
        Thread.sleep(2000);
        //after waiting reserve the seats
        if(seatHoldFirst!=null){
            System.out.println("Reserving seats for customer with email id: v@gmail.com (WRONG EMAIL ID)");
            System.out.println("result: " + ticketService.reserveSeats(seatHoldFirst.getSeatHoldId(), "vq@gmail.com"));
            System.out.println("===================================================");
            System.out.println("Reserving seats for customer with email id: v@gmail.com (CORRECT EMAIL ID)");
            System.out.println("result: " + ticketService.reserveSeats(seatHoldFirst.getSeatHoldId(), "v@gmail.com"));
        }
        System.out.println("waiting for 2 seconds");
        Thread.sleep(2000);
        if(seatHoldFirst!=null) {
            System.out.println("Reserving seats for customer with email id: v@gmail.com (CORRECT EMAIL ID AND SAME BOOKING ID WHICH HAS ALREADY BEEN RESERVED)");
            System.out.println("result: " + ticketService.reserveSeats(seatHoldFirst.getSeatHoldId(), "v@gmail.com"));
        }
    }

}
