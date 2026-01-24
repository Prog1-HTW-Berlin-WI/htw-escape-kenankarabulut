package model;

/**
*
* Klasse Questions mit jeweiligen Attributen und Operationen.
* besteht aus Frage und Antwortmöglichkeiten
* @author Kenan Karabulut 
* @author Kevin Kruschel
*/

public class Questions {
    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private int rightAnswer;

    /**
     * Konstruktor für die Klasse Questions.
     * Erzeugt eine Frage mit seinen Antwortmöglichkeiten und der richtigen Antwort.
     * @param question zu beantwortende Frage
     * @param answer1 erste Antwortmöglichkeit
     * @param answer2 zweite Antwortmöglichkeit
     * @param answer3 dritte Antwortmöglichkeit
     * @param answer4 vierte Antwortmöglichkeit
     * @param rightAnswer richtige Antwort
     */
    public Questions(String question, String answer1, String answer2, String answer3, String answer4, int rightAnswer){
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.rightAnswer = rightAnswer;
    }

    /**
     * Prüft ob die Zahl, die der Spieler eingibt, der richtigen Antwort entspiricht.
     * @param input Zahl die eigegeben wurde.
     * @return true wenn Antwort richtig ist.
     * @return false wenn Antwort falsch war.
     */
    public boolean isRightAnswer(int input){
        if(rightAnswer == input ){
            System.out.println("---------------------------");
            System.out.println("Congrats you chose the right answer..........");
            return true;
            
        }
        else{
            System.out.println("---------------------------");
            System.out.println("This answer ist wrong.........");
            return false;
        }

    }

    /**
     * Zeigt die Fragen und die Antwortmöglichkeiten an.
     */
    public void showQuest(){
        System.out.println("Question: " + question);
        System.out.println("-----------------------");
        System.out.println("Answer: (1)" + answer1);
        System.out.println("Answer: (2)" + answer2);
        System.out.println("Answer: (3)" + answer3);
        System.out.println("Answer: (4)" + answer4);
        System.out.println("\n");
        System.out.println("Choose an answer");
        System.out.println("-----------------------");
        System.out.println("\n");
    }
}
