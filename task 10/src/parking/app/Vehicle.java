package parking.model;

public class Vehicle {
    private String regNo;
    private String vehicleType;

    public Vehicle(String regNo, String vehicleType) {
        setRegNo(regNo);
        setVehicleType(vehicleType);
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        if (regNo == null || regNo.trim().isEmpty()) {
            throw new IllegalArgumentException("Registration number cannot be empty");
        }
        this.regNo = regNo;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        if (vehicleType == null || vehicleType.trim().isEmpty()) {
            throw new IllegalArgumentException("Vehicle type cannot be empty");
        }
        this.vehicleType = vehicleType;
    }

    @Override
    public String toString() {
        return "Vehicle Number: " + regNo
                + "\nVehicle Type: " + vehicleType;
    }
}