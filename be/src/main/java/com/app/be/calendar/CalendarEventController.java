package com.app.be.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class CalendarEventController {

    @Autowired
    private CalendarEventService calendarEventService;

    @PostMapping(path = "/api/calendar-events/create")
    public ResponseEntity<CalendarEvent> createCalendarEvent(@RequestBody CalendarEvent calendarEvent) {
            return calendarEventService.createCalendarEvent(calendarEvent);
    }

    @GetMapping(path = "api/calendar-events")
    public ResponseEntity<List<CalendarEvent>> getAllCalendarEvents() {
        return calendarEventService.getAllCalendarEvents();
    }

    @GetMapping(path = "api/calendar-events/{id}")
    public ResponseEntity<CalendarEvent> getCalendarEventById(@PathVariable("id") long id) {
        return calendarEventService.getCalendarEventById(id);
    }

    @GetMapping(path = "api/calendar-events/username/{username}")
    public ResponseEntity<List<CalendarEvent>> getCalendarEventById(@PathVariable("username") String username) {
        return calendarEventService.getAllCalendarEventsByUsername(username);
    }

    @PutMapping(path = "api/calendar-events/{id}")
    public ResponseEntity<CalendarEvent> updateCalendarEvent(@PathVariable("id") long id, @RequestBody CalendarEvent calendarEvent) {
        return calendarEventService.updateCalendarEvent(id, calendarEvent);
    }

    @DeleteMapping(path = "api/calendar-events/{id}")
    public ResponseEntity<HttpStatus> deleteCalendarEvent(@PathVariable("id") long id) {
        return calendarEventService.deleteCalendarEvent(id);
    }

    @DeleteMapping(path = "api/calendar-events")
    public ResponseEntity<HttpStatus> deleteAllCalendarEvents() {
        return calendarEventService.deleteAllCalendarEvents();
    }

}
