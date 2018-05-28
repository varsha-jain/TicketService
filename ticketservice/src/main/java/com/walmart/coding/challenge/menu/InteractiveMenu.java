package com.walmart.coding.challenge.menu;

import com.walmart.coding.challenge.model.SeatHold;
import com.walmart.coding.challenge.model.Venue;
import com.walmart.coding.challenge.service.TicketService;
import com.walmart.coding.challenge.service.TicketServiceImpl;
import com.walmart.coding.challenge.validations.ValidateInput;

import java.util.Scanner;

public class InteractiveMenu {
    public static void startMenu(){
        System.out.println( "*********Ticket Service***********" );

        int rows = 5;
        int col = 5;
        Venue venue = new Venue(rows,col);
        TicketService ticketService = new TicketServiceImpl(venue);
        Scanner scr = new Scanner(System.in);
        int input = 0;
        do{
            System.out.println("Menu options:-\n1.Available Seats\n2.Book tickets\n3.Reserve seats\n4.Exit");
            System.out.println("Enter your option!");
            while(!scr.hasNextInt()){
                System.out.println("Enter only numbers!");
                scr.next();
            }
            input = scr.nextInt();

            switch(input){
                case 1: System.out.println("Find out the total available seats!");
                        int seatsAvailable = ticketService.numSeatsAvailable();
                        System.out.println("Total seats available are: " + seatsAvailable);
                        break;

                case 2: System.out.println("Book/Hold tickets! Expiration time is 60seconds only.");
                        System.out.println("How many seats do you want to hold?");
                        int num = ValidateInput.validateNumber(scr);
                        System.out.println("Enter your email id");
                        String email = scr.next();
                        boolean isValidEmail = ValidateInput.validateEmail(email);
                        if(!isValidEmail){
                            email = ValidateInput.getValidEmail(isValidEmail, scr);
                        }
                        SeatHold seatHold = ticketService.findAndHoldSeats(num,email);
                        if(seatHold != null)
                            System.out.println("Your booking id is: " + seatHold.getSeatHoldId());
                        else
                            System.out.println("Sorry no more seats available!");
                        System.out.println(" total seats available now are: " + ticketService.numSeatsAvailable());
                        break;

                case 3: System.out.println("Reserve tickets");
                        System.out.println("Enter the booking id:");
                        int id = ValidateInput.validateNumber(scr);
                        System.out.println("Enter your email id");
                        String emailAddress = scr.next();
                        boolean isValidEmailId = ValidateInput.validateEmail(emailAddress);
                        if(!isValidEmailId){
                            emailAddress =  ValidateInput.getValidEmail(isValidEmailId, scr);
                        }
                        System.out.println(ticketService.reserveSeats(id, emailAddress));
                        System.out.println("total seats available now are: " + ticketService.numSeatsAvailable());
                        break;

                case 4: System.out.println("Exit");
                        break;

                default: System.out.println("invalid option!");
                         break;

            }
        }while(input!=4);

    }
}
