package model;
import java.io.Serial;
import java.io.Serializable;

/**
*
* Klasse HTWRoom mit jeweiligen Attributen und Operationen.
* @author Kenan Karabulut 
* @author Kevin Kruschel
*/
public class HTWRoom implements Serializable {

    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden.
    private static final long serialVersionUID = 9065680017147292999L;
    private String identifier;
    private String description;
    private Lecturer lecturer;
    
    
    /**
     * Konstruktor für die Klasse HTWRoom.
     * Erzeugt neuen HTWRoom mit seinen Attributen.
     * @param identifier
     * @param description
     * @param lecturer
     */
    public HTWRoom(String identifier, String description, Lecturer lecturer) {
        this.identifier = identifier;
        this.description = description;
        this.lecturer = lecturer;
    }

    

    public Lecturer getLecturer() {
        return lecturer;
    }



    /**
     * Die Methode ist eine Schablone wie die Räume im Spiel angezeigt werden.
     * wird im EscapeGame unter "explore HTW campus" verwendet.
     */
    public void printInfo() {
        System.out.println("-----------------------");
        System.out.println("roomnumber: " + identifier );
        System.out.println("----------");
        System.out.println("description: " + description);
        System.out.println("----------");
        System.out.println("lecturer :" + getLecturer());
        System.out.println("-----------------------");
    }




}




