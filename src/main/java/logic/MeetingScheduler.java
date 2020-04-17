package logic;

import exception.InvalidPoolSizeException;
import exception.NoRoomAvailableException;
import model.Meeting;
import model.Room;

import java.util.ArrayList;
import java.util.List;

public class MeetingScheduler {

    private int poolSize;
    private List<Room> rooms = new ArrayList<>();

    public MeetingScheduler() {
        this.poolSize = -1;
    }

    public MeetingScheduler(int poolSize) {
        if (poolSize < 1) {
            throw new InvalidPoolSizeException();
        }
    }

    public Room scheduleMeeting(Meeting meeting) {
        Room room;
        if (rooms.isEmpty()) {
            room = createRoom(meeting);
        } else {
            room = useAvailableRoom(meeting);
            if (room == null) {
                room = createRoom(meeting);
            }
        }
        return room;
    }

    private Room useAvailableRoom(Meeting meeting) {
        for (Room room: rooms) {
            if (room.scheduleMeeting(meeting)) {
                return room;
            }
        }
        return null;
    }

    private Room createRoom(Meeting meeting) {
        if (this.poolSize != -1 && rooms.size() == this.poolSize) {
            throw new NoRoomAvailableException();
        }
        return new Room(rooms.size() + 1, meeting);
    }

    public int roomsUsed() {
        return rooms.size();
    }

}
