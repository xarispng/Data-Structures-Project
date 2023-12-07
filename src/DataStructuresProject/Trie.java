package projectdatastructures;

public class Trie {
    protected TrieNode root;
    
    public Trie(){ 
        root = new TrieNode('0'); 
    }
    
    public TrieNode getRoot(){ return root; }
    
    public void add(String s, String h){
        TrieNode node = root;
        while(!s.isEmpty()){
            int pos = 0;
            boolean found = false;
            char letter = s.charAt(0);
            for(int i=0; i<node.getLetters().size(); i++){
                if(node.getLetters().get(i).getCharacter() == letter){
                    found = true;
                    pos = i;
                }
            }
            if(found){
                node = node.getLetters().get(pos);
            }
            else{
                TrieNode n = new TrieNode(letter);
                node.getLetters().add(n);
                node = n;
            }
            s = s.substring(1);
            if(s.isEmpty()){
                node.getHotels().add(h);
            }
        }
        
    }
}