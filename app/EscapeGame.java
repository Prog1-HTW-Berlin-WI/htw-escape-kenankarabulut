package app;

import model.Hero;
import model.Lecturer;
import model.Majuntke;
import model.Questions;
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
    private final HTWRoom[] rooms = new HTWRoom[7];
    private boolean gameRunning = true;
    private boolean gameFinished = false;
    private int roundCounter=0;
    private boolean didBreak= false;
    private final Questions[] questions = new Questions[3];

    CrazyFrog alien0  = new CrazyFrog();
    Yeti alien1  = new Yeti();
    AngrySnail alien3 = new AngrySnail("Snailo the great", 30,"\"Hello you week student. I am Snailo the great. Behold my power Muhahahaha!\"");
    AngrySnail alien4 = new AngrySnail("Simon the destroyer", 12,"\"Hello you week student. I am Simon the destroyer. I may be small but my punches hurt. Lets see if you can keep up!\"");
    Majuntke majuntke = new Majuntke();
    

    /**
    *
    * Konstruktor für die Klasse Escapegame
    * Erzeugt ein neues EscapeGame und setzt einen Wert für seine Attribute fest
    * Die Begrüßung variiert danach ob das Alien freundlich gesinnt ist oder nicht
    */
    public EscapeGame(Hero hero) {
        this.hero = hero;
        Lecturer poeser = new Lecturer("Lecturer Poeser", false);
        Lecturer gnaoui = new Lecturer("Lecturer Gnaoui", false);
        Lecturer safitri = new Lecturer("Lecturer Safitri", false);
        Lecturer vaseva = new Lecturer("Lecturer Vaseva", false);
        Lecturer gaertner = new Lecturer("Lecturer Gaerner", false);

        questions[0] = new Questions("\"What does the static keyword refer to in Java?\"", " An objekt", "A data type", "An element", "A class",4);
        questions[1] = new Questions("\"Which data type would be used to represent the Roman numeral *VI* ?\"", "A String", "A char", "An integer", "A boolean", 1);
        questions[2] = new Questions("\"Which keyword is used to prevent a class from being subclassed ?\"", "static", "private", "final", "abstract", 3);
        
        rooms[0] = new HTWRoom("XX", "This Room ist empty. You can only hear water dripping down the heating-pipes.");
        rooms[1] = new HTWRoom("*cantine*", "You've entered the cantine. You can allread smell the sizzelning meat of the vegan saussage. In the far you see lecturer Poeser eating fries....", poeser);
        rooms[2] = new HTWRoom("A 015", "You've entered room A 015. You can see Lecturer gnaoui playing with the Java-hamster......", gnaoui);
        rooms[3] = new HTWRoom("A 142", "You've entered room A 142. You notice writing on the whiteboard which says *Prog Klausur* and Lecturer Safitri checking the ID cards of the students....", safitri);
        rooms[4] = new HTWRoom("A 219", "You've entered room A 219. You can allread hear aggressive clicking-sounds. In the back of the room you see Lecturer Vaseva playing tetris on her laptop", vaseva);
        rooms[5] = new HTWRoom("*audimax*", "You've entered the Audimax. You see a giant room full of students. In the front you see Lecturer Gaertner programming snake on a huge whiteboard", gaertner);
        rooms[6] = new HTWRoom("BOSS-ROOM", "As you collect your last signature you hear can hear a can hear a weird sound comming from the roof of the college building *⊑⟒⌰⌰⍜ ỻ⎍⋔⏃⋏⌇*. You decide to follow the weird sounds and see Prof. Majuntke sitting on a thrown next to a ufo", majuntke);
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
        System.out.println("Round-Counter: " + this.roundCounter);
        System.out.println("Game-Menu:");
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
        while(roundCounter<=24 && hero.isOperational() && exploring == true && !gameFinished && hero.lectCounter <5){
            int possibility = (int) (Math.random() * 100);

            if(possibility < 20){
                rooms[0].printInfoXX();
                exploring = askNewExploration();
            }
            else if(possibility < 23){
                System.out.println("You have encountered the Yeti.....");
                    
                alien1.setLifePoints(200); 
                alienEncounter(alien1, 25, 60); 
                exploring = askNewExploration();
            }
            else if(possibility <33){
                System.out.println("You have encountered Snailo the great.....");
                
                alien3.setLifePoints(30);
                alienEncounter(alien3, 10, 25);
                exploring = askNewExploration();
        
            }
            else if(possibility < 57){
                System.out.println("You have encountered Siomon the destroyer.....");
                    
                alien4.setLifePoints(12);
                alienEncounter(alien4, 15,10);
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
        if(roundCounter >=24 || !hero.isOperational()){
            looseScreen();
        }
        if(hero.lectCounter >=5){
            int questRnndm = (int) (Math.random() * 3);
            Questions quest = questions[questRnndm];
            finalEncounter(quest);
            return;
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
     * Methode nimmt zufällige Frage und zeigt diese an.
     * Raum mit Frau Majuntke wird angezeigt.
     * Der Spieler hat die Chance in zwei Versuchen eine Multiple-Choice Frage richtig zu beantworten um das Spiel zu gewinnen.
     * Im zweiten Versuch wird wieder eine zufällige Frage von 3 ausgewählt.
     * Wenn spieler im zweiten Versuch Frage falsch beantwortet, verliert er.
     * @param actualQuestion Frage die zufällig in der Methode explore() ausgewählt wurde.
     */
    private void finalEncounter(Questions actualQuestion){
        int guess= 0;
        Scanner scanner = new Scanner(System.in);
        rooms[6].printInfoMA();
        while(guess <2){
            actualQuestion.showQuest();
            int answ = scanner.nextInt();
            if(actualQuestion.isRightAnswer(answ) == true && guess<2){
                winScreen();
                break;
            }
            else{
                guess++;
                if(guess <2){
                    System.out.println("Here you week student. I will give you another try. Dont dissapoint me!");
                    int nextQest = (int) (Math.random() * 3);
                    actualQuestion = questions[nextQest];
                }
            }
        }
        if(guess >=2){
            looseScreen();
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

    /**
     * Im Falle eines Kampfes kann der Spieler kämpfen oder Fliehen.
     * Nach Aktion des Heros führt das Alien eine Attacke aus(kann Hero treffen oder verfehlen).
     * Kampf ist abhängig vom Alien und Anzahl der Schadenspunkte, welche das Alien verursacht.
     * Wärend des Kampfes wird eine kleine Statusanzeige des Heros und des Aliens angezeigt.
     * @param alien Alien welches man bekämpft
     * @param amount Schadenspunkte welche das Alien verursacht 
     * @param exp Erfahrenspunkte welche man bekommt nachdem Alien besiegt wurde
     */
    public void alienEncounter(Alien alien, int amount, int exp){
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
                    System.out.println("The Alien has been defeated!");
                    hero.addExperiencePoints(exp);
                    System.out.println("Returning to the gamemenu......");
                }
            }
            else if(choice.equals("2")){
                if(hero.flee() == true){
                    return;
                }
                else{
                    System.out.println("The Escape has failed!");
                    System.out.println("=======================");
                }
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


    /**
     * Wird angezeigt wenn der Hero das Spiel verliert.
     * beendet das Spiel.
     */
    private void looseScreen(){
        System.out.println("----------------------");
        System.out.println("GAME OVER:");
        System.out.println("You didn't manage to collect all the certificates in time.");
        System.out.println("\n");
        System.out.println("Prof. Majuntke: \"You really bore me.\"");
        System.out.println("\"I have no time for beginners.\"");
        System.out.println("Have fun with my aliens for the rest of your life!");
        System.out.println("\"MUHAHAHAHA!\"");
        System.out.println("\"Beam me up, Scotty!\"");
        System.out.println("\n");
        System.out.println("*Prof. Majuntke gets beamt into the ufo and flies into the abyss...*");
        System.out.println("\n");
        System.out.println("(1) return to the main menu");
        System.out.println("\n");
        System.out.println("Choose an action");
        System.out.println("\n");
        System.out.println("----------------------");
        
        while(true){
            String input = EscapeApp.readUserInput();
            if(input.equals("1")){
                setGameRunning(false);
                setGameFinished(true);
                break;
            }
            else{
                System.out.println("Invalid input. Please choose a correct number (1)");
                break;
            }
        }
    }

    /**
     * Wird angezeigt wenn der Hero das Spiel gewinnt.
     * beendet das Spiel.
     */
    private void winScreen(){
        System.out.println("===========================");
        System.out.println("CONGRATULATIONS!");
        System.out.println("\n");
        System.out.println("Prof. Majuntke \"Not bad you really got it!");
        System.out.println("Here take this certificate and keep working on your java-skills: *cerftificate*\"");
        System.out.println("Prof. Majuntke turns around and enters her ufo. As she turns away you notice a green spot on her neck.");
        System.out.println("The ufo starts with a loud noise and heads into the abyss.");
        System.out.println("\n");
        System.out.println("*The entrance door suddenly opens*");
        System.out.println("(1) leave the college");
        System.out.println("\n");
        System.out.println("Choose an action:");
        System.out.println("\n");
        System.out.println("===========================");
        
        while(true){
            String choice = EscapeApp.readUserInput();
            if(choice.equals("1")){
               setGameFinished(true);
               setGameRunning(true);
               break;
            }
            else{
                System.out.println("Invalid input. Please choose a correct number (1)");
                break;
            }
        }
    }

    

}
