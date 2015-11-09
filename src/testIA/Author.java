package testIA;

public enum Author {
	USER("Utilisateur"), IA("IA");
	
	private String name;

	Author(String pName){
		this.name = pName;
	}
	
	public String toString(){
		return this.name;
	}
}
