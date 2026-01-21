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
    private boolean didBreak= false;

    CrazyFrog alien0  = new CrazyFrog();
    Yeti alien1  = new Yeti();
    Alien alien3 = new AngrySnail("Snailo the great", 30,"Hello you week student. I am Snailo the great. Behold my power Muhahahaha!");
    Alien alien4 = new AngrySnail("Simon the destroyer", 20,"Hello you week student. I am Simon the destroyer. I may be small but my punches hurt. Lets see if you can keep up!");

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
        
        rooms[0] = new HTWRoom("XX", "This Room ist empty. You can only hear water dripping down the heating-pipes.");
        rooms[1] = new HTWRoom("*cantine*", "You've entered the cantine. You can allread smell the sizzelning meat of the vegan saussage. In the far you see lecturer Poeser eating fries....", poeser);
        rooms[2] = new HTWRoom("A 015", "You've entered room A 015. You can see Lecturer gnaoui playing with the Java-hamster......", gnaoui);
        rooms[3] = new HTWRoom("A 142", "You've entered room A 142. You notice writing on the whiteboard which says *Prog Klausur* and Lecturer Safitri checking the ID cards of the students....", safitri);
        rooms[4] = new HTWRoom("A 219", "You've entered room A 219. You can allread hear aggressive clicking-sounds. In the back of the room you see Lecturer Vaseva playing tetris on her laptop", vaseva);
        rooms[5] = new HTWRoom("*audimax*", "You've entered the Audimax. You see a giant room full of students. In the front you see Lecturer Gaertner programming snake on a huge whiteboard", gaertner);
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
        System.out.println("\n");
        System.out.println("(1) explore HTW campus");
        System.out.println("(2) show hero status");
        System.out.println("(3) show signature list");
        System.out.println("(4) start meditation");
        System.out.println("(5) return to menu");
        System.out.println("\n");
        System.out.println("Please choose a number between 1 and 5: ");
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
                this.showStatus();
                break;
            case "3":
                this.heroPaper(hero.getSignedExerciseLeaders());
                break;
            case "4":
               this.meditation();
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
     * ->exploring bleibt true und eine neue Erkundung wird gestartet.
     * ->oder exploring wird false gesetzt und man explore wird nicht mehr ausgeführt.
     */
    private void explore(){
    roundCounter++;
    didBreak = false;
    boolean exploring = true;
        while(roundCounter<=24 && hero.isOperational() && exploring == true){
            int possibility = (int) (Math.random() * 100);

            if(possibility < 20){
                rooms[0].printInfoXX();
                exploring = askNewExploration();
            }
            else if(possibility < 23){
                System.out.println("You have encountered the Yeti.....");
                    
                    alienEncounter(alien1, 25);   
                    exploring = askNewExploration();
            }
            else if(possibility <40){
                System.out.println("You have encountered Snailo the great.....");
                
                    alienEncounter(alien3, 10);
                    exploring = askNewExploration();
                //}
            }
            else if(possibility < 57){
                System.out.println("You have encountered Siomon the destroyer.....");
                    
                    alienEncounter(alien4, 15);
                    exploring = askNewExploration();
            }
            else if(possibility < 72){
                System.out.println("You have encountered Crazy frog.....");
                alien0.printMenu();
                hero.showstats();
                alien0.healHero(hero);
                exploring = askNewExploration();
            }
            else{
            int roomRnndm = (int) (Math.random() * 5);
            HTWRoom room = rooms[roomRnndm];

                if(room.getLecturer() != null){
                    exploring = lecturerEncounter(room.getLecturer(), room);
                }
            }
        }
        if(roundCounter >=24){
            looseScreen();
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
    public boolean askNewExploration(){
        while(true){
            System.out.println("\n" + "choose an action:");
            System.out.println("\n");
            System.out.println("(1) continue exploring");
            System.out.println("(2) return to game menu");
            String choice = EscapeApp.readUserInput();
                if(choice.equals("1")){
                    System.out.println("starting a new round.........");
                    roundCounter++;
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
     * @return false bei beiden Aktionen -> man kehrt immer ins Spielmenü zurück
     */

    public boolean lecturerEncounter(Lecturer lecturer, HTWRoom htwRoom){
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
                    return false;
                    }
                    break; 
                case "2":
                    System.out.println("returning to the game menu.....");
                    return false;
                default:
                System.out.println("Invalid input. Please choose a correct number between 1 and 2");
                break;
            }
        }
    }
    
    /**
     * Zeigt die Liste mit Informationen des Hero-Status an.
     * Spieler kann nach Einsicht ins Spielmenü zurückkehren. 
     */
    
    public void showStatus(){
        hero.heroStats();
        while(true){
        System.out.println("\n");
        System.out.println("(1) return to gamemenu");
        System.out.println("\n");
        String input = EscapeApp.readUserInput();
            if(input.equals("1")){
                return;
            }
            else{
                System.out.println("Invalid input. Please choose a correct number (1)");
            }
        } 
    }

    /**
     * Der Spieler hat die Möglichkeit eine kurze oder lange Meditation durchzuführen.
     * Bei einer kurzen Meditation regeneriert der Spieler 3 HP und didBreak wird true gesetzt, wodurch keine kurze Meditation mehr möglich ist.
     * Bei einer langen Meditation regeneriert der Spieler 10 HP und eine Runde vergeht.
     */
    public void meditation(){
        
        while(true){
            System.out.println("=====================");
            System.out.println("meditation-menu:");
            System.out.println("\n");
            System.out.println("(1) short meditation(3HP) -> possible once a round");
            System.out.println("(2) long meditation(10HP) -> costs one round");
            System.out.println("\n");
            System.out.println("(3) return to gamemenu");
            System.out.println("choose an action:");
            System.out.println("\n");
            String meditationinput = EscapeApp.readUserInput();
            

            switch (meditationinput) {
                case "1":
                    if(didBreak == false){
                    hero.regenerate(false);
                    didBreak = true;
                    break;
                    }
                    else{
                        System.out.println("You allready took a short break. You need to start a new round first!");
                        break;
                    } 
                case "2":
                    hero.regenerate(true);
                    roundCounter++;
                    didBreak=  false;
                    break;
                case "3":
                    System.out.println("returning to the gamemenu........");
                    return;
                default:
                System.out.println("Invalid input. Please choose a correct number between 1 and 3");
                break;
            }
        }
    }
    /**
     * Nimmt einen Lecturer-Array und gibt den Laufzettel mit den Lecturern aus.
     * Spieler kann nach Einsicht des Laufzettels ins Spielmenü zurückkehren.
     * @param arr Array der Lecturer, deren Unterschrift schon gesammelt wurde
     */
    public void heroPaper(Lecturer[] arr ){
        System.out.println("========================");
        System.out.println("Signed-Exerciseleaders");
        System.out.println("\n");
        
        for(int i=0; i<arr.length; i++){
            if(arr[i] != null){
                System.out.println("Nr." + (i+1) + " " + arr[i].getName());
            }
            else{
                System.out.println("Nr."+ (i+1) + " *empty*");
            }
        }
        while(true){
        System.out.println("\n");
        System.out.println("(1) return to gamemenu");
        System.out.println("\n");
        String input = EscapeApp.readUserInput();
            if(input.equals("1")){
                return;
            }
            else{
                System.out.println("Invalid input. Please choose a correct number (1)");
            }
        }
     
    }

    public void alienEncounter(Alien alien, int amount){
        System.out.println("What are you going to do?");
        while(hero.isOperational() && !alien.isDefeated()){
            alien.printMenu();
            hero.showstats();
            System.out.println("----------------");
            System.out.println("(1) attack");
            System.out.println("(2) flee");
            System.out.println("\n");
            String choice = EscapeApp.readUserInput();

            if(choice.equals("1")){
                int heroDamage = hero.attack();
                alien.takeDamage(heroDamage);
                if(alien.isDefeated()){
                    System.out.println("The Alien has been defeated! Returning to the gamemenu....");
                }
            }
            else if(choice.equals("2")){
                hero.flee();
                return;
            }
            else{
                System.out.println("Invalid input. Please choose a correct number (1)");

            }
            if(!alien.isDefeated()){
                System.out.println("The alien prepares an attack!");
                int alienDamage = alien.attack(alien, amount);
                hero.takeDamage(alienDamage);
            }

        }
    }

    private void looseScreen(){
        System.out.println("----------------------");
        System.out.println("GAME OVER:");
        System.out.println("You didn't manage to collect all the certificates in time.");
        System.out.println("\n");
        System.out.println("Prof. Majuntke: You really bore me.");
        System.out.println("I have no time for beginners.");
        System.out.println("Have fun with my aliens for the rest of your life!");
        System.out.println("MUHAHAHAHA!");
        System.out.println("Beam me up, Scotty!");
        System.out.println("\n");
        System.out.println("*Prof. Majuntke gets beamt into the ufo and flies into the abyss...*");
        System.out.println("\n");
        System.out.println("(1) return to the main menu");
        System.out.println("\n");
        System.out.println("Choose an action");
        System.out.println("\n");
        System.out.println("----------------------");
        
        String input = EscapeApp.readUserInput();
        if(input.equals("1")){
            gameRunning = false;
        }


    }

}
