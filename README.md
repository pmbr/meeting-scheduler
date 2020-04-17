# meeting-scheduler

This project is a basic library to manage meetings scheduled on a pool of meeting rooms. That pool can have fixed size or not. 

Meetings will be automatically scheduled to any room based on their availability.

### How to use it

To create a pool of fixed size:

```
MeetingScheduler scheduler = new MeetingScheduler(5);
```

Minimum size of a pool is 1, and attempt to create a pool of size zero or negative will cause ```InvalidPoolSizeException```

To create a pool of flexible size, ie, a pool of virtually infinite size:

```
MeetingScheduler scheduler = new MeetingScheduler();
```

In this case, pool size will be limited by JVM memory, and also limited to max value of int, ie, 2,147,483,647.
Reason is that pool is managed using an instance of ArrayList, which internally control its own size using an int variable.

To schedule a new meeting:

```
Room room = scheduler.schedule(Date start, Date end);
```

```Integer roomNumber = room.getNumber()``` can be used to obtain room where meeting was scheduled.

```int roomsOccupied = scheduler.roomsOccupied()``` can be used to obtain the number of rooms occupied, ie, has any meeting scheduled at any time.

For pools of limited size, attempt to schedule a meeting for a period where no room is available will cause ```NoRoomAvailableException```

### Implementation

Meetings scheduled for each room is managed using an implementation of a linked list. 
This approach was selected to keep scheduled meetings always ordered by start/end and allow transverse them trying to find a spot for a new meeting.  

To simplify logic, two meetings cannot use same room if start date of of a meeting is the end date of previous one. 
For instance, a meeting from 10:00 AM to 11:00 AM and another from 11:00 AM to 12:00 AM will occupy two rooms. 
To use same room, second meeting would need to be scheduled from 11:01 AM to 12:00 AM.