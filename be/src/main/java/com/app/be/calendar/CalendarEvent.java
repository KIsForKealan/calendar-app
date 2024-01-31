package com.app.be.calendar;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "calendar_event")
public class CalendarEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long calendarEventId;

    @Column(name = "username")
    private String username;

    @Column(name = "type")
    private CalendarEventType type;

    @Column(name = "description")
    private String description;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    public CalendarEvent() {}

    public CalendarEvent(String username,
                         CalendarEventType type,
                         String description,
                         LocalDateTime startTime,
                         LocalDateTime endTime) {
        this.username = username;
        this.type = type;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public long getCalendarEventId() {
        return calendarEventId;
    }

    public void setCalendarEventId(long calendarEventId) {
        this.calendarEventId = calendarEventId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public CalendarEventType getType() {
        return type;
    }

    public void setType(CalendarEventType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "{ " +
                "\"calendarEventId\": \"" + calendarEventId + "\"" +
                ",\"username\": \"" + username + "\"" +
                ",\"type\": \"" + type + "\"" +
                ",\"description\": \"" + description + "\"" +
                ",\"startTime\": \"" + startTime + "\"" +
                ",\"endTime\": \"" + endTime + "\"" +
                '}';
    }

}
