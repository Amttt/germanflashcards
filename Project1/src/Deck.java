import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Object;
import java.util.*;

public class Deck {
	public static final String DELIM = "\n";
	public static void main(String[] args) throws FileNotFoundException {
	// Initialize
	Scanner keyboard = new Scanner(System.in);
	Random r = new Random();
	String germanWord[] = new String[1000];
        String englishWord[] = new String[1000];
	int randomIndex = r.nextInt(1000);
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
        	if(s.contains("Ã„"))
        	{
        		s = s.replaceAll("Ã„", "Ä");
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
	// Get value for current game session deck size
	System.out.println("Welcome to the German FlashCard System.\nChoose a deck size between 1 and 50.");
	int studySessionCards = keyboard.nextInt();
	if(studySessionCards > 50) 
	{
		System.out.println("Invalid number. Enter a number between 1 and 50.");
		studySessionCards = keyboard.nextInt();
	}
	// Create array (deck) for current study session. 		
	FlashCard[] sessionDeck = new FlashCard[studySessionCards];
	Set<Integer> set = new HashSet<>(studySessionCards);
	int num = 0;
	while(num < studySessionCards) {
		set.add(randomIndex);
		randomIndex = r.nextInt(1000);
		if(set.size() == num+1) {
			randomIndex = r.nextInt(1000);
			set.add(randomIndex);
			num++;
		}
		else
			randomIndex = r.nextInt(1000);
	}
	// Create Integer array from the set of random numbers
	Integer[] studyDeckArray = set.toArray(new Integer[set.size()]);
	// Create study session deck using randomly chosen numbers from the set.
	for(int i = 0; i < studySessionCards; i++) {
		String germ = germanWord[studyDeckArray[i]];
		String eng = englishWord[studyDeckArray[i]];
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
				if(answer.toLowerCase().equals("hilfe"))
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
		System.out.println(sessionDeck[i]);
    }
}
