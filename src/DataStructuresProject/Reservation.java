package projectdatastructures;

class Reservation extends Hotel {
  private String name;
  private String checkinDate;
  private int stayDurationDays;
  
  public String getNameOfReservation(){ return name; }
  public String getCheckinDate(){ return checkinDate; }
  public int getStayDurationDays(){ return stayDurationDays; }
  

  public Reservation(int givenId, String nm, int st, int nor, String nmr, String cid, int sdd) {
        super(givenId, nm, st, nor);
        name = nmr;
        checkinDate = cid;
        stayDurationDays = sdd;
    }
}