package ie.home;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class HotelRoomTest {
	
	@Test
	public void tests() {
		RoomFinder rf = new RoomFinder();
		rf.findCheapestRooms(3);
	}
}
