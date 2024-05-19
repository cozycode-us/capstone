package cozycode.capstone.parking;

import cozycode.capstone.parking.car.*;
import cozycode.capstone.parking.spaces.*;
import cozycode.capstone.ticketing.Ticket;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

/*
 * Parking Garage Class
 * Initializes Parking Garage and defines all functions of the Parking Garage
 */
public class ParkingGarage {

    private ParkingSpace[][] spaces;

    //Method to turn the list 'spaces' into a parking garage of custom size
    public ParkingGarage(int rows, int cols) {
        spaces = new ParkingSpace[rows][cols];
    }

    // Method to initialize ParkingSpace objects
    public void initializeParkingGarage(int handicapCount, int oversizeCount, int evCount, int carpoolCount) {

        // Initialize the first row with the specified number of each car type
        for (int j = 0; j < spaces[0].length; j++) {
            CarType type;
            if (handicapCount > 0) {
                type = CarType.HANDICAP;
                handicapCount--;
            } else if (oversizeCount > 0) {
                type = CarType.OVERSIZE;
                oversizeCount--;
            } else if (evCount > 0) {
                type = CarType.EV;
                evCount--;
            } else if (carpoolCount > 0) {
                type = CarType.CARPOOL;
                carpoolCount--;
            } else {
                // If all counts are zero, default to regular type
                type = CarType.REGULAR;
            }
            spaces[0][j] = new ParkingSpace(type);
        }

        // Initialize regular
        for (int i = 1; i < spaces.length; i++) {
            for (int j = 0; j < spaces[i].length; j++) {
                spaces[i][j] = new ParkingSpace(CarType.REGULAR); // the spaces class supposedly cannot be used to reference the carType enum
            }
        }
    }

    // Method to print array parking garage
    public void printParkingGarage() {
        for (ParkingSpace[] row : spaces) {
            for (ParkingSpace space : row) {
                if (space != null) {
                    System.out.print(space.getType().toString().charAt(0) + (space.isAvailable() ? " " : "X "));
                } else {
                    // If null, print a placeholder
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }


    // Method to retrieve a specific parking space
    public ParkingSpace getParkingSpace(int row, int col) {
        return spaces[row][col];
    }

    //* Method to assign a specific parking space to the currently entering car
    //! Code to save parking space status between program runs not implemented
    //? Make more complex trickle down prioritization system?
    public Ticket assignSpace(Car car) {
        int counter = 1;
        for (ParkingSpace[] row : spaces) {
            int poopoo = 1;
            for (ParkingSpace space : row) {
                // Checks if space matches car type, if not checks if it is a regular spot as a backup
                if (space.getType().equals(car.getType()) && space.isAvailable() || space.getType().equals(CarType.REGULAR) && space.isAvailable()) {
                    space.assign(car);
                    System.out.println(space.getCar());
                    car.setSpotNumber(counter * 1000 + poopoo);
                    Ticket linus = new Ticket(counter,poopoo,car,space.getType());
                    return linus;
                }
                poopoo++;
            }
            counter++;
        }
        Ticket shyla = new Ticket(-1,-1) ;
        return shyla; // Return -1 if  space is found
    }


    //* Method to clear a specific parking space of the currently leaving car
    //* id is an Employee ID, if check is true, it will verify with the employee that it is their car.
    //* Returns a negative value when there is an issue in freeing the parking spot, 0 otherwise
    public int leaveSpace(int id, boolean check, Component parentComponent) {
        for (ParkingSpace[] arr : spaces) {
            for (ParkingSpace space : arr) {
                if (space.getCar() == null) {
                    continue;
                }
                if (space.getCar().getId() == id) {
                    // Asks the user if it is their car
                    if (check) {
                        String carDetails = String.format(
                                "Parking Space: %s\nCar Model: %s %s\nCar Color: %s\nCar Registration Number: %s\n\nDoes this seem like the correct car?",
                                space.getCar().getSpotNumber(),
                                space.getCar().getMake(),
                                space.getCar().getModel(),
                                space.getCar().getColor(),
                                space.getCar().getRegistration()
                        );

                        int choice = JOptionPane.showConfirmDialog(
                                parentComponent,
                                carDetails,
                                "Confirm Car Details",
                                JOptionPane.YES_NO_OPTION
                        );

                        if (choice == JOptionPane.NO_OPTION) {
                            return -2;
                        }
                    }

                    space.free();
                    return 0;
                }
            }
        }
        return -1;
    }
    }