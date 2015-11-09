package testIA;

import java.util.Vector;

public class Ia extends Thread{

	private Vector<String> listPhrases = new Vector<String>();
	private FenetreIA fenetre;
	
	public Ia(FenetreIA pFenetre){
		super();
		this.fenetre = pFenetre;
	}
	public void addPhrase(String pPhrase) {
		synchronized(listPhrases){
			this.listPhrases.add(pPhrase);
			System.out.println("Ajout de : " + pPhrase);
		}
	}

	@Override
	public void run() {
		while(true){
			synchronized(listPhrases){
				if(this.listPhrases.size()>0){
					String currentPhrase = this.listPhrases.get(0);
					this.listPhrases.remove(0);
					System.out.println("Traitement de : " + currentPhrase);
				}
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Fin de traitement boucle");
		}
	}

}
