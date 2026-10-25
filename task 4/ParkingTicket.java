import java.time.LocalDateTime;

public class ParkingTicket {

    private static int nextTicketNumber = 1001;

    private final int ticketNumber;
    private final String vehicleNo;
    private final int floor;
    private final int slot;
    private final LocalDateTime entryTime;

    public ParkingTicket(String vehicleNo, int floor, int slot) {
        this(nextTicketNumber++, vehicleNo, floor, slot,
             LocalDateTime.now());
    }

    public ParkingTicket(int ticketNumber, String vehicleNo,
                         int floor, int slot,
                         LocalDateTime entryTime) {

        if (vehicleNo == null || vehicleNo.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid vehicle number");
        }

        if (floor < 0 || slot < 0) {
            throw new IllegalArgumentException("Invalid parking position");
        }

        if (entryTime == null) {
            throw new IllegalArgumentException("Entry time is required");
        }

        this.ticketNumber = ticketNumber;
        this.vehicleNo = vehicleNo;
        this.floor = floor;
        this.slot = slot;
        this.entryTime = entryTime;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public int getFloor() {
        return floor;
    }

    public int getSlot() {
        return slot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void displayTicket() {
        System.out.println("Ticket Number: " + ticketNumber);
        System.out.println("Vehicle Number: " + vehicleNo);
        System.out.println("Floor: " + floor);
        System.out.println("Slot: " + slot);
        System.out.println("Entry Time: " + entryTime);
    }

    public static void main(String[] args) {
        ParkingTicket t1 =
                new ParkingTicket("TN01AB1111", 0, 1);

        ParkingTicket t2 =
                new ParkingTicket("TN02CD2222", 1, 0);

        t1.displayTicket();
        System.out.println();
        t2.displayTicket();
    }
}