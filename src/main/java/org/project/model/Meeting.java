package org.project.model;

import java.util.Date;

public class Meeting {

    public Date start;
    public Date end;

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

    public Meeting getNext() {
        return next;
    }

    public void setNext(Meeting next) {
        this.next = next;
    }

}
