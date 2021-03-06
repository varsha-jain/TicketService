package com.walmart.coding.challenge.validations;

import org.apache.commons.validator.routines.EmailValidator;

import java.util.Scanner;

/***
 * @author varsha
 * Class to validate user input
 */
public class ValidateInput {

    /***
     * Function to validate if the user entered integer or not
     * @param scr
     * @return the integer number that user entered
     */
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

    /***
     * Function to validate email address entered by the user
     * @param email
     * @return if the email is vaid or not
     */
    public static boolean validateEmail(String email){
            EmailValidator validator = EmailValidator.getInstance();
            if(validator.isValid(email)){
                return true;
            }else
                return false;
        }

    /***
     * Function that returns valid email entered by the user
     * @param isValidEmail
     * @param scr
     * @return
     */

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
