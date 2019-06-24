package ie.home;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
		int cheapestRoomsAmount = 3;
		int guests = 7;
		List<List<Room>> cheapestRoomsCombinations = roomFinder.findCheapestRooms(guests, cheapestRoomsAmount);
		assertTrue("Retrieved more than "+cheapestRoomsAmount+" rooms: ", cheapestRoomsCombinations.size() <= cheapestRoomsAmount);				

		Room[][] sampleRooms = getSampleRooms();
		AtomicInteger count = new AtomicInteger(0);

		cheapestRoomsCombinations.stream()
		.forEach(combination -> {
			assertTrue("Combination should contain 3 rooms", combination.size() == 3);	
			
			AtomicInteger index = new AtomicInteger(0);
			combination.forEach(room -> {
				assertTrue("Expected rooms don't match", sampleRooms[count.get()][index.get()].equals(room));
				index.incrementAndGet();
			});
			
			count.incrementAndGet();
		});
		
		print(cheapestRoomsCombinations);
	}

	private Room[][] getSampleRooms() {
		Room r1 = new Room(60961871, "TWIN", "2019-04-01", "2019-04-08", 1, 2, 95, "pp");
		Room r2 = new Room(60961872, "TWINTRIPLE", "2019-04-01", "2019-04-08", 2, 3, 70, "pp");
		Room r3 = new Room(60961873, "TWIN", "2019-04-01", "2019-04-08", 1, 2, 95, "pp");
		Room r4 = new Room(60961874, "FAMILY", "2019-04-01", "2019-04-08", 2, 4, 400, "pu");
		Room r5 = new Room(60961876, "FAMILY", "2019-04-01", "2019-04-08", 2, 4, 400, "pu");
		
		Room[][] sampleRooms = new Room[][]
				{
					{r2,r3,r4},
					{r1,r2,r5},
					{r1,r2,r4},
				};
		return sampleRooms;
	}

	private void print(List<List<Room>> cheapestRooms) {
		System.out.println("--------");
		for (List<Room> combination : cheapestRooms) {
			double price = 0;
			for (Room room : combination) {
				if (room != null) {
					
					price += room.getPrice()*room.getMinGuests();
					System.out.println(room.getId() + " | " + room.getType() + " | " + room.getStartDate() + " | " + room.getEndDate() + " | " + room.getMinGuests() + " | " + room.getMaxGuests() + " | " + room.getPrice() + " | " + room.getPriceType());
				}
			}

			System.out.println("Price: " + price);
			System.out.println("--------");
		}
		
	}
}
