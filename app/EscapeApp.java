
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
    * @author Kenan Karabulut
    * @author Kevin Kruschel
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
    * @author Kenan Karabulut 
    * @author Kevin Kruschel
    */
    private void showMainMenu() {
        System.out.println("You're in the main menu");
        System.out.println("What do you want to do next?");
        System.out.println("(1) Start new game");
        System.out.println("(6) Quit");
        System.out.println("");
        System.out.println("Please choose a number between 1 and 6: ");
    }

    /**
    *
    * Speicher die Spielereinigabe in "userInput" gibt diese als String zurück
    * @author Kenan Karabulut 
    * @author Kevin Kruschel
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
    * @author Kenan Karabulut 
    * @author Kevin Kruschel
    */
    private void handleUserInput(String input) {
        switch (input) {
            case "1":
                this.startGame();
                break;
            case "2":
                break;
            // ...
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
    * @author Kenan Karabulut 
    * @author Kevin Kruschel
    */
    private void startGame() {
        this.game = new EscapeGame();
        resumeGame();
    }

    /**
    *
    * Nimmt Input des Spielers und führt jeweilige Aktion aus
    * @author Kenan Karabulut 
    * @author Kevin Kruschel
    */
    private void resumeGame() {
        this.game.setGameRunning(true);
        this.game.run();
    }

    /**
    *
    * löscht den gespeicherten Spielstand
    * @author Kenan Karabulut 
    * @author Kevin Kruschel
    */
    private void deleteGame() {
        if (new File(SAVE_FILE_NAME).delete()) {
            System.out.println("Game deleted!");
        }
    }

    /**
    *
    * Versucht das Spiel in einer Datei zu Speichern
    * Wenn Speicherung fehlschlägt gibt Methode eine Fehlermeldung auf der Konsole aus
    * @author Kenan Karabulut 
    * @author Kevin Kruschel
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
    * @author Kenan Karabulut 
    * @author Kevin Kruschel
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
    * @author Kenan Karabulut 
    * @author Kevin Kruschel
    */
    private boolean isGameRunning() {
        return game != null;
    }

    /**
    *
    * Prüft ob das Spiel gestartet worden ist und beendet wurde und gibt boolean-Wert zurück
    * @author Kenan Karabulut 
    * @author Kevin Kruschel
    */
    private boolean isGameFinished() {
        return game != null && game.isGameFinished();
    }

    /**
    *
    * Prüft ob das Spiel gespeichert wurde und gibt boolean-Wert zurück
    * @author Kenan Karabulut 
    * @author Kevin Kruschel
    */
    private boolean hasSavedGame() {
        return new File(SAVE_FILE_NAME).exists();
    }

}
