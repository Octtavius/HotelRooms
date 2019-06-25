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
	
	public List<List<Room>> findCheapestRooms(int guests, int combinationsAmount) {
		//Changed from Room[] to List<Room>. The perforamce impact is minimal but it is easier to work with.
		List<List<Room>> cheapestCombinations = new ArrayList<List<Room>>(combinationsAmount);
		
		List<Room> availableRooms = dbManager.getRoomsFromDb();
		double[] prices = new double[combinationsAmount];
		availableRooms = RoomHelper.convertPUtoPP(availableRooms);
		int roomsCount = availableRooms.size();
  
		for (int i = 0; i < (1 << roomsCount); i++) 
		{ 
			List<Room> roomsCombination = new ArrayList<Room>(roomsCount);
			int minGuestsRequired = 0;
			int maxGuestsAllowed = 0;
			double price = 0;
			
			// create combination
			for (int j = 0; j < roomsCount; j++) {
			    if ((i & (1 << j)) > 0) {
			    	
			    	price += availableRooms.get(j).getPrice() * availableRooms.get(j).getMinGuests();
			    	minGuestsRequired += availableRooms.get(j).getMinGuests();
			    	maxGuestsAllowed += availableRooms.get(j).getMaxGuests();
		        	roomsCombination.add(availableRooms.get(j));
		        }
			    
			    if (guests >= minGuestsRequired && guests<= maxGuestsAllowed) {
			    	break;
			    }
		    }
			
			price = updatePrice(price, roomsCombination, guests, minGuestsRequired);
					
		    if (maxGuestsAllowed >= guests) {
		    	if (cheapestCombinations.isEmpty()) {		    		
		    		prices[0] = price;			
		    		cheapestCombinations.add(roomsCombination);
		    		continue;
		    	}
		    	
		    	if (cheapestCombinations.size() < combinationsAmount
		    			&& !cheapestCombinations.contains(roomsCombination)) {
		    		cheapestCombinations.add(roomsCombination);
		    		prices[cheapestCombinations.size()-1] = price;
		    		continue;
		    		
		    	}

		    	// replace price with highest price from array if exists
				double pr = price;
				IntStream.range(0, prices.length).parallel()
					.reduce((a,b)-> prices[a] < prices[b] ? b : a)
				 	.ifPresent(idx -> {
				 		if (prices[idx] > pr && !cheapestCombinations.contains(roomsCombination)) {				                		 
				            prices[idx] = pr;
				            cheapestCombinations.set(idx, roomsCombination);
				         }
				 	});
			}
		}
		
		return cheapestCombinations;
	}

	private double updatePrice(double price, List<Room> roomsCombination, int guests, int minGuestsRequired) {
		roomsCombination.sort(
    		      (Room h1, Room h2) -> Double.compare(h1.getPrice(), h2.getPrice()));
			int currentCap = minGuestsRequired;
			
			for (Room room : roomsCombination) {
				if (currentCap < guests) {
					int spaceAvailableInRoom = room.getMaxGuests() - room.getMinGuests();
					if (spaceAvailableInRoom > 0) {
						price += room.getPrice() * spaceAvailableInRoom;
						currentCap += spaceAvailableInRoom;
					}
				}
			}
			
		return price;
	}
}
