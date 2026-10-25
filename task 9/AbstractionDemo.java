interface Chargeable {
    double chargingFee(int kWh);

    default void displayChargingInfo() {
        System.out.println("Charging service is available.");
    }

    static boolean isValidEnergy(int kWh) {
        return kWh >= 0;
    }
}

abstract class Vehicle {
    private final String regNo;

    public Vehicle(String regNo) {
        if (regNo == null || regNo.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid registration number");
        }
        this.regNo = regNo;
    }

    public String getRegNo() {
        return regNo;
    }

    public abstract double calculateFee(int hours);
}

class Car extends Vehicle {
    public Car(String regNo) {
        super(regNo);
    }

    @Override
    public double calculateFee(int hours) {
        return hours * 30.0;
    }
}

class ElectricCar extends Car implements Chargeable {
    public ElectricCar(String regNo) {
        super(regNo);
    }

    @Override
    public double chargingFee(int kWh) {
        if (!Chargeable.isValidEnergy(kWh)) {
            throw new IllegalArgumentException("Invalid energy amount");
        }
        return kWh * 12.0;
    }
}

public class AbstractionDemo {
    public static void main(String[] args) {
        Vehicle car = new Car("TN01AB1111");
        Vehicle ev = new ElectricCar("TN02CD2222");

        System.out.println("Car: " + car.getRegNo());
        System.out.println("Parking Fee: Rs " + car.calculateFee(3));

        System.out.println("Electric Car: " + ev.getRegNo());
        System.out.println("Parking Fee: Rs " + ev.calculateFee(3));

        Chargeable charging = (Chargeable) ev;
        charging.displayChargingInfo();
        System.out.println("Charging Fee: Rs " + charging.chargingFee(10));
    }
}