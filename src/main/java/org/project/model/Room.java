package org.project.model;

public class Room {

    public Integer number;

    private int scheduledMeetings;
    private Meeting firstMeeting;
    private Meeting lastMeeting;

    public Room(Integer number, Meeting meeting) {
        this.number = number;
        this.firstMeeting = meeting;
        this.lastMeeting = this.firstMeeting;
        scheduledMeetings = 1;
    }

    public boolean scheduleMeeting(Meeting meeting) {
        if (meeting.getEnd().before(firstMeeting.getStart())) {
            meeting.setNext(firstMeeting);
            firstMeeting = meeting;
            scheduledMeetings++;
            return true;
        } else if (meeting.getStart().after(lastMeeting.getEnd())) {
            lastMeeting.setNext(meeting);
            lastMeeting = meeting;
            scheduledMeetings++;
            return true;
        } else if (scheduledMeetings > 1) {
            Meeting currentMeeting = firstMeeting;
            Meeting nextMeeting = currentMeeting.getNext();
            while (nextMeeting != null) {
                if (meeting.getStart().after(currentMeeting.getEnd()) && meeting.getEnd().before(nextMeeting.getStart())) {
                    meeting.setNext(nextMeeting);
                    currentMeeting.setNext(meeting);
                    scheduledMeetings++;
                    return true;
                } else {
                    currentMeeting = nextMeeting;
                    nextMeeting = currentMeeting.getNext();
                    if (meeting.getEnd().before(currentMeeting.getStart())) {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    public Integer getNumber() {
        return this.number;
    }

}
