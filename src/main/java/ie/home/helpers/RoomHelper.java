package ie.home.helpers;

import java.util.ArrayList;
import java.util.List;

import ie.home.data.Room;

public class RoomHelper {

	public static List<Room> convertToPpRooms(List<Room> allRooms) {
		List<Room> ppRooms = new ArrayList<Room>();
		
		for (int i = 0; i < allRooms.size(); i++) {
			Room r = allRooms.get(i);
			
			int maxGuests = r.getMaxGuests();
			
			for (int j = maxGuests; j >= r.getMinGuests(); j--) {
				Room nr = new Room(r.getId(),r.getType(),r.getStartDate(),r.getEndDate(), j, j, r.getPrice()/j, "pp");				
				ppRooms.add(nr);
			}
		}

		return ppRooms;
	}
	
	public static List<Room> convertPUtoPP(List<Room> rooms) {
		int index = -1;
		for (int i = 0; i < rooms.size(); i++) {
			if (rooms.get(i).getPriceType().equals("pu")) {
				index = i;
				break;
			}
		}
		List<Room> all = new ArrayList<Room>();
		all.addAll(rooms.subList(0, index));
		all.addAll(RoomHelper.convertToPpRooms(rooms.subList(index, rooms.size())));
		
		return all;
	}
}
