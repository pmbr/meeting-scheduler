package org.project.logic;

import org.junit.Test;
import org.project.model.Room;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class MeetingSchedulerTest {

    @Test
    public void scheduleOneMeetingOccupiesOneRoom() {
        MeetingScheduler scheduler = new MeetingScheduler();

        Room room = scheduler.scheduleMeeting(date(10, 1), date(11, 0));
        assertEquals(new Integer(1), room.getNumber());

        assertEquals(1, scheduler.roomsOccupied());
    }

    @Test
    public void scheduleTwoSequentialMeetingsOccupiesOneRoom() {
        MeetingScheduler scheduler = new MeetingScheduler();

        Room roomMeeting1 = scheduler.scheduleMeeting(date(10, 1), date(11, 0));
        assertEquals(new Integer(1), roomMeeting1.getNumber());

        Room roomMeeting2 = scheduler.scheduleMeeting(date(11, 1), date(12, 0));
        assertEquals(new Integer(1), roomMeeting2.getNumber());

        assertEquals(1, scheduler.roomsOccupied());
    }

    @Test
    public void scheduleMeetingBeforeFirstMeetingScheduledOccupiesNoExtraRoom() {
        MeetingScheduler scheduler = new MeetingScheduler();

        scheduler.scheduleMeeting(date(10, 1), date(11, 0));
        scheduler.scheduleMeeting(date(11, 1), date(12, 0));

        Room room = scheduler.scheduleMeeting(date(9, 1), date(10, 0));
        assertEquals(new Integer(1), room.getNumber());

        assertEquals(1, scheduler.roomsOccupied());
    }

    @Test
    public void scheduleMeetingAfterLastMeetingScheduledOccupiesNoExtraRoom() {
        MeetingScheduler scheduler = new MeetingScheduler();

        scheduler.scheduleMeeting(date(10, 1), date(11, 0));
        scheduler.scheduleMeeting(date(11, 1), date(12, 0));

        Room room = scheduler.scheduleMeeting(date(12, 1), date(13, 0));
        assertEquals(new Integer(1), room.getNumber());

        assertEquals(1, scheduler.roomsOccupied());
    }

    @Test
    public void scheduleMeetingBetweenScheduledMeetingsOccupiesNoExtraRoom() {
        MeetingScheduler scheduler = new MeetingScheduler();

        scheduler.scheduleMeeting(date(7, 1), date(8, 0));
        scheduler.scheduleMeeting(date(11, 1), date(12, 0));
        scheduler.scheduleMeeting(date(15, 1), date(16, 0));

        Room room1 = scheduler.scheduleMeeting(date(9, 1), date(10, 0));
        assertEquals(new Integer(1), room1.getNumber());

        Room room2 = scheduler.scheduleMeeting(date(13, 1), date(14, 0));
        assertEquals(new Integer(1), room2.getNumber());

        assertEquals(1, scheduler.roomsOccupied());
    }

    private Date date(int hrs, int min) {
        return new Date(2020, 4, 16, hrs, min);
    }

}