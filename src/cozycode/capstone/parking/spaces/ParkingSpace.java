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

    public ParkingSpace(CarType type) {
        this.type = type;
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

    public void setAvailable(boolean b) {
        available = b;
    }

}

