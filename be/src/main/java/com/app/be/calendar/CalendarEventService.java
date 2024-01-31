package com.app.be.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class CalendarEventService {

    @Autowired
    private CalendarEventRepository calendarEventRepository;

    public ResponseEntity<CalendarEvent> createCalendarEvent(@RequestBody CalendarEvent calendarEvent) {

        try {
            CalendarEvent event = calendarEventRepository.save(new CalendarEvent(
                    calendarEvent.getUsername(),
                    calendarEvent.getType(),
                    calendarEvent.getDescription(),
                    calendarEvent.getStartTime(),
                    calendarEvent.getEndTime())
            );
            return new ResponseEntity<>(event, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
