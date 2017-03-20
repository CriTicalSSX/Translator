import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;

/**
 * Write a description of class LanguageTree here.
 * 
 * @author Sam Glendenning
 * @author Will Lockett
 * @author Danyal Ali
 */
public class LanguageTree
{
    private TreeNode root;

    /**
     * Built by SG
     * 
     * Used to return the root of the English tree (called from other classes)
     */
    public TreeNode getRoot()
    {
        return root;
    }

    /**
     * Built by SG
     * 
     * Used to set the root of the English tree to a new node
     */
    public void setRoot(TreeNode newRoot)
    {
        root = newRoot;
    }

    /**
     * Built by SG
     * 
     * Used to remove a node from the tree.
     * 
     * @param subRoot - the root of the subtree currently being evaluated. Starts as the root of the tree, and changes as the program traverses left and right
     * @param searchID - the ID of the node the user wants to delete
     */
    public void removeFromTree(TreeNode subRoot, float searchID)
    {
        if (subRoot.getID() == searchID)    //if the root of the tree matches the search ID, extra steps have to be taken to replace the root
        {
            if (subRoot.getLeft() != null)     //if there is a node to the left of the root
            {
                TreeNode biggestLeftNode = new TreeNode();      
                TreeNode leftOfRoot = new TreeNode();
                TreeNode rightOfRoot = new TreeNode();

                biggestLeftNode = getHighestLeft(subRoot.getLeft());    //method retrieves the node with the largest ID number on the left hand side of the tree

                leftOfRoot = root.getLeft();    //stores the left node of the root, otherwise its reference would be lost when the root is deleted
                rightOfRoot = root.getRight();  //stores the right node of the root

                setRoot(biggestLeftNode);        //sets the largest left node as the new root
                root.setLeft(leftOfRoot);        //installs its left node as the previous left node of the root
                root.setRight(rightOfRoot);      //installs its right node as the previous right node of the root
            }
            else if (subRoot.getRight() != null)    //no node to the left of the root, so the right must be investigated
            {
                TreeNode smallestRightNode = new TreeNode();
                TreeNode leftOfRoot = new TreeNode();
                TreeNode rightOfRoot = new TreeNode();

                smallestRightNode = getSmallestRight(subRoot.getRight());   //method retrieves the node with the smallest ID number on the right hand side of the tree

                leftOfRoot = root.getLeft();
                rightOfRoot = root.getRight();

                setRoot(smallestRightNode);     //sets the smallest right node as the new root

                if (leftOfRoot == null)
                {
                    root.setLeft(null);
                }
                else
                {
                    root.setLeft(leftOfRoot);
                }

                if (rightOfRoot == null)
                {
                    root.setRight(null);
                }
                else
                {
                    root.setRight(rightOfRoot);
                }
            }
            else
            {
                setRoot(null);      //if the root was the only node in the tree, it can be safely deleted
            }
        }
        else
        {
            if (subRoot.getLeft() != null)
            {
                if (subRoot.getLeft().getID() == searchID)  //if the left node of the subroot's ID equals the search ID
                {
                    if (subRoot.getLeft().getLeft() == null && subRoot.getLeft().getRight() == null)    //if this node has no children
                    {
                        subRoot.setLeft(null);      //the node can be safely deleted
                    }
                    else if (subRoot.getLeft().getLeft() != null || subRoot.getLeft().getRight() != null)   //if this node has one child
                    {
                        if (subRoot.getLeft().getRight() != null)   //if the node has a right child
                        {
                            subRoot.setLeft(subRoot.getLeft().getRight());      //set the subroot's left to the left node's right to join up the tree 
                        }
                        else    //if this node has a left child
                        {
                            subRoot.setLeft(subRoot.getLeft().getLeft());       //sets the subroot's left to the left node's left to join up the tree
                        } 
                    }
                    else if (subRoot.getLeft().getLeft() != null && subRoot.getLeft().getRight() != null)    //if this node has two children
                    {
                        TreeNode leftNode = new TreeNode();
                        leftNode = subRoot.getLeft().getLeft();      //node to store the left node's own left so that its reference isn't lost upon right node deletion

                        subRoot.setLeft(subRoot.getLeft().getRight());     //sets the subroot's left to the left node's right, as this will link up the tree correctly

                        TreeNode tempNode = new TreeNode();
                        tempNode = subRoot.getLeft();          //temporary node to store the new left of the subroot

                        if (tempNode == null)
                        {
                            subRoot.getLeft().setLeft(leftNode);
                        }
                        else
                        {
                            //Short method to find the furthest left node of the new left of the subroot so that the deleted node's left can be joined at the end

                            while (tempNode != null)
                            {
                                if (tempNode.getLeft() != null)   //while there are nodes to the left of the current one 
                                {
                                    tempNode = tempNode.getLeft();     //set the temporary node to the left node
                                }
                                else
                                {
                                    tempNode.setLeft(leftNode);       //set the new left of the leftmost node to the old left of the deleted node
                                    tempNode = null;                  //delete tempNode and exit the loop
                                }
                            }
                        }
                    }

                    //subroot's left has now lost its reference, so is deleted by the garbage collector
                }
                else
                {
                    removeFromTree(subRoot.getLeft(), searchID);    //node still to be found and not present in this subtree's left hand side, recall the method moving down the left side of the tree       
                }
            }

            if (subRoot.getRight() != null)
            {
                if (subRoot.getRight().getID() == searchID)     //if the right node's ID equals the search ID
                {
                    if (subRoot.getRight().getLeft() == null && subRoot.getRight().getRight() == null)  //if the right node has no children
                    {
                        subRoot.setRight(null);     //the right node can be safely deleted
                    }
                    else if (subRoot.getRight().getLeft() != null && subRoot.getRight().getRight() != null)     //if the right node has two children
                    {
                        TreeNode rightNode = new TreeNode();
                        rightNode = subRoot.getRight().getRight();      //node to store the right node's own right so that its reference isn't lost upon right node deletion

                        subRoot.setRight(subRoot.getRight().getLeft());     //sets the right node's left to the subroot's right, as this will link up the tree correctly

                        TreeNode tempNode = new TreeNode();
                        tempNode = subRoot.getRight();          //temporary node to store the new right of the subroot

                        if (tempNode == null)
                        {
                            subRoot.getRight().setRight(rightNode);
                        }
                        else
                        {
                            //Short method to find the furthest right node of the new right of the subroot so that the deleted node's right can be joined at the end

                            while (tempNode != null)
                            {
                                if (tempNode.getRight() != null)   //while there are nodes to the right of the current one 
                                {
                                    tempNode = tempNode.getRight();     //set the temporary node to the right node
                                }
                                else
                                {
                                    tempNode.setRight(rightNode);       //set the new right of the rightmost node to the old right of the deleted node
                                    tempNode = null;                    //delete tempNode and exit the loop
                                }
                            }
                        }
                    }
                    else if (subRoot.getRight().getLeft() != null || subRoot.getRight().getRight() != null)     //if the right node has one child
                    {
                        if (subRoot.getRight().getRight() != null)      //if there is a node to the right node's right
                        {
                            subRoot.setRight(subRoot.getRight().getRight());        //this node can become the subroot's new right, old right node deleted
                        }
                        else    //if there is nothing to the right node's right
                        {
                            subRoot.setRight(subRoot.getRight().getLeft());     //the left of the right node can become the subroot's new right
                        }
                    }
                }
                else
                {
                    removeFromTree(subRoot.getRight(), searchID);       //no match, continue along the right of the tree
                }
            }
        }
    }

