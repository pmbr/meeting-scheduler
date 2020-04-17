package org.project.exception;

import java.util.Date;

import static java.lang.String.format;

public class InvalidMeetingException extends RuntimeException {

    public InvalidMeetingException(Date start, Date end) {
        super(format("Meeting end date must be bigger than start date. Start date: %s End date: %s", start, end));
    }

}
