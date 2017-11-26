/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Customer;
import org.springframework.samples.petclinic.model.Event;
import org.springframework.samples.petclinic.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Collection;

@Controller
@RequestMapping("/{customerId}")
public class EventController {

    private static final String VIEWS_EVENTS_CREATE_OR_UPDATE_FORM = "events/createOrUpdateEventForm";
    private final CompanyService companyService;

    @Autowired
    public EventController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @ModelAttribute("customer")
    public Customer findCustomer(@PathVariable("customerId") int customerId) {
        return this.companyService.findCustomerById(customerId);
    }

    @InitBinder("customer")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping(value = "/events/new", method = RequestMethod.GET)
    public String initCreationForm(Customer customer, ModelMap model) {
        Event event = new Event();
        customer.addEvent(event);
        model.put("event", event);
        return VIEWS_EVENTS_CREATE_OR_UPDATE_FORM;
    }

    @RequestMapping(value = "/events/new", method = RequestMethod.POST)
    public String processCreationForm(Customer customer, @Valid Event event, BindingResult result, ModelMap model) {
        if (StringUtils.hasLength(event.getName()) && event.isNew() && customer.getEvent(event.getName(), true) != null){
            result.rejectValue("name", "duplicate", "already exists");
        }
        if (result.hasErrors()) {
            model.put("event", event);
            return VIEWS_EVENTS_CREATE_OR_UPDATE_FORM;
        } else {
            customer.addEvent(event);
            this.companyService.saveEvent(event);
            return "redirect:/{customerId}/events/{eventId}";
        }
    }

    @RequestMapping(value = "/events/{eventId}/edit", method = RequestMethod.GET)
    public String initUpdateForm(@PathVariable("eventId") int eventId, ModelMap model) {
        Event event = this.companyService.findEventById(eventId);
        model.put("event", event);
        return VIEWS_EVENTS_CREATE_OR_UPDATE_FORM;
    }

    @RequestMapping(value = "/events/{eventId}/edit", method = RequestMethod.POST)
    public String processUpdateForm(@Valid Event event, BindingResult result, Customer customer, ModelMap model) {
        if (result.hasErrors()) {
            model.put("event", event);
            return VIEWS_EVENTS_CREATE_OR_UPDATE_FORM;
        } else {
            customer.addEvent(event);
            this.companyService.saveEvent(event);
            return "redirect:/customers/{customerId}";
        }
    }

}
