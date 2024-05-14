package cozycode.capstone.parking;

import cozycode.capstone.parking.car.*;
import cozycode.capstone.parking.spaces.*;

public class ParkingGarage {

    private ParkingSpace[][] spaces;

    public ParkingGarage(int rows, int cols) {
        spaces = new ParkingSpace[rows][cols];
    }

    // Method to initialize ParkingSpace objects
    private int posCounter = 0;

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


    // retrieve a specific parking space
    public ParkingSpace getParkingSpace(int row, int col) {
        return spaces[row][col];
    }

    public int[] assignSpace(Car car) {
        int index = 1;
        for (ParkingSpace[] row : spaces) {
            int poopoo = 1;
            for (ParkingSpace space : row) {
                if (space.getType() == car.getType() && space.isAvailable()) {
                    space.setAvailable(false);
                    int[] linus = {index, poopoo};
                    return linus;
                }
                poopoo++;
            }
            index++;
        }
        int[] shyla = {-1,-1} ;
        return shyla; // Return -1 if  space is found
    }
/*
    public static void main(String[] args) {
        ParkingGarage parkingGarage = new ParkingGarage(4, 198);
        parkingGarage.initializeParkingGarage(20, 20, 50, 50); // counts for each car type in row 1

        // Create a car to be assigned
        Car = new Car("ABC123", "Red", "Toyota", "Corolla", CarType.REGULAR);

        // Assign the car to a parking space
        int assignedColumn = parkingGarage.assignSpace(car);

        // Print the result
        if (assignedColumn != -1) {
            System.out.println("Assigned parking space column: " + assignedColumn);
        } else {
            System.out.println("No available space of matching type found.");
        }
    } */
}