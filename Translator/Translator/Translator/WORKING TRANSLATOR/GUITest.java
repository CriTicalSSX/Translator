
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.BorderFactory;
import javax.swing.JToolBar;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.TransferHandler;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

import static javax.swing.GroupLayout.DEFAULT_SIZE;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ItemEvent;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.ItemListener;
import java.awt.Desktop;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;

/**
 * GUI Class
 * 
 * Handles Creation of GUI
 * 
 * @author - Craig Hutcheon
 * 
 * Version - 1.0
 */


public class GUITest extends JFrame {
    
    private JLabel statusbar;
    private JPanel pnl;
    private JPanel basic;
    
    private JComboBox<String> box;
    private String[] languages;
    
    private JTextField field1;
    private JTextField field2;
    private JTextField field3;
    private JTextField field4;
    private JTextField field5;
    private JTextField field6;
    
    Menu menu1 = new Menu();
    LanguageTree engTree = new LanguageTree();
    LanguageTree gerTree = new LanguageTree();
    
    String remove; 
    String add;

    int selection = 1;
    String input = null;
    
    boolean test;
   /**
    * Main GUI Method
    */ 
   public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            GUITest ex = new GUITest();
            ex.setVisible(true);
        });

    }
    
    /**
     * Constructor
     */
    public GUITest() {
        initUI();
        
        engTree.dictionariesDone = false;       //used to prevent "This word is already in the dictionary" appearing on program startup
        gerTree.dictionariesDone = false;
        
        JOptionPane.showMessageDialog(null, "Loading Translator");
        
        menu1.readDictionaries();
        
        engTree.dictionariesDone = true;
        gerTree.dictionariesDone = true;
        
    }

    /**
     * Initialises GUI
     */
    private void initUI() {
        menu1.readDictionaries();
        
        basic = new JPanel();
        basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));
        add(basic);
        
        JSeparator separator = new JSeparator();
        separator.setForeground(Color.gray);

        createMenuBar();
        
        JPanel numbers = new JPanel();
        numbers.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 100));
    
        //Input boxes for translation
        field1 = new JTextField("Input", 25);
        field2 = new JTextField("Output", 25);

        numbers.add(field1);
        numbers.add(field2);
        
        field1.setDragEnabled(true);
        field2.setDragEnabled(true);
        
        basic.add(numbers);
        
        JPanel choice = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        input = field1.getText();
        
        JButton translate;

        translate = new JButton("Translate");
        translate.setMnemonic(KeyEvent.VK_A);
       
        
        choice.add(translate);
       
        
        translate.setTransferHandler(new TransferHandler("text"));
        
        translate.addActionListener((ActionEvent event) -> {
            translate();
        });
        
        languages = new String[]{"English to German", "German to English"};

        box = new JComboBox<>(languages);
        box.addItemListener(new ItemListener(){
        public void itemStateChanged(ItemEvent ie)
        {
            if(ie.getStateChange() == ItemEvent.DESELECTED) //edit: bracket was missing
            {
              selection = 1;
              input = field1.getText();
            }
            else if(ie.getStateChange() == ItemEvent.SELECTED)
            {
                selection = 2;
                input = field2.getText();
            }
        }
        });
        
        
        choice.add(box);
        basic.add(choice);
        
        
        setTitle("Translator");
        setSize(new Dimension(1280, 720));
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Creates layout for GUI
     */
     private void createLayout(JComponent... arg) {

        JPanel pane = (JPanel) getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);

        pane.setToolTipText("Content pane");
        
        gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
                .addGap(200)
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
                .addGap(120)
        );
        
        pack();
    }
    
    /**
     * Creates Menu Bar
     */
    private void createMenuBar() {

        JMenuBar menubar = new JMenuBar();
        
        JMenu file = new JMenu("File");
        JMenu view = new JMenu("View");
        JMenu add = new JMenu("Add");
        JMenu remove = new JMenu("Remove");
        file.setMnemonic(KeyEvent.VK_F); 
        view.setMnemonic(KeyEvent.VK_V);
        
        JMenuItem eMenuItem = new JMenuItem("Exit");
        eMenuItem.setMnemonic(KeyEvent.VK_E);
        eMenuItem.setToolTipText("Exit application");
        eMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                Object[] options = { "OK", "CANCEL" };
                JOptionPane.showOptionDialog(null, "Click OK to Exit", "Warning",

                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,

                   null, options, options[0]);
                   
                  exit();
                    
            }
        });
        
        
        JMenuItem english = new JMenuItem("English Dictionary");
        english.setMnemonic(KeyEvent.VK_E);
        english.setToolTipText("Open English Dictionary");
        english.addActionListener((ActionEvent event) -> {
                try
                {
                    File engFile = new File("en.txt");
                    Desktop.getDesktop().open(engFile);     //opens the English dictionary file
                }
                catch(IOException e)
                {
                    System.out.println("The English dictionary text file is missing...");
                }
        });
        
        JMenuItem german = new JMenuItem("German Dictionary");
        german.setMnemonic(KeyEvent.VK_G);
        german.setToolTipText("Open German Dictionary");
        german.addActionListener((ActionEvent event) -> {
                try
                {
                    File gerFile = new File("de.txt");
                    Desktop.getDesktop().open(gerFile);     //opens the German dictionary file
                }
                catch(IOException e)
                {
                    System.out.println("The German dictionary text file is missing...");
                }
        });
        
         JMenuItem removeenglish = new JMenuItem("English Word");
        removeenglish.setMnemonic(KeyEvent.VK_E);
        removeenglish.setToolTipText("Remove English Word");
        removeenglish.addActionListener((ActionEvent event) -> {
                remove();
        });
        
        JMenuItem removegerman = new JMenuItem("German Word");
        removegerman.setMnemonic(KeyEvent.VK_G);
        removegerman.setToolTipText("Remove German Word");
        removegerman.addActionListener((ActionEvent event) -> {
                remove();
        });
        
        JMenuItem addenglish = new JMenuItem("English Word");
        addenglish.setMnemonic(KeyEvent.VK_E);
        addenglish.setToolTipText("Add English Word");
        addenglish.addActionListener((ActionEvent event) -> {
                add();
        });
        
        JMenuItem addgerman = new JMenuItem("German Word");
        addgerman.setMnemonic(KeyEvent.VK_G);
        addgerman.setToolTipText("Add German Word");
        addgerman.addActionListener((ActionEvent event) -> {
                add();
        });
        
        pnl = (JPanel) getContentPane();
        
        file.add(eMenuItem);
        view.add(english);
        view.add(german);
        add.add(addenglish);
        add.add(addgerman);
        remove.add(removeenglish);
        remove.add(removegerman);

        menubar.add(file);
        menubar.add(view);
        menubar.add(add);
        menubar.add(remove);

        setJMenuBar(menubar);
    }
    
    private void translate()
    {
      System.out.println("");
      System.out.print("TRANSLATION: ");
      String[] textSplit = input.split(" ");
      
      for (int i=0; i<textSplit.length; i++)
        {
            float originalFloat = menu1.stringToFloat(textSplit[i] + " ");
                              
            if (selection == 1)
            {                         
                System.out.print(engTree.findNode(originalFloat, 1) + " ");
            }
            else
            {
                System.out.print(gerTree.findNode(originalFloat, 2) + " ");
            }
        }
                        
    }
    
    private static void exit()
    {
      System.exit(0);
    }
    
    private void remove()
    {
      remove = JOptionPane.showInputDialog("Please Enter Word to Remove");
    }
         
    private void add()
    {
        add = JOptionPane.showInputDialog("Please Enter Word to Add");  
    }
    
}