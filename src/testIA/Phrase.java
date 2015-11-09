package testIA;

public class Phrase{
	
	public String text;
	public Author author;

	public Phrase(){
		this(Author.USER, "");
	}

	public Phrase(Author pAuthor, String pText) {
		this.text = pText;
		this.author = pAuthor;
	}

}
