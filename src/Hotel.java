package projectdatastructures;

import java.util.ArrayList;

public class Hotel extends List{
    private int id;
    private String name;
    private int stars;
    private int numberOfRooms;
    private ArrayList<Reservation> R;
    
    public int getID(){ return id; }
    public String getName(){ return name; }
    public void setName(String str) { name = str; }
    public int getStars(){ return stars; }
    public int getNumberOfRooms(){ return numberOfRooms; }
    public ArrayList<Reservation> getReservations(){ return R;}
    
    public Hotel(int givenId , String nm, int st, int nor){
        id = givenId;
        name = nm;
        stars = st;
        numberOfRooms = nor;
        R = new ArrayList<>();
    }   
}