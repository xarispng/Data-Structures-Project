package projectdatastructures;

import java.util.ArrayList;

public class TrieNode {
    protected char c;
    protected ArrayList<TrieNode> N;
    protected boolean isSurname;
    protected ArrayList<String> Hotels;
    
    public TrieNode(char ch){
        this.c = ch;
        this.isSurname = false;
        N = new ArrayList<>();
        Hotels = new ArrayList<>();
    }
    
    public char getCharacter() { return c; }
    public ArrayList<TrieNode> getLetters() { return N; }
    public boolean getIsSurname() { return isSurname; }
    public ArrayList<String> getHotels() { return Hotels; }
}