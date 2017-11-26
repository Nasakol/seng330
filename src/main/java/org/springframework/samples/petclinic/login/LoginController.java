//package org.springframework.samples.petclinic.login;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.SessionAttributes;
//
//import org.springframework.samples.petclinic.login.LoginService;
//
//@Controller
//@SessionAttributes("name")
//public class LoginController {
//	
//	@Autowired
//	private LoginService loginService;
//	
//	
//	@RequestMapping(value="/login", method=RequestMethod.GET)
//	public String loginpage() {
//		return "login";
//	}
//	
//	@RequestMapping(value="/login", method=RequestMethod.POST)
//	public String handleLoginRequest(@RequestParam String name, @RequestParam String password, ModelMap model) {
//		
//		boolean isUserValid = loginService.validateUser(name, password);
//		System.out.println("name"+name);
//		if(isUserValid) {
//			model.put("name", name);
//			return "welcome";
//		}else {
//			model.put("errorMessage", "inValid username or password");
//			return "login";
//		}
//	
//
//		
//	}
//}
