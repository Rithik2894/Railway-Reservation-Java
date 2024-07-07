package railwayRerservation;

import java.util.*;

public class TicketCanceling extends TicketBooking {

    private static char preferenceTracker = '\0';
    private static int canceledSeatNumber = 0;

    private static HashMap<Integer,Character> seatNumberWithBerth = new HashMap<Integer,Character>();

    public static String canceling(int id){

        for(Passenger p: confirmedList){
            if(p.getid()==id){
                cancel(p);
                return "success";

            }
        }
        for(Passenger p: racQueue){
            if(p.getid()==id){
                cancel(p);
                return "success";

            }
        }
        for(Passenger p: waitingQueue){
            if(p.getid()==id){
                cancel(p);
                return "success";

            }
        }
        return "Invalid ID";
    }

    public static void cancel(Passenger p){

        if(p.getTicketType().equals("Berth")){

            preferenceTracker = p.getpreference();
            canceledSeatNumber= p.getseatnumber();

            seatNumberWithBerth.put(canceledSeatNumber,preferenceTracker);

            removeFromAllLists(p);
            racToBerth(racQueue.poll());
            waitingListToRAC(waitingQueue.poll());

        }
        else if(p.getTicketType().equals("RAC")){

            racQueue.remove(p);
            waitingListToRAC(waitingQueue.poll());

        }
        else{
            waitingListToRAC(waitingQueue.poll());
        }


    }
    public static void racToBerth(Passenger p){

        if(p!=null){
            p.setPreference(preferenceTracker);
            p.setseatnumber(canceledSeatNumber);
            p.setTicketType("Berth");

            if (preferenceTracker=='U'){
                upperList.add(p);

            }else if (preferenceTracker=='M'){
                    middleList.add(p);
            }else {

                lowerList.add(p);
            }

            confirmedList.add(p);
            seatNumberWithBerth.remove(canceledSeatNumber);
            preferenceTracker = '\0';
            canceledSeatNumber = 0;

        }
    }

    public static void waitingListToRAC(Passenger p){

        racQueue.add(p);
        waitingQueue.remove(p);

    }

    public static void removeFromAllLists(Passenger p){

        confirmedList.remove(p);
        upperList.remove(p);
        lowerList.remove(p);
        middleList.remove(p);

    }
    public static Map<Integer, Character> getSeatNumberWithBerth() {
        return seatNumberWithBerth;
    }


}
