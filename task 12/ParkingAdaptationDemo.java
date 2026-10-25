
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

    public abstract double calculateFee(int hours, int entryHour);
}

class Car extends Vehicle {
    public Car(String regNo) {
        super(regNo);
    }

    public double calculateFee(int hours, int entryHour) {
        return hours * 30.0;
    }
}

class ElectricCar extends Car {
    public ElectricCar(String regNo) {
        super(regNo);
    }

    public double calculateFee(int hours, int entryHour) {
        return hours * 30.0;
    }
}

class EBike extends Vehicle {
    public EBike(String regNo) {
        super(regNo);
    }

    public double calculateFee(int hours, int entryHour) {
        return 20.0;
    }
}

class DeliveryVan extends Vehicle {
    public DeliveryVan(String regNo) {
        super(regNo);
    }

    public double calculateFee(int hours, int entryHour) {
        if (entryHour >= 9 && entryHour < 17) {
            return hours * 60.0;
        }
        return hours * 30.0;
    }
}

interface Pass {
    double getDiscountedFee(double fee);
}

class OneDayVisitorPass implements Pass {
    public double getDiscountedFee(double fee) {
        return fee * 0.5;
    }
}

class FiveFloorParking {
    private final String[][] slots;

    public FiveFloorParking(int[] capacities) {
        if (capacities == null || capacities.length != 5) {
            throw new IllegalArgumentException(
                    "Exactly five floor capacities are required");
        }

        slots = new String[5][];

        for (int i = 0; i < 5; i++) {
            if (capacities[i] < 0) {
                throw new IllegalArgumentException("Invalid floor capacity");
            }
            slots[i] = new String[capacities[i]];
        }
    }

    public int[] allocate(String regNo) {
        for (int floor = 0; floor < slots.length; floor++) {
            for (int slot = 0; slot < slots[floor].length; slot++) {
                if (slots[floor][slot] == null) {
                    slots[floor][slot] = regNo;
                    return new int[]{floor, slot};
                }
            }
        }
        return null;
    }

    public boolean release(int floor, int slot) {
        if (floor < 0 || floor >= slots.length
                || slot < 0 || slot >= slots[floor].length
                || slots[floor][slot] == null) {
            return false;
        }

        slots[floor][slot] = null;
        return true;
    }
}

public class ParkingAdaptationDemo {
    public static void main(String[] args) {
        Vehicle car = new Car("TN01AB1111");
        Vehicle ebike = new EBike("TN02CD2222");
        Vehicle van = new DeliveryVan("TN03EF3333");

        System.out.println("Car Fee: Rs "
                + car.calculateFee(2, 10));

        System.out.println("E-bike Flat Fee: Rs "
                + ebike.calculateFee(2, 10));

        System.out.println("Delivery Van Fee (10 AM): Rs "
                + van.calculateFee(2, 10));

        Pass visitorPass = new OneDayVisitorPass();
        System.out.println("Visitor Pass Fee: Rs "
                + visitorPass.getDiscountedFee(
                        car.calculateFee(2, 10)));

        FiveFloorParking parking =
                new FiveFloorParking(new int[]{2, 3, 2, 4, 3});

        int[] location = parking.allocate("TN01AB1111");

        if (location != null) {
            System.out.println("Allocated Floor: " + location[0]);
            System.out.println("Allocated Slot: " + location[1]);
        }

        System.out.println("Slot Released: "
                + parking.release(0, 0));
    }
}