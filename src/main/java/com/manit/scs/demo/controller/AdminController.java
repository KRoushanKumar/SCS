package com.manit.scs.demo.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.manit.scs.demo.dao.AdmissionYearRepository;
import com.manit.scs.demo.dao.UserDao;
import com.manit.scs.demo.entity.User;
import com.manit.scs.demo.service.ApplicationService;
import com.manit.scs.demo.service.DepartmentService;
import com.manit.scs.demo.service.UserService;
import com.manit.scs.demo.user.BarChartOfMCA;


@Controller
@RequestMapping("/systems")
public class AdminController {

	
	@Autowired
	private UserDao userDao;
	private AdmissionYearRepository adYearRepo;
	private DepartmentService departmentService;
	private UserService userService;
	private ApplicationService appicationService;
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
    public AdminController(AdmissionYearRepository adYearRepo,DepartmentService departmentService,UserService userService,
    		ApplicationService appicationService,DataSource dataSource) {
		super();
		this.adYearRepo = adYearRepo;
		this.departmentService=departmentService;
		this.userService=userService;
		this.appicationService=appicationService;
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	@GetMapping("/admin")
	public String adminLogin()
	{
		return null;
	}
	
	@GetMapping("/addStudent")
	public String addStudent(Model model)
	{
		User newUser = new User();
		
//		newUser.setScholarNo("202120000");
//		newUser.setFirstName("First name");
//		newUser.setLastName("Last Name");
		
		model.addAttribute("student", newUser);
		
		return "addStudent";
	}
	
	@PostMapping("/addStudentProcess")
	public String addStudentProcess(@Valid @ModelAttribute("student") User student,Model theModel)
	{
		System.out.println(student);
		
		if(student.getScholarNo().length()==0 || student.getFirstName().length()==0 || student.getLastName().length()==0)
		{
			theModel.addAttribute("required", "All fields are required.");
			return "addStudent";
		}
		
		student.setPassword("student@123");
		student.setEmail("student@gmai.com");
		student.setUserName(student.getScholarNo());
		userDao.save(student);
		theModel.addAttribute("sucsess", "New Student Added ");
		return "addStudent";
	}
	@PostMapping("/addStudentProcessWithCSV")
	public String addStudentProcessCSV(@RequestParam("CSVInput") MultipartFile multipartFile,Model theModel) 
	{
		String line="";
		
		System.out.println(multipartFile.getOriginalFilename());
		//String fileName = multipartFile.getOriginalFilename();
		
		try {
			InputStream is = multipartFile.getInputStream();
		    BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			while((line=br.readLine())!=null)
			{
				String []data = line.split(",");
				User student = new User();
				student.setPassword("student@123");
				student.setEmail("student@gmai.com");
				student.setUserName(data[0]);
				student.setFirstName(data[1]);
				student.setLastName(data[2]);
				student.setScholarNo(data[0]);
				userDao.save(student);
			}
		}
		catch (Exception e) {
			
			e.printStackTrace();
			theModel.addAttribute("student",new User());
			theModel.addAttribute("error", "Upload Only .csv file");
			return "addStudent";
			
		}
		
        User newUser = new User();
		
//		newUser.setScholarNo("202120000");
//		newUser.setFirstName("First name");
//		newUser.setLastName("Last Name");
		
		theModel.addAttribute("student", newUser);
		
		theModel.addAttribute("sucsess", "New Student Added ");
		return "addStudent";
	}
	
	@GetMapping("/viewStudent")
	public String viewStudent(Model model)
	{
		model.addAttribute("admissionYear",adYearRepo.findAll());
		model.addAttribute("department",departmentService.findAll());
		return "viewStudent";
	}
	
	@GetMapping("/viewStudentProcess")
	public String viewStudentProcess(@RequestParam("year") int year,@RequestParam("department") String department,Model theMode)
	{
		System.out.println(year+ " " + department);
		
		theMode.addAttribute("students",userService.MyClassmates(year,department));
		theMode.addAttribute("admissionYear",adYearRepo.findAll());
		theMode.addAttribute("department",departmentService.findAll());
		return "viewStudent";
	}
	
	@GetMapping("/viewApplication")
	public String viewApplication(Model theMode)
	{
		
		theMode.addAttribute("allApplication", appicationService.findAll());
		return "ad_viewApplication";
				
	}
	@GetMapping("/application/approve/{id}")
	public String approveApp(@PathVariable int id)
	{
		appicationService.approveApp(id);
		//theMode.addAttribute("allApplication", appicationService.findAll());
		return "redirect:/systems/viewApplication";
	}
	
	@GetMapping("/application/reject/{id}")
	public String rejectApp(@PathVariable int id)
	{
		appicationService.rejectApp(id);
		//theMode.addAttribute("allApplication", appicationService.findAll());
		return "redirect:/systems/viewApplication";
	}
	
	@GetMapping("/displayBarGraph")
	public String barGraph(Model model) {
		Map<Integer, Integer> surveyMap = new LinkedHashMap<>();
		
		
		String query3="SELECT"
				+ "  year,"
				+ "  COUNT(*) AS `students`"
				+ "FROM"
				+ "  user where department='MCA'"
				+ "GROUP BY"
				+ "  year";
		
		//EntityManager em  ;
		List<BarChartOfMCA> barChartData = jdbcTemplate.query(query3, new RowMapper<BarChartOfMCA>() {

			@Override
			public BarChartOfMCA mapRow(ResultSet rs, int rowNum) throws SQLException {
				BarChartOfMCA bar = new BarChartOfMCA();

				bar.setYear(rs.getInt("year"));
				bar.setStudents(rs.getInt("students"));
				

				return bar;
			}

		});
		System.out.println(barChartData);
		for(int i = 0; i < barChartData.size(); i++) {
            System.out.println(barChartData.get(i).getYear());
            System.out.println(barChartData.get(i).getStudents());
            surveyMap.put(barChartData.get(i).getYear(), barChartData.get(i).getStudents());
    		
            
        }

		model.addAttribute("surveyMap", surveyMap);
		
		
		return "barGraph";
	}

	@GetMapping("/displayPieChart")
	public String pieChart(Model model) {
		model.addAttribute("pass", 20);
		model.addAttribute("fail", 50);
		return "pieChart";
	}
}
