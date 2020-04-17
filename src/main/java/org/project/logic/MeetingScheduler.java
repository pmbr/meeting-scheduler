package org.project.logic;

import org.project.exception.InvalidMeetingException;
import org.project.exception.InvalidPoolSizeException;
import org.project.exception.NoRoomAvailableException;
import org.project.model.Meeting;
import org.project.model.Room;

import java.util.ArrayList;
import java.util.Date;
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
        this.poolSize = poolSize;
    }

    public Room scheduleMeeting(Date start, Date end) {
        if (end.before(start)) {
            throw new InvalidMeetingException(start, end);
        }
        Meeting meeting = new Meeting(start, end);
        Room room;
        if (rooms.isEmpty()) {
            room = createRoomAndScheduleMeeting(meeting);
        } else {
            room = useAvailableRoom(meeting);
            if (room == null) {
                room = createRoomAndScheduleMeeting(meeting);
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

    private Room createRoomAndScheduleMeeting(Meeting meeting) {
        if (this.poolSize != -1 && rooms.size() == this.poolSize) {
            throw new NoRoomAvailableException();
        }
        Room room = new Room(rooms.size() + 1);
        room.scheduleMeeting(meeting);
        rooms.add(room);
        return room;
    }

    public int roomsOccupied() {
        return rooms.size();
    }

}
