import java.util.ArrayList;
import java.util.Scanner;

class TicketCounter{
    private int availableSeats=10;
    public synchronized void bookTicket(String name,int seats){
        if(availableSeats>=seats && availableSeats>0){
            System.out.println("Hi "+name+" Your "+seats+"ticket is successfully booked");
            availableSeats=availableSeats-seats;
            System.out.println("Available seats are : "+availableSeats);
        }else{
            System.out.println("Sorry "+name+"Only "+availableSeats+" eats are available");
        }
    }
}
class TicketBookingThread extends Thread{
    private TicketCounter tc;
    private String name;
    private int seats;
    TicketBookingThread(TicketCounter tc,String name,int seats){
        this.tc=tc;
        this.name=name;
        this.seats=seats;
    }

    public void run(){
             tc.bookTicket(name,seats);
    }
}
class SeatReservation {
    public static void main(String[] args){
        System.out.println("Total seats available are 10");
        Scanner sc = new Scanner(System.in);
        TicketCounter tc = new TicketCounter();
        System.out.println("Enter Number of person who are trying to book tickets: ");
        int n=sc.nextInt();
        ArrayList<TicketBookingThread> arr = new ArrayList<>();
        for(int i=0;i<n;i++){
            System.out.println(i+1+"person name :");
            sc.nextLine();
            String name=sc.nextLine();
            System.out.println("Enter no of seats to book");
            int seats=sc.nextInt();
            arr.add(new TicketBookingThread(tc,name,seats));
        }
        for(int i=0;i<n;i++){
            arr.get(i).start();
        }
    }
}
