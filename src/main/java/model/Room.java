package model;

import java.util.Date;

public class Room {

    public Room(Integer number, Date start, Date end) {
        this.number = number;
        this.firstMeeting = new Meeting(start, end);
        this.lastMeeting = this.firstMeeting;
        scheduledMeetings = 1;
    }

    private int scheduledMeetings;

    public Integer number;

    public Meeting firstMeeting;
    public Meeting lastMeeting;

    public boolean scheduleMeeting(Date start, Date end) {
        Meeting meeting = new Meeting(start, end);
        if (end.before(firstMeeting.end)) {
            firstMeeting.previous = meeting;
            meeting.next = firstMeeting;
            firstMeeting = meeting;
        } else if (start.after(lastMeeting.end)) {
            lastMeeting.previous = meeting;
            meeting.next = lastMeeting;
            lastMeeting = meeting;
        } else if (scheduledMeetings > 1){
            Meeting previousMeeting = firstMeeting;
            Meeting nextMeeting = firstMeeting.next;
            while (nextMeeting != null) {
                if (end.after(previousMeeting.end) && start.before(nextMeeting.start)) {
                    previousMeeting.next = meeting;
                    nextMeeting.previous = meeting;
                    return true;
                }
            }
        }

        return false;
    }



}
