package model;

import java.util.Date;

public class Meeting {

    public Date start;
    public Date end;

    public Meeting previous;
    public Meeting next;

    public Meeting(Date start, Date end) {
        this.start = start;
        this.end = end;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public Meeting getPrevious() {
        return previous;
    }

    public void setPrevious(Meeting previous) {
        this.previous = previous;
    }

    public Meeting getNext() {
        return next;
    }

    public void setNext(Meeting next) {
        this.next = next;
    }


}
