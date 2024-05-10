package cozycode.capstone.parking.spaces;
import cozycode.capstone.parking.car.*;

public class ParkingGarage {

    private ParkingSpace[][] spaces;

    public ParkingGarage(int rows, int cols) {
        spaces = new ParkingSpace[rows][cols];
    }

    // Method to initialize the parking garage with ParkingSpace objects
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

        // Initialize regular type
        for (int i = 1; i < spaces.length; i++) {
            for (int j = 0; j < spaces[i].length; j++) {
                spaces[i][j] = new ParkingSpace(CarType.REGULAR);
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
    public static void main(String[] args) {
        ParkingGarage parkingGarage = new ParkingGarage(4, 198);
        parkingGarage.initializeParkingGarage(20, 20, 50, 50); // counts for each car type in row 1
        parkingGarage.printParkingGarage();


    }
}