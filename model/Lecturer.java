package model;
import java.io.Serializable;

/**
*
* Klasse Lecturer mit jeweiligen Attributen und Operationen.
* @author Kenan Karabulut 
* @author Kevin Kruschel
*/
public class Lecturer implements Serializable {

    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden.
    private static final long serialVersionUID = 540082607047283589L;
    private String name;
    private boolean hasSigned = false;

    
    /**
     * Konstruktor für die Klasse Lecturer.
     * Erzeugt neuen Lecturer mit seinen Attributen.
     * @param name
     * @param hasSigned
     */
    public Lecturer(String name, boolean hasSigned) {
        this.name = name;
        this.hasSigned = hasSigned;
    }

    /**
     * Getter-Methode für das Attribut name.
     * (gibt namen des Lecturers zurück)
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Zeigt ob Bedingung erfüllt ist, damit der Lecturer den Laufzettel unterschreibt.
     * Bedingt mindestens 3 Kills des Heros.
     * @param hero (Instanz der Klasse Hero)
     * @return true falls Bedingung erfüllt sonst false
     */
    public boolean isReadyToSign(Hero hero){
        if (hero.getKillCounter() >=3){
            return true;
        }
        return false;
    }

    /**
     * Lässt den Übungsleiter unterschreiben/markiert Ihn als erledigt.
     */
    public void sign(){
        this.hasSigned = true;
    }

}