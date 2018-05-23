public class FlashCard {
	
	private String germanWord;
	private String englishWord;
	// Default constructor
	public FlashCard()
	{
		this.germanWord = "blank";
		this.englishWord = "blank";
	}
	public FlashCard(String aGermanWord, String anEnglishWord)
	{
		this.setGermanWord(aGermanWord);
		this.setEnglishWord(anEnglishWord);
	}
	// Accessors
	public String getGermanWord()
	{
		return this.germanWord;
	}
	public String getEnglishWord()
	{
		return this.germanWord;
	}
	// Mutators
	public void setGermanWord(String aGermanWord)
	{
		this.germanWord = aGermanWord;
	}
	public void setEnglishWord(String anEnglishWord)
	{
		this.englishWord = anEnglishWord;
	}
	
	// Other methods
	public String toString()
	{
		return this.germanWord+" "+this.englishWord;
	}
	public String toStringGerman()
	{
		return this.germanWord;
	}
	public String toStringEnglish()
	{
		return this.englishWord;
	}
}
