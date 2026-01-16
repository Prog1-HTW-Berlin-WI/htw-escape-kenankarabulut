package model;

/**
*
* Klasse Alien mit jeweiligennAttributen und Operationen.
* @author Kenan Karabulut 
* @author Kevin Kruschel
*/
public abstract class Alien {

    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden.
    private static final long serialVersionUID = 1729389822767173584L;
    private String name;
    private int lifePoints;
    private boolean friendly;
    private String greeting;


     
    

    /**
     * Konstruktor für die Klasse Alien
     * Erzeugt neues Alien mit seinen Attributen
     * Die Begrüßung variiert danach ob das Alien freundlich gesinnt ist oder nicht
     * @param name
     * @param lifePoints
     * @param friendly
     */

    public Alien(String name, int lifePoints, boolean friendly, String greeting) {
        this.name = name;
        this.lifePoints = lifePoints;
        this.friendly = friendly;
        this.greeting = greeting;

       // if(this.friendly == true){
            //this.greeting ="Hello dear student. Do you want to train programming with me :)?";
       // }
        //else{
        //    this.greeting ="Muhahaha you week student behold my power!";
        //}
    }
    

    /**
    *
    * Getter-Methode für das Attribut name
    * (gibt namen des Aliens zurück)
    * @return name
    */
    public String getName() {
    return name;
    }


    /**
    *
    * Die Methode bekommt einen int-Wert und reduziert die lifePoints um diesen Wert
    * Die Konsole gibt eine Nachricht mit der Anzahl des Schadens aus oder eine Nachricht falls das Alien besiegt wurde
    * @param amount (Anzahl an Schaden)
    */
    public void takeDamage(int amount) {
        this.lifePoints -= amount;
        System.out.println("Alien: " + getName() + "suffered " + amount + "damage.");
            if (this.lifePoints < 0){
                this.lifePoints = 0;
                System.out.print("Alien: " + getName()+ "has been defeated and crawls away.");
            }
    }

    /**
     * Gibt an ob das Alien besiegt wurde (keine Lebenspunkte mehr hat)
     * @return ob lifePoints gleich 0 sind
     */
    public boolean isDefeated(){
        return this.lifePoints == 0;
    }


    /**
     * Die Methode ist eine Schablone wie das Kampfmenü gegen ein Alien im Spiel angezeigt werden.
     *
     */
    public void printMenu(){
        System.out.println("===========================================");
        System.out.println("action: An opponent approched you!");
        System.out.println("----------");
        System.out.println("description: " + greeting);
        System.out.println("----------");
        System.out.println("alien lifepoints: " + lifePoints);
        System.out.println("===========================================");
    }


}