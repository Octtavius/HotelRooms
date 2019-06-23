package ie.home;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import ie.home.helpers.RoomHelper;
import ie.home.manager.dao.DatabaseManager;

public class RoomFinder {
	List<Room[]> finalResult = new ArrayList<Room[]>(3);
	
	DatabaseManager dbManager;
	
	public RoomFinder(DatabaseManager dbManager) {
		this.dbManager = dbManager; 
	}
	
	public List<Room[]> findCheapestRooms(int guests) {
		List<Room> set = dbManager.getRoomsFromDb();
		double[] prices = new double[3];
		set = RoomHelper.convertPUtoPP(set);
		int roomsCount = set.size();
  
		for (int i = 0; i < (1 << roomsCount); i++) 
		{ 
			Room roomsCombination[] = new Room[roomsCount];
			int minGuestsRequired = 0;
			int maxGuestsAllowed = 0;
			double price = 0;
			
			boolean sameRoomInCombination = false;
			// Print current subset 
			for (int j = 0; j < roomsCount; j++) {
			    if ((i & (1 << j)) > 0) {
			    	if (containsRoom(set.get(j), roomsCombination)) {
			    		sameRoomInCombination = true;
			    		break;
			    	}
			    	
			    	price += set.get(j).getPrice()*set.get(j).getMinGuests();
			    	minGuestsRequired += set.get(j).getMinGuests();
			    	maxGuestsAllowed += set.get(j).getMaxGuests();
		        	roomsCombination[j] = set.get(j);
		        }
		    }
			
			if (sameRoomInCombination) {
				sameRoomInCombination = false;
				continue;
			}
			
		    if (minGuestsRequired >= guests && maxGuestsAllowed >= guests) {
		    	if (finalResult.isEmpty()) {		    		
		    		prices[0] = price;			
		    		finalResult.add(roomsCombination);
		    		continue;
		    	}
		    	
		    	if (finalResult.size() < 3) {
		    		finalResult.add(roomsCombination);
		    		prices[finalResult.size()-1] = price;
		    		continue;
		    		
		    	}

				double pr = price;
				IntStream.range(0, prices.length).parallel()
					.reduce((a,b)->prices[a]<prices[b]? b: a)
				 	.ifPresent(ix -> {
				 		if (prices[ix] > pr) {				                		 
				            prices[ix] = pr;
				            finalResult.set(ix, roomsCombination);
				         }
				 	});
			}
		} 
		
		return finalResult;
	}
	
	private boolean containsRoom(Room room, Room[] rooms) {
		for (Room roomEl : rooms) {
			if (roomEl != null && roomEl.getId() == room.getId()) {
				return true;
			}
		}

		return false;
	}
}
