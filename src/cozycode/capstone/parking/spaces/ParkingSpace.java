package cozycode.capstone.parking.spaces;

import cozycode.capstone.parking.car.*;

/*
 * ParkingSpace Class
 * Defines a ParkingSpace Object that stores a Car when it isn't available
 */
public class ParkingSpace {

    // Instance variables
    // * type is Final because it should not change
    private final CarType type;
    private boolean available = true;
    private Car car;
    // ! Position does not seem to be required with current implementation, remove?
    private int position;

    // * Instantiates a ParkingSpace Object with the specified type and position
    // ! Remove position?
    public ParkingSpace(CarType type, int posCounter) {
        this.type = type;
        this.position = posCounter;
    }

    // Getter methods

    // ! Possibly obsolete, returns position
    public int getPosition() {
        return position;
    }

    public CarType getType() {
        return type;
    }

    public Car getCar() {
        return car;
    }

    public boolean isAvailable() {
        return available;
    }


    // Assigns a space, taking a Car as input
    public void assign(Car car) {
        available = false;
        this.car = car;
    }

    // Makes a space available again after a person leaves the spot
    public void free() {
        this.car = null;
        available = true;
    }
}
