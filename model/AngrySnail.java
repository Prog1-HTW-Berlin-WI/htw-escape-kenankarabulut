package model;

public class AngrySnail extends Alien{
    
    /**
     * Konstruktor für die Klasse AngrySnail (Unterklasse von Alien)
     * @param name Name des Aliens
     * @param lifePoints aktuelle Lebenspunkte
     * @param greeting Begrüßung des Aliens bei Begegnung
     */
    public AngrySnail(String name, int lifePoints, String greeting){
        super(name, lifePoints, false, greeting);
    }
}
