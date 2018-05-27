package com.walmart.coding.challenge.service;

import com.walmart.coding.challenge.model.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/***
 * @author varsha
 */
public class TicketServiceImpl implements TicketService {

    private Venue venue;
    private long _timeLimit = 60L;
    private int availableSeats;
    public TicketServiceImpl(Venue v){
        this.venue = v;
        this.availableSeats = v.getCountSeats();
    }
    public int numSeatsAvailable() {
        int availSeats = 0;
        List<Seat> seats = venue.getSeats();
        for(Seat seat : seats){
            if(seat.getStatus() == Status.AVAILABLE){
                availSeats++;
            }
        }
        return availSeats;
    }

    public SeatHold findAndHoldSeats(int numSeats, String customerEmail) {
        if(availableSeats < numSeats){
            System.out.println("Sorry no more seats available!");

        } else{
            List<Seat> seats = venue.getSeats();
            List<Seat> bookedSeats = new ArrayList<Seat>();
            for(Seat seat : seats){
                if(seat.getStatus() == Status.AVAILABLE){
                    if(bookedSeats.size() == numSeats){
                        break;
                    }
                    bookedSeats.add(seat);
                    seat.setStatus(Status.HOLD);
                }
            }
            availableSeats = availableSeats - bookedSeats.size();
            SeatHold seatHold = new SeatHold();
            seatHold.setCustomer(new Customer(customerEmail));
            seatHold.setSeats(bookedSeats);
            Date d = new Date();
            seatHold.setBookingTime(d);
            return seatHold;

        }
        return null;
    }



    public String reserveSeats(int seatHoldId, String customerEmail) {
        return null;
    }
}
