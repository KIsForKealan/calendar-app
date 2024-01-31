package com.app.be.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class CalendarEventController {

    @Autowired
    private CalendarEventService calendarEventService;

    @PostMapping(path = "/api/calendar-event/create")
    public ResponseEntity<CalendarEvent> createCalendarEvent(@RequestBody CalendarEvent calendarEvent) {
            return calendarEventService.createCalendarEvent(calendarEvent);
    }

}
