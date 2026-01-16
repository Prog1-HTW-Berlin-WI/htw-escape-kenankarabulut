package model;
import java.io.Serializable;

/**
*
* Klasse Hero mit jeweiligen Attributen und Operationen.
* @author Kenan Karabulut 
* @author Kevin Kruschel
*/
public class Hero implements Serializable {

    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden.
    private static final long serialVersionUID = 3578735620108186013L;
    private String name;
    private int healthPoints = 50;
    private int experiencePoints = 0;
    private Lecturer[] signedExerciseLeaders;
    private int killCounter;
    


    /**
    *
    * Getter-Methode für das Attribut KillCounter
    * (gibt aktuelle Anzahl der getöteten Aliens zurück)
    * @return killCounter
    */
    public int getKillCounter() {
        return killCounter;
    }


    /**
     * Konstruktor für Objekte der Klasse Hero
     * @param name
     */
    public Hero(String name) {
        this.name = name;
    }

    /**
    *
    * Getter-Methode für das Attribut ExperiencePoints.
    * (gibt aktuelle Anzahl der ExperiencePoints zurück)
    * @return experiencePoints
    */
    public int getExperiencePoints() {
        return experiencePoints;
    }

    
    /**
    *
    * Getter-Methode für das Attribut HealthPoints.
    * (gibt aktuelle Anzahl der Healthpoints zurück)
    * @return experiencePoints
    */
    public int getHealthPoints() {
        return healthPoints;
    }

    /**
    *
    * Getter-Methode für das Attribut name.
    * (gibt namen des Heros zurück)
    * @return name
    */
    public String getName() {
        return name;
    }

    /**
    *
    * Die Methode bekommt einen int-Wert und reduziert die healthPoints um diesen Wert.
    * Die Konsole gibt eine Nachricht mit der Anzahl des Schadens aus oder eine Nachricht falls der Hero besiegt wurde.
    * @param amount (Anzahl an Schaden)
    */
    public void takeDamage(int amount) {
    this.healthPoints -= amount;
    System.out.print("You've suffered " + amount + " damage!");
        if (this.healthPoints < 0){
            this.healthPoints = 0;
            System.out.print("Hero: " + getName()+ "has been defeated");
        }
    }
   
    /**
    *
    * Ermöglicht es dem Helden sich zu regenerieren.
    * Dabei kann er aus zwei Optionen wählen.
    * ist longRest = true heilt der Held 3LP.
    * ist longRest = false heilt der Held 10LP.
    * Es gib eine Konsolenausgabe mit der Anzahl der regnenerierten LP.
    * und eine Ausgabe falls max Anzahl an LP erreicht wurde.
    * 
    * @param longRest boolscher Wert der bestimmt ob es eine lange oder kurze Pause ist
    */
    public void regenerate(boolean longRest) {
        if (longRest == false){
            this.healthPoints += 3;
            System.out.println("You've took a short break and regenerated 3 lifepoints");
        }
        if (longRest == true){
            this.healthPoints += 10;
            System.out.println("You took a long break and regenerated 10 lifepoints");
        }
        if (this.healthPoints > 50){
            System.out.println("You've gained max health!");
            this.healthPoints = 50;
        }
    }

    /**
     * Ermöglicht dem Helden vor einem Kampf zu fliehen (mit einer Wahrscheinlichkeit von 42%).
     * Konsolenausgabe ob Hero fliehen konnte.
     * @return true falls Flucht erfolgreich
     * @return false falls Flucht fehlgeschlagen
     */
    public boolean flee(){
        if((Math.random() * 100) < 42){
            System.out.println("You've successfully gotten away!");
            return true;
        }
    System.out.println("The Escape has failed!");
    return false;
    }

    /**
     * Ermöglicht es dem Helden ein feindliches Wesen anzugreifen.
     * Zu 75% ist dies einer normaler Treffer.
     * Zu 13% verfehlt der Treffer.
     * Zu 12% ist der Treffer kritisch.
     * Konsolenausgabe mit der Anzahl des Schadens und Art des Treffers.
     * @return ausgeteilter Schaden
     */

    public int attack(){
    int damage = 0;
    int possibility = (int) (Math.random() * 100);
        if(possibility < 75){
            damage = (int) (getExperiencePoints() * 2.3 + 1);
            System.out.println("You've hit the enemy and dealt: " + damage );
        }
        else if(possibility < 88){
            damage = 0;
            System.out.println("You've missed the enemy and dealt: " + damage );
        }
        else{
            damage = (int) (getExperiencePoints() * 2.3 + 1) * 2;
            System.out.println("You've hit the enemy and dealt: " + damage + " *CRITICAL HIT*" );
        }
    return damage;
    }

    /**
     * Zeigt an ob Lecturer schon unterschrieben hat.
     * @return true wenn Lecturer unterschrieben hat.
     * @return false wenn Lecturer noch nicht unterschrieben hat.
     */
    public boolean isSignedExcerciseLeader(Lecturer lecturer) {
        for(int i=0; i<signedExerciseLeaders.length; i++){
            if (signedExerciseLeaders[i] == lecturer) {
                return true;
            }
        }
        return false;
    }

    /**
     *  
     * Ermöglicht es dem Helden die Unterschrift des Lecturers auf den Laufzettel zu schreiben.
     * Konsolenausgabe falls Unterschift eingesammelt wurde oder Fehlermeldung, wenn Hero Unterschrift schon besitzt.
     * @param lecturer bei dem die Unterschift auf Laufzettel geschrieben werden soll
     */
    public void signExerciseLeader (Lecturer lecturer){
        if(isSignedExcerciseLeader(lecturer)){
            System.out.println("Action failed. You already got the signature of this exercise leader");
            return;
        }
        for(int i=0; i<signedExerciseLeaders.length; i++){
            if (signedExerciseLeaders[i] == null){
                signedExerciseLeaders[i] = lecturer;
                System.out.println("You've collected "+ lecturer.getName() +"'s signature!" );
                return;
            }
        }
    }
    /**
     * Addiert die Erfahrensppunkte zu den aktuellen Erfahrungspunkten.
     * @param experiencePoints (Erfahrenspunkte die addiert werden)
     */
    public void addExperiencePoints(int experiencePoints){
        this.experiencePoints += experiencePoints;
    }

    /**
     * Gibt an ob der Hero noch handlungsfähig ist.
     * @return ob healthPoints über 0 sind
     */
    public boolean isOperational(){
        return this.healthPoints > 0;
    }

    /**
     * Addiert gegebenen Wert den healthPoints dazu.
     * Für Klasse CrazyFrog der diese Methode auslöst(Hero heilt).
     * @param amount (Anzahl an healthPoints die man dazuaddiert)
     */
    public void takeHealing(int amount){
        this.healthPoints += amount;
        if (this.healthPoints > 50){
            System.out.println("You've gained max health!");
            this.healthPoints = 50;
        }
    }


    /**
     * Kurze Übersicht des Status des Heros.
     * Wird angezeigt, wenn der Spieler einem Alien begegnet.
     */
    public void showstats(){
        System.out.println("-----------------------");
        System.out.println("Hero-stats");
        System.out.println("healthpoins: " + healthPoints);
        System.out.println("experiencepoints: " + experiencePoints);
        System.out.println("-----------------------");
    }

}
