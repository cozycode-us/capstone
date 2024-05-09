package cozycode.capstone.parking;

public class ParkingGarage {
    private final ParkingSpot[][] parkingSpots;

    public ParkingGarage(int numRows, int numCols) {
        parkingSpots = new ParkingSpot[numRows][numCols];

        // Initialize all spots as regular parking spots
        for (ParkingSpot[] row : parkingSpots) {
            for (int j = 0; j < row.length; j++) {
                row[j] = new ParkingSpot("Regular");
            }
        }

        // Assign types to specific spots in the first row
        ParkingSpot[] firstRow = parkingSpots[0];
        int currentSpot = 0;
        while (currentSpot < 140) {
            for (int i = 0; i < 20 && currentSpot < firstRow.length; i++) {
                firstRow[currentSpot++] = new ParkingSpot("Handicap");
            }
            for (int i = 0; i < 20 && currentSpot < firstRow.length; i++) {
                firstRow[currentSpot++] = new ParkingSpot("Oversized");
            }
            for (int i = 0; i < 50 && currentSpot < firstRow.length; i++) {
                firstRow[currentSpot++] = new ParkingSpot("EV");
            }
            for (int i = 0; i < 50 && currentSpot < firstRow.length; i++) {
                firstRow[currentSpot++] = new ParkingSpot("Carpool");
            }
        }
    }

    public void printParkingLot() {
        for (ParkingSpot[] row : parkingSpots) {
            for (ParkingSpot spot : row) {
                System.out.print(spot.getType().charAt(0) + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        ParkingGarage parkingGarage = new ParkingGarage(4, 198);

        // Print the parking lot layout after types have been assigned
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