/**
 * TreeNode class contains the specific fields for each node. The left and right fields are used to link the nodes together in the tree
 * 
 * @author Sam Glendenning
 * @author 
 */
public class TreeNode
{
    private int id;
    private String engWord, gerWord;
    private TreeNode left, right;

    public TreeNode(int id, String engWord, String gerWord)
    {
        this.id = id;
        this.engWord = engWord;
        this.gerWord = gerWord;
        this.left = null;
        this.right = null;
    }
    
    /**
     * Get the ID contained in this tree node. 
     * 
     * @return id - the ID as an integer
     */
    public int getID()
    {
        return id;
    }
    
    public void setID(int id)
    {
        this.id = id;
    }
    
    public String getEngWord()
    {
        return engWord;
    }
    
    public void setEngWord(String engWord)
    {
        this.engWord = engWord;
    }
    
    public String getGerWord()
    {
        return engWord;
    }
    
    public void setGerWord(String gerWord)
    {
        this.gerWord = gerWord;
    }
    
    public TreeNode getLeft()
    {
        return left;
    }
    
    public void setLeft(TreeNode left)
    {
        this.left = left;
    }
    
    public TreeNode getRight()
    {
        return right;
    }
    
    public void setRight(TreeNode right)
    {
        this.right = right;
    }
}
