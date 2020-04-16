package logic;

import model.Room;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MeetingScheduler {

    private List<Room> rooms = new ArrayList<>();

    public Room scheduleMeeting(Date start, Date end) {
        Room room;
        if (rooms.isEmpty()) {
            room = createRoom(start, end);
        } else {
            room = useAvailableRoom(start, end);
            if (room == null) {
                room = createRoom(start, end);
            }
        }
        return room;
    }

    private Room useAvailableRoom(Date start, Date end) {
        for (Room room: rooms) {
            if (room.scheduleMeeting(start, end)) {
                return room;
            }
        }
        return null;
    }

    private Room createRoom(Date start, Date end) {
        return new Room(rooms.size() + 1, start, end);
    }

}
