package model;

public class Yeti extends Alien {
    
    
    /**
     * Konstruktor für die Klasse Yeti (Unterklasse von Alien).
     * Erzeugt Yeti mit seinen Attributen.
     */
    public Yeti(){
        super("Yeti", 200, false,"\"AAAAAAAARRRRRRGGGGGGHHH IM HUNGRY. I have no idea how I've got here!. Ohhh you look tasty...... \"");
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

    @Override
    public void printMenu() {
        super.printMenu();
    }
    
}
