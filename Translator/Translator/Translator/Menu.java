import java.util.Date;
import java.awt.Desktop;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileWriter;

/**
 * Acts as a wrapper class for the LanguageTrees and takes in user input.
 * 
 * @author Sam Glendenning
 * @author Danyal Ali
 * @author Craig Hutcheon
 * @author Will Lockett
 */
public class Menu
{
    LanguageTree engTree = new LanguageTree();      //the English binary tree
    LanguageTree gerTree = new LanguageTree();      //the German binary tree

    /**
     * Built by SG, DA
     * 
     * Main method first called
     */
    public static void main(String[] args)
    {
        Menu menu = new Menu();
        LanguageTree engTree = new LanguageTree();
        LanguageTree gerTree = new LanguageTree();

        engTree.dictionariesDone = false;       //used to prevent "This word is already in the dictionary" appearing on program startup
        gerTree.dictionariesDone = false;

        System.out.print('\f');
        System.out.println("Loading translator...");

        menu.readDictionaries();

        engTree.dictionariesDone = true;
        gerTree.dictionariesDone = true;

        System.out.print('\f');
        menu.loadMenu();
    }

    /**
     * Built by DA
     * 
     * Method to output the menu options to the user
     */
    public void displayMenu() {
        System.out.println("+-----------------------------------------------+");
        System.out.println("|   Welcome to the English-German translator!   |");
        System.out.println("|         Please Choose an option below:        |");
        System.out.println("+-----------------------------------------------+");
        System.out.println("|                                               |");
        System.out.println("|      1) Enter text for translation            |");
        System.out.println("|      2) Translate a text file                 |");
        System.out.println("|      3) Add a word to the dictionary          |");
        System.out.println("|      4) Remove a word from the dictionary     |");
        System.out.println("|      5) Display English dictionary            |");
        System.out.println("|      6) Display German dictionary             |");
        System.out.println("|      7) Exit Program                          |");
        System.out.println("|                                               |");
        System.out.println("+-----------------------------------------------+");
        System.out.println(" ");
        System.out.print("Please select an option from above: ");
        System.out.println(" ");
    }

    /**
     * Built by SG, DA
     * 
     * Controls user input into the program, and calls the appropriate method. 
     */
    public void loadMenu()
    {      
        int menuChoice = 1;

        while (menuChoice !=7)
        {           
            displayMenu();      //Calls the menu display

            menuChoice = Genio.getInteger();        //user's numbered menu choice

            while (menuChoice < 1 || menuChoice > 7)
            {
                System.out.println("Please enter a number between 1 and 7.");
                menuChoice = Genio.getInteger();
            }

            //switch statement allowing user to make their choice in the text based menu
            switch(menuChoice)
            {
                //runs the method for translating words and phrases the user enters
                case 1: textToTranslate();

                break;

                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    

                //asks user to input which language they would like to translate
                case 2: System.out.print('\f');
                System.out.println("Enter the name of the text file you wish to translate.");
                System.out.println("The file must be in the same directory as the program files.");

                String nameOfTextFile = Genio.getString();

                if (!nameOfTextFile.endsWith(".txt"))
                {
                    nameOfTextFile = nameOfTextFile + ".txt";       //adds .txt to the end of the filename if not given by the user
                }

                System.out.println("Please specify the language the file is in.");
                System.out.println("1. English");
                System.out.println("2. German");

                int language = Genio.getInteger();

                while (language < 1 || language > 2)
                {
                    System.out.println("Error. Enter either 1 for English or 2 for German.");
                    language = Genio.getInteger();
                }

                readFromFile(nameOfTextFile, language);

                break;

                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                case 3: addWord();      //allows the user to add a word to the dictionaries

                break;

                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                case 4: removeWord();   //allows the user to remove a word from the dictionaries

                break;

                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                case 5: try
                        {
                            File engFile = new File("en.txt");
                            Desktop.getDesktop().open(engFile);     //opens the English dictionary file
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
                            Desktop.getDesktop().open(gerFile);     //opens the German dictionary file
                        }
                        catch(IOException e)
                        {
                            System.out.println("The German dictionary text file is missing...");
                        }

                break;

                case 7: menuExit();     //calls method to exit the program automatically
            }
        } 

    }

