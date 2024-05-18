package cozycode.capstone.ticketing;

import cozycode.capstone.parking.car.Car;
import cozycode.capstone.parking.car.CarType;

public class Ticket {

    // * instance variables are never changed; they are final
    // First number returned by ParkingGarage.assignSpace()
    private final int floor;
    // Second number returned by ParkingGarage.assignSpace()
    private final int number;
    // Employee's car
    private final Car car;
    // Type of parking spot
    private final CarType type;

    public Ticket(int floor, int number, Car car, CarType type) {
        this.floor = floor;
        this.number = number + floor * 1000;
        this.car = car;
        this.type = type;
    }

    public Ticket(int floor,int number) {
        this.floor = floor;
        this.number = number;
        this.car = null;
        this.type = null;
    }

    public int getFloor() {
        return floor;
    }

    // ? Should this return number or the full parking space number
    public int getNumber() {
        return number;
    }

    public Car getCar() {
        return car;
    }

    public CarType getType() {
        return type;
    }
}
