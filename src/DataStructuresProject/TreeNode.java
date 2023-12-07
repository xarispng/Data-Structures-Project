package projectdatastructures;

public class TreeNode {
    protected int id;
    protected String hotelName;
    protected TreeNode left;
    protected TreeNode right;
    protected TreeNode father;
    //kokkino = 1 kai mauro = 0
    protected int colour = 1;

public TreeNode(int x, TreeNode f, String hn){
    id = x;
    hotelName = hn;
    left = right = null;
    father = f;
    colour = 1;
}    
   
public int getData(){ return id; }
public String getHotelName(){ return hotelName; }

public TreeNode getLeft(){ return left; }
public void setLeft(TreeNode l){ left = l; }

public TreeNode getRight(){ return right; }
public void setRight(TreeNode r){ right = r; }

public TreeNode getFather(){ return father; }
public void setFather(TreeNode f){ father = f; }

public int getColour(){ return colour; }
public void setColour(int x){ colour = x; }

}