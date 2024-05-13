package eg.edu.guc.yugioh.board.player;
import eg.edu.guc.yugioh.cards.spells.*;
import eg.edu.guc.yugioh.board.*;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import eg.edu.guc.yugioh.cards.*;

import java.io.*;

import eg.edu.guc.yugioh.exceptions.*;

public class Deck {
	
	private static ArrayList<Card> monsters;
	private static ArrayList<Card> spells;
	private ArrayList<Card> deck;
	private static String monstersPath = "Database-Monsters.csv";
	private static String spellsPath = "Database-Spells.csv";
	private static int c;
	private Scanner sc;
	
	
	public Deck() throws IOException, MissingFieldException, EmptyFieldException, UnknownCardTypeException, UnknownSpellCardException {
		
		c = 0;
		monsters = new ArrayList<Card>();
		spells = new ArrayList<Card>();
		monsters = loadCardsFromFile(monstersPath);
		spells = loadCardsFromFile(spellsPath);
		deck = new ArrayList<Card>();
		buildDeck(monsters,spells);
		shuffleDeck();
		

	}
	
	
	public ArrayList<Card> loadCardsFromFile(String path) throws IOException, MissingFieldException, EmptyFieldException, UnknownCardTypeException, UnknownSpellCardException {
		
		sc = new Scanner (System.in);
		
		while(true) {
			
			int sourceLine = 1;
			
		try {
			
		String currentLine = "";
		ArrayList<Card> cardList = new ArrayList<Card>();
		String [] cardArray;
		FileReader fileReader = new FileReader(path);
		BufferedReader br = new BufferedReader(fileReader);
		
		while ((currentLine = br.readLine()) != null) {
		System.out.println(currentLine);
		cardArray = currentLine.split(",");
		
		if(cardArray[0].equals("Monster") && cardArray.length != 6)
			throw new MissingFieldException(path,sourceLine);
		
			
		if(cardArray[0].equals("Spell") && cardArray.length != 3)
			throw new MissingFieldException(path,sourceLine);
		
		for(int i= 0; i < cardArray.length; i++) {
			
			if(cardArray[i].equals(" ") || cardArray[i].equals(""))
			throw new EmptyFieldException(path,sourceLine,i+1);
		}
			
		if(!(cardArray[0].equals("Monster") || cardArray[0].equals("Spell"))) {
			
			
			throw new UnknownCardTypeException(path,sourceLine,cardArray[0]); 
			
		}
				
		
		if(cardArray[0].equals("Monster")) {
			
			cardList.add( new MonsterCard(cardArray[1], cardArray[2], Integer.parseInt(cardArray[5]),Integer.parseInt(cardArray[3]), Integer.parseInt(cardArray[4])));
			
		}
		
		else {
			
			if(cardArray[0].equals("Spell")) {
				
			switch(cardArray[1]) {
			
			case "Card Destruction": cardList.add (new CardDestruction(cardArray[1], cardArray[2])); break;
			case "Change Of Heart": cardList.add (new ChangeOfHeart(cardArray[1], cardArray[2])); break;
			case "Dark Hole": cardList.add (new DarkHole (cardArray[1], cardArray[2])); break;
			case "Graceful Dice": cardList.add (new GracefulDice(cardArray[1], cardArray[2])); break;
			case "Harpie's Feather Duster": cardList.add (new HarpieFeatherDuster(cardArray[1], cardArray[2])); break;
			case "Heavy Storm": cardList.add (new HeavyStorm(cardArray[1], cardArray[2])); break;
			case "Mage Power": cardList.add (new MagePower(cardArray[1], cardArray[2])); break;
			case "Monster Reborn": cardList.add (new MonsterReborn(cardArray[1], cardArray[2])); break;
			case "Pot of Greed": cardList.add (new PotOfGreed(cardArray[1], cardArray[2])); break;
			case "Raigeki": cardList.add (new Raigeki(cardArray[1], cardArray[2])); break;
			default: throw new UnknownSpellCardException(path,sourceLine,cardArray[1]);
			
			}
			
			}
		}
		
		sourceLine++;
		
		}
	
		return cardList;
		
		} catch(FileNotFoundException e1) {
			
			if(c >= 3) {
				e1.printStackTrace();
				throw e1;
			} 
			
			else {
			
			c++;
			System.out.println("Please enter the correct path");
			path = sc.nextLine();
			
		}
			
		} catch (MissingFieldException e2) {
			
			if(c >= 3) {
				e2.printStackTrace();
				throw e2;
			}
			
			else {
				
				c++;
				System.out.println("Please enter the correct file having all fields");
				path = sc.nextLine();
				
			}
		
		} catch(EmptyFieldException e3) {
			
			if(c >= 3) {
				e3.printStackTrace();
				throw e3;
			}
			
			else {
				
				c++;
				System.out.println("Please enter the correct file having non empty fields");
				path = sc.nextLine();
				
			}
			
		} catch(UnknownCardTypeException e4) {
			
			if(c >= 3) {
				e4.printStackTrace();
				throw e4;
			}
			
			else {
				
				c++;
				System.out.println("Please enter the correct file having the correct card type");
				path = sc.nextLine();
				
			}
			
		} catch(UnknownSpellCardException e5) {
			
			if(c >= 3) {
				e5.printStackTrace();
				throw e5;
			}
			
			else {
				
				c++;
				System.out.println("Please enter the correct file having the correct spell cards");
				path = sc.nextLine();
				
			}
		}
		
		}
	
	}
	
	
	private void buildDeck(ArrayList<Card> monsters, ArrayList<Card> spells) {
			
		for(int i = 0; i < 15; i++) {
		int counter1 = (int) (Math.random()*monsters.size());
		deck.add(new MonsterCard(monsters.get(counter1).getName(), monsters.get(counter1).getDescription(), ((MonsterCard) monsters.get(counter1)).getLevel(), ((MonsterCard) monsters.get(counter1)).getAttackPoints(), ((MonsterCard) monsters.get(counter1)).getDefensePoints() ));
		
		}
		
		for(int j = 0; j < 5; j++) {
		int counter2 = (int) (Math.random()*spells.size());
		
		switch(spells.get(counter2).getName()) {
		
		case "Card Destruction": deck.add (new CardDestruction(spells.get(counter2).getName(), spells.get(counter2).getDescription())); break;
		case "Change Of Heart": deck.add (new ChangeOfHeart(spells.get(counter2).getName(), spells.get(counter2).getDescription())); break;
		case "Dark Hole": deck.add (new DarkHole (spells.get(counter2).getName(), spells.get(counter2).getDescription())); break;
		case "Graceful Dice": deck.add (new GracefulDice(spells.get(counter2).getName(), spells.get(counter2).getDescription())); break;
		case "Harpie's Feather Duster": deck.add (new HarpieFeatherDuster(spells.get(counter2).getName(), spells.get(counter2).getDescription())); break;
		case "Heavy Storm": deck.add (new HeavyStorm(spells.get(counter2).getName(), spells.get(counter2).getDescription())); break;
		case "Mage Power": deck.add (new MagePower(spells.get(counter2).getName(), spells.get(counter2).getDescription())); break;
		case "Monster Reborn": deck.add (new MonsterReborn(spells.get(counter2).getName(), spells.get(counter2).getDescription())); break;
		case "Pot of Greed": deck.add (new PotOfGreed(spells.get(counter2).getName(), spells.get(counter2).getDescription())); break;
		case "Raigeki": deck.add (new Raigeki(spells.get(counter2).getName(), spells.get(counter2).getDescription())); break;
		default: break;
		
		}
		
		}
		
		
	}
	
	
	private void shuffleDeck () {
		
		Collections.shuffle(deck);
	}
	
	public ArrayList<Card> drawNCards(int n) {
		
		ArrayList<Card> drawing = new ArrayList<Card>();
		
		while(n!=0) {
			
			Card temp = drawOneCard();
			drawing.add(temp);
			n--;
		}
		
		return drawing;
	}
		
	
	public Card drawOneCard() {
		
		Card temp = deck.remove(0);
		temp.setLocation(Location.DECK);
		return temp;
	}
	
	public static ArrayList<Card> getMonsters() {
		return monsters;
	}
	public static void setMonsters(ArrayList<Card> monsters1) {
		monsters = monsters1;
	}
	public static ArrayList<Card> getSpells() {
		return spells;
	}
	public static void setSpells(ArrayList<Card> spells1) {
		spells = spells1;
	}
	public ArrayList<Card> getDeck() {
		return deck;
	}


	public static String getMonstersPath() {
		return monstersPath;
	}


	public static void setMonstersPath(String monstersPath) {
		Deck.monstersPath = monstersPath;
	}


	public static String getSpellsPath() {
		return spellsPath;
	}


	public static void setSpellsPath(String spellsPath) {
		Deck.spellsPath = spellsPath;
	}
	

}
