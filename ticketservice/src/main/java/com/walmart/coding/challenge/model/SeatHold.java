package com.walmart.coding.challenge.model;

import java.util.Date;
import java.util.List;

/***
 * @author varsha
 * model that holds the seats booked by a particular customer
 */
public class SeatHold {
    private int seatHoldId; //id of all the seats held by the customer
    private Customer customer; //customer who held seats with Id seatHoldId
    private Date bookingTime; //time when the booking was made. This is required so that seats can be reserved for a specific amount of time
    private List<Seat> seats; //list of all the seats held by the customer

    /***
     *
     * @return id of the seats held
     */
    public int getSeatHoldId() {
        return seatHoldId;
    }

    /***
     *
     * @param seatHoldId
     */
    public void setSeatHoldId(int seatHoldId) {
        this.seatHoldId = seatHoldId;
    }

    /***
     *
     * @return the customer who made this booking
     */
    public Customer getCustomer() {
        return customer;
    }

    /***
     *
     * @param customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /***
     *
     * @return the booking time when this booking was made
     */
    public Date getBookingTime() {
        return bookingTime;
    }

    /***
     *
     * @param bookingTime
     */
    public void setBookingTime(Date bookingTime) {
        this.bookingTime = bookingTime;
    }

    /***
     *
     * @return list of seats booked
     */
    public List<Seat> getSeats() {
        return seats;
    }

    /***
     *
     * @param seats
     */
    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
