package testIA;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public class FenetreIA extends JFrame implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GridBagLayout layoutMain;
	private JTextArea inputArea;
	private JTextArea outputArea;
	JScrollPane scrPanIn;
	JScrollPane scrPanOut;
	private Vector<Phrase> listDiscussion = new Vector<Phrase>();
	private Ia robot;
	
	public FenetreIA(){    
		this.setTitle("IA");
	    this.setMinimumSize(new Dimension(1000, 600));
	    this.setLocationRelativeTo(null);         
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    this.robot = new Ia(this);
	    this.robot.start();
	   
	    this.layoutMain = new GridBagLayout();
	    this.setLayout(this.layoutMain);
	    
	    GridBagConstraints gbc = new GridBagConstraints();
		Border bdr = BorderFactory.createLineBorder(Color.GRAY, 1);

	    this.scrPanOut = new JScrollPane();
	    this.scrPanIn = new JScrollPane();

	    this.inputArea = new JTextArea("");
	    this.outputArea = new JTextArea("");
	    this.outputArea.setTabSize(2);
	    this.outputArea.setEditable(false);
	    /*this.inputArea.setPreferredSize(new Dimension(250, 250));
	    this.outputArea.setPreferredSize(new Dimension(250, 250));*/

	    int yGrid = 1;

	    //
	    gbc.gridwidth = 1;
	    gbc.gridheight= 1;
	    gbc.gridx = 0;
	    gbc.gridy = yGrid++;
	    gbc.weightx = 1.0;
	    gbc.weighty = 1.0;
	    gbc.fill = GridBagConstraints.BOTH;
	    this.scrPanOut.setViewportView(this.outputArea);
	    this.outputArea.setWrapStyleWord(true);
	    this.outputArea.setLineWrap(true);
	    this.scrPanOut.setBorder(bdr);
	    this.getContentPane().add(this.scrPanOut,gbc);

	    //
	    gbc.gridwidth = 1;
	    gbc.gridheight= 1;
	    gbc.gridx = 0;
	    gbc.gridy = yGrid++;
	    gbc.weightx = 1.0;
	    gbc.weighty = 1.0;
	    gbc.fill = GridBagConstraints.BOTH;
	    this.scrPanIn.setViewportView(this.inputArea);
	    this.inputArea.setWrapStyleWord(true);
	    this.inputArea.setLineWrap(true);
	    this.inputArea.addKeyListener(this);
	    this.scrPanIn.setBorder(bdr);
	    this.getContentPane().add(this.scrPanIn,gbc);
	    
	    
	    
	    this.setVisible(true);
	}

	private void sendInputText() {
		String inpt = this.inputArea.getText().trim();
		this.inputArea.setText("");
		if(inpt!=""){
			this.robot.addPhrase(inpt);
			addTexteOutPut(Author.USER, inpt);
		}
	}

	synchronized public void  addTexteOutPut(Author auth, String pText) {
		this.listDiscussion.add(new Phrase(auth, pText));
		while(this.listDiscussion.size()>50)
			this.listDiscussion.remove(0);
		
		String outpt = "";
		Author lastAuth = null;
		
		for(int i = 0; i<this.listDiscussion.size(); i++){
			Phrase ph = this.listDiscussion.get(i);
			if(i == 0){
				outpt = ph.author + " : " + ph.text;
			}
			else{
				outpt = outpt 
						+ "\n" 
						+ (lastAuth!=ph.author?"\n" + ph.author + " : ":"\t") 
						+ ph.text;
			}
			lastAuth = ph.author;
		}
		
		outpt.trim();

		this.outputArea.setText(outpt);
		SwingUtilities.invokeLater(new Runnable() {
		          public void run() {
		        	  scrPanOut.validate();
		      		  scrPanOut.getVerticalScrollBar().setValue(scrPanOut.getVerticalScrollBar().getMaximum());
		          }
		        });
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
			this.sendInputText();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
			this.inputArea.setText("");
		}
	}
}
