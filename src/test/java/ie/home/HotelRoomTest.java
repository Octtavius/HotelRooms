package ie.home;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ie.home.data.Room;
import ie.home.manager.dao.DatabaseManager;

public class HotelRoomTest {
	
	RoomFinder roomFinder;
	DatabaseManager dbManager;
	
	@Before
	public void setUp() throws Exception {
		dbManager = new DatabaseManager();
		roomFinder = new RoomFinder(dbManager); 
	}

	@After
	public void tearDown() throws Exception {
		roomFinder = null;
		dbManager = null;
	}
	
	@Test
	public void tests() {
		int guests = 3;
		int topRoomsAmount = 3;
		List<List<Room>> cheapestRooms = roomFinder.findCheapestRooms(guests, topRoomsAmount);
		
		assertTrue("Retrieved more than "+topRoomsAmount+" rooms: ", cheapestRooms.size() <= topRoomsAmount);
		
		System.out.println("--------");
		for (int i =0; i < cheapestRooms.size(); i++) {
			List<Room> combination = cheapestRooms.get(i);
			double price = 0;
			for (int j = 0; j < combination.size(); j++) {
				Room r = combination.get(j);
				if (r != null) {
					
					price += r.getPrice()*r.getMinGuests();
					System.out.println(r.getId() + " | " + r.getType() + " | " + r.getStartDate() + " | " + r.getEndDate() + " | " + r.getMinGuests() + " | " + r.getMaxGuests() + " | " + r.getPrice());
				}
			}
			System.out.println("Price: " + price);
			System.out.println("--------");
		}
	}
}
