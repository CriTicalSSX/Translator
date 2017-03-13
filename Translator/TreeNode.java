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

    /**
     * default constrctor for tree node objects
     */
    public TreeNode()
    {
        id = 0;
        engWord = "";
        gerWord = "";
        left = null;
        right = null;
    }
    
    /**
     * alternate constructor for tree node objects that fills in fields according to users input
     */
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
    
    /**
     * Sets the ID contained in this node
     * 
     * @param id The unique ID of the word
     */
    public void setID(int id)
    {
        this.id = id;
    }
    
    /**
     * Gets the english word stored in the node
     * 
     * @return engWord The english word stored in the node
     */
    public String getEngWord()
    {
        return engWord;
    }
    
    /**
     * Sets the english word stored in the node
     * 
     * @param engWord The english word to be stored in the node
     */
    public void setEngWord(String engWord)
    {
        this.engWord = engWord;
    }
    
    /**
     * Gets the german word stored in the node
     * 
     * @return gerWord The german word stored in the node
     */
    public String getGerWord()
    {
        return engWord;
    }
    
    /**
     * Sets the german word to be stored in the node
     * 
     * @param gerWord The german word stored in the node
     */
    public void setGerWord(String gerWord)
    {
        this.gerWord = gerWord;
    }
    
    /**
     * Gets the memory adress of the left child of the node
     * 
     * @return left The left child of the current node
     */
    public TreeNode getLeft()
    {
        return left;
    }
    
    /**
     * Sets the memory adress of the left child of the node
     * 
     * @param left The left child of the current node
     */
    public void setLeft(TreeNode left)
    {
        this.left = left;
    }
    
    /**
     * Gets the memory adress of the right child of the node
     * 
     * @return right The right child of the current node
     */
    public TreeNode getRight()
    {
        return right;
    }
    
    /**
     * Sets the memory adress of the right child of the node
     * 
     * @param right The right child of the current node
     */
    public void setRight(TreeNode right)
    {
        this.right = right;
    }
}
