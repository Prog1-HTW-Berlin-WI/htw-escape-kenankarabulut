package app;

import model.Hero;
import model.Lecturer;
import model.Yeti;

import java.util.Scanner;

import model.Alien;
import model.AngrySnail;
import model.CrazyFrog;
import model.HTWRoom;

/**
*
* Klasse für den Ablauf innerhalb des Spiels.
* @author Kenan Karabulut 
* @author Kevin Kruschel
*/
public class EscapeGame {
    private final Hero hero;
    private final HTWRoom[] rooms = new HTWRoom[6];
    private boolean gameRunning = true;
    private boolean gameFinished = false;
    private int roundCounter=0;

    CrazyFrog alien0  = new CrazyFrog();
    Yeti alien1  = new Yeti();
    Alien alien3 = new AngrySnail(null, 30,"Hello you week student. I am Snailo the great. Behold my power Muhahahaha!");
    Alien alien4 = new AngrySnail(null, 20,"Hello you week student. I am Simon the destroyer. I may be small but my punches hurt. Lets see if you can keep up!");

    /**
    *
    * Konstruktor für die Klasse Escapegame
     * Erzeugt ein neues EscapeGame und setzt einen Wert für seine Attribute fest
     * Die Begrüßung variiert danach ob das Alien freundlich gesinnt ist oder nicht
    *
    */
    public EscapeGame(Hero hero) {
        this.hero = hero;
        Lecturer poeser = new Lecturer("Lecturer Poeser", false);
        Lecturer gnaoui = new Lecturer("Lecturer Gnaoui", false);
        Lecturer safitri = new Lecturer("Lecturer Safitri", false);
        Lecturer vaseva = new Lecturer("Lecturer Vaseva", false);
        Lecturer gaertner = new Lecturer("Lecturer Gaerner", false);
        
        rooms[0] = new HTWRoom("XX", "This Room ist empty. You can only hear water dripping down the heating-pipes.", null);
        rooms[1] = new HTWRoom("*cantine*", "You've entered the cantine. You can allread smell the sizzelning meat of the vegan saussage. In the far you see lecturer Poeser eatin fries....", poeser);
        rooms[2] = new HTWRoom("A 015", "You've entered room A 015. You can see Lecturer gnaoui playing with the Java-hamster......", gnaoui);
        rooms[3] = new HTWRoom("A 142", "You've entered room A 142. You notice writing on the whiteboard which says *Prog Klausur* and Lecturer Safitri checking the ID cards of the students....", safitri);
        rooms[4] = new HTWRoom("A 219", "You've entered room A 219. You can allread hear aggressive clicking-sounds. In the back of the room you see Lecturer Vaseva playing tetris on her laptop", vaseva);
        rooms[5] = new HTWRoom("*audimax*", "You've entered the Audimax. You see a giant room full of students. In the front you See Lecturer Gaertner programming snake on the huge whiteboard", gaertner);
    }

    /**
    *
    * Prüft ob das Spiel gestartet worden ist und gibt boolean Wert zurück
    * @return boolean Wert der zeigt, ob Spiel läuft bzw. gestartet wurde
    */
    public boolean isGameRunning() {
        return gameRunning;
    }

