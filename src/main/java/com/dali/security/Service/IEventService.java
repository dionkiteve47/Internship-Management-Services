package com.dali.security.Service;

import com.dali.security.Entity.Event;

import java.util.List;

public interface IEventService {
    Event addEvent(Event event);
    List<Event> getAllEvent();
    Event getEventById(long idEvent);
    void deleteEvent(long idEvent);
    Event updateEvent(Event event);

}
