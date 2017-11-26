package org.springframework.samples.petclinic.event;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import org.springframework.samples.petclinic.event.service.EventService;

@Controller
@SessionAttributes("name")
public class EventController {

	@Autowired
	private EventService service;

	@RequestMapping(value = "/list-events", method = RequestMethod.GET)
	public String showLoginPage(ModelMap model) {
		model.addAttribute("events", service.retrieveEvents("user"));
		return "list-events";
	}
	@RequestMapping(value = "/add-event", method = RequestMethod.GET)
	public String showEventPage() {
		return "addEvent";
	}

	@RequestMapping(value = "/add-event", method = RequestMethod.POST)
	public String addEvent(ModelMap model, @RequestParam String eventName) {
		service.addEvent((String) model.get("name"), eventName, new Date(), false);
		model.clear();// to prevent request parameter "name" to be passed
		return "redirect:/list-events";
	}
}