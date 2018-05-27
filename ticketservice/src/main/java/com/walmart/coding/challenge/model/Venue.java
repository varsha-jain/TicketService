package com.walmart.coding.challenge.model;

import java.util.ArrayList;
import java.util.List;

public class Venue {

    private List<Seat> seats; //all the seats in the theatre
    private int countSeats; //total no of seats
    private int rows; //total rows in the venue
    private int noOfSeatsPerRow; //col

    public Venue(int rows, int noOfSeatsPerRow) {
        this.rows = rows;
        this.noOfSeatsPerRow = noOfSeatsPerRow;
        this.seats = new ArrayList<Seat>();
        setCapacity(); //set the total capacity of the venue
        setSeatsAvailability(); //set the availability of each seat as Available initially
    }

    private void setCapacity(){
        this.countSeats = this.rows * this.noOfSeatsPerRow;
    }

    private void setSeatsAvailability(){
        for(int i=0; i<rows; i++){
            for(int j=0; j< noOfSeatsPerRow; j++){
                Seat seat = new Seat(i,j,Status.AVAILABLE); //new seat
                seats.add(seat);
            }
        }
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public int getCountSeats() {
        return countSeats;
    }

    public void setCountSeats(int countSeats) {
        this.countSeats = countSeats;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getNoOfSeatsPerRow() {
        return noOfSeatsPerRow;
    }

    public void setNoOfSeatsPerRow(int noOfSeatsPerRow) {
        this.noOfSeatsPerRow = noOfSeatsPerRow;
    }
}
