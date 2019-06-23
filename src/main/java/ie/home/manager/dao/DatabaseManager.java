package ie.home.manager.dao;

import java.util.ArrayList;
import java.util.List;

import ie.home.Room;

public class DatabaseManager {

	public List<Room> getRoomsFromDb() {
		List<Room> rooms = new ArrayList<Room>();
		
		Room r1 = new Room(60961871, "TWIN", "2019-04-01", "2019-04-08", 2, 2, 95, "pp");
		Room r2 = new Room(60961872, "TWIN", "2019-04-01", "2019-04-08", 1, 2, 130, "pp");
		Room r3 = new Room(60961873, "DOUBLE", "2019-04-01", "2019-04-08", 2, 2, 100, "pp");
		Room r4 = new Room(60961874, "DOUBLE", "2019-04-01", "2019-04-08", 2, 2, 120, "pp");
		Room r5 = new Room(60961880, "TWIN", "2019-04-01", "2019-04-08", 1, 2, 150, "pp");
		
		// using UNION in database, we will have all pu rooms after the pp
		Room r6 = new Room(60961875, "FAMILY", "2019-04-01", "2019-04-08", 2, 4, 400, "pu");
		Room r7 = new Room(60961876, "FAMILY", "2019-04-01", "2019-04-08", 1, 2, 300, "pu");
		Room r8 = new Room(60961877, "FAMILY", "2019-04-01", "2019-04-08", 2, 4, 450, "pu");
		Room r9 = new Room(60961878, "FAMILY", "2019-04-01", "2019-04-08", 2, 3, 300, "pu");
		
		rooms.add(r1);
		rooms.add(r3);
		rooms.add(r4);
		rooms.add(r2);
		rooms.add(r5);
		
		rooms.add(r6);
		rooms.add(r7);
		rooms.add(r8);
		rooms.add(r9);
		
		return rooms;
	}
}
