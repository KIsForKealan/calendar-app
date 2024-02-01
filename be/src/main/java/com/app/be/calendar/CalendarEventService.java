package com.app.be.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class CalendarEventService {

    @Autowired
    private CalendarEventRepository calendarEventRepository;

    public ResponseEntity<CalendarEvent> createCalendarEvent(@RequestBody CalendarEvent calendarEvent) {
        try {
            final CalendarEvent event = calendarEventRepository.save(new CalendarEvent(
                    calendarEvent.getUsername(),
                    calendarEvent.getType(),
                    calendarEvent.getDescription(),
                    calendarEvent.getStartTime(),
                    calendarEvent.getEndTime())
            );
            return new ResponseEntity<>(event, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<CalendarEvent>> getAllCalendarEvents() {
        final List<CalendarEvent> allEvents = calendarEventRepository.findAll();

        if (allEvents.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(allEvents, HttpStatus.OK);
    }

    public ResponseEntity<CalendarEvent> getCalendarEventById(long id) {
        final Optional<CalendarEvent> event = calendarEventRepository.findById(id);

        if (event.isPresent()) {
            return new ResponseEntity<>(event.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<CalendarEvent>> getAllCalendarEventsByUsername(String username) {
        final List<CalendarEvent> allEvents = calendarEventRepository.findAllByUsername(username);

        if (allEvents.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(allEvents, HttpStatus.OK);
    }

    public ResponseEntity<CalendarEvent> updateCalendarEvent(long id, CalendarEvent calendarEvent) {
        final Optional<CalendarEvent> event = calendarEventRepository.findById(id);

        if (event.isPresent()) {
            CalendarEvent updatedEvent = event.get();
            updatedEvent.setUsername(calendarEvent.getUsername());
            updatedEvent.setType(calendarEvent.getType());
            updatedEvent.setDescription(calendarEvent.getDescription());
            updatedEvent.setStartTime(calendarEvent.getStartTime());
            updatedEvent.setEndTime(calendarEvent.getEndTime());

            try {
                return new ResponseEntity<>(calendarEventRepository.save(updatedEvent), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpStatus> deleteAllCalendarEvents() {
        try {
            calendarEventRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<HttpStatus> deleteCalendarEvent(long id) {
        try {
            calendarEventRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
