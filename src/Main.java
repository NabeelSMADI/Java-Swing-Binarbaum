import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
/**
 * Main Klasse
 *
 * @author Nabeel SMADI
 */
public class Main extends JPanel implements MouseListener {
	 static JPanel panel = new JPanel() ;
	Baum baum = new Baum();
	boolean waitForAdd = false;
	boolean waitForchange = false;
	JTextField addtext ;
	JTextField deltext ;
	JTextField postext ;
	 JButton add ; 
     JButton del ;
     JButton pos ;
     JButton balance ;
	int tiefe = 1;
	int iArray = 0;
	Knoten[] array ;
	
	 /**
     * Klasse  Konstruktor (Menü + GUI)
     */   
	 public Main() {
		    JFrame frame = new JFrame();
	        frame.setTitle("Sortierten Binärbaum");
	        frame.setLocationRelativeTo(null);
	        frame.setLocation(200, 30);
	        frame.setSize(1006, 677);
		    panel.setPreferredSize(new Dimension(1000, 625));
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setResizable(false);
	        frame.getContentPane().setBackground( new Color(18, 199, 24)); 
	        
	        JMenuBar menuBar = new JMenuBar();
	        JMenu file = new JMenu("Datei");
	        JMenuItem newBaum = new JMenuItem("Neue Baum");
	        JMenuItem openBaum = new JMenuItem("Baum öffnen");
	        JMenuItem saveBaum = new JMenuItem("Baum speichern");
	        JMenuItem close = new JMenuItem("Programm beenden");
	        menuBar.add(file);
	        file.add(newBaum);
	        file.addSeparator();
	        file.add(openBaum);
	        file.add(saveBaum);
	        file.addSeparator();
	        file.add(close);
	        newBaum.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            baum.wurzel = null;
	            panel.repaint();
	            }

	        });
	        openBaum.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	
				load();
			     panel.repaint();
	            }

	        });
	        saveBaum.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	
					
	            	save();
	            }

	        });
	        close.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	System.exit(0);
	            }

	        });
	       

	         panel = new JPanel() {
	            @Override
	            protected void paintComponent(Graphics g) {
	                super.paintComponent(g);
	            	Font f = new Font("Dialog", Font.ROMAN_BASELINE, 20);
	            	g.setFont(f);
	        				
	            	 
	            	  Knoten k = baum.wurzel;
	            	  struktur(g,k);
	         
	                }
	        
	          
	            @Override
	            public Dimension getPreferredSize() {
	                return new Dimension(1000, 625);
	            }
	        };
	     
	        add = new JButton("hinzufügen"); 
	        del = new JButton("Entfernen");
	        pos = new JButton("Pos ändern");
	        balance = new JButton("Ausbalancieren");
	        add.setLayout(null);
	        add.setSize(60,40);
	     
	        addtext = new JTextField(3);
	        addtext.setLayout(null);
	        add.addActionListener(new ActionListener() { 
	        	  public void actionPerformed(ActionEvent e) { 
	        	   if(addtext.getText().length() != 0){
	        		   waitForAdd = true;
		        	   add.setEnabled(false);
		        	   addtext.setEnabled(false);
		        	   del.setEnabled(false);
		        	   deltext.setEnabled(false);
		        	   pos.setEnabled(false);
		        	   postext.setEnabled(false);
		        	   balance.setEnabled(false);  
	        	   }
	        	 
	        	  } 
	        	} );
	        addtext.setSize(40,60);
	        addtext.setVisible(true);
	        addtext.setLocation(200, 0);
	        panel.add(addtext);
	        panel.add(add);
	        addtext.addKeyListener(new KeyAdapter() {
	            public void keyTyped(KeyEvent e) { 
	                if (addtext.getText().length() >= 3 ) // limit textfield to 3 characters
	                    e.consume(); 
	            }  
	        });
	        
	        
	         
	        del.setLayout(null);
	        del.setSize(60,40);
	        deltext = new JTextField(3);
	        deltext.setLayout(null);
	        del.addActionListener(new ActionListener() { 
	        	  public void actionPerformed(ActionEvent e) { 
	        	  baum.entfernen(deltext.getText());
	        	  panel.repaint();
	              deltext.setText("");
	        	  } 
	        	} );
	        deltext.setSize(40,60);
	        deltext.setVisible(true);
	        deltext.setLocation(200, 0);
	        panel.add(deltext);
	        panel.add(del);
	        deltext.addKeyListener(new KeyAdapter() {
	            public void keyTyped(KeyEvent e) { 
	                if (deltext.getText().length() >= 3 ) // limit textfield to 3 characters
	                    e.consume(); 
	            }  
	        });
	        postext = new JTextField(3);
	        postext.setLayout(null);
	        postext.setSize(40,60);
	        postext.setVisible(true);
	        postext.setLocation(200, 0);
	        panel.add(postext);
	        postext.addKeyListener(new KeyAdapter() {
	            public void keyTyped(KeyEvent e) { 
	                if (deltext.getText().length() >= 3 ) // limit textfield to 3 characters
	                    e.consume(); 
	            }  
	        });
	        balance.setLayout(null);
	        balance.setSize(60,40);
	        balance.addActionListener(new ActionListener() { 
	        	  public void actionPerformed(ActionEvent e) { 
	        		  if(baum.wurzel != null){
	        			  balance();
		        		  panel.repaint(); 
	        		  }
	        		
	        		
	        	  } 
	        	} );
	        pos.setLayout(null);
	        pos.setSize(60,40);
	        pos.addActionListener(new ActionListener() { 
	        	  public void actionPerformed(ActionEvent e) { 
	        		  if(baum.suchen(postext.getText()) != null){
	        			  waitForchange = true;
			        	   add.setEnabled(false);
			        	   addtext.setEnabled(false);
			        	   del.setEnabled(false);
			        	   deltext.setEnabled(false);
			        	   pos.setEnabled(false);
			        	   postext.setEnabled(false);
			        	   balance.setEnabled(false);
	      			}
	        		 
	        	  } 
	        	} );
	        panel.add(pos);
	        panel.add(balance);
	        panel.addMouseListener(this);
	        frame.add(panel);
	        frame.setJMenuBar(menuBar);
	        frame.pack();
	        // Display frame after all components added
	        frame.setVisible(true);
	 }
	 
	
	 /**
	     * Baum darstellen
	     */   
	 public void struktur(Graphics g, Knoten k){
		 if (k != null) {
				tiefe++;
				struktur(g,k.rechts);
				tiefe--;
				
				if(k.rechts != null){
					g.drawLine(k.xPos, k.yPos, k.rechts.xPos -15, k.rechts.yPos -15);
				}
				if(k.links != null){
					g.drawLine(k.xPos, k.yPos, k.links.xPos -15, k.links.yPos -15);
				}
		 	 
  				g.setColor(new Color(18, 199, 24));
      			g.fillOval(k.xPos-25, k.yPos-25,50,50);
   				g.setColor(Color.black);
    		    g.drawString(k.daten, k.xPos-15, k.yPos+5);
				tiefe++;
				struktur(g,k.links);
				tiefe--;
			}
		}
	 
	 /**
	     * main Methode
	     */   
	public static void main(String[] args) {
	
		  SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                new Main();
	            }
	        });

	}

	
	 /**
     * wenn Maus geklickt
     */ 
	public void mouseClicked(MouseEvent arg0) {
		int x, y;
		x = arg0.getX();
		y = arg0.getY();
		if(waitForAdd){
			baum.einfuegen(addtext.getText(), x, y);
			addtext.setText("");
			 panel.repaint();
			 waitForAdd = false;
			 add.setEnabled(true);
      	   addtext.setEnabled(true);
      	   del.setEnabled(true);
      	   deltext.setEnabled(true);
    	   pos.setEnabled(true);
    	   postext.setEnabled(true);
    	   balance.setEnabled(true);
		} 
		if(waitForchange){
			baum.suchen(postext.getText()).xPos = x;
				baum.suchen(postext.getText()).yPos = y;
		postext.setText("");
			 panel.repaint();
			 waitForchange = false;
		   add.setEnabled(true);
      	   addtext.setEnabled(true);
      	   del.setEnabled(true);
      	   deltext.setEnabled(true);
     	   pos.setEnabled(true);
    	   postext.setEnabled(true);
    	   balance.setEnabled(true);
		} 
	}

	
	/**
     * Baum speichern
     */ 
	public void save () {
		JFileChooser fileChooser = new JFileChooser();
    	int result = fileChooser.showSaveDialog(this);
    	if (result == JFileChooser.APPROVE_OPTION) {
    		File selectedFile = fileChooser.getSelectedFile();
        try {
            FileOutputStream datei =
            new FileOutputStream(selectedFile.getAbsolutePath());

      try {
          ObjectOutputStream object = new ObjectOutputStream(datei);
          try {
              object.writeObject(baum);
              object.flush();
          }
          catch(IOException e)
          {System.out.println("Fehler beim Schreiben des Objektes"); }
          datei.close();
      }
      catch(IOException e)
      {System.out.println("Fehler beim Anlegen des Streams (schreiben)"); }
        }
        catch(FileNotFoundException e)
        {System.out.println("Fehler beim oefnen der Datei zum Schreiben"); }
    	}}

	/**
     * Baum Offenen 
     */ 
    public void load() {
    	JFileChooser fileChooser = new JFileChooser();
    	int result = fileChooser.showOpenDialog(this);
    	if (result == JFileChooser.APPROVE_OPTION) {
    		File selectedFile = fileChooser.getSelectedFile();
    	
        try {
            FileInputStream datei =
            new FileInputStream(selectedFile.getAbsolutePath());
        try {
                ObjectInputStream object = new ObjectInputStream(datei);
            try {
                    baum=(Baum)object.readObject();
        }
            catch(ClassNotFoundException e)
        {System.out.println("Fehler beim Lesen des Objektes"); }
            datei.close();
        }
            catch(IOException e)
        {System.out.println("Fehler beim Anlegen des Streams (lesen)"); }
        }
        catch(FileNotFoundException e)
        {System.out.println("Fehler beim ???oeffnen der Datei zum Lesen"); }
    	}
    }
    
    /**
     * die Höhe eines Baumes Finden
     */ 
    public int findh(Knoten n) {
    	if(n.links != null && n.rechts != null){
    		if(findh(n.links) > findh(n.rechts)){
    			return findh(n.links) +1;
    		}else{
    			return findh(n.rechts) +1;
    		}
    	}else if(n.links == null && n.rechts != null)
    	{
    		return findh(n.rechts) +1;
    	}else if(n.links != null && n.rechts == null)
    	{
    		return findh(n.links) +1;
    	}
    	return 1;
    }
    
    /**
     * Die baum Knoten Zählen
     */ 
    public int count(Knoten n) {
    	if(n.links != null && n.rechts != null){
    			return count(n.links)+count(n.rechts) +2;
    	}else if(n.links == null && n.rechts != null)
    	{
    		return count(n.rechts) +1;
    	}else if(n.links != null && n.rechts == null)
    	{
    		return count(n.links) +1;
    	}
    	return 0;
    }
    
    /**
     * Baum Ausbalancieren
     */ 
    public void balance() {
       array = new Knoten[count(baum.wurzel)+1];
       inOrder(baum.wurzel);
       iArray = 0;
       baum.wurzel = sortedArrayToBST(array,0,array.length-1);
     
    }
    
    /**
     * Baum zu Array
     */ 
    public void inOrder(Knoten Knote) { 
    	if(Knote !=  null) {
    		 inOrder(Knote.links);
    		 array[iArray] = Knote;
    		 iArray++;
    		 inOrder(Knote.rechts);
    	}
    }
    
    /**
     *  Array zu AVL-Baum 
     */ 
    public Knoten sortedArrayToBST(Knoten[] array, int start, int end) {
		if (start > end)
			return null;
 
		int mid = (start + end) / 2;
		Knoten root = array[mid];
		root.links = sortedArrayToBST(array, start, mid - 1);
		root.rechts = sortedArrayToBST(array, mid + 1, end);
 
		return root;
	}
    
    
    
    
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
