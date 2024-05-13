package cozycode.capstone.parking.car;

public class Car {

    private final String registration;
    private final String color;
    private final String model;
    private final String make;
    private final CarType type;

    public Car(String regNum, String color, String model, String make, CarType type) {
        this.registration = regNum;
        this.color = color;
        this.model = model;
        this.make = make;
        this.type = type;
    }

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
}
