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
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Employee;
import org.springframework.samples.petclinic.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController {

    private static final String VIEWS_EMPLOYEE_CREATE_OR_UPDATE_FORM = "employees/createOrUpdateEmployeeForm";
    private final CompanyService companyService;


    @Autowired
    public EmployeeController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping(value = "/employees/new", method = RequestMethod.GET)
    public String initCreationForm(Map<String, Object> model) {
        Employee employee = new Employee();
        model.put("employee", employee);
        return VIEWS_EMPLOYEE_CREATE_OR_UPDATE_FORM;
    }

    @RequestMapping(value = "/employees/new", method = RequestMethod.POST)
    public String processCreationForm(@Valid Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_EMPLOYEE_CREATE_OR_UPDATE_FORM;
        } else {
            this.companyService.saveEmployee(employee);
            return "redirect:/employees/" + employee.getId();
        }
    }

    @RequestMapping(value = "/employees/find", method = RequestMethod.GET)
    public String initFindForm(Map<String, Object> model) {
        model.put("employee", new Employee());
        return "employees/findEmployees";
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public String processFindForm(Employee employee, BindingResult result, Map<String, Object> model) {

        // allow parameterless GET request for /employees to return all records
        if (employee.getLastName() == null) {
            employee.setLastName(""); // empty string signifies broadest possible search
        }

        // find employees by last name
        Collection<Employee> results = this.companyService.findEmployeeByLastName(employee.getLastName());
        if (results.isEmpty()) {
            // no employees found
            result.rejectValue("lastName", "notFound", "not found");
            return "employees/findEmployees";
        } else if (results.size() == 1) {
            // 1 employee found
            employee = results.iterator().next();
            return "redirect:/employees/" + employee.getId();
        } else {
            // multiple employees found
            model.put("selections", results);
            return "employees/employeesList";
        }
    }

    @RequestMapping(value = "/employees/{employeeId}/edit", method = RequestMethod.GET)
    public String initUpdateEmployeeForm(@PathVariable("employeeId") int employeeId, Model model) {
        Employee employee = this.companyService.findEmployeeById(employeeId);
        model.addAttribute(employee);
        return VIEWS_EMPLOYEE_CREATE_OR_UPDATE_FORM;
    }

    @RequestMapping(value = "/employees/{employeeId}/edit", method = RequestMethod.POST)
    public String processUpdateEmployeeForm(@Valid Employee employee, BindingResult result, @PathVariable("employeeId") int employeeId) {
        if (result.hasErrors()) {
            return VIEWS_EMPLOYEE_CREATE_OR_UPDATE_FORM;
        } else {
            employee.setId(employeeId);
            this.companyService.saveEmployee(employee);
            return "redirect:/employees/{employeeId}";
        }
    }

    /**
     * Custom handler for displaying an employee.
     *
     * @param employeeId the ID of the employee to display
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping("/employees/{employeeId}")
    public ModelAndView showEmployee(@PathVariable("employeeId") int employeeId) {
        ModelAndView mav = new ModelAndView("employees/{employeeId/employeeDetails");
        mav.addObject(this.companyService.findEmployeeById(employeeId));
        return mav;
    }

}
