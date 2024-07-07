package railwayRerservation;

import java.util.*;
import java.util.Map.Entry;

public class TicketBooking {

    public static int berthLimit = 2;
    public static int racLimit = 1;
    public static int waitingListLimit = 1;

    //Seats
    public static int upperSeatNumber = 1;
    public static int middleSeatNumber = 2;
    public static int lowerSeatNumber = 3;

    //Lists
    public static ArrayList<Passenger> confirmedList = new ArrayList<Passenger>();
    public static Queue<Passenger> racQueue = new LinkedList<Passenger>();
    public static Queue<Passenger> waitingQueue = new LinkedList<Passenger>();

    public static ArrayList<Passenger> upperList = new ArrayList<Passenger>();
    public static ArrayList<Passenger> middleList = new ArrayList<Passenger>();
    public static ArrayList<Passenger> lowerList = new ArrayList<Passenger>();


    public static void bookTicket(Passenger p) {

        if (upperList.size() == berthLimit && lowerList.size() == berthLimit && middleList.size() == berthLimit) {

            if (updateRacQueue(p)) {
                System.out.println("Your Ticket is Booked as RAC");
            } else if (updateWaitingQueue(p)) {
                System.out.println("Your Ticket is added to Waiting list");
            } else {
                p.setid(p.getid() - 1);
                System.out.println("Sorry, NO Trip for you");
            }

        } else if (checkAvalability(p)) {
            System.out.println("Booking confirmed\n" + "Your Ticket ID" + p.getid());
            p.setTicketType("berth");
            confirmedList.add(p);
        } else {
            System.out.println("Preferred seats no available");
            p.setid(p.getid() - 1);
            availableList(p);
        }

    }


    public static boolean checkAvalability(Passenger p) {

        Map<Integer, Character> map = TicketCanceling.getSeatNumberWithBerth();

        if (p.getpreference() == 'U') {
            if (upperList.size() < berthLimit) {
                if (!map.isEmpty()) {

                    getseatdetails(map, p);

                } else {
                    p.setseatnumber(upperSeatNumber);
                    upperSeatNumber += 3;
                }

            }
            upperList.add(p);
            return true;

        }
        if (p.getpreference() == 'M') {
            if (lowerList.size() < berthLimit) {
                if (!map.isEmpty()) {
                    getseatdetails(map, p);
                } else {
                    p.setseatnumber(middleSeatNumber);
                    middleSeatNumber += 3;
                }
                middleList.add(p);
                return true;

            }
        }
        if (p.getpreference() == 'L') {
            if (lowerList.size() < berthLimit) {
                if (!map.isEmpty()) {
                    getseatdetails(map, p);
                } else {
                    p.setseatnumber(lowerSeatNumber);
                    lowerSeatNumber += 3;
                }
                lowerList.add(p);
                return true;
            }
        }


        return false;

    }

    public static void getseatdetails(Map<Integer, Character> map, Passenger p) {
        int seatnumber = checkForPreferenceAvailability(map, p.getpreference());
        p.setseatnumber(seatnumber);
        map.remove(seatnumber);
    }

    public static int checkForPreferenceAvailability(Map<Integer, Character> map, char preference) {

        int seatnumber = 0;

        for (Entry<Integer, Character> entry : map.entrySet()) {
            if (preference == entry.getValue()) {
                seatnumber = entry.getKey();
                break;
            }
        }
        return seatnumber;
    }

    public static boolean updateRacQueue(Passenger p) {
        if (racQueue.size() < racLimit) {
            p.setTicketType("RAC");
            racQueue.add(p);
            return true;
        }
        return false;
    }

    public static boolean updateWaitingQueue(Passenger p) {
        if (waitingQueue.size() < waitingListLimit) {
            p.setTicketType("Waiting list");
            waitingQueue.add(p);
            return true;
        }
        return false;
    }

    public static void availableList(Passenger p) {
        int AvailUpper = berthLimit - upperList.size();
        int Availmiddle = berthLimit - middleList.size();
        int Availlower = berthLimit - lowerList.size();
        System.out.println("Check available tickets");
        System.out.println("For Upper Berth" + AvailUpper + "seats Available");
        System.out.println("For Upper Berth" + Availmiddle + "seats Available");
        System.out.println("For Upper Berth" + Availlower + "seats Available");
    }

    public static void displayConfirmed() {
        System.out.println("-------------------------");
        for (Passenger p : confirmedList) {
            System.out.println(p.toString());
            System.out.println("-------------------------");
        }
    }

    public static void displayRAC() {
        System.out.println("-------------------------");
        for (Passenger p : racQueue) {
            System.out.println(p.toString());
            System.out.println("-------------------------");
        }
    }

    public static void displayWaiting() {
        System.out.println("-------------------------");
        for (Passenger p : waitingQueue) {
            System.out.println(p.toString());
            System.out.println("-------------------------");
        }
    }
}

