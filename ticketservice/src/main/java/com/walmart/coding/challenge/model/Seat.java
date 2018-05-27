package com.walmart.coding.challenge.model;

/***
 * @author varsha
 */
public class Seat {

    private int row; //row where seat is
    private int col; //col where seat is
    private Status status; //status of the seat

    public Seat(int row, int col, Status status){
        this.row = row;
        this.col = col;
        this.status = status;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
