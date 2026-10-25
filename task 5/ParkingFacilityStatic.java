import java.util.ArrayList;

class ParkingTicket {
private static int nextTicketNumber = 1001;
private final int ticketNumber;
private final String vehicleNo;

public ParkingTicket(String vehicleNo) {
    this.ticketNumber = nextTicketNumber++;
    this.vehicleNo = vehicleNo;
}

public int getTicketNumber() {
    return ticketNumber;
}

public String getVehicleNo() {
    return vehicleNo;
}
}

class ParkingFacilityStatic {
private final String facilityName;
private final ArrayList<ParkingTicket> tickets = new ArrayList<>();
private static int totalTicketsIssued = 0;

public ParkingFacilityStatic(String facilityName) {
    this.facilityName = facilityName;
}

public ParkingTicket issueTicket(String vehicleNo) {
    ParkingTicket ticket = new ParkingTicket(vehicleNo);
    tickets.add(ticket);
    totalTicketsIssued++;
    return ticket;
}

public static int getTotalTicketsIssued() {
    return totalTicketsIssued;
}

public void displayTicket(ParkingTicket ticket) {
    System.out.println("Facility: " + facilityName);
    System.out.println("Ticket Number: " + ticket.getTicketNumber());
    System.out.println("Vehicle Number: " + ticket.getVehicleNo());
}

public static void main(String[] args) {
    ParkingFacilityStatic north = new ParkingFacilityStatic("North Gate");
    ParkingFacilityStatic south = new ParkingFacilityStatic("South Gate");

    ParkingTicket t1 = north.issueTicket("TN01AB1111");
    ParkingTicket t2 = south.issueTicket("TN02CD2222");

    north.displayTicket(t1);
    System.out.println();

    south.displayTicket(t2);
    System.out.println();

    System.out.println("Total Tickets Issued: "
            + ParkingFacilityStatic.getTotalTicketsIssued());
}

}