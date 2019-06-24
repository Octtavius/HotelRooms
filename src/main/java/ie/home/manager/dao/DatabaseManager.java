package ie.home.manager.dao;

import java.util.ArrayList;
import java.util.List;

import ie.home.data.Room;

public class DatabaseManager {

	public List<Room> getRoomsFromDb() {
		List<Room> rooms = new ArrayList<Room>();
		
		Room r1 = new Room(60961869, "DOUBLE", "2019-04-01", "2019-04-08", 2, 2, 120, "pp");
		Room r2 = new Room(60961870, "DOUBLE", "2019-04-01", "2019-04-08", 2, 2, 120, "pp");
		Room r3 = new Room(60961871, "TWIN", "2019-04-01", "2019-04-08", 1, 2, 95, "pp");
		Room r4 = new Room(60961872, "TWINTRIPLE", "2019-04-01", "2019-04-08", 2, 3, 70, "pp");
		Room r5 = new Room(60961873, "TWIN", "2019-04-01", "2019-04-08", 1, 2, 95, "pp");
		
		// using UNION in database, we will have all pu rooms after the pp
		Room r6 = new Room(60961874, "FAMILY", "2019-04-01", "2019-04-08", 2, 4, 400, "pu");
		Room r7 = new Room(60961876, "FAMILY", "2019-04-01", "2019-04-08", 2, 4, 400, "pu");
		
		rooms.add(r1);
		rooms.add(r3);
		rooms.add(r4);
		rooms.add(r2);
		rooms.add(r5);
		
		rooms.add(r6);
		rooms.add(r7);
		
		return rooms;
	}
}
