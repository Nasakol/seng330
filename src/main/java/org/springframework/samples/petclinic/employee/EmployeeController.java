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
package org.springframework.samples.petclinic.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Employee;
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
class EmployeeController {

	private static final String VIEWS_EMPLOYEE_CREATE_OR_UPDATE_FORM = "employees/createOrUpdateemployeeForm";
    private final EmployeeRepository employees;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepo) {
        this.employees = employeeRepo;
    }


    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/employees/new")
    public String initCreationForm(Map<String, Object> model) {
        Employee employee = new Employee();
        model.put("employee", employee);
        return VIEWS_EMPLOYEE_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/employees/new")
    public String processCreationForm(@Valid Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_EMPLOYEE_CREATE_OR_UPDATE_FORM;
        } else {
            this.employees.save(employee);
            return "redirect:/employees/" + employee.getId();
        }
    }

    @GetMapping("/employees/find")
    public String initFindForm(Map<String, Object> model) {
        model.put("employee", new Employee());
        return "employees/findemployees";
    }

    @GetMapping("/employees")
    public String processFindForm(Employee employee, BindingResult result, Map<String, Object> model) {

        // allow parameterless GET request for /employees to return all records
        if (employee.getLastName() == null) {
            employee.setLastName(""); // empty string signifies broadest possible search
        }

        // find employees by last name
        Collection<Employee> results = this.employees.findByLastName(employee.getLastName());
        if (results.isEmpty()) {
            // no employees found
            result.rejectValue("lastName", "notFound", "not found");
            return "employees/findemployees";
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

    @GetMapping("/employees/{employeeId}/edit")
    public String initUpdateemployeeForm(@PathVariable("employeeId") int employeeId, Model model) {
        Employee employee = this.employees.findById(employeeId);
        model.addAttribute(employee);
        return VIEWS_EMPLOYEE_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/employees/{employeeId}/edit")
    public String processUpdateemployeeForm(@Valid Employee employee, BindingResult result, @PathVariable("employeeId") int employeeId) {
        if (result.hasErrors()) {
            return VIEWS_EMPLOYEE_CREATE_OR_UPDATE_FORM;
        } else {
            employee.setId(employeeId);
            this.employees.save(employee);
            return "redirect:/employees/{employeeId}";
        }
    }

    /**
     * Custom handler for displaying an employee.
     *
     * @param employeeId the ID of the employee to display
     * @return a ModelMap with the model attributes for the view
     */
    @GetMapping("/employees/{employeeId}")
    public ModelAndView showemployee(@PathVariable("employeeId") int employeeId) {
        ModelAndView mav = new ModelAndView("employees/employeeDetails");
        mav.addObject(this.employees.findById(employeeId));
        return mav;
    }
    
    

}
