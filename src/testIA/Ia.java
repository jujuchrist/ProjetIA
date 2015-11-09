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
			String currentPhrase = "";
			boolean doitTraiter = false;
			synchronized(listPhrases){
				if(this.listPhrases.size()>0){
					currentPhrase = this.listPhrases.get(0);
					this.listPhrases.remove(0);
					doitTraiter = true;
				}
			}
			
			if(doitTraiter){
				String reponse = traiter(currentPhrase);
				if(reponse != null)
					this.fenetre.addTexteOutPut(Author.IA, reponse);
			}
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private String traiter(String currentPhrase) {
		String[] lstMots = currentPhrase.split("\\s+");
		String result = "";
		for(String str : lstMots){
			result += str + " ";
		}
		if(result.length()>0){
			return result.substring(0, result.length()-1);
		}
		return null;
	}

}
