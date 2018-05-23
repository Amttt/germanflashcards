import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Object;
import java.util.*;

public class Deck {
	public static final String DELIM = "\n";
	public static void main(String[] args) throws FileNotFoundException {
	// Initialize
	Scanner keyboard = new Scanner(System.in);
	Random generator = new Random();
	String germanWord[] = new String[1000];
        String englishWord[] = new String[1000];
        // Create German word array from csv
    	Scanner scanner = new Scanner(new File("/Users//annma//OneDrive//GERMANWORDFINALLIST.csv"));
        scanner.useDelimiter(DELIM);
        int count = 0;
        while(scanner.hasNext())
        {
        	String s = scanner.next();
        	if (s.contains("Ã¼"))
        	{
        		s = s.replaceAll("Ã¼", "ü"); 
        	}
        	if (s.contains("Ã¶"))
        	{
        		s = s.replaceAll("Ã¶", "ö"); 
        	}
        	if (s.contains("Ã¤"))
        	{
        		s = s.replaceAll("Ã¤", "ä"); 
        	}
        	if (s.contains("ÃŸ"))
        	{
        		s = s.replaceAll("ÃŸ", "ß"); 
        	}
        	s = s.replace("\n", "").replace("\r", "");
        	germanWord[count] = s;
            count++;
        }
        scanner.close();    
        // Create English word array from csv
        int count2 = 0;
        Scanner scanner2 = new Scanner(new File("/Users//annma//OneDrive//ENGLISHWORDFINALLIST.csv"));
        scanner.useDelimiter(DELIM);
        while(scanner2.hasNext())
        {
        	String ss = scanner2.next();
        	englishWord[count2] = ss;
        	count2++;
        }
        scanner2.close();	   
        // Create combined array with German and English words
	FlashCard[] fullDeck = new FlashCard[1000];
	for(int i=1;i<fullDeck.length;i++)
	{			
		String germ = germanWord[i];
		String eng = englishWord[i];
		fullDeck[i] = new FlashCard(germ, eng);
	}
	// Get value for current game session deck size
	System.out.println("Welcome to the German FlashCard System. How many words would you like to study?");
	int studySessionCards = keyboard.nextInt();
	// Create array (deck) for current study session. 
	FlashCard[] sessionDeck = new FlashCard[studySessionCards];
	int[] usedNumbers = new int[studySessionCards];
	for(int i = 0; i < studySessionCards; i++)
	{
		int randomIndex = generator.nextInt(1000);
		for(int j=0;j<usedNumbers.length;j++) // This should prevent duplicates from appearing
		{
			if(randomIndex == usedNumbers[j])
			{
				randomIndex = generator.nextInt(1000);
			}
		}
		String germ = germanWord[randomIndex];
		String eng = englishWord[randomIndex];
		usedNumbers[i] = randomIndex;
		sessionDeck[i] = new FlashCard(germ, eng);
	}			
	// Create game loop
	int count3 = 0; // New counter for session loop;
	int helpCt = 0;
	int incorrect = 0;
	boolean sessionOver = false;
	while(sessionOver == false)
	{
		System.out.println("Here is the deck to study. When ready, begin answering!");
		for(int i=0;i<sessionDeck.length;i++)
		{
			System.out.println(sessionDeck[i]);
		}
		while(count3 < studySessionCards)
		{
			for(int i=0;i<sessionDeck.length;i++)
			{
				System.out.println("Here is the German word: "+sessionDeck[i].toStringGerman());
				String answer = keyboard.nextLine();
				answer = keyboard.next(); // Fix Java glitch
				if(answer.equals("help"))
				{
					System.out.println("answer: "+sessionDeck[i].toStringEnglish());
					helpCt++;
					i = i-1;
				}
				else if(answer.equalsIgnoreCase(sessionDeck[i].toStringEnglish()))
				{
					System.out.println("Correct!");
					count3++;
				}
				else
				{
					System.out.print("Incorrect!\n");
					incorrect++;
					i = i-1;
				}
			}
		}
		sessionOver = true; // Game loop is over
	}
	System.out.println("Congrats!\nYou asked for help "+helpCt+" times.\nYou answered incorrectly "+incorrect+" times.\nHere is the deck you just completed:");
	for(int i=0;i<sessionDeck.length;i++) // Prints the previous session's deck list 
	{
		System.out.println(sessionDeck[i]);
	}
    }
}
