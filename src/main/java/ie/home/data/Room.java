package ie.home.data;

/**
 * @author I323506
 *
 */
public class Room {
	private int id;
	private String type;
	private String startDate;
	private String endDate;
	private int minGuests;
	private int maxGuests;
	private double price;
	private String priceType;
	
	public Room() {}
	public Room(int id, String type, String startDate, String endDate, int minGuests, int maxGuests, double price,
			String priceType) {
		super();
		this.id = id;
		this.type = type;
		this.startDate = startDate;
		this.endDate = endDate;
		this.minGuests = minGuests;
		this.maxGuests = maxGuests;
		this.price = price;
		this.priceType = priceType;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public int getMinGuests() {
		return minGuests;
	}

	public int getMaxGuests() {
		return maxGuests;
	}

	public double getPrice() {
		return price;
	}

	public String getPriceType() {
		return priceType;
	}

	public String getStartDate() {
		return startDate;
	}
	
	public String getEndDate() {
		return endDate;
	}
	
	@Override
	public boolean equals(Object object) {
		if (this == object) {
            return true;
        }
		
		if (object == null || getClass() != object.getClass()) {
		    return false;
		}
		
		Room room = (Room) object;
		return getId() == room.getId();
	}
}
