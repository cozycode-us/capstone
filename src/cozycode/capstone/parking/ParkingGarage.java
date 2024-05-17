package cozycode.capstone.parking;

import cozycode.capstone.parking.car.*;
import cozycode.capstone.parking.spaces.*;
import cozycode.capstone.ticketing.Ticket;

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
    public Ticket assignSpace(Car car) {
        int counter = 1;
        for (ParkingSpace[] row : spaces) {
            int poopoo = 1;
            for (ParkingSpace space : row) {
                if (space.getType() == car.getType() && space.isAvailable()) {
                    space.setAvailable(false);
                    Ticket linus = new Ticket(counter,poopoo,car);
                    return linus;
                }
                poopoo++;
            }
            counter++;
        }
        Ticket shyla = new Ticket(-1,-1) ;
        return shyla; // Return -1 if  space is found
    }


    //*Method to clear a specific parking space of the currently leaving car
    public void leaveSpace(int id) {
        for (ParkingSpace[] arr : spaces) {
            for (ParkingSpace space : arr) {
                if (space.getCar().getId() == id) {
                    space.free();
                }
            }
        }
    }
}