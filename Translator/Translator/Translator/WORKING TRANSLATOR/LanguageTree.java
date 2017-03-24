import java.io.IOException;
import java.io.PrintWriter;

/**
 * Two instances of the LanguageTree class are created, one for the English binary tree and one for the German binary tree.
 * Both are used to manipulate tens of thousands of their own TreeNode objects; one for each word in the dictionary text files. 
 * This is an efficient data structure to use when searching through tens of thousands of objects because it only requires at most two dozen searches to find the word.
 * 
 * @author Sam Glendenning
 * @author Will Lockett
 * @author Danyal Ali
 */
public class LanguageTree
{
    private TreeNode root;      //holds the first node in the tree, may be changed if the root is removed

    boolean dictionariesDone = false;       //prevents a bug when the program is first opened. Changes to true when the dictionaries have been read in

    /**
     * Built by SG
     * 
     * Used to return the root of the tree
     * 
     * @return root - the root of the current tree
     */
    public TreeNode getRoot()
    {
        return root;
    }

    /**
     * Built by SG
     * 
     * Used to set the root of the tree to a new node. 
     * 
     * @param newRoot - the TreeNode object to become the new root
     */
    public void setRoot(TreeNode newRoot)
    {
        root = newRoot;
    }

    /**
     * Built by WL
     * 
     * Adds a new node to the tree
     * 
     * @param id The unique id of the english word
     * @param engWord The english word 
     * @param gerWord The germal translation of the english word
     */
    public void addToTree(float id, String engWord, String gerWord)
    {
        //creates a new node
        TreeNode newNode = new TreeNode(id, engWord, gerWord);

        if (root == null)
        {
            setRoot(newNode);       //if the tree is empty, set this node as the root
        }
        else
        {
            TreeNode previous = new TreeNode();
            previous = root;    //keeps track of the node in the layer above the one being evaluated in the following code

            TreeNode current = new TreeNode();
            current = root;     //the node that the user's new node is being compared to

            while (current != null)
            {
                previous = current;

                if (newNode.getID() < previous.getID())     //if the new ID is less than the value of the one being compared to
                {
                    if (previous.getLeft() != null)         //if there is not a space in the tree to the left
                    {
                        current = previous.getLeft();       //set the current node to that node in preparation for the next iteration of the loop (next comparison)               
                    }         
                    else    //if there is a space to the left of the node
                    {
                        previous.setLeft(newNode);      //set that node's left to the new node
                        current = null;                 //no more comparisons necessary, current = null so that the loop exits
                    }
                }
                else if (newNode.getID() > previous.getID())    //if the new ID is greater than the value of the one being compared to
                {
                    if (previous.getRight() != null)            //if there is not a space in the tree to the right
                    {
                        current = previous.getRight();          //set the current node to that node in preparation for the next iteration of the loop (next comparison)   
                    }
                    else    //if there is a space to the right of the node
                    {
                        previous.setRight(newNode);     //set that node's right to the new node
                        current = null;                 //no more comparisons necessary, current = null so that the loop exits
                    }
                }
                else if (newNode.getID() == previous.getID())       //if the current node's ID equals the ID of the new node
                {
                    if (dictionariesDone == true)
                    {
                        System.out.println("This word already exists in the dictionary.");       //cannot add a duplicate node to the tree
                    }

                    current = null;                                                     //no more comparisons necessary, current = null so that the loop exits
                }
            }
        }
    }

    /**
     * Built by DA
     * 
     * Method for finding the translation of the word we already have. Contains a while loop that runs until the word is found or we reach the place where the word
     * should be and it is not there.
     *
     * @param find - the ID number of the word being searched for
     * @param language - an integer value of 1 or 2, used to determine which language of the word we wish to extract
     * @return the translation of the word we already have
     */
    public String findNode(float find, int language)
    {
        if (root.getID() == find)        //if the current ID matches the user's search
        {
            if (language == 1)          //if we have the English word
            {
                return root.getGerWord();     //return the German word
            }
            else
            {
                return root.getEngWord();       //vice versa
            }
        }   
        else
        {
            TreeNode current = null;
            current = root;
            boolean found = false;

            while (current != null && found == false) {     //while the current node we are evaluating is not null and the correct node hasn't been found
                if (find == current.getID()) {      //if the ID of the word we have matches the current node's ID

                    found = true;       //we have found the correct node

                } else {
                    if (find < current.getID() && current.getLeft() != null) {      //if the ID of the word is less than the current node's ID
                        current = current.getLeft();                              //set the current node to the current node's left, run loop again
                    } else if (find > current.getID() && current.getRight() != null) {      //if the ID of the word is greater than the current node's ID
                        current = current.getRight();                             //set the current node to the current node's right, run loop again
                    } else {
                        found = true;        
                    }
                }
            }

            if (found == true) {
                if (language == 1)
                {
                    return current.getGerWord();        //as above
                }
                else
                {
                    return current.getEngWord();
                }           
            } else {
                return null;            //the word has not been found, return null value indicating this
            }  
        }        
    }  
}

