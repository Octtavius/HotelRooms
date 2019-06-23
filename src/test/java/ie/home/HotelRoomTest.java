package ie.home;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ie.home.manager.dao.DatabaseManager;

public class HotelRoomTest {
	
	Room roomsCombination[] = null;
	List<Room[]> finalResult = new ArrayList<Room[]>(3);
	
	
	RoomFinder rf;
	DatabaseManager dbManager;
	
	@Before
	public void setUp() throws Exception {
		dbManager = new DatabaseManager();
		rf = new RoomFinder(dbManager); 
	}

	@After
	public void tearDown() throws Exception {
		rf = null;
		dbManager = null;
	}
	
	List<Room> rooms;
	double[] prices = null;
	
	@Test
	public void tests() {
		List<Room[]> cheapestRooms = rf.findCheapestRooms(3);
		
		for (int i =0; i < cheapestRooms.size(); i++) {
			Room[] comb = cheapestRooms.get(i);
			double temppr = 0;
			for (int j = 0; j < comb.length; j++) {
				Room r = comb[j];
				if (r != null) {
					
					temppr += r.getPrice()*r.getMinGuests();
				}
			}
			System.out.println("prL: " + temppr);
		}
	}
}
