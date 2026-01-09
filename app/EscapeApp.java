package app;

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
    private EscapeGame game;
    private boolean gameRunning = true;

    /**
    *
    * Startet das Spiel und initialisiert die Spielumgebung.
    * 
    */
    public static void main(String[] args) {
        System.out.println("Welcome to the HTW escape");
        System.out.println("========================================\n");

        EscapeApp app = new EscapeApp();

        while (true) {
            app.showMainMenu();
            String choice = app.readUserInput();
            app.handleUserInput(choice);
            System.out.println("====================");
        }
    }

    /**
    *
    * Zeigt die verschiedenen Aktionen im Hauptmenü an
    * unter Berücksichtigung von bestimmten if-Bedingungen
    * 
    */
    private void showMainMenu() {
        System.out.println("You're in the main menu");
        System.out.println("What do you want to do next?");

        if(!isGameRunning())
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
        System.out.println("");
        System.out.println("Please choose a number between 1 and 6: ");
    }

    /**
    *
    * Speicher die Spielereinigabe in "userInput" gibt diese als String zurück
    * @return Eingabe des Spielers
    */
    private String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        // TBD
        return userInput;
    }

    /**
    *
    * Nimmt Input des Spielers als Parameter und führt jeweilige Aktion(case) aus
    * unter Berücksichtigung von bestimmten if-Bedingungen
    * @param String input (Eingabe des Spielers)
    * 
    */
    private void handleUserInput(String input) {
        switch (input) {
            case "1":
                if(!isGameRunning())
                this.startGame();
                break;
            case "2":
                if(isGameRunning()){
                this.resumeGame();
                }
                break;
            case "3":
                if(hasSavedGame())
                this.loadGame();
                break;
            case "4":
                if(isGameRunning())
                this.saveGame();
                break;
            case "5":
                if(hasSavedGame())
                this.deleteGame();
                break;
            case "6":
                break;
            default:
                System.out.println("Invalid input. Please choose a correct number between 1 and 6");
                break;
        }
    }
    /**
    *
    * erstellt neues Spiel und führt Methode "resumeGame()" aus
    * 
    */
    private void startGame() {
        this.game = new EscapeGame();
        resumeGame();
    }

    /**
    *
    * Ändert den Zustand des Spiels auf "laufend" und setzt das Spiel fort
    * 
    */
    private void resumeGame() {
        this.game.setGameRunning(true);
        this.game.run();
    }

    /**
    *
    * löscht den gespeicherten Spielstand und setzt das Spiel zurück
    * Konsolenausgabe bestätigt erfolgreiche Löschung des Spiels
    * 
    */
    private void deleteGame() {
        if (new File(SAVE_FILE_NAME).delete()) {
            game = null;
            System.out.println("Game deleted!");
        }
    }

    /**
    *
    * Versucht das Spiel in einer Datei zu Speichern
    * Wenn Speicherung fehlschlägt gibt Methode eine Fehlermeldung auf der Konsole aus
    *
    *
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
    * Sucht nach einem Spielstand auf dem Rechner und lädt diesen, falls vorhanden
    * Wenn Spielstand nicht geladen werden konnte, gibt Methode eine Fehlermeldung auf der Konsole aus
    * 
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
    * Prüft ob das Spiel gestartet worden ist und gibt boolean Wert zurück
    * @return boolean Wert der zeigt, ob Spiel existiert
    * 
    */
    private boolean isGameRunning() {
        return game != null;
    }

    /**
    *
    * Prüft ob das Spiel gestartet worden ist und beendet wurde und gibt boolean-Wert zurück
    * @return boolean Wert der zeigt, ob Spiel existiert und beendet wurde
    */
    private boolean isGameFinished() {
        return game != null && game.isGameFinished();
    }

    /**
    *
    * Prüft ob das Spiel gespeichert wurde und gibt boolean-Wert zurück
    * @return boolean Wert der zeigt, ob Spielstand existiert 
    */
    private boolean hasSavedGame() {
        return new File(SAVE_FILE_NAME).exists();
    }

}
