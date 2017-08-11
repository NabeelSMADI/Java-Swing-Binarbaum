import java.io.Serializable;
/**
 * Baum Klasse
 *
 * @author Nabeel SMADI
 */
public class Baum implements Serializable{
	  public  Knoten wurzel=null;    // Baum ist leer
	   private int tiefe;

	   /**
	     *  Einfuegen eines Knotens
	     */   
	   public void einfuegen( String x,int xPos, int yPos) {
	       if (wurzel== null)
	           wurzel = new  Knoten(x,1,xPos,yPos);
	       else  einrek (wurzel,x,1,xPos,yPos);
	   }
	   /**
	     *  Einfuegen eines Knotens (hilfe methode)
	     */
	   private void einrek (Knoten p, String x,int grad,int xPos,int yPos){
	       if (x.compareTo(p.daten) < 0)  {
	           if (p.links==null)
	               p.links =new  Knoten(x,grad+1,xPos,yPos);
	           else einrek(p.links, x,grad+1,xPos,yPos);
	       }
	       else  if (p.rechts==null)
	                 p.rechts =new  Knoten(x,grad+1,xPos,yPos);
	             else  einrek (p.rechts,x,grad+1,xPos,yPos);
	   }
	 
		
	   /**
	     *  entfernen eines Knotens
	     */
		  public boolean entfernen(String x) {
			  Knoten Ziel = null;
			  Knoten Eltern = null;
			  Knoten Knote = wurzel;
			   
			  while (Knote != null)
		        {
		            if (x.compareTo( Knote.daten ) == 0)  
		            {
		            	Ziel = Knote;
		                break;
		            }
		            else if (x.compareTo( Knote.daten ) > 0)
		            {
		            	Eltern = Knote;
		            	Knote = Knote.rechts;
		            }
		            else  
		            {
		            	Eltern = Knote;
		            	Knote = Knote.links;
		            }
		        }
			  if (Ziel == null)
		        {
		            return false;
		        }

			  boolean isLeft = false ;
			  if(Ziel != wurzel){
				  isLeft = (Ziel == Eltern.links );
			  }
		       
			  if ( Ziel.rechts == null && Ziel.links == null && Ziel != wurzel)
		        {
		            if (isLeft)
		            {
		            	Eltern.links = null;
		            }
		            else
		            {
		            	Eltern.rechts = null;
		            }
		        }else if(Ziel.rechts == null && Ziel.links == null && Ziel == wurzel)
		        {
		        	wurzel = null;
		        }else if(Ziel.rechts != null && Ziel.links != null)
		        {
		        	if(Ziel != wurzel && isLeft){
		        		Eltern.links = Ziel.links;
		        	}else if(Ziel != wurzel && !isLeft){
		        		Eltern.rechts = Ziel.links;
		        	}else if(Ziel == wurzel){
		        		wurzel = Ziel.links;
		        	}
		        	Knoten rechts = Ziel.links;
		        	while(rechts.rechts != null){
		        		rechts = rechts.rechts;
		        	}
		        	rechts.rechts = Ziel.rechts;
		        }else if(Ziel.rechts == null && Ziel.links != null){
		        	if(Ziel != wurzel && isLeft){
		        		Eltern.links = Ziel.links;
		        	}else if(Ziel != wurzel && !isLeft){
		        		Eltern.rechts = Ziel.links;
		        	}else if(Ziel == wurzel){
		        		wurzel = Ziel.links;
		        	}
		        }else if(Ziel.rechts != null && Ziel.links == null){
		        	if(Ziel != wurzel && isLeft){
		        		Eltern.links = Ziel.rechts;
		        	}else if(Ziel != wurzel && !isLeft){
		        		Eltern.rechts = Ziel.rechts;
		        	}else if(Ziel == wurzel){
		        		wurzel = Ziel.rechts;
		        	}
		        }
			  
		        return true;    
		    }
	
		  /**
		     *  suchen nach eines Knotens bei String wert
		     */
		  public Knoten suchen(String x) {
			  Knoten Ziel = null;
			  Knoten Eltern = null;
			  Knoten Knote = wurzel;
			   
			  while (Knote != null)
		        {
		            if (x.compareTo( Knote.daten ) == 0)  
		            {
		            	Ziel = Knote;
		                break;
		            }
		            else if (x.compareTo( Knote.daten ) > 0)
		            {
		            	Eltern = Knote;
		            	Knote = Knote.rechts;
		            }
		            else  
		            {
		            	Eltern = Knote;
		            	Knote = Knote.links;
		            }
		        }
			
		            return Ziel;
		        
			  
			}
		  
}
