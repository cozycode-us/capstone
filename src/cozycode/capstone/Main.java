package cozycode.capstone;

import cozycode.capstone.parking.ParkingGarage;
import cozycode.capstone.parking.car.Car;
import cozycode.capstone.parking.car.CarType;
import cozycode.capstone.ticketing.Ticket;

import java.util.Scanner;

public class Main {

    /*
     * Runtime method
     * Asks for user input
     */
    public static void main(String[] args) {

        // Instantiates parking garage
        ParkingGarage jumpmanJunction = new ParkingGarage(4,198);
        jumpmanJunction.initializeParkingGarage(20,20,50,50);

        // Initializes variables for controlling the loop and reading user input
        boolean exit = false;
        Scanner sc = new Scanner(System.in);

        // * Runs until the user wants to close the program
        // ! Exiting program is not implemented
        while (!exit) {

            // Determines if user wants to enter or leave
            System.out.println("Do you want to find or leave a parking spot? (f/l)");

            String response = sc.nextLine();
            if (response.equals("f")) {
                // * Asks user for identifying information
                System.out.println("What is your car's registration number?");
                String reg = sc.nextLine();
                System.out.println("What is your car's color?");
                String color = sc.nextLine();
                System.out.println("What is the make?");
                String make = sc.nextLine();
                System.out.println("The model?");
                String model = sc.nextLine();
                CarType type = determineType(sc);
                System.out.println("What is your Employee ID?");
                int id = sc.nextInt();
                sc.nextLine();

                // Instantiates a Car Object using the information provided
                Car car = new Car(reg,color,model,make,type,id);

                // * Assigns the spot and returns the spot assigned
                Ticket mySpot = jumpmanJunction.assignSpace(car);

                // Prints out the parking ticket information
                System.out.println("\nParking Assignment:");
                System.out.println("Car Model: " + mySpot.getCar().getMake() + " " + mySpot.getCar().getModel());
                System.out.println("Floor: " + mySpot.getFloor());
                System.out.println("Space #: " + mySpot.getNumber());
                System.out.println("Space Type: " + mySpot.getType() + "\n");

                // Checks with the user if the spot is OK
                if (!mySpot.getType().equals(mySpot.getCar().getType())) {
                    System.out.println("Note: You did not get a spot of the requested type! Is this spot still acceptable? (y/n)");
                    System.out.println("Responding with 'n' will cancel your parking assignment.");
                    String input = sc.nextLine();
                    if (input.equals("n")) {
                        jumpmanJunction.leaveSpace(mySpot.getCar().getId(), false);
                        System.out.println("Your parking assignment has been successfully canceled.\n");
                    } else {
                        System.out.println("Great, we apologize for the inconvenience. You may head to your parking spot.\n");
                    }
                } else {
                    System.out.println("You got a spot of the requested type! Is this spot acceptable? (y/n)");
                    System.out.println("Responding with 'n' will cancel your parking assignment.");
                    String input = sc.nextLine();
                    if (input.equals("n")) {
                        jumpmanJunction.leaveSpace(mySpot.getCar().getId(), false);
                        System.out.println("Your parking assignment has been successfully canceled.\n");
                    } else {
                        System.out.println("Great! You may head to your parking spot.\n");
                    }
                }

            } else if (response.equals("l")) {

                // Asks the employee for their ID if they want to leave
                System.out.println("What is your Employee ID?");
                int id = sc.nextInt();
                sc.nextLine();

                // Tries to free the spot
                int successful = jumpmanJunction.leaveSpace(id, true);

                /*
                 * If the user typed in an ID not tied to a parking spot, successful == -1
                 * If the user said the corresponding car was not theirs, successful == -2
                 * If the user said everything looks good, successful == 0
                 */
                if(successful == -1) {
                    System.out.println("There was an error freeing your parking space. Please make sure you entered your ID correctly.");
                } else if (successful == -2) {
                    System.out.println("Successfully canceled operation. Make sure you inputted your ID correctly.");
                } else {
                    System.out.println("Thank you for visiting Jumpman Junction! Your parking space has been successfully unassigned.");
                }


            } else {

                // If the user didn't input a valid option, give clearer instructions
                System.out.println("Oops, that didn't work. Make sure you are inputting 'f' for finding a spot or 'l' for leaving.");
            }

        }
    }

    /*
     * determineType method used to figure out which parking spot to give the user
     * Takes hierarchy of spot types into consideration
     * Uses Scanner Object used for previous user input
     ? Might need to add paid spots in future
     */
    public static CarType determineType(Scanner sc) {
        String response = "";

        // Checks if user has a handicap permit
        while (!response.equals("n")) {
            System.out.println("Do you have a handicap permit? (y/n)");
            response = sc.nextLine();
            if (!(response.equals("y") || response.equals("n"))) {
                System.out.println("Oops, that didn't work. Make sure you are inputting 'y' or 'n'.");
            } else if (response.equals("y")) {
                return CarType.HANDICAP;
            }
        }
        response = "";

        // Checks if user has an oversized car
        while (!response.equals("n")) {
            System.out.println("Is your car oversize? (y/n)");
            response = sc.nextLine();
            if (!(response.equals("y") || response.equals("n"))) {
                System.out.println("Oops, that didn't work. Make sure you are inputting 'y' or 'n'.");
            } else if (response.equals("y")) {
                return CarType.OVERSIZE;
            }
        }
        response = "";

        // Checks if the user has an EV
        while (!response.equals("n")) {
            System.out.println("Is your car an EV? (y/n)");
            response = sc.nextLine();
            if (!(response.equals("y") || response.equals("n"))) {
                System.out.println("Oops, that didn't work. Make sure you are inputting 'y' or 'n'.");
            } else if (response.equals("y")) {
                return CarType.EV;
            }
        }
        response = "";

        // Checks if the user is in a carpool
        while (!response.equals("n")) {
            System.out.println("Are you in a carpool? (y/n)");
            response = sc.nextLine();
            if (!(response.equals("y") || response.equals("n"))) {
                System.out.println("Oops, that didn't work. Make sure you are inputting 'y' or 'n'.");
            } else if (response.equals("y")) {
                return CarType.CARPOOL;
            }
        }

        // If no special spot is required, give them a regular spot
        return CarType.REGULAR;
    }
}
