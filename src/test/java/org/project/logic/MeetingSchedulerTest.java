package org.project.logic;

import org.junit.Test;
import org.project.exception.InvalidPoolSizeException;
import org.project.exception.NoRoomAvailableException;
import org.project.model.Room;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class MeetingSchedulerTest {

    @Test
    public void schedulingOneMeetingOccupiesOneRoom() {
        MeetingScheduler scheduler = new MeetingScheduler();

        Room room = scheduler.scheduleMeeting(date(10, 1), date(11, 0));
        assertEquals(new Integer(1), room.getNumber());

        assertEquals(1, scheduler.roomsOccupied());
    }

    @Test
    public void schedulingTwoSequentialMeetingsOccupiesOneRoom() {
        MeetingScheduler scheduler = new MeetingScheduler();

        Room roomMeeting1 = scheduler.scheduleMeeting(date(10, 1), date(11, 0));
        assertEquals(new Integer(1), roomMeeting1.getNumber());

        Room roomMeeting2 = scheduler.scheduleMeeting(date(11, 1), date(12, 0));
        assertEquals(new Integer(1), roomMeeting2.getNumber());

        assertEquals(1, scheduler.roomsOccupied());
    }

    @Test
    public void schedulingMeetingBeforeFirstMeetingScheduledOccupiesNoExtraRoom() {
        MeetingScheduler scheduler = new MeetingScheduler();

        scheduler.scheduleMeeting(date(10, 1), date(11, 0));
        scheduler.scheduleMeeting(date(11, 1), date(12, 0));

        Room room = scheduler.scheduleMeeting(date(9, 1), date(10, 0));
        assertEquals(new Integer(1), room.getNumber());

        assertEquals(1, scheduler.roomsOccupied());
    }

    @Test
    public void schedulingMeetingAfterLastMeetingScheduledOccupiesNoExtraRoom() {
        MeetingScheduler scheduler = new MeetingScheduler();

        scheduler.scheduleMeeting(date(10, 1), date(11, 0));
        scheduler.scheduleMeeting(date(11, 1), date(12, 0));

        Room room = scheduler.scheduleMeeting(date(12, 1), date(13, 0));
        assertEquals(new Integer(1), room.getNumber());

        assertEquals(1, scheduler.roomsOccupied());
    }

    @Test
    public void schedulingMeetingBetweenScheduledMeetingsOccupiesNoExtraRoom() {
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

    @Test
    public void schedulingMeetingStartingWhenAnotherEndsOccupiesExtraRoom() {
        MeetingScheduler scheduler = new MeetingScheduler();

        Room roomMeeting1 = scheduler.scheduleMeeting(date(9, 1), date(10, 0));
        assertEquals(new Integer(1), roomMeeting1.getNumber());

        Room roomMeeting2 = scheduler.scheduleMeeting(date(10, 0), date(11, 0));
        assertEquals(new Integer(2), roomMeeting2.getNumber());

        assertEquals(2, scheduler.roomsOccupied());
    }

    @Test
    public void schedulingMeetingEndingWhenAnotherStartsOccupiesExtraRoom() {
        MeetingScheduler scheduler = new MeetingScheduler();

        Room roomMeeting1 = scheduler.scheduleMeeting(date(10, 0), date(11, 0));
        assertEquals(new Integer(1), roomMeeting1.getNumber());

        Room roomMeeting2 = scheduler.scheduleMeeting(date(9, 1), date(10, 0));
        assertEquals(new Integer(2), roomMeeting2.getNumber());

        assertEquals(2, scheduler.roomsOccupied());
    }

    @Test(expected = NoRoomAvailableException.class)
    public void schedulingMeetingForPeriodWithNoRoomAvailableThrownException() {
        MeetingScheduler scheduler = new MeetingScheduler(2);

        scheduler.scheduleMeeting(date(7, 1), date(8, 0));
        scheduler.scheduleMeeting(date(7, 15), date(8, 40));
        scheduler.scheduleMeeting(date(6, 1), date(10, 0));
    }

    @Test
    public void schedulingSetOfMeetingsOccupiesExpectedNumberOfRooms() {
        MeetingScheduler scheduler = new MeetingScheduler();

        Room roomMeeting1 = scheduler.scheduleMeeting(date(10, 0), date(11, 0));
        assertEquals(new Integer(1), roomMeeting1.getNumber());

        Room roomMeeting2 = scheduler.scheduleMeeting(date(10, 30), date(11, 30));
        assertEquals(new Integer(2), roomMeeting2.getNumber());

        Room roomMeeting3 = scheduler.scheduleMeeting(date(13, 00), date(14, 00));
        assertEquals(new Integer(1), roomMeeting3.getNumber());

        Room roomMeeting4 = scheduler.scheduleMeeting(date(13, 30), date(13, 45));
        assertEquals(new Integer(2), roomMeeting4.getNumber());

        Room roomMeeting5 = scheduler.scheduleMeeting(date(17, 00), date(18, 00));
        assertEquals(new Integer(1), roomMeeting5.getNumber());

        Room roomMeeting6 = scheduler.scheduleMeeting(date(16, 00), date(19, 00));
        assertEquals(new Integer(2), roomMeeting6.getNumber());

        Room roomMeeting7 = scheduler.scheduleMeeting(date(17, 10), date(17, 50));
        assertEquals(new Integer(3), roomMeeting7.getNumber());

        Room roomMeeting8 = scheduler.scheduleMeeting(date(17, 00), date(18, 00));
        assertEquals(new Integer(4), roomMeeting8.getNumber());

        assertEquals(4, scheduler.roomsOccupied());
    }

    @Test(expected = InvalidPoolSizeException.class)
    public void creatingPoolOfZeroSizeThrowsException() {
        new MeetingScheduler(0);
    }

    @Test(expected = InvalidPoolSizeException.class)
    public void creatingPoolOfNegativeSizeThrowsException() {
        new MeetingScheduler(-10);
    }

    private Date date(int hrs, int min) {
        return new Date(2020, 4, 16, hrs, min);
    }

}