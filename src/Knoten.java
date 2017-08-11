import java.io.Serializable;
/**
 * Knoten Klasse
 *
 * @author Nabeel SMADI
 */
public class Knoten implements Serializable{
	public String daten; // Datenelement
	public Knoten links, rechts; // Zeiger auf Nachfolgeknoten
	public int grad, xPos , yPos; // Zeiger auf Nachfolgeknoten
	
	 /**
     * Klasse  Konstruktor 
     */   
	Knoten(String n,int g,int x,int y) {
		daten = n;
		links = null;
		rechts = null;
		grad = g;
		xPos = x;
		yPos = y;
	}
}
