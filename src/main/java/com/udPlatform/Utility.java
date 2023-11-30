package com.udPlatform;

import java.io.Serializable;
import java.util.Scanner;

public class Utility implements Serializable {


    public static void listOfSelection(String message,String[] operations){
        System.out.println(message);
        for (int i = 0; i <operations.length ; i++) {
            System.out.println(i+"-"+operations[i]);
        }
    }


    public static String inputString(String tag, Scanner sc ){
        System.out.println("Input "+tag);
        String input;
        while (true){
            input=sc.nextLine();
            if(input.isBlank()){
                System.out.println("Please enter some value");
            }else break;
        }
        System.out.println("You have entered "+input+" as "+tag+".");
        return input.trim();
    }

    public static int inputInt(String tag, Scanner sc ){
        System.out.println("Input "+tag);
        while (true){
            String input=sc.nextLine().trim();
            if(getValidInt(input)){
                System.out.println("You have entered "+input+" as "+tag+".");
                return Integer.parseInt(input);
            }
        }
    }


    public static int getSelection(Scanner sc,int max){
        System.out.println("Choose a selection-");
        String input;
        int selection;

        while(true){
            input=sc.nextLine().trim();
            boolean validation=getValidInt(input);
            if(validation){
                selection=Integer.parseInt(input);
                if(selection<0 || selection>=max){
                    System.out.println("Please choose a valid number!");
                }else{
                    System.out.println("You have selected "+selection);
                    return selection;
                }
            }
        }
    }

    public static boolean getValidInt(String number){
        try {
            Integer.parseInt(number);
            return true;
        }catch (NumberFormatException e){
            System.out.println("Your input is not a Number- \"PLEASE TRY AGAIN\"");
        }
        return false;
    }


}
