import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TrelloClone {

	public static Map <String, List<Card>> board = new HashMap<>();
	static Set<String> users = new HashSet<>();
	static Set<String> tags = new HashSet<>();
	
	public static void main(String[] args) throws ParseException {
		
		User jitendra = new User("Jitendra");
		User lucky = new User("Loky");
		User mannu = new User("Mannu");
		
		users.add("Jitendra");
		users.add("Loky");
		users.add("Mannu");
		
		tags.add("Design");
		tags.add("Dev");
		tags.add("Review");
		tags.add("Test");
		
		List<Card> column = new ArrayList<>();
		Card card1 = new Card("StoryADesign", jitendra, "Design", new Date(), "DesignProgress");
		Card card2 = new Card("StoryBTest", lucky, "Test", new Date(), "TestProgress");
		Card card3 = new Card("StoryCDev", jitendra, "Dev", new Date(), "DevProgress");
		Card card4 = new Card("StoryDReview", mannu, "Review", new Date(), "ReviewProgress");
		column.add(card1);
		column.add(card2);
		column.add(card3);
		column.add(card4);
		board.put("Planning", column);
		
		List<Card> column1 = new ArrayList<>();
		Card card5 = new Card("StoryADesign", mannu, "Design", new Date(), "DesignProgress");
		Card card6 = new Card("StoryBTest", jitendra, "Test", new Date(), "TestProgress");
		Card card7 = new Card("StoryCDev", lucky, "Dev", new Date(), "DevProgress");
		Card card8 = new Card("StoryDReview", jitendra, "Review", new Date(), "ReviewProgress");
		column1.add(card5);
		column1.add(card6);
		column1.add(card7);
		column1.add(card8);
		board.put("Follow Up",column1);
		
		List<Card> column2 = new ArrayList<>();
		Card card9 = new Card("StoryADesign", mannu, "Design", new Date(), "DesignProgress");
		Card card10 = new Card("StoryBTest", jitendra, "Test", new Date(), "TestProgress");
		Card card11 = new Card("StoryCDev", lucky, "Dev", new Date(), "DevProgress");
		Card card12 = new Card("StoryDReview", jitendra, "Review", new Date(), "ReviewProgress");
		column2.add(card9);
		column2.add(card10);
		column2.add(card11);
		column2.add(card12);
		board.put("Dev", column2);
		
		System.out.println(getBoard());
		System.out.println(getCardsWithTag("Test"));
		System.out.println(getColumnCards("Planning"));
		System.out.println(getCardsAfter("10/07/2021 10:15:12"));
		System.out.println(getCardsForUser("Jitendra"));		
	}
	
	public static List<Card> getColumnCards(String column) {
		List<Card> result = new ArrayList<>();
		
		if (!board.containsKey(column))
			return result;
		
		return board.get(column);
	}
	
	public static List<Card> getCardsWithTag(String tag) {
		
		List<Card> result = new ArrayList<>();
		
		if(!tags.contains(tag))
			return result;
			
		for (Map.Entry<String, List<Card>> entry: board.entrySet()) {
			
			for(Card currCard : entry.getValue()) {
				if (currCard.getTag().equals(tag)) {
					result.add(currCard);
				}
			}
		}
		
		return result;
	}
	
	public static List<Card> getCardsForUser(String name) {
		
		List<Card> result = new ArrayList<>();
		
		if(!users.contains(name))
			return result;
			
		for (Map.Entry<String, List<Card>> entry: board.entrySet()) {
			
			for(Card currCard : entry.getValue()) {
				if (currCard.getUser().getName().equals(name)) {
					result.add(currCard);
				}
			}
		}
		
		return result;
	}
	
	public static List<Card> getCardsAfter(String timeStamp) throws ParseException {
		
		List<Card> result = new ArrayList<>();
		
		String JSON_DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";		
		SimpleDateFormat DATE_FOMATTER = new SimpleDateFormat(JSON_DATE_TIME_FORMAT);
		
		Date input = DATE_FOMATTER.parse(timeStamp);
		for (Map.Entry<String, List<Card>> entry: board.entrySet()) {
			
			for(Card currCard : entry.getValue()) {
				
				Date cardStart = currCard.getStart();
				String startTime = DATE_FOMATTER.format(cardStart);
				
				cardStart = DATE_FOMATTER.parse(startTime);
				
				if (cardStart!= null && cardStart.after(input)) {
					result.add(currCard);
				}
			}
		}
		
		return result;
	}
	
	public static Map <String, List<Card>> getBoard () {
		return board;
	}
	
}

class Card {
	
	String name;
	User user;
	String tag;
	Date start;
	String comment;
	
	public Card(String name, User user, String tag, Date start, String comment) {
		super();
		this.name = name;
		this.user = user;
		this.tag = tag;
		this.start = start;
		this.comment = comment;
	}
	
	
	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	
	@Override
	public String toString() {
		
		return "{"+ "name : "+ name +", tag : " + tag + ", user : "+ user + ", comment : "+ comment + ", start : "+ start +"}";
	}
}

class User {
	
	String name;
	String number;
	String eamil;
	String title;
	
	
	public User(String name) {
		super();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getEamil() {
		return eamil;
	}
	public void setEamil(String eamil) {
		this.eamil = eamil;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String toString() {
		
		return "{"+ "name : "+ name +", Titel : " + title + "}";
	}
}
