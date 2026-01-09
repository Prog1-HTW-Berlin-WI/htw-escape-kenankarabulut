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
    *
    */
    public EscapeGame() {
        this.hero = new Hero();
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
    * Startet das Spiel oder setzt es fort(wenn es schon gestartet wurde)
    * 
    */
    public void run() {
        System.out.println("The game has started. Or not?");
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
}
