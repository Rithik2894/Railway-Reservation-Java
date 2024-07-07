package railwayRerservation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        for (; ; ) {

            System.out.println("\nChoose a Option\n" +
                    "1.Book Ticket\n" +
                    "2.Cancel Ticket\n" +
                    "3.Display Confirmed\n" +
                    "4.Display RAC\n" +
                    "5.Display Waiting List\n");

            Scanner s = new Scanner(System.in);
            System.out.println("Enter Your Number : ");
            int n = s.nextInt();

            switch (n) {

                case 1: {
                    System.out.println("Enter name : ");
                    String name = s.next();
                    System.out.println("Enter age : ");
                    int age = s.nextInt();
                    System.out.println("Enter berth : ");
                    char preference = Character.toUpperCase(s.next().charAt(0));
                    if (preference == 'U' || preference == 'M' || preference == 'L')
                        TicketBooking.bookTicket(new Passenger(name, age, preference));
                    else
                        System.out.println("Invalid berth");
                    break;
                }

                case 2: {
                    System.out.println("Enter your Ticket id : ");
                    int id = s.nextInt();
                    System.out.println(TicketCanceling.canceling(id));
                    break;
                }

                case 3: {
                    TicketBooking.displayConfirmed();
                    break;
                }

                case 4: {
                    TicketBooking.displayRAC();
                    break;
                }

                case 5: {
                    TicketBooking.displayWaiting();
                    break;
                }

                case 6: {
                    System.out.println("\tThank you!");
                    s.close();
                    break;
                }
            }
        }
    }
}