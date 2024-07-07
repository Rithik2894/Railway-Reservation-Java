package railwayRerservation;


public class Passenger {
    private static int idProvider = 1;
    private int id;
    private String name;
    private int age;
    private char preference;
    private String ticketType;
    private int seatNumber;

    public Passenger() {
    }


    public Passenger(String name, int age, char preference) {
        this.id = idProvider++;
        this.age=age;
        this.name= name;
        this.preference=preference;

    }

    public int getid(){
        return id;
    }

    public void setid(int id){
        Passenger.idProvider=id;
    }

    public int getage(){
        return age;
    }

    public void setAge(int age){
        this.age=age;
    }
    public String getname(){
        return name;
    }
    public void setname(String name){
        this.name=name;
    }
    public char getpreference(){
        return preference;
    }
    public void setPreference(char preference){
        this.preference = preference;
    }
    public int getseatnumber(){
        return seatNumber;
    }
    public void setseatnumber(int seatnumber){
        this.seatNumber= seatnumber;
    }
    public String getTicketType(){
        return ticketType;
    }
    public void setTicketType(String ticketType){
        this.ticketType= ticketType;
    }


    public String toString(){
        return "Passenger Ticket id : "+id+
                "\nPassenger name : " + name +
                "\nPassenger age : " + age +
                "\nPassenger Seat no : "+seatNumber+
                "\nPassenger preference : " + preference;

    }




}


