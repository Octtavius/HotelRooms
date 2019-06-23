package ie.home;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RoomFinder {
	List<Room[]> finalResult = new ArrayList<Room[]>(3);
	Room roomsCombination[] = null;
	double[] prices = null;
	
	public List<Room> findCheapestRooms(int guests) {
		Room roomsCombination[] = null;
//		double[] prices = null;
		
		List<Room> allRooms = getRoomsFromDb();
		int roomsCount = allRooms.size();
		
		
		for (int i = 0; i < (1<<roomsCount); i++) 
		{ 
			roomsCombination = new Room[roomsCount];
//			System.out.print("{ "); 
			int minCapacity = 0;
			double price = 0;
			// Print current subset 
			for (int j = 0; j < roomsCount; j++) {
			    if ((i & (1 << j)) > 0) {
			    	price += allRooms.get(j).getPrice();
			    	minCapacity += allRooms.get(j).getMinGuests();
//			    	System.out.print(allRooms.get(j).getMinGuests() + " ");
		        	roomsCombination[j] = allRooms.get(j);
		        }
		    }
			  
		    if (minCapacity >= guests) {
		    	addNewLowPrice(price, roomsCombination);
//		    	System.out.println(" } " + price + ": " +price/guests + " pp" ); 
			} else {
				
//				System.out.println(" }"); 
			}
		} 
		
		// print lowest prices
		System.out.println("Lowest prices");
		for (int i = 0; i < prices.length; i++) {
			System.out.print(prices[i] + " ");
		}
		
		return null;
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	double price = 0.0;
//	public List<Room> findCheapestRooms(int guests, List<Room> allRooms) {
//		
//		int minCapacity = 0;
//		int maxCapacity = 0;
//		boolean added = false;
//		
//		List<Room> result = new ArrayList<Room>();
//		
//		for (int i = 0; i < allRooms.size(); i++) {
//			Room r = allRooms.get(i);
//			
//			if (guests == r.getMaxGuests() || guests == r.getMinGuests()) { 
//				result.clear();
//				result.add(r);
//
//				
//				price = r.getPrice()*r.getMinGuests();
//				break;
//			}   
//			
//			if (replacingRoom(result, r, guests)) {
//				break;
//			}
//			
////			if (!added && guests < minCapacity + r.getMinGuests()) {
////				//calculate if it is worth paying more
////				double potentialPrice = price + (r.getPrice() * r.getMinGuests());
////				potentialPrice = potentialPrice/Double.valueOf(guests);
////				if ((i<allRooms.size() - 1)) {
////					if( potentialPrice < allRooms.get(i+1).getPrice()) {
////						result.add(r);
////						minCapacity += r.getMinGuests();
////						maxCapacity += r.getMaxGuests();
////						price += r.getPrice()*r.getMinGuests();
////						added = true;
////					}
////				}
////			}
//			
//			if (guests >= r.getMaxGuests()
//					&& !((guests - minCapacity) < r.getMinGuests())
//					&& !containsRoom(result, r.getId())
//					) {
//				
//				result.add(r);
//				minCapacity += r.getMinGuests();
//				maxCapacity += r.getMaxGuests();
//				price += r.getPrice()*r.getMinGuests();
//			} else {
//				if (guests >= r.getMinGuests()
//						&& !((guests - minCapacity) < r.getMinGuests())
//								&& !containsRoom(result, r.getId())
//						) {
//					result.add(r);
//					minCapacity += r.getMinGuests();
//					maxCapacity += r.getMaxGuests();
//					price += r.getPrice()*r.getMinGuests();
//				}
//			}
//			
//			if (minCapacity <= guests && maxCapacity >= guests) {
//				break;
//			}
//		}
//		
//		System.out.println("Price: " + price);
//		System.out.println("Price pp: " + price/guests);
//		
//		return result;
//	}
//	
//	private boolean replacingRoom(List<Room> result, Room r, int guests) {
//		if (result.isEmpty()) {
//			return false;
//		}
//		int total = 0;
//		int[] capacity = new int[result.size()];
//		
//		
//		for (int i = 0; i < result.size(); i++) {
//			capacity[i] = result.get(i).getMinGuests();
//			total += result.get(i).getMinGuests(); 
//		}
//		
//		for (int i = capacity.length - 1; i >= 0 ; i--) {
//			int c = capacity[i];
//			int t = total + r.getMinGuests() - c;
////			double potentialPrice = price - result.get(i).getPrice() * result.get(i).getMinGuests();
////			
////			potentialPrice = price + r.getPrice()*r.getMinGuests();
//
//			if (t == guests) {
//				price -= result.get(i).getPrice() * result.get(i).getMinGuests();
//				price += r.getPrice() * r.getMinGuests();
//				result.remove(i);
//				result.add(r);
//				return true;
//			}
//		}
//		
//		return false;
//	}
//
//	public boolean containsRoom(final List<Room> list, final int id){
//	    return list.stream().anyMatch(o -> o.getId() == id);
//	}
//	
	public List<Room> convertToPpRooms() {
		
		List<Room> familyRooms = getFamilyRoomsFromDb();
		List<Room> ppRooms = new ArrayList<Room>();
		
		for (int i = 0; i < familyRooms.size(); i++) {
			Room r = familyRooms.get(i);
			
			int maxGuests = r.getMaxGuests();
			
			for (int j = maxGuests; j >= r.getMinGuests(); j--) {
				Room nr = new Room();
				nr.setId(r.getId());
				nr.setType(r.getType());
				nr.setStartDate(r.getStartDate());
				nr.setEndDate(r.getEndDate());
				nr.setMinGuests(j);
				nr.setMaxGuests(j);
				nr.setPriceType("pp");
				
				nr.setPrice(r.getPrice()/j);
				
				ppRooms.add(nr);
			}
		}
		
		ppRooms.addAll(getRoomsFromDb());
		
		Collections.sort(ppRooms, Comparator.comparingDouble(Room::getPrice));
		return ppRooms;
	}
	
	
	private List<Room> getFamilyRoomsFromDb() {
		List<Room> rooms = new ArrayList<Room>();
		Room r1 = new Room(60961875, "FAMILY", "2019-04-01", "2019-04-08", 2, 4, 400, "pu");
		Room r2 = new Room(60961876, "FAMILY", "2019-04-01", "2019-04-08", 1, 2, 300, "pu");
		Room r3 = new Room(60961877, "FAMILY", "2019-04-01", "2019-04-08", 2, 4, 450, "pu");
		Room r4 = new Room(60961878, "FAMILY", "2019-04-01", "2019-04-08", 2, 3, 300, "pu");
		
		rooms.add(r1);
		rooms.add(r2);
		rooms.add(r3);
		rooms.add(r4);
		
		return rooms;
	}
	
	private List<Room> getRoomsFromDb() {
		List<Room> rooms = new ArrayList<Room>();
		
		Room r1 = new Room(60961871, "TWIN", "2019-04-01", "2019-04-08", 2, 2, 95, "pp");
		Room r2 = new Room(60961872, "TWIN", "2019-04-01", "2019-04-08", 1, 2, 130, "pp");
		Room r5 = new Room(60961880, "TWIN", "2019-04-01", "2019-04-08", 1, 2, 150, "pp");
		Room r3 = new Room(60961873, "DOUBLE", "2019-04-01", "2019-04-08", 2, 2, 100, "pp");
		Room r4 = new Room(60961874, "DOUBLE", "2019-04-01", "2019-04-08", 2, 2, 120, "pp");
		
		rooms.add(r1);
		rooms.add(r3);
		rooms.add(r4);
		rooms.add(r2);
		rooms.add(r5);
		
		return rooms;
	}
}
