package projectdatastructures;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class List {
    private ArrayList<Hotel> H;
    
        public List(){
        H = new ArrayList<>();
    }
    
public ArrayList<Hotel> getHotels(){ return H;}

public void menu(){
        try {
            boolean check = false;
            while(!check){
            BufferedReader br= new BufferedReader(new InputStreamReader (System.in));
            System.out.println("-----------------------------------------");
            System.out.println("1. Load Hotels and Reservations from file");
            System.out.println("2. Save Hotels and Reservations to file ");
            System.out.println("3. Add a Hotel (with its reservations) ");
            System.out.println("4. Search and Display a Hotel by id ");
            System.out.println("5. Display all Hotels of specific stars category and number of reservations");
            System.out.println("6. Display Reservations or Hotels by surname search ");
            System.out.println("7. Exit");
            System.out.println("-----------------------------------------");
            System.out.println("Select number of option:");
            String line = br.readLine();
            int x = Integer.parseInt(line);
            if(x == 1){
                this.loadHotels();
            }
            else if(x == 2){
                this.saveHotels();
                System.out.println("-----------------------------------------");
                System.out.println("Hotels saved.");
            }
            else if(x == 3){
                boolean found = false;
                System.out.println("-----------------------------------------");
                System.out.println("Give hotel id:");
                String line1 = br.readLine();
                while(!found){
                    for(int i=0; i<H.size(); i++){
                        if(Integer.parseInt(line1) == H.get(i).getID()){
                            System.out.println("-----------------------------------------");
                            System.out.println("Existed id.");
                            found = true;
                        }
                    }
                    if(found){
                        System.out.println("-----------------------------------------");
                        System.out.println("Give another hotel id:");
                        String line5 = br.readLine();
                        line1 = line5;
                        found = false;
                    }
                    else break;
                }
                System.out.println("-----------------------------------------");
                System.out.println("Give hotel name:");
                String line2 = br.readLine();
                System.out.println("-----------------------------------------");
                System.out.println("Give hotel stars:");
                String line3 = br.readLine();
                System.out.println("-----------------------------------------");
                System.out.println("Give hotel number of rooms:");
                String line4 = br.readLine();
                this.add_a_Hotel(Integer.parseInt(line1), line2, Integer.parseInt(line3), Integer.parseInt(line4));
                System.out.println("-----------------------------------------");
                System.out.println("Hotel added.");
            }
            else if(x == 4){
                boolean check1 = false;
                int k = 0;
                while(!check1){
                    System.out.println("-----------------------------------------");
                    System.out.println("1. Linear search.");
                    System.out.println("2. Binary search.");
                    System.out.println("3. Interpolation search.");
                    System.out.println("4. Search using Red-Black Tree.");
                    System.out.println("-----------------------------------------");
                    System.out.println("Select type of search:");
                    String line6 = br.readLine();
                    int y = Integer.parseInt(line6);
                    if(y == 1 || y == 2 || y ==3){
                        System.out.println("-----------------------------------------");
                        System.out.println("Give hotel id:");
                        String line7 = br.readLine();
                        k = Integer.parseInt(line7);
                    }
                    else if(y == 4){
                        this.searchTree();
                        break;
                    }
                    if(y == 1){
                        System.out.println("-----------------------------------------");
                        this.search_ID(k);
                        break;
                    }
                    else if(y == 2){
                        System.out.println("-----------------------------------------");
                        this.binarySearch(k, 0, H.size());
                        break;
                    }
                    else if(y == 3){
                        System.out.println("-----------------------------------------");
                        this.interpolationSearch(k);
                        break;
                    }
                }
            }
            else if(x == 5){
                System.out.println("-----------------------------------------");
                System.out.println("Give hotel stars:");
                String line8 = br.readLine();
                System.out.println("-----------------------------------------");
                System.out.println("Give number of reservations:");
                String line9 = br.readLine();
                System.out.println("-----------------------------------------");
                this.search_Stars_NoOfReservations(Integer.parseInt(line8), Integer.parseInt(line9));
            }
            else if(x == 6){
                boolean check1 = false;
                while(!check1){
                    System.out.println("-----------------------------------------");
                    System.out.println("1. Search reservations.");
                    System.out.println("2. Search hotels using Trie.");
                    System.out.println("-----------------------------------------");
                    System.out.println("Select type of search:");
                    String line11 = br.readLine();
                    int y = Integer.parseInt(line11);
                    if(y == 1){
                        System.out.println("-----------------------------------------");
                        System.out.println("Give surname:");
                        String line10 = br.readLine();
                        System.out.println("-----------------------------------------");
                        this.search_Surname(line10);
                        break;
                    }
                    else if(y == 2){
                        this.searchTrie();
                        break;
                    }
                }
            }
            else if(x == 7){
                System.exit(0);
            }
            }   
        } catch (IOException ex) {
            System.out.println(ex);
        }
}
        
public void loadHotels(){
    BufferedReader br;
    try {
        br = new BufferedReader(new FileReader("data.csv"));
        String x;
        int a = 1;
        while ((x = br.readLine()) != null){
            if(a == 1){
                a++;
            }
            else{
                String[] myString;
                myString = x.split(";");
                Hotel myHotel = new Hotel(Integer.valueOf(myString[0]), myString[1], 
                Integer.valueOf(myString[2]), Integer.valueOf(myString[3]));
                H.add(myHotel);
                for(int i=4;  i<x.split(";").length; i=i+3){
                    myHotel.getReservations().add(new Reservation(Integer.valueOf(myString[0]), 
                    myString[1], Integer.valueOf(myString[2]), Integer.valueOf(myString[3]),
                    myString[i],  myString[i+1], Integer.valueOf(myString[i+2])));
                }
            }
        }
        System.out.println("-----------------------------------------");
        System.out.println("Hotels loaded.");
    }
    catch (FileNotFoundException ex){
        System.out.println("-----------------------------------------");
        System.out.println("There is no existing file.");
    } 
    catch (IOException ex){
        System.out.println(ex);
    }
}
    
public void saveHotels(){
        try (FileWriter myFile = new FileWriter(new File("data.csv"))) {
            for(int i=0; i<H.size()+1; i++){
                if(i == 0){
                    myFile.write(H.size() + ";");
                }
                else{
                    int k = i - 1;
                    myFile.write(H.get(k).getID() + ";" + H.get(k).getName() + ";" + H.get(k).getStars() + 
                    ";" + H.get(k).getNumberOfRooms());
                    for(int j=0; j<H.get(k).getReservations().size(); j++){
                        myFile.write( ";" + H.get(k).getReservations().get(j).getNameOfReservation() + ";" + 
                        H.get(k).getReservations().get(j).getCheckinDate() + ";" + 
                        H.get(k).getReservations().get(j).getStayDurationDays());
                    }
                }
                myFile.write("\r\n");
            }
        } 
        catch (IOException ex) {
            System.out.println(ex);
        }
    }

public void add_a_Hotel(int id, String name, int stars, int numberOfRooms){
    try {
        BufferedReader br= new BufferedReader(new InputStreamReader (System.in));
        Hotel myHotel = new Hotel(id, name, stars, numberOfRooms);
        H.add(myHotel);
        System.out.println("How many reservations for hotel " + name + "?");
        String line = br.readLine();
        int x = Integer.parseInt(line);
        for(int i=0; i<x; i++){
            System.out.println("Give the name of the reservation no" + (i+1) + " :");
            String line1 = br.readLine();
            System.out.println("Give the checkinDate no" + (i+1) + " :");
            String line2 = br.readLine();
            System.out.println("Give the stayDurationDays no" + (i+1) + " :");
            String line3 = br.readLine();
            int y = Integer.parseInt(line);
            myHotel.getReservations().add(new Reservation(id, name, stars, numberOfRooms, line1, line2 ,y));
        }
        this.bubbleSort();
    } 
    catch (IOException ex){
        System.out.println(ex);
    }
    
}

public void search_ID(int id){
    long startTime = System.nanoTime();
    boolean found = false;
    for(int i=0; i<H.size(); i++){
        if(id == H.get(i).getID()){
            System.out.println(H.get(i).getName());
            found = true;
            break;
        }
    }
    if(!found) System.out.println("Not found");
    else{
        long estimatedTime = System.nanoTime() - startTime;
        double mseconds = (double)estimatedTime / 1000000.0;
        System.out.println("Elapsed time of method search_ID is: " + mseconds + " milliseconds.");
    }
}

public void search_Stars_NoOfReservations(int stars, int nor){
    long startTime = System.nanoTime();
    boolean found = false;
    for(int i=0; i<H.size(); i++){
        if(stars == H.get(i).getStars() && nor == H.get(i).getReservations().size()){
            System.out.println(H.get(i).getName());
            found = true;
        }
    }
    if(!found) System.out.println("Not found");
    long estimatedTime = System.nanoTime() - startTime;
    double mseconds = (double)estimatedTime / 1000000.0;
    System.out.println("Elapsed time of method search_Stars_NoOfReservations is: " + mseconds + " milliseconds.");
}

public void search_Surname(String srn){
    long startTime = System.nanoTime();
    boolean found = false;    
    int count = 0;
    for(int i=0; i<H.size(); i++){
        for(int j=0; j<H.get(i).getReservations().size(); j++){
            if(srn.equals(H.get(i).getReservations().get(j).getNameOfReservation())){
                count++;
                found = true;  
            }
        }
    }
    if(!found) System.out.println("Not found");
    else{
        System.out.println(count + " reservations.");
        long estimatedTime = System.nanoTime() - startTime;
        double mseconds = (double)estimatedTime / 1000000.0;
        System.out.println("Elapsed time of method search_Surname is: " + mseconds + " milliseconds.");
    }
}

public void binarySearch(int id, int start, int end){
    long startTime = System.nanoTime();
    while(end > start){
        int i = (start + end)/2;
        if(id == H.get(i).getID()){
            System.out.println(H.get(i).getName());
            long estimatedTime = System.nanoTime() - startTime;
            double mseconds = (double)estimatedTime / 1000000.0;
            System.out.println("Elapsed time of method binarySearch is: " + mseconds + " milliseconds.");
            break;
        }
        else if(id < H.get(i).getID() ){ end = i; } 
        else if(id > H.get(i).getID()){ start = i; }
        //Ean exei perasei arketos xronos den brethike
        if((System.nanoTime() - startTime) > 2000000000){
            System.out.println("Not found");
            break;
        }
   }
}

public void interpolationSearch(int id){
    long startTime = System.nanoTime();
    boolean found = false;
    int low = 0;
    int high=H.size()-1;
    int mid = 0;
    while((id <= H.get(high).getID()) && 
        (id >= H.get(low).getID()) && 
        (H.get(high).getID() != H.get(low).getID())){
        
        mid = low + ((id - H.get(low).getID())*(high - low)/
        (H.get(high).getID() - H.get(low).getID()));
        
        if(H.get(mid).getID() < id){ low = mid+1; }
        else if(H.get(mid).getID() > id){ high = mid-1; }
        else{
            System.out.println(H.get(mid).getName());
            long estimatedTime = System.nanoTime() - startTime;
            double mseconds = (double)estimatedTime / 1000000.0;
            found = true;
            System.out.println("Elapsed time of method interpolationSearch is: " + mseconds + " milliseconds.");
            break;
        }
    }
    if(!found) System.out.println("Not found");
}

public void bubbleSort(){
    int n = H.size();
    for(int i=0; i<n; i++){
        for(int j=1; j<(n-i); j++){
            if(H.get(j-1).getID() > H.get(j).getID()){
                Collections.swap(H, j-1, j);
            }
        }
    }
}

public void searchTree(){
  try {
    BufferedReader br= new BufferedReader(new InputStreamReader (System.in));
    Tree T = new Tree(H.get(0).getID(), H.get(0).getName());
    for(int i=1; i<H.size(); i++){
        boolean found = false;
        TreeNode r = T.getRoot();
        while(!found){
            if(H.get(i).getID() > r.getData()){
                if(r.getRight() == null){
                    T.insertRight(r, H.get(i).getID(), H.get(i).getName());
                    found = true;
                }
                else r = r.getRight();
            }
        }
    }          

    System.out.println("-----------------------------------------");
    System.out.println("Red Black Tree Created.");
    System.out.println("-----------------------------------------");

    System.out.println("Give hotel id:");
    String line = br.readLine();
    int k = Integer.parseInt(line);
    long startTime = System.nanoTime();
    boolean found1 = false;
    for(int i=0; i<H.size(); i++){
        TreeNode r = T.getRoot();
        while(!found1){
            if(k == r.getData()){
                found1 = true;
                long estimatedTime = System.nanoTime() - startTime;
                double mseconds = (double)estimatedTime / 1000000.0;
                System.out.println("-----------------------------------------");
                System.out.println(r.getHotelName());
                System.out.println("Elapsed time of method RBT_Search is: " + mseconds + " milliseconds.");
                break;
            }
            else if(k > r.getData() && r.getRight()!= null){
                r = r.getRight();
            }
            else if(r.getLeft()!=null){
                r = r.getLeft();
            }
            else break;
        }
    }
    if(!found1){
        System.out.println("-----------------------------------------");
        System.out.println("Not found.");
    }

    } catch (IOException ex) {
        System.out.println(ex);
    }
}

public void searchTrie(){
        try {
            Trie T = new Trie();
            for(int i=0; i<H.size(); i++){
                for(int j=0; j<H.get(i).getReservations().size(); j++){
                    T.add(H.get(i).getReservations().get(j).getNameOfReservation(), H.get(i).getName());
                }
            }
            System.out.println("-----------------------------------------");
            System.out.println("Trie Created.");
            System.out.println("-----------------------------------------");
            
            BufferedReader br= new BufferedReader(new InputStreamReader (System.in));
            System.out.println("Give surname:");
            String line = br.readLine();
            long startTime = System.nanoTime();
            boolean found = false;
            TrieNode r = T.getRoot();
            while(!line.isEmpty()){
                int pos = 0;
                boolean found1 = false;
                char letter = line.charAt(0);
                for(int i=0; i<r.getLetters().size(); i++){
                    if(r.getLetters().get(i).getCharacter() == letter){
                        found1 = true;
                        pos = i;
                    }
                }
                if(found1){
                    r = r.getLetters().get(pos);
                }
                else{
                    System.out.println("-----------------------------------------");
                    System.out.println("Not found.");
                    break;
                }
                line = line.substring(1);
                if(line.isEmpty()){
                    long estimatedTime = System.nanoTime() - startTime;
                    double mseconds = (double)estimatedTime / 1000000.0;
                    System.out.println("-----------------------------------------");
                    for(int i=0; i<r.getHotels().size(); i++){
                        System.out.println(r.getHotels().get(i));
                    }
                    System.out.println("Elapsed time of method Trie_Search is: " + mseconds + " milliseconds.");
                }
            }
        
        } catch (IOException ex) {
            System.out.println(ex);
        }
}

}