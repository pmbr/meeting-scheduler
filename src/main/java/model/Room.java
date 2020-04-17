package model;

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
        if (meeting.getEnd().before(firstMeeting.getEnd())) {
            firstMeeting.setPrevious(meeting);
            meeting.setNext(firstMeeting);
            firstMeeting = meeting;
        } else if (meeting.start.after(lastMeeting.getEnd())) {
            lastMeeting.setPrevious(meeting);
            meeting.setNext(lastMeeting);
            lastMeeting = meeting;
        } else if (scheduledMeetings > 1){
            Meeting previousMeeting = firstMeeting;
            Meeting nextMeeting = firstMeeting.getNext();
            while (nextMeeting != null) {
                if (meeting.getEnd().after(previousMeeting.getEnd()) && meeting.getStart().before(nextMeeting.getStart())) {
                    previousMeeting.setNext(meeting);
                    nextMeeting.setPrevious(meeting);
                    return true;
                }
            }
        }

        return false;
    }

    public Integer getNumber() {
        return this.number;
    }



}