    /**
     * Built by CH
     * 
     *Method designed to turn user input into usable integer value
     *Uses charAt(i) to traverse string 
     *
     *@param input - the String passed in that is being converted
     *@return float - the value of the string as a float
     */
    float stringToFloat(String input)
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
     *
     *@param temp - the current character of the string
     *@return a string number - depends on character value
     */
    String getNumber(char temp)
    {
        if (temp == '\'')
            return "10";
        else if (temp == 'A' || temp == 'a')
            return "11";
        else if (temp == 'Ä' || temp == 'ä')
            return "12";
        else if (temp == 'Å' || temp == 'å')
            return "13";
        else if (temp == 'B' || temp == 'b')
            return "14";
        else if (temp == 'C' || temp == 'c')
            return "15";
        else if (temp == 'D' || temp == 'd')
            return "16";
        else if (temp == 'E' || temp == 'e')
            return "17";
        else if (temp == 'É' || temp == 'é')
            return "18";
        else if (temp == 'F' || temp == 'f')
            return "19";
        else if (temp == 'G' || temp == 'g')
            return "20";
        else if (temp == 'H' || temp == 'h')
            return "21";
        else if (temp == 'I' || temp == 'i')
            return "22";
        else if (temp == 'J' || temp == 'j')
            return "23";
        else if (temp == 'K' || temp == 'k')
            return "24";
        else if (temp == 'L' || temp == 'l')
            return "25";
        else if (temp == 'M' || temp == 'm')
            return "26";
        else if (temp == 'N' || temp == 'n')
            return "27";
        else if (temp == 'O' || temp == 'o')
            return "28";
        else if (temp == 'Ö' || temp == 'ö')
            return "29";
        else if (temp == 'P' || temp == 'p')
            return "30";
        else if (temp == 'Q' || temp == 'q')
            return "31";
        else if (temp == 'R' || temp == 'r')
            return "32";
        else if (temp == 'S' || temp == 's')
            return "33";
        else if (temp == 'ß')
            return "34";
        else if (temp == 'T' || temp == 't')
            return "35";
        else if (temp == 'U' || temp == 'u')
            return "36";
        else if (temp == 'Ü' || temp == 'ü')
            return "37";
        else if (temp == 'V' || temp == 'v')
            return "38";
        else if (temp == 'W' || temp == 'w')
            return "39";
        else if (temp == 'X' || temp == 'x')
            return "40";
        else if (temp == 'Y' || temp == 'y')
            return "41";
        else if (temp == 'Z' || temp == 'z')
            return "42";
        else if (temp == ' ' || temp == '\t' || temp == '\r' || temp == '\n' || Character.isWhitespace(temp))
            return "43";

        return null;
    }