    /**
    *
    * Setzt den Zustand des Spiels auf laufend oder nicht laufend
    * @param boolean gameRunning, true oder false je nach dem ob Spiel läuft oder nicht
    */
    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
    }

    /**
    *
    * Prüft ob das Spiel beendet wurde 
    * gibt boolean-Wert zurück
    * @return boolean Wert der zeigt, ob Spiel beendet wurde
    */
    public boolean isGameFinished() {
        return gameFinished;
    }

    /**
    *
    * Setzt den Zustand des Spiels auf beendet wurde oder nicht beendet
    * @param boolean gameFinished, true oder false je nach dem ob Spiel beendet wurde oder nicht
    */
    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }
   
    /**
    *
    * Zeigt das Spielmenü an und liest die Eingabe des Spielern.
    * 
    */
    public void run() {
        while(gameRunning == true){
        Scanner scanner = new Scanner(System.in);
        
        
        System.out.println("-------------------");
        System.out.println("roundCounter: " + this.roundCounter);
        System.out.println("gamemenu:");
        System.out.println("");
        System.out.println("(1) explore HTW campus");
        System.out.println("(2) show hero status");
        System.out.println("(3) show signature list");
        System.out.println("(4) start meditation");
        System.out.println("(5) return to menu");
        
        String menuInput = scanner.nextLine();
        handleUserInput(menuInput);
        }
    }
    
    /**
     * Nimmt die Eingabe des Spielers und führt die jeweilige Aktion aus.
     * gibt bei ungültiger Eingabe eine Fehlermeldung aus.
     * @param menuInput
     */

    private void handleUserInput(String menuInput) {
        switch (menuInput) {
            case "1":
                this.explore();
                break;
            case "2":
            
                break;
            case "3":
                
                break;
            case "4":
               
                break;
            case "5":
                gameRunning = false;
                break;
            default:
                System.out.println("Invalid input. Please choose a correct number between 1 and 5");
                break;
        }
    }

    /**
     * Startet eine Erkundung des Spielers.
     * Zu 20% betritt man den leeren Raum.
     * Zu 52% trifft man auf ein Alien -> Die Wahrscheinlichkeit wird nochmal auf 4 Aliens geteilt.
     * Zu 28% trifft man auf einen Übungsleiter (einer von 5 verschiedenen).
     * Nach jeder Erkundung hat der Spieler die Wahl eine neue Erkundung direkt zu starten.
     * ->autoExplore bleibt true und eine neue Erkundung wird gestartet
     */
    private void explore(){
    roundCounter++;
    boolean autoExplore = true;
        while(roundCounter<=24 && hero.isOperational() && autoExplore == true){
            int possibility = (int) (Math.random() * 100);

            if(possibility < 20){
                rooms[0].printInfo();
                autoExplore = askNewRound();
                /* 
                System.out.println("choose an action:");
                System.out.println("(1) continue exploring");
                System.out.println("(2) return to game menu");
                String choice = EscapeApp.readUserInput();
                switch (choice) {
                    case "1":
                        this.explore();
                        System.out.println("starting a new round.........");
                        break; 
                    case "2":
                        System.out.println("returning to the game menu.....");
                        return;
                }
                */
            }
            else if(possibility < 23){
                System.out.println("You have encountered the Yeti.....");
                //while(hero.isOperational() && !alien1.isDefeated()){
                    alien1.printMenu();
                    hero.showstats();
                    //alienEncounter();
                    autoExplore = askNewRound();
            }
            else if(possibility <40){
                System.out.println("You have encountered nailo the great.....");
                //while(hero.isOperational() && !alien3.isDefeated()){
                    alien3.printMenu();
                    hero.showstats();
                    //alienEncounter();
                    autoExplore = askNewRound();
                //}
            }
            else if(possibility < 57){
                System.out.println("You have encountered Siomon the destroyer.....");
                //while(hero.isOperational() && !alien4.isDefeated()){
                    alien4.printMenu();
                    hero.showstats();
                    //alienEncounter();
                    autoExplore = askNewRound();
                //}
            }
            else if(possibility < 72){
                System.out.println("You have encountered Crazy frog.....");
                alien0.printMenu();
                hero.showstats();
                alien0.healHero(hero);
                autoExplore = askNewRound();
            }
            else{
            int roomRnndm = (int) (Math.random() * 5);
            HTWRoom room = rooms[roomRnndm];

                if(room.getLecturer() != null){
                    lecturerEncounter(room.getLecturer(), room);
                }
            }
        }
    }

    /**
    *
    * Getter-Methode für den Referenzdatentyp "Hero" 
    * (gibt aktuelles Objekt der Klasse Hero zurück)
    * @return aktuelles Objekt der Klasse Hero
    */
    public Hero getHero() {
        return hero;
    }

    /**
     * Der Spieler hat die Wahl nach einer Erkundung direkt eine neue zu starten oder ins Menü zurückzukehren.
     * Die Methode liest die Eingabe des Spielers.
     * Je nach dem für was sich der Spieler entscheidet, gibt die Methode true oder false zurück.
     * 
     * @return true falls Spieler neue Erkundung startet.
     * @return false falls Spieler in Spielmenü zurrückkehren will.
     */
    public boolean askNewRound(){
        while(true){
        System.out.println("choose an action:");
        System.out.println("(1) continue exploring");
        System.out.println("(2) return to game menu");
        String choice = EscapeApp.readUserInput();
            if(choice.equals("1")){
                System.out.println("starting a new round.........");
                return true;
            }
            else if(choice.equals("2")){
                    System.out.println("returning to the game menu.....");
                    return false;
            }
            else{
                System.out.println("Invalid input. Please choose a correct number between 1 and 2");
            }
        }
    }

    /**
     * Methode falls ein Spieler auf einen Übungsleiter trifft.
     * Zeigt Raumbeschreibung mit jeweiligem Übungsleiter an.
     * Der Spieler hat die Wahl nach einer Unterschrift zu fragen oder den Raum zu verlassen.
     * @param lecturer Übungsleiter dem man begegnet.
     * @param htwRoom in dem sich Übungsleiter befindet.
     */

    public void lecturerEncounter(Lecturer lecturer, HTWRoom htwRoom){
        htwRoom.printInfo();
        while(true){
        System.out.println("\n");
        System.out.println("(1) ask for a signature");
        System.out.println("(2) exit room");
        String lecturAction = EscapeApp.readUserInput();
            switch (lecturAction) {
                case "1":
                    if(lecturer.isReadyToSign(hero)){
                    hero.signExerciseLeader(lecturer);
                    }
                    break; 
                case "2":
                    System.out.println("returning to the game menu.....");
                    return;
                default:
                System.out.println("Invalid input. Please choose a correct number between 1 and 2");
                break;
            }
        }


    } 
}