    /**
     * Built by SG
     * 
     * Used to return the node with the largest ID on the left hand side of the tree, used to set a new root when the old root is being deleted
     * 
     * @param subRoot - the root of the subtree currently being evaluated
     * @return getHighestLeft - the node with the highest value of ID
     */
    public TreeNode getHighestLeft(TreeNode subRoot)
    {      
        TreeNode highestLeftNode = new TreeNode();
        highestLeftNode = subRoot;      //subRoot is originally the first node left of the root as this is the value passed in. Changes as the program traverses right

        if (subRoot.getRight() != null)     //if there is a node to the right of the subroot i.e. a larger ID value
        {
            if (subRoot.getRight().getRight() == null)      //if there is nothing to the right of THAT node
            {
                highestLeftNode = subRoot.getRight();       //the right node has the largest ID, stored in highestLeftNode
                subRoot.setRight(null);                     //this right node MUST be deleted or else the tree goes on infinitely with infinite duplications
            }            
            else    //if there are nodes to the right of that node
            {
                highestLeftNode = getHighestLeft(subRoot.getRight());       //recall the method with the right node as the new subroot
            }
        }   

        return highestLeftNode;
    }

    /**
     * Built by SG
     * 
     * Used to return the node with the smallest ID on the right hand side of the tree, used to set a new root when the old root is being deleted
     * 
     * @param subRoot - the root of the subtree currently being evaluated
     * @return getSmallestRight - the node with the lowest value of ID
     */
    public TreeNode getSmallestRight(TreeNode subRoot)
    {
        TreeNode smallestRightNode = new TreeNode();
        smallestRightNode = subRoot;    //subRoot is originally the first node right of the root as this is the value passed in. Changes as the program traverses left

        if (subRoot.getLeft() != null) //if there is a node to the left of the subroot i.e. a larger ID value
        {
            if (subRoot.getLeft().getLeft() == null)    //if there is nothing to the left of THAT node
            {
                smallestRightNode = subRoot.getLeft();  //the left node has the smallest ID, stored in smallestRightNode
                subRoot.setLeft(null);                  //this left node MUST be deleted or else the tree goes on infinitely with infinite duplications
            }
            else        //if there are nodes to the left of that node
            {
                smallestRightNode = getSmallestRight(subRoot.getLeft());    //recall the method with the left node as the new subroot
            }
        }

        return smallestRightNode;  
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
                    System.out.println("This word already exists in the dictionary.");       //cannot add a duplicate node to the tree
                    current = null;                                                     //no more comparisons necessary, current = null so that the loop exits
                }
            }
        }
    }
   
    /**
     * Built by DA
     * 
     * Method for finding a translation
     *
     * @param find - 
     * @return
      */
    public String findNode(float find, int language)
    {
        if (root.getID() == find)        //if the current ID matches the user's search
        {
            if (language == 1)
            {
                return root.getGerWord();     
            }
            else
            {
                return root.getEngWord();
            }
        }   
        else
        {
            TreeNode current = null;
            current = root;
            boolean found = false;
             
            while (current != null && found == false) {
                if (find == current.getID()) {
                    
                    found = true;

                } else {
                    if (find < current.getID() && current.getLeft() != null) {
                          current = current.getLeft();
                    } else if (find > current.getID() && current.getRight() != null) {
                          current = current.getRight();
                    } else {
                          found = true;
                    }
                }
            }

            if (found == true) {
                if (language == 1)
                {
                    return current.getGerWord();
                }
                else
                {
                    return current.getEngWord();
                }           
            } else {
                return null;
            }  
        }        
    }
    
    /**
     * built by WL
     * 
     * allows the user to add new words to the dictionaries
     * 
     * @param engWord The english word to be added to the dictionary
     * @param gerWord The german word to be added to the dictionary
     */
    public void addToDictionary(String engWord, String gerWord)
    {
        //try-catch statement to catch any errors that may occur
        try
        {
            //sets up an output stream to the english dictionary
            File engDict = new File("en.txt");
            PrintWriter engPR = new PrintWriter(new FileWriter(engDict, true));
            
            //sets up an output stream to the german dictionary
            File gerDict = new File("de.txt");
            PrintWriter gerPR = new PrintWriter(new FileWriter(gerDict, true));
            
            //adds the words to the dictionaries
            engPR.append(engWord + "\n");
            gerPR.append(gerWord + "\n");
            
            //closes the output streams
            gerPR.close();
            engPR.close();
        }
        catch (IOException e)
        {
            System.out.println("an error occured while writing to the dictionary!");
        }
    }
}

