package com.greatLearning.employeeRegistration.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greatLearning.employeeRegistration.entity.employee;
import com.greatLearning.employeeRegistration.service.employeeService;




@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private employeeService employeeService;



	// add mapping for "/list"

	@RequestMapping("/list")
	public String listemployees(Model theModel) {
		

		// get employees from db
		List<employee> theemployees = employeeService.findAll();

		// add to the spring model
		theModel.addAttribute("employee", theemployees);
		

		return "list-employees";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data
		employee theemployee = new employee();

		theModel.addAttribute("employee", theemployee);

		return "employee-form";
	}

	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId,
			Model theModel) {

		// get the employee from the service
		employee theemployee = employeeService.findById(theId);


		// set employee as a model attribute to pre-populate the form
		theModel.addAttribute("employee", theemployee);

		// send over to our form
		return "employee-form";			
	}


	@PostMapping("/save")
	public String saveBook(@RequestParam("id") int id,
			@RequestParam("firstname") String firstname,@RequestParam("lastname") String lastname,@RequestParam("email") String email) {

		System.out.println(id);
		employee theemployee;
		if(id!=0)
		{
			theemployee=employeeService.findById(id);
			theemployee.setFirstname(firstname);
			theemployee.setLastname(lastname);
			theemployee.setEmail(email);
		}
		else
			theemployee=new employee(firstname, lastname, email);
		// save the employee
		employeeService.save(theemployee);


		// use a redirect to prevent duplicate submissions
		return "redirect:/employees/list";

	}

	

	@RequestMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId) {

		// delete the employee
		employeeService.deleteById(theId);

		// redirect to /employees/list
		return "redirect:/employees/list";

	}


	@RequestMapping("/search")
	public String search(@RequestParam("firstname") String firstname,
			@RequestParam("lastname") String lastname,
			Model theModel) {

		// check names, if both are empty then just give list of all employees

		if (firstname.trim().isEmpty() && lastname.trim().isEmpty()) {
			return "redirect:/employee/list";
		}
		else {
			// else, search by first name and last name
			List<employee> theemployee =
					employeeService.searchBy(firstname, lastname);

			// add to the spring model
			theModel.addAttribute("employee", theemployee);

			// send to list-employees
			return "list-employees";
		}

	}
	
	@RequestMapping(value = "/403")
	public ModelAndView accesssDenied(Principal user) {

		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() 
			+ ", you do not have permission to access this page!");
		} else {
			model.addObject("msg", 
			"You do not have permission to access this page!");
		}

		model.setViewName("403");
		return model;

	}
}


















