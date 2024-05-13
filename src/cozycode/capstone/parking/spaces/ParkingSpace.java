package cozycode.capstone.parking.spaces;

import cozycode.capstone.parking.car.*;

public class ParkingSpace {

    private final CarType type;
    private boolean available = true;
    private Car car;
    private int position;

    public ParkingSpace(CarType type, int posCounter) {
        this.type = type;
        this.position = posCounter;
    }
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

    public void assign(Car car) {
        available = false;
        this.car = car;
    }

    public void free() {
        this.car = null;
        available = true;
    }
}
