import java.util.Objects;

abstract class Vehicle {
private final String regNo;

public Vehicle(String regNo) {
    this.regNo = regNo;
}

public String getRegNo() {
    return regNo;
}

public abstract double calculateFee(int hours);

@Override
public String toString() {
    return getClass().getSimpleName() + "[regNo=" + regNo + "]";
}

@Override
public boolean equals(Object obj) {
    if (this == obj) {
        return true;
    }

    if (!(obj instanceof Vehicle)) {
        return false;
    }

    Vehicle other = (Vehicle) obj;
    return regNo.equals(other.regNo);
}

@Override
public int hashCode() {
    return Objects.hash(regNo);
}

}

class TwoWheeler extends Vehicle {
public TwoWheeler(String regNo) {
super(regNo);
}

@Override
public double calculateFee(int hours) {
    return hours * 10.0;
}

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

class ElectricCar extends Vehicle {
public ElectricCar(String regNo) {
super(regNo);
}

@Override
public double calculateFee(int hours) {
    return hours * 30.0 + hours * 12.0;
}

}

public class VehicleOverrideDemo {
public static void main(String[] args) {
Vehicle v1 = new TwoWheeler("TN01A1");
Vehicle v2 = new Car("TN02B2");
Vehicle v3 = new ElectricCar("TN03C3");

    System.out.println(v1);
    System.out.println("Fee: Rs " + v1.calculateFee(3));

    System.out.println(v2);
    System.out.println("Fee: Rs " + v2.calculateFee(3));

    System.out.println(v3);
    System.out.println("Fee: Rs " + v3.calculateFee(3));

    Vehicle c1 = new Car("TN02B2");
    Vehicle c2 = new ElectricCar("TN02B2");

    System.out.println("Same registration: " + c1.equals(c2));
}

}