    /**
     * Built by SG
     * 
     * Used at the start of the program to read in the English and German dictionaries line by line, calculate their ID numbers and store them in both trees.
     * These files are the source of all the words the program uses to fetch translations
     * 
     */
    public void readDictionaries()
    {
        try
        {
            FileReader engFileReader = new FileReader("en.txt");
            BufferedReader engBufferedReader = new BufferedReader(engFileReader);       //filereader and bufferedreader for English dictionary

            FileReader gerFileReader = new FileReader("de.txt");
            BufferedReader gerBufferedReader = new BufferedReader(gerFileReader);       //filereader and bufferedreader for German dictionary

            String engWord = engBufferedReader.readLine();      //reads a line of the English dictionary
            String gerWord = gerBufferedReader.readLine();      //reads a line of the German dictionary

            while (engWord != null && gerWord != null)          //while there are words to be read in
            {
                float engID = stringToFloat(engWord);           //calculate ID number of English word
                float gerID = stringToFloat(gerWord);           //calculate ID number of German word

                engTree.addToTree(engID, engWord, gerWord);     //add to English tree
                gerTree.addToTree(gerID, engWord, gerWord);     //add to German tree

                engWord = engBufferedReader.readLine();         //read a new line
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

    /**
     * Built by SG
     * 
     * Used when the user wishes to translate a text file.
     * 
     * @param nameOfTextFile - the name of the user's text file as a string
     * @param languageChoice - either 1 or 2 depending on the language the text file is in
     */
    public void readFromFile(String nameOfTextFile, int languageChoice)
    {
        Date currentDate = new Date();        
        long currentTime = currentDate.getTime();       //used in calculating how long it takes to translate the file

        try
        {
            FileReader fileToRead = new FileReader(nameOfTextFile);
            BufferedReader filebr = new BufferedReader(fileToRead);         //filereader and bufferedreader for the file

            FileOutputStream myFileOutputStream = new FileOutputStream("translation.txt");      //name of file where translated text is stored is called translation.txt
            PrintWriter myPrintWriter = new PrintWriter(myFileOutputStream);                    //the printwriter for outputting the translation

            String nextLine = filebr.readLine();

            while (nextLine != null)
            {
                if (nextLine.equals(""))            //if the line is blank
                {
                    myPrintWriter.println("");      //print a blank line
                }
                else
                {
                    String[] lineSplit = nextLine.split(" ");       //string array used to analyse every word

                    for (int i=0; i<lineSplit.length; i++)          //for every word in the line
                    {   
                        try
                        {
                            myPrintWriter.print(Double.parseDouble(lineSplit[i]) + " ");        //print a number if a number is detected
                        }
                        catch (Exception e)
                        {
                            float originalFloat = stringToFloat(lineSplit[i] + " ");        //calculate ID of word
    
                            if (languageChoice == 1)
                            {        
                                myPrintWriter.print(engTree.findNode(originalFloat, 1) + " ");      //print that word's translation to the text file
                            }
                            else
                            {
                                myPrintWriter.print(gerTree.findNode(originalFloat, 2) + " ");
                            }
    
                            myPrintWriter.flush();
                        }
                    }

                    myPrintWriter.print("\n");      //new line once the end of the line is reached
                    myPrintWriter.flush();
                }

                nextLine = filebr.readLine();       //read a new line
            }

            myPrintWriter.close();
            filebr.close();

            System.out.println("Your file has been translated and can be found in translation.txt");

            Date newCurrentDate = new Date();

            long currentLongTime = newCurrentDate.getTime();        
            long timeToTranslate = currentLongTime - currentTime;       //timeToTranslate is the time elapsed since translation began
            
            if (timeToTranslate < 1000)             //if the translation took less than 1 second
            {
                System.out.println("The time taken to translate this file was " + timeToTranslate + " milliseconds.");      //give time in milliseconds
            }
            else
            {
                System.out.println("The time taken to translate this file was " + ((double)timeToTranslate)/1000 + " seconds.");    //give time in seconds
            }
        }
        catch (IOException e)
        {
            System.out.println(nameOfTextFile + " does not exist.");        //the user's file does not exist in the directory
        }

        try 
        {
            Thread.sleep(4000);
        } 
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Built by WL
     * 
     * Allows the user to add new words to the dictionaries
     * 
     * @param engWord - The english word to be added to the dictionary
     * @param gerWord - The german word to be added to the dictionary
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

    /**
     * Built by DA, SG
     * 
     * Used when the user wants to enter a word or phrase to immediately translate.
     */
    public void textToTranslate()
    {
        System.out.print('\f');
        System.out.println("Please choose which language you wish to translate from.");
        System.out.println("1. English");
        System.out.println("2. German");

        int languageChoice = Genio.getInteger();

        while (languageChoice < 1 || languageChoice > 2)
        {
            System.out.println("Please enter either 1 for English or 2 for German.");
            languageChoice = Genio.getInteger();
        }

        System.out.println("Enter text for translation.");
        String originalText = Genio.getString();       

        while (!validateString(originalText))   //checks for any invalid symbols in the text
        {
            System.out.println("Error. Ensure there are no symbols in your text.");
            originalText = Genio.getString();
            validateString(originalText);
        }

        System.out.println("");
        System.out.print("TRANSLATION: ");

        String[] textSplit = originalText.split(" ");       //string array to store the user-entered phrase

        for (int i=0; i<textSplit.length; i++)
        {
            try 
            {
                System.out.print(Double.parseDouble(textSplit[i]) + " ");       //prints a double value if a number value is found
            }
            catch (Exception e)
            {           
                float originalFloat = stringToFloat(textSplit[i] + " ");        //calculates ID number of current word

                if (languageChoice == 1)
                {                         
                    System.out.print(engTree.findNode(originalFloat, 1) + " ");     //prints German translation if English word
                }
                else
                {
                    System.out.print(gerTree.findNode(originalFloat, 2) + " ");     //prints English translation if German word
                }
            }
        }

        System.out.println("");
    }

    /**
     * Built by SG
     * 
     * Used to remove a word from the dictionary and the trees
     */
    public void removeWord()
    {
        System.out.print('\f');
        System.out.println("Please choose the language you are entering the word in.");
        System.out.println("1. English");
        System.out.println("2. German");

        int languageChoice = Genio.getInteger();

        while (languageChoice < 1 || languageChoice > 2)
        {
            System.out.println("Error. Choose either 1 for English or 2 for German.");
            languageChoice = Genio.getInteger();
        }

        System.out.println("");
        System.out.println("Enter the word to remove in lowercase.");       //need to validate lowercase                     
        String wordToRemove = Genio.getString();

        float idToRemove = stringToFloat(wordToRemove);

        if (languageChoice == 1)
        {
            String tempGerWord = gerTree.findNode(idToRemove, 1);     //fetching the word to remove's translation
            engTree.removeFromTree(engTree.getRoot(), idToRemove);      //removing the original word from the English tree

            idToRemove = stringToFloat(tempGerWord);                      //converting the translation of the removed word to a float
            gerTree.removeFromTree(gerTree.getRoot(), idToRemove);      //passing this into the German tree so the word to remove and its translation are removed from both trees
        }
        else
        {
            String tempEngWord = engTree.findNode(idToRemove, 2);     //vice versa to above
            gerTree.removeFromTree(gerTree.getRoot(), idToRemove);

            idToRemove = stringToFloat(tempEngWord);
            engTree.removeFromTree(engTree.getRoot(), idToRemove);
        }

        removeFromDictionary(wordToRemove, languageChoice);         //call the method to remove the word from the dictionary text files
    }

    /**
     * Built by WL
     * 
     * Used to add a word to the trees and the dictionaries
     */
    public void addWord()
    {
        System.out.print('\f');
        System.out.println("Enter the English word to add.");
        String engWord = Genio.getString();     //the English translation of the word
        
        while (!validateString(engWord))   //checks for any invalid symbols in the text
        {
            System.out.println("Error. Ensure there are no symbols in your text.");
            engWord = Genio.getString();
            validateString(engWord);
        }

        System.out.println("Enter the German word to add.");
        String gerWord = Genio.getString();     //the German translation of the word
        
        while (!validateString(gerWord))   //checks for any invalid symbols in the text
        {
            System.out.println("Error. Ensure there are no symbols in your text.");
            gerWord = Genio.getString();
            validateString(gerWord);
        }

        float engID = stringToFloat(engWord);       //calulates the ID number of the English word
        float gerID = stringToFloat(gerWord);       //calulates the ID number of the German word

        if (engTree.findNode(engID, 2).equals(engWord) || gerTree.findNode(gerID, 1).equals(gerWord))       //if either of the words are present in either of the trees
        {
            System.out.println("Those words are already present in the tree.");
        }
        else
        {
            engTree.addToTree(engID, engWord, gerWord);     //add English word to English tree
            gerTree.addToTree(gerID, engWord, gerWord);     //add German word to German tree

            addToDictionary(engWord, gerWord);          //add the two words to the dictionary files themselves

            System.out.println("The word in English and German has been added to the dictionaries.");
        }

        try 
        {
            Thread.sleep(2000);
        } 
        catch(InterruptedException ex)
        {   
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Built by SG
     * 
     * Used to validate whether a String has any invalid symbols in it, otherwise it can't be added to the dictionaries and trees.
     * 
     * @param originalText - the string passed in to be validated
     * @return true/false - depends on string validity
     */
    public boolean validateString(String originalText)
    {
        if (originalText.contains("\"") || originalText.contains("£") || originalText.contains("$") || originalText.contains("%") || originalText.contains("^") || originalText.contains("&") || originalText.contains("*") || originalText.contains("(") || originalText.contains(")") || originalText.contains("-") || originalText.contains("+") || originalText.contains("=") || originalText.contains("_") || originalText.contains("[") || originalText.contains("]") || originalText.contains("{") || originalText.contains("}") || originalText.contains(":") || originalText.contains(";") || originalText.contains("@") || originalText.contains("#") || originalText.contains("~") || originalText.contains("/") || originalText.contains(">") || originalText.contains("<") || originalText.contains("|") || originalText.contains("\\") || originalText.contains("¬"))
        {
            return false;
        }   
        else
        {
            return true;
        }
    }

    /**
     * Built by WL
     * 
     * Deletes a user selected entry and it equivalent from both dictionaries
     * 
     * @param wordToDelete The word the user wants to delete from the dictionary
     */
    public void removeFromDictionary(String wordToDelete, int languageChoice)
    {
        File deDict = new File("de.txt");
        File enDict = new File("en.txt");
        File deTemp = new File("deTemp.txt");
        File enTemp = new File("enTemp.txt");

        try
        {
            BufferedReader enReader = new BufferedReader(new FileReader(enDict));
            BufferedReader deReader = new BufferedReader(new FileReader(deDict));

            PrintWriter enWriter = new PrintWriter(new FileWriter(enTemp));
            PrintWriter deWriter = new PrintWriter(new FileWriter(deTemp));

            String enCurrentLine = enReader.readLine();
            String deCurrentLine = deReader.readLine();
            String trimmedLine = null;

            while (enCurrentLine != null)
            {
                if (languageChoice == 1)
                {
                    trimmedLine = enCurrentLine.trim();
                    if (trimmedLine.equals(wordToDelete))
                    {
                        enCurrentLine = enReader.readLine();
                        deCurrentLine = deReader.readLine();
                        continue;
                    }

                    enWriter.write(enCurrentLine + System.getProperty("line.seperator"));
                    deWriter.write(deCurrentLine + System.getProperty("line.seperator"));
                }
                else
                {
                    trimmedLine = deCurrentLine.trim();
                    if (trimmedLine.equals(wordToDelete))
                    {
                        enCurrentLine = enReader.readLine();
                        deCurrentLine = deReader.readLine();
                        continue;
                    }

                    enWriter.write(enCurrentLine + System.getProperty("line.seperator"));
                    deWriter.write(deCurrentLine + System.getProperty("line.seperator"));
                }

                enCurrentLine = enReader.readLine();
                deCurrentLine = deReader.readLine();
            }

            enReader.close();
            deReader.close();
            enWriter.close();
            deWriter.close();

            enTemp.renameTo(enDict);
            deTemp.renameTo(deDict);
        }
        catch (IOException e)
        {
            System.out.println("Sorry, an error has occured");
        }
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
