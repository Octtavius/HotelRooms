package ie.home;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import ie.home.data.Room;
import ie.home.helpers.RoomHelper;
import ie.home.manager.dao.DatabaseManager;

public class RoomFinder {
	
	private DatabaseManager dbManager;
	
	public RoomFinder(DatabaseManager dbManager) {
		this.dbManager = dbManager; 
	}
	
	public List<List<Room>> findCheapestRooms(int guests, int roomAmount) {
		List<List<Room>> cheapestRooms = new ArrayList<List<Room>>(roomAmount);
		
		List<Room> availableRooms = dbManager.getRoomsFromDb();
		double[] prices = new double[roomAmount];
		availableRooms = RoomHelper.convertPUtoPP(availableRooms);
		int roomsCount = availableRooms.size();
  
		for (int i = 0; i < (1 << roomsCount); i++) 
		{ 
			List<Room> roomsCombination = new ArrayList<Room>();
			int minGuestsRequired = 0;
			int maxGuestsAllowed = 0;
			double price = 0;
			
			boolean sameRoomInCombination = false;
			// create combination
			for (int j = 0; j < roomsCount; j++) {
			    if ((i & (1 << j)) > 0) {
			    	if (containsRoom(availableRooms.get(j), roomsCombination)) {
			    		sameRoomInCombination = true;
			    		break;
			    	}
			    	
			    	price += availableRooms.get(j).getPrice() * availableRooms.get(j).getMinGuests();
			    	minGuestsRequired += availableRooms.get(j).getMinGuests();
			    	maxGuestsAllowed += availableRooms.get(j).getMaxGuests();
		        	roomsCombination.add(availableRooms.get(j));
		        }
		    }
			
			if (sameRoomInCombination) {
				sameRoomInCombination = false;
				continue;
			}
			
		    if (minGuestsRequired >= guests && maxGuestsAllowed >= guests) {
		    	if (cheapestRooms.isEmpty()) {		    		
		    		prices[0] = price;			
		    		cheapestRooms.add(roomsCombination);
		    		continue;
		    	}
		    	
		    	if (cheapestRooms.size() < roomAmount) {
		    		cheapestRooms.add(roomsCombination);
		    		prices[cheapestRooms.size()-1] = price;
		    		continue;
		    		
		    	}

		    	// replace price with highest price from array if exists
				double pr = price;
				IntStream.range(0, prices.length).parallel()
					.reduce((a,b)-> prices[a] < prices[b] ? b : a)
				 	.ifPresent(idx -> {
				 		if (prices[idx] > pr) {				                		 
				            prices[idx] = pr;
				            cheapestRooms.set(idx, roomsCombination);
				         }
				 	});
			}
		} 
		
		return cheapestRooms;
	}
	
	private boolean containsRoom(Room room, List<Room> roomsCombination) {
		for (Room roomEl : roomsCombination) {
			if (roomEl != null && roomEl.getId() == room.getId()) {
				return true;
			}
		}

		return false;
	}
}
