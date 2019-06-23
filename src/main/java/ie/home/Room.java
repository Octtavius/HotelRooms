package ie.home;

public class Room {
	private int id;
	private String type;
	private String startDate;
	private String endDate;
	private int minGuests;
	private int maxGuests;
	private double price;
	private String priceType;
	
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
	
	public Room() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getStartDate() {
		return startDate;
	}
	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public String getEndDate() {
		return endDate;
	}
	
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public int getMinGuests() {
		return minGuests;
	}
	
	public void setMinGuests(int minGuests) {
		this.minGuests = minGuests;
	}
	
	public int getMaxGuests() {
		return maxGuests;
	}
	
	public void setMaxGuests(int maxGuests) {
		this.maxGuests = maxGuests;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getPriceType() {
		return priceType;
	}
	
	public void setPriceType(String priceType) {
		this.priceType = priceType;
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
