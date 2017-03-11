
/**
 * Displays the Menu options to the user
 * 
 * @author Sam Glendenning
 * @author (add your name if you added to or amended this class)
 */
public class Menu
{
    EnglishTree engTree = new EnglishTree();
    GermanTree gerTree = new GermanTree();
    
    /**
     * Main method, can be amended or moved elsewhere if necessary (last edited by SG)
     */
    public static void main(String[] args)
    {
        Menu menu = new Menu();
        menu.loadMenu();
    }
    
    /**
     * Basic text menu, can be used as the foundation for the GUI. Will need user to confirm they wish to return to the main menu before
     * loading the menu, otherwise [System.out.print('\f');] will erase all text on screen (this can obviously be changed) (last edited by SG)
     */
    public void loadMenu()
    {
        int menuChoice = 0;
        
        while (menuChoice != 7)
        {    
            System.out.print('\f');
            System.out.println("Welcome to the English-German translator!");
            System.out.println("Please choose an option.");
            System.out.println("");
            System.out.println("1. Enter text for translation");
            System.out.println("2. Read text file to translate");
            System.out.println("3. Add a word to the dictionary");
            System.out.println("4. Remove a word from the dictionary");
            System.out.println("5. Display English dictionary");
            System.out.println("6. Display German dictionary");
            System.out.println("7. Exit program");
            
            menuChoice = Genio.getInteger();
            
            while (menuChoice < 1 || menuChoice > 7)
            {
                System.out.println("Please enter a number between 1 and 7.");
                menuChoice = Genio.getInteger();
            }
            
            switch(menuChoice)
            {
                case 1: System.out.print('\f');
                        System.out.println("Please choose which language you wish to translate from.");
                        System.out.println("1. English");
                        System.out.println("2. German");
                        
                        int languageChoice = Genio.getInteger();
                        
                        while (languageChoice < 1 || languageChoice > 2)
                        {
                            System.out.println("Please enter either 1 for English or 2 for German.");
                            languageChoice = Genio.getInteger();
                        }
                        
                        if (languageChoice == 1)
                        {
                            
                        }
                        else
                        {
                            
                        }
                        
                
                case 2:
                
                
                case 3:
                
                
                case 4: System.out.print('\f');
                        System.out.println("Please choose the language of the word to remove.");
                        System.out.println("1. English");
                        System.out.println("2. German");
                        
                        languageChoice = Genio.getInteger();
                        
                        while (languageChoice < 1 || languageChoice > 2)
                        {
                            System.out.println("Error. Choose either 1 for English or 2 for German.");
                            languageChoice = Genio.getInteger();
                        }
                        
                        System.out.println("");
                        System.out.println("Enter the word to remove in lowercase.");
                        
                        String wordToRemove = Genio.getString();
                        
                        /**
                         * The following is code to be added, but can't be compiled yet as stringToInt() has not yet been coded.
                         
                        int idToRemove = stringToInt(wordToRemove);
                        
                        if (languageChoice == 1)
                        {
                            engTree.removeFromTree(engTree.getRoot(), idToRemove);
                            System.out.println("The word has been removed from the English dictionary.");
                        }
                        else
                        {
                            gerTree.removeFromTree(gerTree.getRoot(), idToRemove);
                            System.out.println("The word has been removed from the German dictionary.");
                        }
                        
                        */
                       
                       
                case 5:
                
                
                case 6: 
                
                
            }
        }
        
        menuExit();
    }
  
    /**
     * Used to automatically end the program.
     */
    private static void menuExit()
    {
        System.out.print('\f');
        System.out.println("Thank you for using the translator!");
        
        try 
        {
            Thread.sleep(2000);
        } 
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        
        System.exit(0);
    }  
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
