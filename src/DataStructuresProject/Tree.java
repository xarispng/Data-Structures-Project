package projectdatastructures;

public class Tree {
    protected TreeNode root;
    
    public Tree(int x, String hn){ 
        root = new TreeNode(x, null, hn); 
        this.RBT_case1(root);
    }
    
    public TreeNode getRoot(){ return root; }
    
//  Efoson taksinomw ta stoixeia me bash to id(apo to mikrotero sto megalitero)
//  den ifistatai logos na uparxei eisagwgi apo ta aristera afou kathe kainourgios kombos
//  tha exei megalitero id apo olous tous proigoumenous kai ara tha eisagetai terma deksia
//  sto dentro. Auto sinepagetai me to oti den tha uparxei kai diplh peristrofh afou kathe
//  neos kombos tha einai deksi paidi deksiou paidiou.
    public void insertRight(TreeNode f, int x, String hn){
        if((f != null) && (f.getRight()== null)){
            TreeNode n = new TreeNode(x, f, hn);
            f.setRight(n);
            //metatrepw to dentro se red-black
            this.RBT_case1(n);
        }
    }
    
    public TreeNode getGrandparent(TreeNode n){
    if((n != null) && (n.getFather() != null))
        return n.getFather().getFather();
    else return null;
    }
    public TreeNode getUncle(TreeNode n){
    TreeNode g = getGrandparent(n);
    if(g == null) return null;
    if (n.getFather() == g.getLeft()) return g.getRight();
    else return g.getLeft();
    }
    
    public void RBT_case1(TreeNode n){
        if(n.getFather() == null){
            n.setColour(0);
        }
        else this.RBT_case2(n);
    }
    public void RBT_case2(TreeNode n){
        if(n.getFather().getColour() == 0)
            return;
        else this.RBT_case3(n);
    }
    public void RBT_case3(TreeNode n){
        TreeNode u = getUncle(n);
        if((u != null) && (u.getColour() == 1)){
            n.getFather().setColour(0);
            u.setColour(0);
            TreeNode g = getGrandparent(n);
            g.setColour(1);
            RBT_case1(g);
        }
        else {
            this.RBT_case4(n);
        }
    }
    public void RBT_case4(TreeNode n){
        TreeNode g = getGrandparent(n);
        n.getFather().setColour(0);
        g.setColour(1);
        if(n == n.getFather().getRight()){
            //rotateLeft();
            if(n.getFather().getLeft() == null){
                TreeNode tempf = n.getFather();

                tempf.setLeft(g);

                tempf.setFather(g.getFather());
                g.setRight(null);
                g.setFather(tempf);
                n.setFather(tempf);
                if(tempf.getFather() == null){
                    root = tempf;      
                }
                else{
                    TreeNode g1 = getGrandparent(n);
                    g1.setRight(tempf);
                    
                }
                if(tempf.getFather() != null){
                  if(tempf.getFather().getColour() == 0){
                        this.RBT_case1(tempf);
                    }  
                }      
            }
            else{
                TreeNode tree3 = n.getFather().getLeft();
                TreeNode tempf = n.getFather();

                g.setRight(tree3);
                tempf.setLeft(g);

                tempf.setFather(g.getFather());
                g.setFather(tempf);
                n.setFather(tempf);
                tree3.setFather(g);
                if(tempf.getFather() == null){
                    root = tempf;      
                }
                else{
                    TreeNode g1 = getGrandparent(n);
                    g1.setRight(tempf);
                }
                if(tempf.getFather() != null){
                  if(tempf.getFather().getColour() == 0){
                        this.RBT_case1(tempf);
                    }  
                }      
            }
        }
    }
}