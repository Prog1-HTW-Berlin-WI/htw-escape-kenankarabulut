package model;

/**
*
* Klasse AngrySnail mit jeweiligen Attributen und Operationen.
* für feindlich gesinnte Aliens.
* Unterklasse der Klasse Alien.
* @author Kenan Karabulut 
* @author Kevin Kruschel
*/
public class AngrySnail extends Alien{

    private int lifePoints;
    
    /**
     * Konstruktor für die Klasse AngrySnail (Unterklasse von Alien)
     * @param name Name des Aliens
     * @param lifePoints aktuelle Lebenspunkte
     * @param greeting Begrüßung des Aliens bei Begegnung
     */
    public AngrySnail(String name, int lifePoints, String greeting){
        super(name, lifePoints, false, greeting);
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }

    
}
