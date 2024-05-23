package cozycode.capstone;

import cozycode.capstone.parking.ParkingGarage;
import cozycode.capstone.ticketing.Display;

public class Main {

    public static void main(String[] args) {
        ParkingGarage jumpmanJunction = new ParkingGarage(4, 198);
        jumpmanJunction.initializeParkingGarage(20, 20, 50, 50);
        Display display = new Display(jumpmanJunction);
    }

}