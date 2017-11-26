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

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Event;
import org.springframework.samples.petclinic.model.Employee;
import org.springframework.samples.petclinic.model.EmployeeShift;
import org.springframework.samples.petclinic.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class EmployeeShiftController {

    private final CompanyService companyService;


    @Autowired
    public EmployeeShiftController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    /**
     * Called before each and every @RequestMapping annotated method.
     * 2 goals:
     * - Make sure we always have fresh data
     * - Since we do not use the session scope, make sure that Employee object always has an id
     * (Even though id is not part of the form fields)
     *
     * @param employeeId
     * @return Employee
     */
    @ModelAttribute("employee")
    public EmployeeShift loadEmployeeWithEmployeeShift(@PathVariable("employeeId") int employeeId) {
        Employee employee = this.companyService.findEmployeeById(employeeId);
        EmployeeShift shift = new EmployeeShift();
        employee.addEmployeeShift(shift);
        return shift;
    }

    // Spring MVC calls method loadEmployeeWithEmployeeShift(...) before initNewEmployeeShiftForm is called
    @RequestMapping(value = "/employees/{employeeId}/employeeShifts/new", method = RequestMethod.GET)
    public String initNewEmployeeShiftForm(@PathVariable("employeeId") int employeeId, Map<String, Object> model) {
        return "employees/createOrUpdateEmployeeShiftForm";
    }

    // Spring MVC calls method loadEmployeeWithEmployeeShift(...) before processNewEmployeeShiftForm is called
    @RequestMapping(value = "/employees/{employeeId}/employeeShifts/new", method = RequestMethod.POST)
    public String processNewEmployeeShiftForm(@Valid EmployeeShift shift, BindingResult result) {
        if (result.hasErrors()) {
            return "employees/createOrUpdateEmployeeShiftForm";
        } else {
            this.companyService.saveEmployeeShift(shift);
            return "redirect:/employees/{employeeId}";
        }
    }

    @RequestMapping(value = "/employees/{employeeId}/employeeShifts", method = RequestMethod.GET)
    public String showEmployeeShifts(@PathVariable int employeeId, Map<String, Object> model) {
        model.put("employeeShifts", this.companyService.findEmployeeById(employeeId).getEmployeeShifts());
        return "employeeShiftList";
    }

}
