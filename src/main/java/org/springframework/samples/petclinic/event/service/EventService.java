package org.springframework.samples.petclinic.event.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import org.springframework.samples.petclinic.model.Event;

@Service
public class EventService {
	private static List<Event> events = new ArrayList<Event>();
	private static int eventCount = 3;

	static {
		events.add(new Event(1, "user", "Wedding", new Date(),
				false));
		events.add(new Event(2, "user", "Birthday Party", new Date(), false));
		events.add(new Event(3, "user", "Conference", new Date(),
				false));
	}

	public List<Event> retrieveEvents(String user) {
		List<Event> filteredEvents = new ArrayList<Event>();
		for (Event event : events) {
			if (event.getUser().equals(user))
				filteredEvents.add(event);
		}
		return filteredEvents;
	}

	public void addEvent(String name, String desc, Date targetDate, boolean isDone) {
		events.add(new Event(++eventCount, name, desc, targetDate, isDone));
	}

	public void deleteEvent(int id) {
		Iterator<Event> iterator = events.iterator();
		while (iterator.hasNext()) {
			Event event = iterator.next();
			if (event.getId() == id) {
				iterator.remove();
			}
		}
	}
}