package com.manit.scs.demo.controller;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.manit.scs.demo.dao.UserRepo;
import com.manit.scs.demo.entity.Application;
import com.manit.scs.demo.entity.MessageDemo;
import com.manit.scs.demo.entity.User;
import com.manit.scs.demo.service.MessageDemoService;
import com.manit.scs.demo.service.UserService;

@Controller
public class StudentController {
	
	@Autowired
	UserService userService;
	MessageDemoService messageDemoService;
	
	JdbcTemplate jdbcTemplate;
	UserRepo userRepo;
	
	@Autowired
//	public StudentController()
//	{
//		
//	}
//	public StudentController(UserService userService) {
//		super();
//		this.userService = userService;
//	}
//	public StudentController(UserService userService, MessageDemoService messageDemoService) {
//		super();
//		this.userService = userService;
//		this.messageDemoService = messageDemoService;
//	}
   

	public StudentController(UserService userService, MessageDemoService messageDemoService,
			UserRepo userRepo,DataSource dataSource) {
		super();
		this.userService = userService;
		this.messageDemoService = messageDemoService;
		this.userRepo = userRepo;
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}


	@GetMapping("/showMyClassmates")
	public String ShowMyclassmates(Model theStudent,HttpSession request)
	{
		int year=1;
		
		User currentUser = (User) request.getAttribute("user");
		if(currentUser!=null)
			year = currentUser.getYear();
		else
			return "redirect:/showMyLoginPage";
		
		String depart="";
		depart= currentUser.getDepartmentId();
		System.out.println(" year "+year);
		System.out.println(" Department "+depart);
		theStudent.addAttribute("students",userService.MyClassmates(year,depart));
		
		return "MyClassmate";
	}
	@GetMapping("/GroupChat")
	public String showGroupChat(Model theStudent,HttpSession request)
	{
		int year=1;
		User currentUser = (User) request.getAttribute("user");
		if(currentUser!=null)
			year = currentUser.getYear();
		else
			return "redirect:/showMyLoginPage";
		String depart="";
		depart= currentUser.getDepartmentId();
		System.out.println(" year "+year);
		System.out.println(" Department "+depart);
		theStudent.addAttribute("students",userService.MyClassmates(year,depart));
		theStudent.addAttribute("message",new MessageDemo());
		theStudent.addAttribute("groupChat",messageDemoService.getUserMessage(year, depart));
		return "groupChat";
	}
	
	@PostMapping("/SendMessage/send")
	public String SaveMessage(@ModelAttribute("messageDemo") MessageDemo messageDemo,
							  HttpSession request)
	{
		User currentUser = (User) request.getAttribute("user");
		
		messageDemo.setStatus('A');
		messageDemo.setDepartment(currentUser.getDepartmentId());
		messageDemo.setYear(currentUser.getYear());
		messageDemo.setSender_id(currentUser.getId());
		
		//currentUser.addMessage(messageDemo);
		//userRepo.save(currentUser);
		
		MessageDemo newMessage =   messageDemoService.save(messageDemo);
		System.out.println(newMessage.getId());
		
		messageDemoService.addUserId(currentUser.getId(),newMessage.getId());
		
		return "redirect:/GroupChat";
	}
	
	
	@GetMapping("/ApplicationPage")
	public String ApplicationPage(Model theModel,HttpSession request)
	{
		User currentUser = (User) request.getAttribute("user");
		currentUser = userService.findByUserName(currentUser.getUserName());
		
		theModel.addAttribute("application", new Application());
		theModel.addAttribute("allApplication", currentUser.getApplications());
		return "ApplicationPage";
	}
	
	@PostMapping("/ProcessApplication")
	public String ProcessApplication(@ModelAttribute("application") Application application,Model theModel,
			HttpSession request)
	{
		User currentUser = (User) request.getAttribute("user");
		currentUser = userService.findByUserName(currentUser.getUserName());
		System.out.println(currentUser);
		application.setStatus('P');
		application.setData(java.time.LocalDate.now().toString());
		currentUser.addApplication(application);
		userRepo.save(currentUser);
		System.out.println(application.toString());
		
		theModel.addAttribute("application", new Application());
		theModel.addAttribute("allApplication", currentUser.getApplications());
		return "ApplicationPage";
		
	}
	@GetMapping("/startChat")
	public String startChat()
	{
		//return "static/index.html";
		return "index";
	}
	
}
