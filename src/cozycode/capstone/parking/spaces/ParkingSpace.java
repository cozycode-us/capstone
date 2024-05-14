package cozycode.capstone.parking.spaces;

import cozycode.capstone.parking.car.*;

public class ParkingSpace {

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

    public void assign(Car car) {
        available = false;
        this.car = car;
    }

    public void free() {
        this.car = null;
        available = true;
    }

    public void setAvailable(boolean b) {
        available = b;
    }

}

