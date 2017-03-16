import java.awt.Desktop;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * Displays the Menu options to the user
 * 
 * @author Sam Glendenning
 * @author Danyal Ali
 * @author Craig Hutcheon
 */
public class Menu
{
    LanguageTree engTree = new LanguageTree();
    LanguageTree gerTree = new LanguageTree();
    
    /**
     * Main method, can be amended or moved elsewhere if necessary (last edited by SG)
     */
    public static void main(String[] args)
    {
        Menu menu = new Menu();
        System.out.print('\f');
        System.out.println("Loading translator...");
        menu.readDictionaries();
        menu.loadMenu();
    }
    
    /**
     * Built by SG, DA
     * 
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
            System.out.println("1. Enter text for translation");            //findNode
            System.out.println("2. Read text file to translate");           //Read custom user text file (not dictionaries)
            System.out.println("3. Add a word to the dictionary");          //stringToInt and then addToTree
            System.out.println("4. Remove a word from the dictionary");     //removeTree
            System.out.println("5. Display English dictionary");            //Desktop function, dead easy 
            System.out.println("6. Display German dictionary");             //Desktop function, dead easy 
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
                        
                        System.out.println("Enter text for translation in lowercase.");
                        String originalText = Genio.getString();
                        
                        System.out.println("");
                        System.out.print("TRANSLATION: ");
                        
                        String[] textSplit = originalText.split(" ");
                        
                        for (int i=0; i<textSplit.length; i++)
                        {
                            float originalFloat = stringToInt(textSplit[i] + " ");
                              
                            if (languageChoice == 1)
                            {                         
                                System.out.print(engTree.findNode(originalFloat, 1) + " ");
                            }
                            else
                            {
                                System.out.print(gerTree.findNode(originalFloat, 2) + " ");
                            }
                        }
                        
                        System.out.println("");
                        System.out.println("");
                        System.out.println("Enter any key to return to the main menu.");
                        String anyKey = Genio.getString();
                        
                        break;
                        
                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
                
                case 2:     
                
                        break;
                
                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                
                case 3: System.out.print('\f');
                        System.out.println("Enter the English word to add.");
                        String engWord = Genio.getString() + " ";
                        
                        System.out.println("Enter the German word to add.");
                        String gerWord = Genio.getString() + " ";
                        
                        float engID = stringToInt(engWord);
                        float gerID = stringToInt(gerWord);
                       
                        engTree.addToTree(engID, engWord, gerWord);
                        gerTree.addToTree(gerID, engWord, gerWord);
                        break;
                
                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        
                case 4: System.out.print('\f');
                        System.out.println("Please choose the language you are entering the word in.");
                        System.out.println("1. English");
                        System.out.println("2. German");
                        
                        languageChoice = Genio.getInteger();
                        
                        while (languageChoice < 1 || languageChoice > 2)
                        {
                            System.out.println("Error. Choose either 1 for English or 2 for German.");
                            languageChoice = Genio.getInteger();
                        }
                        
                        System.out.println("");
                        System.out.println("Enter the word to remove in lowercase.");       //need to validate lowercase                     
                        String wordToRemove = Genio.getString();
                        
                        float idToRemove = stringToInt(wordToRemove);
                        
                        if (languageChoice == 1)
                        {
                            String tempGerWord = gerTree.findNode(idToRemove, 1);     //fetching the word to remove's translation
                            engTree.removeFromTree(engTree.getRoot(), idToRemove);      //removing the original word from the English tree
                            
                            idToRemove = stringToInt(tempGerWord);                      //converting the translation of the removed word to a float
                            gerTree.removeFromTree(gerTree.getRoot(), idToRemove);      //passing this into the German tree so the word to remove and its translation are removed from both trees
                        }
                        else
                        {
                            String tempEngWord = engTree.findNode(idToRemove, 2);     //vice versa to above
                            gerTree.removeFromTree(gerTree.getRoot(), idToRemove);
                            
                            idToRemove = stringToInt(tempEngWord);
                            engTree.removeFromTree(engTree.getRoot(), idToRemove);
                        }
                        
                        break;
                       
                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        
                case 5: try
                        {
                            File engFile = new File("en.txt");
                            Desktop.getDesktop().open(engFile);
                        }
                        catch(IOException e)
                        {
                            System.out.println("The English dictionary text file is missing...");
                        }
                        
                        break;
                
                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                
                case 6: try
                        {
                            File gerFile = new File("de.txt");
                            Desktop.getDesktop().open(gerFile);
                        }
                        catch(IOException e)
                        {
                            System.out.println("The German dictionary text file is missing...");
                        }
                        
                        break;
            }
        }
        
        menuExit();
    }
    
    /**
     * Built by CH
     * 
    *Method designed to turn user input into usable integer value
    *Uses charAt(i) to traverse string 
    */
    float stringToInt(String input)
    {
        String stringToChar;// Used to store user entry
        String stringNum = "";// Used to store combined char strings
        stringToChar = input;
        int i = 0; // Used to traverse string
        
        while (i < stringToChar.length()) //or throws out of bounds exception
        {
             char temp = stringToChar.charAt(i); // converts char i in string to char
             String num = getNumber(temp); // gets number value in string form from getNumber
             
             if (num != null)
             {
                 stringNum = stringNum + num; //Combines existing number string with new number 
             }
                   
             i++;
        }

        if (stringNum == "")
        {
            return 0;
        }
        else
        {
            float result = Float.parseFloat(stringNum); //converts stringNum to integer value
            return result; //Returns result value
        }
    }
     
