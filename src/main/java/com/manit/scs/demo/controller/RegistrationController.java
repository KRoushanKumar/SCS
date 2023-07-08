package com.manit.scs.demo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.manit.scs.demo.dao.AdmissionYearRepository;
import com.manit.scs.demo.entity.AdmissionYear;
import com.manit.scs.demo.entity.User;
import com.manit.scs.demo.service.DepartmentService;
import com.manit.scs.demo.service.UserService;
import com.manit.scs.demo.user.CrmUser;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	
    @Autowired
    private UserService userService;
	private AdmissionYearRepository adYearRepo;
	private DepartmentService departmentService;
	
	@Autowired
    public RegistrationController(AdmissionYearRepository adYearRepo,DepartmentService departmentService) {
		super();
		this.adYearRepo = adYearRepo;
		this.departmentService=departmentService;
	}
    
  

	private Logger logger = Logger.getLogger(getClass().getName());
    

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	
	
	@GetMapping("/validStudent")
	public String validStudent(Model theModel)
	{
		theModel.addAttribute("student", new User());
		
		
		return "validStudent";
	}
	
	@PostMapping("/getStudentbyScholar")
	public String getStudentbyScholar( @ModelAttribute("student") User theUser,Model theStudent)
	{
		System.out.println("sch = "+theUser.getScholarNo());
		
		User foundUser = userService.findByScholarId(theUser.getScholarNo());
		CrmUser crmUser = new CrmUser();
		
		
		try {
		
			if(foundUser!=null)
			{
				crmUser.setFirstName(foundUser.getFirstName());
				crmUser.setLastName(foundUser.getLastName());
				crmUser.setUserName(foundUser.getUserName());
				crmUser.setId(foundUser.getId());
				theStudent.addAttribute("crmUser", crmUser);
				theStudent.addAttribute("admissionYear",adYearRepo.findAll());
				theStudent.addAttribute("department",departmentService.findAll());
				return "registration-form";
			}
			
		}
		catch (Exception e) {
			
		} 
		theStudent.addAttribute("userNotFound", "No recond Found");
		return "validStudent";
	}
	
	@GetMapping("/showRegistrationForm")
	public String showMyLoginPage(Model theModel) {
		
		theModel.addAttribute("crmUser", new CrmUser());
		theModel.addAttribute("admissionYear",adYearRepo.findAll());
		theModel.addAttribute("department",departmentService.findAll());
		
		return "registration-form";
	}

	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(
				@Valid @ModelAttribute("crmUser") CrmUser theCrmUser, 
				BindingResult theBindingResult, 
				Model theModel,
				@RequestParam("imgInp") MultipartFile multipartFile
				)throws IOException {
		
		String userName = theCrmUser.getUserName();
		logger.info("Processing registration form for: " + userName);
		
		// form validation
		 if (theBindingResult.hasErrors()){
			 
			 AdmissionYear year1 = new AdmissionYear(1,2021);
				AdmissionYear year2 = new AdmissionYear(2,2022);
				
				List<AdmissionYear> y = new ArrayList<AdmissionYear>();
				y.add(year1);
				y.add(year2);
				
				theModel.addAttribute("admissionYear",adYearRepo.findAll());
				theModel.addAttribute("department",departmentService.findAll());
			 return "registration-form";
	        }

		// check the database if user already exists
//        User existing = userService.findByUserName(userName);
//        if (existing != null){
//        	theModel.addAttribute("crmUser", new CrmUser());
//			theModel.addAttribute("registrationError", "User name already exists.");
//			
//			AdmissionYear year1 = new AdmissionYear(1,2021);
//			AdmissionYear year2 = new AdmissionYear(2,2022);
//			
//			List<AdmissionYear> y = new ArrayList<AdmissionYear>();
//			y.add(year1);
//			y.add(year2);
//			
//			theModel.addAttribute("admissionYear",adYearRepo.findAll());
//			theModel.addAttribute("department",departmentService.findAll());
//			logger.warning("User name already exists.");
//        	return "registration-form";
//        }
        
        String imageFileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		theCrmUser.setImage(imageFileName);
		
        
        
        // create user account      
		 System.out.println(" id = " + theCrmUser.getId());
         User savedUser =  userService.saveByRet(theCrmUser);
        
        String uploadDir = "./src/main/resources/static/img/stuImg/" + savedUser.getId() ;
        
		Path UploadPath = Paths.get(uploadDir);
		if(!Files.exists(UploadPath))
		{
			try {
				Files.createDirectories(UploadPath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try(InputStream inputStream = multipartFile.getInputStream() )
		{
			Path filePath=UploadPath.resolve(imageFileName);
			Files.copy(inputStream, filePath,StandardCopyOption.REPLACE_EXISTING);
			System.out.println(filePath.toFile().getAbsolutePath());
		}
		catch(IOException e)
		{
			throw new IOException("Could not save file" + imageFileName);
		}
        
        logger.info("Successfully created user: " + userName);
        
        return "registration-confirmation";		
	}
}
