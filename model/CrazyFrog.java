package model;

public class CrazyFrog extends Alien {

    private String greeting;
    
    /**
     * Konstruktor für die Klasse AngrySnail (Unterklasse von Alien)
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
