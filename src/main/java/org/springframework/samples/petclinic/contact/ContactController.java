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
package org.springframework.samples.petclinic.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;


@Controller
class ContactController {

	private static final String VIEWS_CONTACT_CREATE_OR_UPDATE_FORM = "contacts/createOrUpdateContactForm";
    private final ContactRepository contacts;

    @Autowired
    public ContactController(ContactRepository contactRepo) {
        this.contacts = contactRepo;
    }


    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/contacts/new")
    public String initCreationForm(Map<String, Object> model) {
        Contact contact = new Contact();
        model.put("contact", contact);
        return VIEWS_CONTACT_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/contacts/new")
    public String processCreationForm(@Valid Contact contact, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_CONTACT_CREATE_OR_UPDATE_FORM;
        } else {
            this.contacts.save(contact);
            return "redirect:/contacts/" + contact.getId();
        }
    }

    @GetMapping("/contacts/find")
    public String initFindForm(Map<String, Object> model) {
        model.put("contact", new Contact());
        return "contacts/findcontacts";
    }

    @GetMapping("/contacts")
    public String processFindForm(Contact contact, BindingResult result, Map<String, Object> model) {

        // allow parameterless GET request for /contacts to return all records
        if (contact.getName() == null) {
            contact.setName(""); // empty string signifies broadest possible search
        }

        // find contacts by name
        Collection<Contact> results = this.contacts.findByName(contact.getName());
        if (results.isEmpty()) {
            // no contacts found
            result.rejectValue("name", "notFound", "not found");
            return "contacts/findcontacts";
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

    @GetMapping("/contacts/{contactId}/edit")
    public String initUpdatecontactForm(@PathVariable("contactId") int contactId, Model model) {
        Contact contact = this.contacts.findById(contactId);
        model.addAttribute(contact);
        return VIEWS_CONTACT_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/contacts/{contactId}/edit")
    public String processUpdatecontactForm(@Valid Contact contact, BindingResult result, @PathVariable("contactId") int contactId) {
        if (result.hasErrors()) {
            return VIEWS_CONTACT_CREATE_OR_UPDATE_FORM;
        } else {
            contact.setId(contactId);
            this.contacts.save(contact);
            return "redirect:/contacts/{contactId}";
        }
    }

    /**
     * Custom handler for displaying an contact.
     *
     * @param contactId the ID of the contact to display
     * @return a ModelMap with the model attributes for the view
     */
    @GetMapping("/contacts/{contactId}")
    public ModelAndView showcontact(@PathVariable("contactId") int contactId) {
        ModelAndView mav = new ModelAndView("contacts/contactDetails");
        mav.addObject(this.contacts.findById(contactId));
        return mav;
    }
    
    

}
