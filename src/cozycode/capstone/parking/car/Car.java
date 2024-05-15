package cozycode.capstone.parking.car;

/*
 * Car class
 * Stores information regarding an employee's car at a parking space sfgjios
 */
public class Car {

    // Instance variables
    // * Final because they are permanent to the car, as long as it's at the parking space
    private final String registration;
    private final String color;
    private final String model;
    private final String make;
    private final CarType type;
    private final int id;

    // * Instantiates a Car Object with the specified attributes
    public Car(String regNum, String color, String model, String make, CarType type, int id) {
        this.registration = regNum;
        this.color = color;
        this.model = model;
        this.make = make;
        this.type = type;
        this.id = id;
    }

    // Getter methods for various information about the Car
    public String getRegistration() {
        return registration;
    }

    public String getColor() {
        return color;
    }

    public String getModel() {
        return model;
    }

    public String getMake() {
        return make;
    }

    public CarType getType() {
        return type;
    }

    public int getId() {
        return id;
    }
}
