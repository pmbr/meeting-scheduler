package org.project.model;

public class Room {

    public Integer number;

    private Meeting firstMeeting;
    private Meeting lastMeeting;

    public Room(Integer number) {
        this.number = number;
    }

    public boolean scheduleMeeting(Meeting meeting) {
        if (firstMeeting == null) {
            this.firstMeeting = meeting;
            this.lastMeeting = meeting;
        } else if (meeting.getEnd().before(firstMeeting.getStart())) {
            meeting.setNext(firstMeeting);
            firstMeeting = meeting;
            return true;
        } else if (meeting.getStart().after(lastMeeting.getEnd())) {
            lastMeeting.setNext(meeting);
            lastMeeting = meeting;
            return true;
        } else if (firstMeeting.getNext() != null) {
            Meeting currentMeeting = firstMeeting;
            Meeting nextMeeting = currentMeeting.getNext();
            while (nextMeeting != null) {
                if (meeting.getStart().after(currentMeeting.getEnd()) && meeting.getEnd().before(nextMeeting.getStart())) {
                    meeting.setNext(nextMeeting);
                    currentMeeting.setNext(meeting);
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
