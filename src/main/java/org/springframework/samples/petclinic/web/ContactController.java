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

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Contact;
import org.springframework.samples.petclinic.model.Contact;
import org.springframework.samples.petclinic.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactController {

    private final CompanyService companyService;
    private static final String VIEWS_CONTACT_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateContactForm";


    @Autowired
    public ContactController(CompanyService companyService) {
        this.companyService = companyService;
    }
    

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
    
    @RequestMapping(value = { "/contacts"})
    public String showContactList(Map<String, Object> model) {
        Collection<Contact> contacts = this.companyService.findAllContact();
        model.put("contacts", contacts);
        return "contacts/contactList";
    }

    @RequestMapping(value = "/contacts/new", method = RequestMethod.GET)
    public String initCreationForm(Map<String, Object> model) {
        Contact contact = new Contact();
        model.put("contact", contact);
        return VIEWS_CONTACT_CREATE_OR_UPDATE_FORM;
    }

    @RequestMapping(value = "/contacts/new", method = RequestMethod.POST)
    public String processCreationForm(@Valid Contact contact, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_CONTACT_CREATE_OR_UPDATE_FORM;
        } else {
            this.companyService.saveContact(contact);
            return "redirect:/contacts/" + contact.getId();
        }
    }

    @RequestMapping(value = "/contacts/find", method = RequestMethod.GET)
    public String initFindForm(Map<String, Object> model) {
        model.put("contact", new Contact());
        return "contacts/findContacts";
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public String processFindForm(Contact contact, BindingResult result, Map<String, Object> model) {

        // allow parameterless GET request for /contacts to return all records
        if (contact.getName() == null) {
            contact.setName(""); // empty string signifies broadest possible search
        }

        // find contacts by last name
        Collection<Contact> results = this.companyService.findContactByName(contact.getName());
        if (results.isEmpty()) {
            // no contacts found
            result.rejectValue("lastName", "notFound", "not found");
            return "contacts/findContacts";
        } else if (results.size() == 1) {
            // 1 contact found
            contact = results.iterator().next();
            return "redirect:/contacts/" + contact.getId();
        } else {
            // multiple contacts found
            model.put("selections", results);
            return "contacts/contactsList";
        }
    } 
    

    @RequestMapping(value = "/contacts/{contactId}/edit", method = RequestMethod.GET)
    public String initUpdateContactForm(@PathVariable("contactId") int contactId, Model model) {
        Contact contact = this.companyService.findContactById(contactId);
        model.addAttribute(contact);
        return VIEWS_CONTACT_CREATE_OR_UPDATE_FORM;
    }

    @RequestMapping(value = "/contacts/{contactId}/edit", method = RequestMethod.POST)
    public String processUpdateContactForm(@Valid Contact contact, BindingResult result, @PathVariable("contactId") int contactId) {
        if (result.hasErrors()) {
            return VIEWS_CONTACT_CREATE_OR_UPDATE_FORM;
        } else {
            contact.setId(contactId);
            this.companyService.saveContact(contact);
            return "redirect:/contacts/{contactId}";
        }
    }

    /**
     * Custom handler for displaying an contact.
     *
     * @param contactId the ID of the contact to display
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping("/contacts/{contactId}")
    public ModelAndView showContact(@PathVariable("contactId") int contactId) {
        ModelAndView mav = new ModelAndView("contacts/contactDetails");
        mav.addObject(this.companyService.findContactById(contactId));
        return mav;
    }

    


}
