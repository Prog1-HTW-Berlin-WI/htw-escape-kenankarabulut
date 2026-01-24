package model;

/**
*
* Klasse CrazyFrog mit jeweiligen Attributen und Operationen.
* für das freundlich gesinnte Alien, welches des Spieler heilt.
* Unterklasse der Klasse Alien
* @author Kenan Karabulut 
* @author Kevin Kruschel
*/
public class CrazyFrog extends Alien {

    
    /**
     * Konstruktor für die Klasse AngrySnail (Unterklasse von Alien)
     * Erzeugt CrazyFrog mit seinen Attributen.
     * 
     */
    public CrazyFrog(){
        super("Crazy Frog", 20, true,"RingDingDingDing!");
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public boolean isDefeated() {
        return super.isDefeated();
    }

    @Override
    public void takeDamage(int amount) {
        super.takeDamage(amount);
    }
    
    /**
     * Alien heilt den Helden um 10 Lebenspunkte.
     * Ruft die Methode "takeHealing" der Klasse "Hero" aus.
     * @param hero
     */
    public void healHero(Hero hero){
        hero.takeHealing(10);
        System.out.println("Crazy Frog used a magic spell and healed 10 lifepoints!");
    }

    @Override
    public void printMenu() {
        super.printMenu();
    }

    
}