    /**
     * Built by CH
     * 
    *Used to Convert char value into a number float
    */
    String getNumber(char temp)
    {
        String num = null;
     
        if (temp == '\'')
                      num = "10";
        else if (temp == 'A' || temp == 'a')
                      num = "11";
        else if (temp == 'Ä' || temp == 'ä')
                      num = "12";
        else if (temp == 'Å' || temp == 'å')
                      num = "13";
        else if (temp == 'B' || temp == 'b')
                      num = "14";
        else if (temp == 'C' || temp == 'c')
                      num = "15";
        else if (temp == 'D' || temp == 'd')
                      num = "16";
        else if (temp == 'E' || temp == 'e')
                      num = "17";
        else if (temp == 'É' || temp == 'é')
                      num = "18";
        else if (temp == 'F' || temp == 'f')
                      num = "19";
        else if (temp == 'G' || temp == 'g')
                      num = "20";
        else if (temp == 'H' || temp == 'h')
                      num = "21";
        else if (temp == 'I' || temp == 'i')
                      num = "22";
        else if (temp == 'J' || temp == 'j')
                      num = "23";
        else if (temp == 'K' || temp == 'k')
                      num = "24";
        else if (temp == 'L' || temp == 'l')
                      num = "25";
        else if (temp == 'M' || temp == 'm')
                      num = "26";
        else if (temp == 'N' || temp == 'n')
                      num = "27";
        else if (temp == 'O' || temp == 'o')
                      num = "28";
        else if (temp == 'Ö' || temp == 'ö')
                      num = "29";
        else if (temp == 'P' || temp == 'p')
                      num = "30";
        else if (temp == 'Q' || temp == 'q')
                      num = "31";
        else if (temp == 'R' || temp == 'r')
                      num = "32";
        else if (temp == 'S' || temp == 's')
                      num = "33";
        else if (temp == 'ß')
                      num = "34";
        else if (temp == 'T' || temp == 't')
                      num = "35";
        else if (temp == 'U' || temp == 'u')
                      num = "36";
        else if (temp == 'Ü' || temp == 'ü')
                      num = "37";
        else if (temp == 'V' || temp == 'v')
                      num = "38";
        else if (temp == 'W' || temp == 'w')
                      num = "39";
        else if (temp == 'X' || temp == 'x')
                      num = "40";
        else if (temp == 'Y' || temp == 'y')
                      num = "41";
        else if (temp == 'Z' || temp == 'z')
                      num = "42";
        else if (temp == ' ' || temp == '\t' || temp == '\r' || temp == '\n' || Character.isWhitespace(temp))
                      num = "43";
                      
        return num;
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
    
    public void readDictionaries()
    {
        try
        {
            FileReader engFileReader = new FileReader("en.txt");
            BufferedReader engBufferedReader = new BufferedReader(engFileReader);
            
            FileReader gerFileReader = new FileReader("de.txt");
            BufferedReader gerBufferedReader = new BufferedReader(gerFileReader);
            
            String engWord = engBufferedReader.readLine() + " ";
            String gerWord = gerBufferedReader.readLine() + " ";
            
            while (engWord != null && gerWord != null)
            {
                float engID = stringToInt(engWord);
                float gerID = stringToInt(gerWord);
                
                engTree.addToTree(engID, engWord, gerWord);
                gerTree.addToTree(gerID, engWord, gerWord);
                
                engWord = engBufferedReader.readLine();
                gerWord = gerBufferedReader.readLine();
                
                if (engWord != null)
                {
                    engWord = engWord + " ";
                    gerWord = gerWord + " ";
                }
            }
                
            engBufferedReader.close();
            gerBufferedReader.close();
        }
        catch(IOException e)
        {
            System.out.println("SYSTEM ERROR. DICTIONARY FILES ARE MISSING.");
            menuExit();
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
