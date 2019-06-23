package ie.home;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class HotelRoomTest {
	
	Room roomsCombination[] = null;
	List<Room[]> finalResult = new ArrayList<Room[]>(3);
	@Before
	public void setUp() throws Exception {
		Room r1 = new Room(60961871, "TWIN", "2019-04-01", "2019-04-08", 2, 2, 95, "pp");
		Room r2 = new Room(60961872, "TWIN", "2019-04-01", "2019-04-08", 1, 2, 100, "pp");
		Room r3 = new Room(60961873, "DOUBLE", "2019-04-01", "2019-04-08", 2, 2, 120, "pp");
		Room r4 = new Room(60961874, "DOUBLE", "2019-04-01", "2019-04-08", 2, 2, 120, "pp");
		
		Room set[] = {r2, r4, r3, r4}; 
	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	List<Room> rooms;
	double[] prices = null;
	
	@Test
	public void test1() {
		Room[] set = getAllRooms();
		int roomsCount = set.length;
		int guests = 3;
  
		for (int i = 0; i < (1<<roomsCount); i++) 
		{ 
			roomsCombination = new Room[roomsCount];
			
			int minCapacity = 0;
			double price = 0;
			// Print current subset 
			for (int j = 0; j < roomsCount; j++) {
			    if ((i & (1 << j)) > 0) {
			    	price += set[j].getPrice();
			    	minCapacity += set[j].getMinGuests();
			    	System.out.print(set[j].getMinGuests() + " ");
		        	roomsCombination[j] = set[j];
		        }
		    }
			  
		    if (minCapacity >= guests) {
		    	addNewLowPrice(price, roomsCombination);	
	    	}
		    System.out.println();
		} 

		System.out.println();
		for (int i =0; i < finalResult.size(); i++) {
			System.out.println("--------");
			Room[] comb = finalResult.get(i);
			double temppr = 0;
			for (int j = 0; j < comb.length; j++) {
				Room r = comb[j];
				if (r != null) {
					
					temppr += r.getPrice();
					System.out.println(r.getId() + " | " + r.getType() + " | " + r.getStartDate() + " | " + r.getEndDate() + " | " + r.getMinGuests() + " | " + r.getMaxGuests() + " | " + r.getPrice() + " | " + r.getPriceType());
				}
			}
			System.out.println("prL: " + temppr);
			System.out.println("--------");
		}
	}
	
	private void addNewLowPrice(double price, Room[] combinations) {			
		if (prices == null) {
			prices = new double[3];
			prices[0] = price;			
			finalResult.add(combinations);
			return;
		}
		
		for (int i = 0; i < prices.length; i++) {
			if (prices[i] == 0.0) {
				prices[i] = price;
				finalResult.add(combinations);
				return;
			}
		}
		
		double highestPrice = prices[0];
		int idx = 0;
		for (int i = 1; i < prices.length; i++) {
			if (prices[i] > highestPrice) {
				highestPrice = prices[i];
				idx = i;
			}
		}
		
		if (price < highestPrice && idx != -1) {
			prices[idx] = price;
			roomsCombination[idx] = null;
			finalResult.set(idx, combinations);
		}
		
		System.out.print("");
	}

	private Room[] getAllRooms() {
		Room r1 = new Room(60961871, "TWIN", "2019-04-01", "2019-04-08", 2, 2, 95, "pp");
		Room r2 = new Room(60961872, "TWIN", "2019-04-01", "2019-04-08", 1, 2, 100, "pp");
		Room r3 = new Room(60961873, "DOUBLE", "2019-04-01", "2019-04-08", 2, 2, 110, "pp");
		Room r4 = new Room(60961874, "DOUBLE", "2019-04-01", "2019-04-08", 2, 2, 120, "pp");
		
		Room set[] = {r2, r4, r3, r1}; 
		
		return set;
	}

//	@Test
//	@Ignore
//	public void test() {
//		RoomFinder finder = new RoomFinder();
//		
//		List<Room> result = finder.findCheapestRooms(7);
//		
//		for (int i =0; i < result.size(); i++) {
//			Room r = result.get(i);
//			System.out.println(r.getType() + " | " + r.getStartDate() + " | " + r.getEndDate() + " | " + r.getMinGuests() + " | " + r.getMaxGuests() + " | " + r.getPrice() + " | " + r.getPriceType());
//		}
//	}
	
//	@Test
////	@Ignore
//	public void testPu() {
//		RoomFinder finder = new RoomFinder();
//		
//		List<Room> result = finder.convertToPpRooms();
//		

}
