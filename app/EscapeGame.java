package app;

import model.Hero;
import model.HTWRoom;

/**
*
* Klasse für den Ablauf innerhalb des Spiels.
* @author Kenan Karabulut 
* @author Kevin Kruschel
*/
public class EscapeGame {
    private final Hero hero;
    private final HTWRoom[] rooms = new HTWRoom[3];
    private boolean gameRunning = true;
    private boolean gameFinished = false;

    /**
    *
    * Konstruktor für die Klasse EscapeGame
    * Erstellt ein neues Objekt der Klasse Hero
    * @author Kenan Karabulut 
    * @author Kevin Kruschel
    */
    public EscapeGame() {
        this.hero = new Hero();
    }

    /**
    *
    * Prüft ob das Spiel gestartet worden ist und gibt boolean Wert zurück
    * @author Kenan Karabulut 
    * @author Kevin Kruschel
    */
    public boolean isGameRunning() {
        return gameRunning;
    }

    /**
    *
    * Setzt den Zustand des Spiels (ob es läuft oder nicht)
    * @author Kenan Karabulut 
    * @author Kevin Kruschel
    */
    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
    }

    /**
    *
    * Prüft ob das Spiel beendet wurde 
    * gibt boolean-Wert zurück
    * @author Kenan Karabulut 
    * @author Kevin Kruschel
    */
    public boolean isGameFinished() {
        return gameFinished;
    }

    /**
    *
    * Setzt den Zustand des Spiels (ob es beendet wurde oder nicht)
    * @author Kenan Karabulut 
    * @author Kevin Kruschel
    */
    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    /**
    *
    * Startet das Spiel oder setzt es fort(wenn es schon gestartet wurde)
    * @author Kenan Karabulut 
    * @author Kevin Kruschel
    */
    public void run() {
        System.out.println("The game has started. Or not?");
    }

    /**
    *
    * Getter-Methode für den Referenzdatentyp "Hero" 
    * (gibt aktuelles Objekt der Klasse Hero zurück)
    * @author Kenan Karabulut 
    * @author Kevin Kruschel
    */
    public Hero getHero() {
        return hero;
    }
}
