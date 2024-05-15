package cozycode.capstone.ticketing;

import cozycode.capstone.parking.car.Car;

public class Ticket {

    // * instance variables are never changed; they are final
    // First number returned by ParkingGarage.assignSpace()
    private final int floor;
    // Second number returned by ParkingGarage.assignSpace()
    private final int number;
    // Employee's car
    private final Car car;

    public Ticket(int floor, int number, Car car) {
        this.floor = floor;
        this.number = number;
        this.car = car;
    }

    public int getFloor() {
        return floor;
    }

    public int getNumber() {
        return number;
    }

    public Car getCar() {
        return car;
    }
}
