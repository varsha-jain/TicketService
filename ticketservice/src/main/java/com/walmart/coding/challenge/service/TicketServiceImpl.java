package com.walmart.coding.challenge.service;

import com.walmart.coding.challenge.model.*;

import java.util.*;

/***
 * @author varsha
 */
public class TicketServiceImpl implements TicketService {

    private Venue venue;
    private long _timeLimit = 60L; //expiration time limit
    private int availableSeats; //variable to hold the count of seats
    private HashMap<Integer, SeatHold> bookedTickets; //map to store the booked tickets
    private HashMap<String, SeatHold> reservedSeats; //map to store the reserved seats

    public TicketServiceImpl(Venue v){
        this.venue = v;
        this.availableSeats = v.getCountSeats();
        this.bookedTickets = new HashMap<Integer, SeatHold>();
        this.reservedSeats = new HashMap<String, SeatHold>();
    }

    /***
     * function to return the count of available seats in the venue. Seats are considered to be unavailable if they are reserved or on hold
     * @return the total no of seats available in the venue
     */
    public int numSeatsAvailable() {
        int availSeats = 0;
        List<Seat> seats = venue.getSeats(); //get all the seats in the venue
        for(Seat seat : seats){ //for every seat in the venue
            if(seat.getStatus() == Status.AVAILABLE){ //check the status of the seat
                availSeats++; // if the seat is available then increment the counter
            }
        }
        return availSeats; //return the count
    }

    /***
     *
     * @param numSeats the number of seats to find and hold
     * @param customerEmail unique identifier for the customer
     * @return seat hold object which will hold the number of seats booked by a customer
     * Assumption: If the user wants to book no of seats more then available then his request is not fulfilled.
     */
    public SeatHold findAndHoldSeats(int numSeats, String customerEmail) {
        if(availableSeats >= numSeats){ //if user wants to book more no of seats than available
            List<Seat> seats = venue.getSeats(); //retrieves a collection of all the seats in the venue
            List<Seat> bookedSeats = new ArrayList<Seat>(); //create a new list of seats that the user is going to book
            for(Seat seat : seats){
                if(seat.getStatus() == Status.AVAILABLE){ //check the status of every seat in the venue
                    if(bookedSeats.size() == numSeats){ //if the no of seats added to the booked list
                        // is same as the no of seats requested by the user then stop
                        break;
                    }
                    bookedSeats.add(seat); //add the seat if it's available
                    seat.setStatus(Status.HOLD); //update the status of the booked seat to HOLD
                }
            }
            availableSeats = availableSeats - bookedSeats.size(); //decrement the count of available seats in the venue
            SeatHold seatHold = new SeatHold(); //create a new seat hold instance
            /***
             * Set all the properties of the seat hold object
             */
            seatHold.setCustomer(new Customer(customerEmail));
            seatHold.setSeats(bookedSeats);
            Date d = new Date();
            //set the time of booking as the time when the booking was made.This will be used to check for expiration
            seatHold.setBookingTime(d);
            bookedTickets.put(seatHold.getSeatHoldId(), seatHold); //add the seathold with it's id in the map. This will be used while reserving the seat hold object
            return seatHold;

        }else{
            System.out.println("Sorry, request cannot be fulfilled. Number of requested seats is more than the available seats!");
        }
        return null;
    }

    /***
     *
     * @param seatHoldId the seat hold identifier
     * @param customerEmail the email address of the customer to which the
    seat hold is assigned
     * @return
     */

    public String reserveSeats(int seatHoldId, String customerEmail) {
        /***
         * check in the booked tickets map if the seat hold object with the corresponding id exists
         */
        for(Map.Entry<Integer, SeatHold> map : bookedTickets.entrySet()){
            int id = map.getKey(); //get the seat hold id
            //if id and email matches then we have found the correct seat hold object

            if(id == seatHoldId && customerEmail.equalsIgnoreCase(map.getValue().getCustomer().getEmail())){
                SeatHold seatHold = map.getValue(); //get the object
                long timeLimit = Math.abs((seatHold.getBookingTime().getTime() - (new Date().getTime())))/1000; //calculate the time limit
                //System.out.println("time limit: " + timeLimit);
                bookedTickets.remove(seatHoldId); //remove the object from booked tickets because either the seats are going to be reserved or made available.
                if(timeLimit < _timeLimit){ //if the customer reserves seats within the time limit
                    reservedSeats.put(customerEmail, seatHold); //add the corr seathold object with customer in the map
                    for(Seat seat: seatHold.getSeats()){ //update the status of all the seats reserved by the customer
                        seat.setStatus(Status.RESERVED);
                    }
                    displaySeatDetails(seatHold); //display seat details to customer
                    return "Seats reserved successfully!";

                }else{
                    //time limit expired so make those seats available again
                    if(reservedSeats.get(customerEmail) ==null){ //
                        for(Seat seat: seatHold.getSeats()){
                            seat.setStatus(Status.AVAILABLE);
                        }
                        availableSeats = availableSeats + seatHold.getSeats().size(); //update the count of available seats
                    }else{
                        System.out.println("you already have a booking with this id and email address");
                    }

                }
            }else{
                //user tries to reserve seats with wrong email address
                System.out.println("Either id or email address is not verified. Please continue with correct values.");
            }
        }
        return "Sorry couldn't reserve seats successfully!"; //reservation request couldn't be completed
    }

    /***
     * function to display seat details to customer
     * @param seatHold
     */
    public void displaySeatDetails(SeatHold seatHold){
        System.out.println("your seat details are as follows-");
        System.out.println("Customer: " + seatHold.getCustomer().getEmail());
        if(seatHold != null){
            for(Seat seat : seatHold.getSeats()){
                System.out.println("Seat location:  row " + seat.getRow() + " ,column " + seat.getCol());
            }
        }
    }
}
