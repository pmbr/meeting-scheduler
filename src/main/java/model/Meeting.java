package model;

import java.util.Date;

public class Meeting {

    public Meeting(Date start, Date end) {
        this.start = start;
        this.end = end;
    }

    public Meeting previous;
    public Meeting next;

    public Date start;
    public Date end;

}
