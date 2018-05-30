package com.walmart.coding.challenge.model;

/***
 * @author varsha
 * model that represents a particular seat object
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

    /**
     *
     * @return the row number
     */
    public int getRow() {
        return row;
    }

    /***
     *
     * @param row
     */
    public void setRow(int row) {
        this.row = row;
    }

    /***
     *
     * @return column id of that seat
     * row id and col id define the location of every seat
     */
    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    /***
     *
     * @return the status of each seat
     */
    public Status getStatus() {
        return status;
    }

    /***
     * set the status of the seat
     * @param status
     */
    public void setStatus(Status status) {
        this.status = status;
    }
}
