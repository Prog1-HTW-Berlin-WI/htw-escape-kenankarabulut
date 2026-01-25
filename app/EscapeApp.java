package app;

import model.Hero;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
*
* Klasse für das Hauptmenü des Spiels.
* @author Kenan Karabulut
* @author Kevin Kruschel
*/
public class EscapeApp {

    public static final String SAVE_FILE_NAME = "save";
    public static int roundCounter;
    private EscapeGame game;
    private boolean gameRunning = true;

    /**
    *
    * Startet das Spiel und initialisiert die Spielumgebung.
    */
    public static void main(String[] args) {
        System.out.println("Welcome to the HTW escape");
        System.out.println("========================================\n");

        EscapeApp app = new EscapeApp();

        while (true) {
            app.showMainMenu();
            String choice = readUserInput();
            app.handleUserInput(choice);
            System.out.println("====================");
        }
    }

    /**
    *
    * Zeigt die verschiedenen Aktionen im Hauptmenü an.
    * Unter Berücksichtigung von bestimmten If-Bedingungen.
    */
    private void showMainMenu() {
        System.out.println("You're in the main menu");
        System.out.println("What do you want to do next?");

        if(!isGameRunning() || isGameFinished())
        System.out.println("(1) Start new game");
        if(isGameRunning())
        System.out.println("(2) resume game");
        if(hasSavedGame())
        System.out.println("(3) load game");
        if(isGameRunning())
        System.out.println("(4) save game");
        if(hasSavedGame())
        System.out.println("(5) delete game");
        System.out.println("(6) close game");
        System.out.println("\n");
        System.out.println("Please choose a number between 1 and 6: ");
    }

    /**
    *
    * Erstellt einen Scanner und liest die Eingabe des Spielers.
    * @return Eingabe des Spielers
    */
    public static String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        // TBD
        return userInput;
    }

    /**
    *
    * Nimmt die Eingabe des Spielers als Parameter und führt jeweilige Aktion(case) aus.
    * Unter Berücksichtigung von bestimmten if-Bedingungen.
    * @param input (Eingabe des Spielers)
    */
    private void handleUserInput(String input) {
        switch (input) {
            case "1":
                if(!isGameRunning() || isGameFinished()){
                this.startGame();
                break;
                }
                else{
                    System.out.println("Invalid input. You allready started a game. You need to delete it first");
                    break;
                }
                
            case "2":
                if(isGameRunning()){
                this.resumeGame();
                break;
                }
                else{
                    System.out.println("Invalid input. You need to start a game first.");
                    break;
                }
            case "3":
                if(hasSavedGame()){
                this.loadGame();
                break;
                }
                else{
                    System.out.println("Invalid input. You need a saved game first.");
                    break;
                }
            case "4":
                if(isGameRunning()){
                this.saveGame();
                break;
                }
                else{
                    System.out.println("Invalid input. You need to start a game if you want so save.");
                    break;
                }
            case "5":
                if(hasSavedGame()){
                this.deleteGame();
                break;
                }
                else{
                    System.out.println("Invalid input. You need to save a game first if you want to delete it.");
                    break;
                }
            case "6":
                System.exit(0);
            default:
                System.out.println("Invalid input. Please choose a correct number between 1 and 6");
                break;
        }
    }

    /**
    *
    * Erstellt ein neues Spiel und erzeugt einen Hero.
    * Eingabe des Spielers ist Name des Heros.
    */
    private void startGame() {
        while(true){
            showIntro();
            System.out.println("==========================================");
            System.out.println("Create your hero:");
            System.out.println("\n");
            System.out.println("please enter the name of your hero:");
            System.out.println("(max. 10 letters)");
            System.out.println("\n");

            String nameInput = readUserInput();
            if(nameInput.length()<=10 && nameInput.length()>1){
                Hero myHero = new Hero(nameInput);
                this.game = new EscapeGame(myHero);
                resumeGame();
                break;
            }
            else{
                System.out.println("==========================================");
                System.out.println("Invalid name. Please try again (max. 10 letters)");
            }
        }
    }

    /**
    *
    * Ändert den Zustand des Spiels auf "laufend" und setzt das Spiel fort.
    */
    private void resumeGame() {
        this.game.setGameRunning(true);
        this.game.run();
    }

    /**
    *
    * Löscht den gespeicherten Spielstand und setzt das Spiel zurück.
    * Konsolenausgabe bestätigt erfolgreiche Löschung des Spiels.
    */
    private void deleteGame() {
        if (new File(SAVE_FILE_NAME).delete()) {
            game = null;
            System.out.println("Game deleted!");
        }
    }

    /**
    *
    * Versucht das Spiel in einer Datei zu speichern.
    * Wenn Speicherung fehlschlägt, gibt Methode eine Fehlermeldung auf der Konsole aus.
    */
    private void saveGame() {
        try (FileOutputStream fos = new FileOutputStream(SAVE_FILE_NAME);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(game);
            oos.flush();
        } catch (Exception ex) {
            System.err.println("Something went wrong while saving the game: " + ex.getMessage());
            return;
        }
        System.out.println("Game saved!");
    }

    /**
    *
    * Sucht nach einem Spielstand auf dem Rechner und lädt diesen, falls vorhanden.
    * Wenn Spielstand nicht geladen werden konnte, gibt Methode eine Fehlermeldung auf der Konsole aus.
    */
    private void loadGame() {
        try (FileInputStream fis = new FileInputStream(SAVE_FILE_NAME);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            this.game = (EscapeGame) ois.readObject();
            System.out.println("Game loaded!");
        } catch (Exception ex) {
            System.err.println("Something went wrong while loading the game: " + ex.getMessage());
        }
    }

    /**
    *
    * Prüft, ob das Spiel gestartet worden ist und gibt boolean Wert zurück.
    * @return boolean Wert der zeigt, ob Spiel existiert
    */
    private boolean isGameRunning() {
        return game != null;
    }

    /**
    *
    * Prüft ob das Spiel gestartet worden ist und beendet wurde und gibt boolean-Wert zurück.
    * @return boolean Wert der zeigt, ob Spiel existiert und beendet wurde.
    */
    private boolean isGameFinished() {
        return game != null && game.isGameFinished();
    }

    /**
    *
    * Prüft ob das Spiel gespeichert wurde und gibt boolean-Wert zurück.
    * @return boolean Wert der zeigt, ob Spielstand existiert.
    */
    private boolean hasSavedGame() {
        return new File(SAVE_FILE_NAME).exists();
    }

    private void showIntro(){
        System.out.println("============================");
        System.out.println("Intro");
        System.out.println("\n");
        System.out.println("It's a normal high school day. You barely manage to get out of bed and nearly missed your train to the HTW campus.");
        System.out.println(" On the way you look at your schedule plan and notice that the next lecture is programming with Prof. Majuntke.");
        System.out.println("You enter the room and notice that no one is there. Then you remember that today's lesson is hosted on Zoom.");
        System.out.println("As you return to the front door to leave the campus you notice, that its locked. Suddenly you can hear Prof. Majuntke through the speaker:");
        System.out.println("\"Hello Student. Today is your lucky day where you can win a special certificate from me,");
        System.out.println("which will guarantee you any job related to programming you can imagine, but it comes with a cost.");
        System.out.println("Collect all the 5 signatures from the Lecturers and find me but look out. There could be dangers awaiting you on the way.....\"");
        System.out.println("\n");
        System.out.println("(1) start");

        while(true){
        String input =readUserInput();
            if(input.equals("1")){
                break;
            }
            else{
                System.out.println("Invalid input. Please choose a correct number (1)");
            }
        }
    }

}
