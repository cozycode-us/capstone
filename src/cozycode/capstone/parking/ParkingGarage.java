package cozycode.capstone.parking;
import cozycode.capstone.parking.car.*;
import cozycode.capstone.parking.spaces.*;
import java.util.ArrayList;
import java.io.*;
public class ParkingGarage {

    private ParkingSpace[][] spaces;

    public ParkingGarage(int rows, int cols) {
        spaces = new ParkingSpace[rows][cols];
    }

    // Method to initialize the parking garage with ParkingSpace objects
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
            posCounter ++;
            spaces[0][j] = new ParkingSpace(type, posCounter);
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


    // assign & reserve a specific space to a specific car
    public int assignSpace(Car car) {
        int counter = 0;
        ArrayList<Car> typeList = new ArrayList<Car>();
        ArrayList<Car> freeList = new ArrayList<Car>();


        // for handicap cars
        if (Car.carType() == CarType.HANDICAP) {
            // creates new list of applicable spots
            for (int i = 0; i < spaces.length; i++) {
                for (int j = 0; j < spaces[i].length; j++) {
                    if (space.getType() == CarType.HANDICAP) {
                        typeList.add(space);
                    }
                }
            }
            // creates new list of applicable spots that are open
            for (ArrayList[] space : typeList) {
                if (ParkingSpace.isAvailable == true) {
                    freeList.add(space);
                }
            }
            ParkingSpace thisSpace = freeList.get(0);
            thisSpace.assign(car);
            serializeToFile(spaces, "parkingData.txt");
            return thisSpace.getPosition();
        }

        // for oversize cars
        else if (Car.carType() == CarType.OVERSIZE) {
            // creates new list of applicable spots
            for (int i = 0; i < spaces.length; i++) {
                for (int j = 0; j < spaces[i].length; j++) {
                    if (space.getType() == CarType.OVERSIZE) {
                        typeList.add(space);
                    }
                }
            }
            // creates new list of applicable spots that are open
            for (ArrayList[] space : typeList) {
                if (ParkingSpace.isAvailable == true) {
                    freeList.add(space);
                }
            }
            ParkingSpace thisSpace = freeList.get(0);
            thisSpace.assign(car);
            serializeToFile(spaces, "parkingData.txt");
            return thisSpace.getPosition();
        }

        // for EVs
        else if (Car.carType() == CarType.EV) {
            // creates new list of applicable spots
            for (int i = 0; i < spaces.length; i++) {
                for (int j = 0; j < spaces[i].length; j++) {
                    if (space.getType() == CarType.EV) {
                        typeList.add(space);
                    }
                }
            }
            // creates new list of applicable spots that are open
            for (ArrayList[] space : typeList) {
                if (ParkingSpace.isAvailable == true) {
                    freeList.add(space);
                }
            }
            ParkingSpace thisSpace = freeList.get(0);
            thisSpace.assign(car);
            serializeToFile(spaces, "parkingData.txt");
            return thisSpace.getPosition();
        }

        // for carpools
        else if (Car.carType() == CarType.CARPOOL) {
            // creates new list of applicable spots
            for (int i = 0; i < spaces.length; i++) {
                for (int j = 0; j < spaces[i].length; j++) {
                    if (space.getType() == CarType.CARPOOL) {
                        typeList.add(space);
                    }
                }
            }
            // creates new list of applicable spots that are open
            for (ArrayList[] space : typeList) {
                if (ParkingSpace.isAvailable == true) {
                    freeList.add(space);
                }
            }
            ParkingSpace thisSpace = freeList.get(0);
            thisSpace.assign(car);
            serializeToFile(spaces, "parkingData.txt");
            return thisSpace.getPosition();
        }

        // for regulars and above types if specialty spots run out
        else {
            if (spaces.length == 0) {
                return -1;
            } else {
                // creates new list of applicable spots
                for (int i = 0; i < spaces.length; i++) {
                    for (int j = 0; j < spaces[i].length; j++) {
                        if (space.getType() == CarType.REGULAR) {
                            typeList.add(space);
                        }
                    }
                }
                // creates new list of applicable spots that are open
                for (ArrayList[] space : typeList) {
                    if (ParkingSpace.isAvailable == true) {
                        freeList.add(space);
                    }
                }
                ParkingSpace thisSpace = freeList.get(0);
                thisSpace.assign(car);
                serializeToFile(spaces, "parkingData.txt");
                return thisSpace.getPosition();
            }
        }
    }

        /*
         * Makes a space available when a user leaves it
         */
    public void leaveSpace(int id) {
        for (ParkingSpace[] space : spaces) {
            for (ParkingSpace parkingSpace : space) {
                if (parkingSpace.getCar().getId() == id) {
                    parkingSpace.free();
                }
            }
        }
    }
}

