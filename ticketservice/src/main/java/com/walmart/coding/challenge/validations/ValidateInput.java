package com.walmart.coding.challenge.validations;

import org.apache.commons.validator.routines.EmailValidator;

import java.util.Scanner;

public class ValidateInput {

        public static int validateNumber(Scanner scr){
            int number = 0;
            do{
                System.out.println("Enter a positive value!");
                while(!scr.hasNextInt()){
                    System.out.println("enter only numbers!");
                    scr.next();
                }
                number = scr.nextInt();
            }while(number<=0);
            return number;
        }

        public static boolean validateEmail(String email){
            EmailValidator validator = EmailValidator.getInstance();
            if(validator.isValid(email)){
                return true;
            }else
                return false;
        }

        public static String getValidEmail(boolean isValidEmail, Scanner scr){
            String email = "";
            while(!isValidEmail){
                System.out.println("Enter valid email address");
                email = scr.next();
                isValidEmail = ValidateInput.validateEmail(email);
            }
            return email;
        }
}
