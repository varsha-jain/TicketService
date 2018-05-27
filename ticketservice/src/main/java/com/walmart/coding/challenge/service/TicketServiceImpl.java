package com.walmart.coding.challenge.service;

import com.walmart.coding.challenge.model.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/***
 * @author varsha
 */
public class TicketServiceImpl implements TicketService {

    private Venue venue;
    private long _timeLimit = 60L;
    private int availableSeats;
    private HashMap<Integer, SeatHold> bookedTickets;
    private HashMap<String, SeatHold> reservedSeats;

    public TicketServiceImpl(Venue v){
        this.venue = v;
        this.availableSeats = v.getCountSeats();
        this.bookedTickets = new HashMap<Integer, SeatHold>();
        this.reservedSeats = new HashMap<String, SeatHold>();
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
            bookedTickets.put(seatHold.getSeatHoldId(), seatHold);
            return seatHold;

        }
        return null;
    }



    public String reserveSeats(int seatHoldId, String customerEmail) {
        for(Map.Entry<Integer, SeatHold> map : bookedTickets.entrySet()){
            int id = map.getKey();
            if(id == seatHoldId && customerEmail.equalsIgnoreCase(map.getValue().getCustomer().getEmail())){
                SeatHold seatHold = map.getValue();
                long timeLimit = (seatHold.getBookingTime().getTime() - (new Date().getTime()))/1000;
                if(timeLimit < _timeLimit){
                    reservedSeats.put(customerEmail, seatHold);
                    for(Seat seat: seatHold.getSeats()){
                        seat.setStatus(Status.RESERVED);
                    }

                }else{
                    //time limit expired so make those seats available again
                    for(Seat seat: seatHold.getSeats()){
                        seat.setStatus(Status.AVAILABLE);
                    }
                    availableSeats = availableSeats + seatHold.getSeats().size();
                    System.out.println("inisde else loop: " + availableSeats);
                }
                bookedTickets.remove(seatHoldId);
                return "Seats reserved successfully!";
            }
        }
        return "Sorry couldn't reserve seats successfully!";
    }
}
