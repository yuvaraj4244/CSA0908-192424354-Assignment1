import java.util.Scanner;

class Vehicle {
protected String vehicleNo;

public Vehicle(String vehicleNo) {
    this.vehicleNo = vehicleNo;
    System.out.println("Vehicle constructor");
}

public double calculateFee(int hours) {
    return hours * 20.0;
}

public void display() {
    System.out.println("Vehicle Number: " + vehicleNo);
}

}

class Car extends Vehicle {
protected int seats;

public Car(String vehicleNo, int seats) {
    super(vehicleNo);
    this.seats = seats;
    System.out.println("Car constructor");
}

@Override
public double calculateFee(int hours) {
    return hours * 40.0;
}

@Override
public void display() {
    super.display();
    System.out.println("Seats: " + seats);
}

}

class ElectricCar extends Car {
private boolean chargingRequired;

public ElectricCar(String vehicleNo, int seats,
                   boolean chargingRequired) {
    super(vehicleNo, seats);
    this.chargingRequired = chargingRequired;
    System.out.println("ElectricCar constructor");
}

@Override
public double calculateFee(int hours) {
    double fee = super.calculateFee(hours);

    if (chargingRequired) {
        fee += hours * 15.0;
    }

    return fee;
}

@Override
public void display() {
    super.display();
    System.out.println("Charging Required: " + chargingRequired);
}

}

public class VehicleDemo {
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);

    System.out.print("Enter vehicle number: ");
    String number = sc.nextLine();

    System.out.print("Enter parking hours: ");
    int hours = sc.nextInt();

    System.out.print("Is charging required (true/false): ");
    boolean charging = sc.nextBoolean();

    Vehicle vehicle = new ElectricCar(number, 5, charging);

    System.out.println("\nVehicle Details:");
    vehicle.display();
    System.out.println("Parking Fee: Rs. "
            + vehicle.calculateFee(hours));

    sc.close();
}

}