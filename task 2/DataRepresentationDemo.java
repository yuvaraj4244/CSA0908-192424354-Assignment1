import java.util.Scanner;

public class DataRepresentationDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int slotNumber;
        String vehicleNumber;
        long entryTime;
        int duration;
        double fee;

        String[][] slots = {
            {"Car", "Bike", "Empty"},
            {"Empty", "EV", "Car", "Empty"},
            {"Bike", "Empty"}
        };

        System.out.print("Enter slot number: ");
        slotNumber = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter vehicle number: ");
        vehicleNumber = sc.nextLine();

        System.out.print("Enter parking duration in minutes: ");
        duration = sc.nextInt();

        int hours = (duration + 59) / 60;
        fee = hours * 30.0;

        System.out.println("\n--- Parking Details ---");
        System.out.println("Slot Number: " + slotNumber);
        System.out.println("Vehicle Number: " + vehicleNumber);
        System.out.println("Duration: " + duration + " minutes");
        System.out.println("Billable Hours: " + hours);
        System.out.println("Parking Fee: Rs " + fee);

        System.out.println("\n--- Floor Details ---");
        for (int i = 0; i < slots.length; i++) {
            System.out.println("Floor " + (i + 1) + ": "
                    + slots[i].length + " slots");
        }

        sc.close();
    }
}