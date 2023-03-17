// Theodore Ingberman
// 3/05/23
// CS 145
// Lab #6
// This class represents the logic behind the 20 questions game.
// It askes the user a series of yes or no questions by traversing 
// the binary tree.  If the object doesn't exist in the tree
// the user will be prompted to make a new object and question. 

import java.io.PrintStream;
import java.util.Scanner;

public class QuestionTree { // start of QuestionTree
    private UserInterface ui; // ui object acts as println and saving booleans
    private QuestionNode root; // overall root node
    private int gamesWon; // keeps track of games won 
    private int gamesPlayed; // keeps track of games played 
   
    public QuestionTree(UserInterface ui) { // start of constructor
        this.ui = ui;
        root = new QuestionNode("computer");
        // start the binary tree with the node computer 
    } //  end of constructor 

    public void play() { // start of play method
        gamesPlayed++;
        root = playHelper(root);
    } // end of play method 

    private QuestionNode playHelper(QuestionNode current) { 
        // this recursive method guesses their object and will prompt them to 
        // add a new word if everything is false 
        if(isLeaf(current)) { // start of first if else 
            ui.print("Would your object happen to be " + current.data+"?");
            if(ui.nextBoolean()) { // start of 2nd if else 
            // case where the CPU wins 
                gamesWon++;
                ui.println("I Win!");
            } else {
                ui.print("I lose. What is your object? ");
                QuestionNode object = new QuestionNode(ui.nextLine());
                //creates a node with the data of the object
                ui.print("Type a yes/no question to distinguish your"
                +"item from " + object.data+":"); 
                String question = ui.nextLine();
                // creates a string from the question
                ui.print("And waht is the answer for your object?");
                if(ui.nextBoolean()) { // start of 3rd if else 
                    current = new QuestionNode(question, object, current);
                    // if yes, the object is to the left of the question node
                } else {
                    current = new QuestionNode(question, current, object);
                } // end of 3rd if else
            } // end of 2nd if else
        } else {
            ui.print(current.data);
            if(ui.nextBoolean()) { // start of 2nd if else
                current.leftNode = playHelper(current.leftNode);
            } else {
                current.rightNode = playHelper(current.rightNode);
            } // end of 2nd if else
        } // end of first if else 
        return current;
    } // end of playHelper method

    public boolean isLeaf(QuestionNode current) { // start of isLeaf method
        //method that checks if the current node is a child or parent node
        return (current.leftNode == null && current.rightNode == null);
    } // end of isLeaf method

    public void save(PrintStream output) { // start of save method
        saveHelper(root, output);
    } // end of save method

    private void saveHelper(QuestionNode root, PrintStream output) { 
        //this recursive method will save the results of a game to a text file
        if(isLeaf(root)) { // start of if else 
            output.print("A:");
            output.println(root.data);
        } else {
            output.print("Q:");
            output.println(root.data);
            saveHelper(root.leftNode, output);
            saveHelper(root.rightNode, output);
        } // end of if else
    } // end of saveHelper 
        
    public void load(Scanner input) { // start of load 
        while(input.hasNextLine()) { // start of while loop
            root = loadHelper(input);
        } // end of while loop
    } // end of load method

    private QuestionNode loadHelper(Scanner input) { // start of loadHelper
        // recursive method for loading a previous game into the program
        String line = input.nextLine();
        String nodeType = line.substring(0,2);
        String data = line.substring(2);
        QuestionNode newNode = new QuestionNode(data);  

        if (nodeType.contains("Q:")) { // start of if 
            newNode.leftNode = loadHelper(input);
            newNode.rightNode = loadHelper(input);   
        } // end of if
        return newNode; 
    } // end of loadHelper method

    public int totalGames() { // start of totalGames method
        return gamesPlayed;
    } // end of totalGames method 

    public int gamesWon() { // start of gamesWon method
        return gamesWon;
    } // end of gamesWon method
} // end of QuestionTree class
