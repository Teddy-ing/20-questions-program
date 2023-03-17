// Theodore Ingberman
// 3/05/23
// CS 145
// Lab #6
// This class represents the Questionnode object

public class QuestionNode { // start of QuestionNode class
    
    String data;
    QuestionNode leftNode;
    QuestionNode rightNode;
    

    public QuestionNode(String data) {
        this.data = data;
    }
    
    public QuestionNode(String data, QuestionNode leftNode,
    QuestionNode rightNode) {
    this.data = data;
    this.leftNode = leftNode;
    this.rightNode = rightNode; 
   } 
} // end of QuestionNode class

