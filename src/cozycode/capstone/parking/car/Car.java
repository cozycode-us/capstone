package cozycode.capstone.parking.car;

public class Car {

    private final String registration;
    private final String color;
    private final String model;
    private final String make;
    private final CarType type;
    private final int id;

    public Car(String regNum, String color, String model, String make, CarType type, int id) {
        this.registration = regNum;
        this.color = color;
        this.model = model;
        this.make = make;
        this.type = type;
        this.id = id;
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

    public int getId() {
        return id;
    }
}
