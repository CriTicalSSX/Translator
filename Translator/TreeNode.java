/**
 * Class for every word object. Tens of thousands of these are manipulated by the LanguageTree class.
 * 
 * @author Sam Glendenning
 */
public class TreeNode
{
    private float id;       //the ID of the word depends on whether it is present in the English tree or German tree (see stringToFloat on how this is created)
    private String engWord, gerWord;        //contain the actual String English and German words
    private TreeNode left, right;           //references to the TreeNode objects on the left and right hand sides of each TreeNode

    /**
     * Blank constructor, used when passing in values is not necessary
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
     * Most commonly-used constructor. Left and right are left as null because we haven't placed the node in the tree yet. These are set once that happens
     * 
     * @param id - the ID constructed by the program, used as a reference number for each word. Is a float because an integer is not big enough for long words
     * @param engWord - the String English version of the word
     * @param gerWord - the String German version of the word
     */
    public TreeNode(float id, String engWord, String gerWord)
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
     * @return id - the ID as an float
     */
    public float getID()
    {
        return id;
    }
    
    /**
     * Sets the ID contained in this node
     * 
     * @param id - the unique ID number of this word
     */
    public void setID(float id)
    {
        this.id = id;
    }
    
    /**
     * Gets the English word stored in the node
     * 
     * @return engWord - the English word stored in the node
     */
    public String getEngWord()
    {
        return engWord;
    }
    
    /**
     * Sets the English word stored in the node
     * 
     * @param engWord - the English word to be stored in the node
     */
    public void setEngWord(String engWord)
    {
        this.engWord = engWord;
    }
    
    /**
     * Gets the German word stored in the node
     * 
     * @return gerWord - the German word stored in the node
     */
    public String getGerWord()
    {
        return gerWord;
    }
    
    /**
     * Sets the German word to be stored in the node
     * 
     * @param gerWord - the German word stored in the node
     */
    public void setGerWord(String gerWord)
    {
        this.gerWord = gerWord;
    }
    
    /**
     * Fetches the node to the left of this one, used for tree traversal
     * 
     * @return left - the left child of the current node
     */
    public TreeNode getLeft()
    {
        return left;
    }
    
    /**
     * Sets the left child of this node to the one passed in, used during tree construction
     * 
     * @param left - the left child of the current node
     */
    public void setLeft(TreeNode left)
    {
        this.left = left;
    }
    
    /**
     * Fetches the node to the right of this one, used for tree traversal
     * 
     * @return right - the right child of the current node
     */
    public TreeNode getRight()
    {
        return right;
    }
    
    /**
     * Sets the right child of this node to the one passed in, used during tree construction
     * 
     * @param right - the right child of the current node
     */
    public void setRight(TreeNode right)
    {
        this.right = right;
    }
}
