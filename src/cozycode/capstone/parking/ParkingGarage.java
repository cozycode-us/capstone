package cozycode.capstone.parking;
public class ParkingGarage {
    private final ParkingSpot[][] parkingSpots;

    public ParkingGarage(int numRows, int numCols) {
        parkingSpots = new ParkingSpot[numRows][numCols];
        // Initialize all spots as regular parking spots
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                parkingSpots[i][j] = new ParkingSpot("Regular");
            }
        }
    }

    public void printParkingLot() {
        for (int i = 0; i < parkingSpots.length; i++) {
            for (int j = 0; j < parkingSpots[i].length; j++) {
                System.out.print(parkingSpots[i][j].getType().charAt(0) + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        ParkingGarage parkingGarage = new ParkingGarage(4, 198);

        // Print the parking lot layout
        parkingGarage.printParkingLot();
    }
}

class ParkingSpot {
    private String type;

    public ParkingSpot(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}