import java.util.HashMap;

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

    public abstract double calculateFee(int minutes);
}

class TwoWheeler extends Vehicle {
    public TwoWheeler(String regNo) {
        super(regNo);
    }

    public double calculateFee(int minutes) {
        return ((minutes + 59) / 60) * 10.0;
    }
}

class Car extends Vehicle {
    public Car(String regNo) {
        super(regNo);
    }

    public double calculateFee(int minutes) {
        return ((minutes + 59) / 60) * 30.0;
    }
}

class ElectricCar extends Car {
    public ElectricCar(String regNo) {
        super(regNo);
    }

    public double chargingFee(int kWh) {
        return kWh * 12.0;
    }
}

class ParkingSystem {
    private final HashMap<String, Vehicle> parkedVehicles = new HashMap<>();
    private final int capacity;
    private double totalCollection = 0;

    public ParkingSystem(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Invalid capacity");
        }
        this.capacity = capacity;
    }

    public boolean park(Vehicle vehicle) {
        if (vehicle == null || parkedVehicles.containsKey(vehicle.getRegNo())
                || parkedVehicles.size() >= capacity) {
            return false;
        }

        parkedVehicles.put(vehicle.getRegNo(), vehicle);
        return true;
    }

    public double leave(String regNo, int minutes) {
        if (minutes < 0) {
            throw new IllegalArgumentException("Invalid parking duration");
        }

        Vehicle vehicle = parkedVehicles.remove(regNo);

        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle not found");
        }

        double fee = vehicle.calculateFee(minutes);
        totalCollection += fee;
        return fee;
    }

    public double getTotalCollection() {
        return totalCollection;
    }

    public int getOccupancy() {
        return parkedVehicles.size();
    }
}

public class ParkingSystemTest {
    public static void main(String[] args) {
        ParkingSystem parking = new ParkingSystem(2);

        System.out.println("Test 1 - Park two-wheeler: "
                + parking.park(new TwoWheeler("TN01A1")));

        System.out.println("Test 2 - Park car: "
                + parking.park(new Car("TN02B2")));

        System.out.println("Test 3 - Full parking: "
                + parking.park(new ElectricCar("TN03C3")));

        System.out.println("Test 4 - Duplicate registration: "
                + parking.park(new Car("TN01A1")));

        System.out.println("Test 5 - Two-wheeler fee for 60 minutes: Rs "
                + parking.leave("TN01A1", 60));

        System.out.println("Test 6 - Car fee for 61 minutes: Rs "
                + parking.leave("TN02B2", 61));

        System.out.println("Test 7 - Final occupancy: "
                + parking.getOccupancy());

        System.out.println("Total collection: Rs "
                + parking.getTotalCollection());

        try {
            parking.leave("UNKNOWN", 30);
        } catch (IllegalArgumentException e) {
            System.out.println("Test 8 - Unknown vehicle: " + e.getMessage());
        }

        try {
            parking.leave("TN04D4", -5);
        } catch (IllegalArgumentException e) {
            System.out.println("Test 9 - Invalid duration: " + e.getMessage());
        }
    }
}