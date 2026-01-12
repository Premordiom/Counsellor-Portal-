package in.subh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.subh.DTO.CounsellorDTO;
import in.subh.service.CounsellorService;
import in.subh.service.EnquriyService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import in.subh.DTO.DeshbordResponseDTO;

@Controller
public class CounsellorController {
   
	@Autowired
	private CounsellorService counsellorService; 
	
	@Autowired
	private EnquriyService enqService;
	
	
	@GetMapping("/")
	public String index(Model model) {
		
		CounsellorDTO cdto = new CounsellorDTO();
		model.addAttribute("counsellor", cdto);
		
		return "index";
		
	}
	@GetMapping("/logout")
	public String logout(HttpServletRequest req, Model model) {
		
		HttpSession session = req.getSession(false);
		session.invalidate();
		
		CounsellorDTO cdto = new CounsellorDTO();
		model.addAttribute("counsellor", cdto);
		
		return "index";
		}
	
    @PostMapping("/login")
	public String handeleLogin(HttpServletRequest req ,@ModelAttribute("counsellor")CounsellorDTO counsellor, Model model) {
        
    	CounsellorDTO counsellorDTO = counsellorService.login(counsellor);
    	
    	if(counsellorDTO == null) {
    		 
			model.addAttribute("error", "Invalid email or password");
			return "index";
    	}
    	else {
    		Integer counsellorId = counsellorDTO.getCounsellorId(); 
    		
    		HttpSession session = req.getSession(true);
    		req.getSession(true).setAttribute("counsellorId", counsellorId);
    		
    		
    		DeshbordResponseDTO dashbordDto=enqService.getDashboardInfo(counsellorId);
    	
    		model.addAttribute("dashboardDto", dashbordDto);

    	
    	return "dashboard";
    	}
    	
        
	}
    
    
    
    
    
    
    
    
    @GetMapping("/register")
	public String registerPage(Model model) {
		
		CounsellorDTO cdto = new CounsellorDTO();
		model.addAttribute("counsellor", cdto);
		
		return "register";
    }
    
    @PostMapping("/register")
    public String handelRegister(@ModelAttribute("counsellor") CounsellorDTO counsellor, Model model) {
    	
    	boolean unique=counsellorService.uniqueEmailCheck(counsellor.getEmail());
    	
    	if(unique) {
    		
    		boolean register = counsellorService.register(counsellor);
    		
    		if(register) {
    			model.addAttribute("smsg", "Registration Successful!");
    		}
    		
    		else {
    			model.addAttribute("error", "Failed to register! Please try again.");
    			
    			
    		}
			
		} else {
			model.addAttribute("error", "Email already exists! Please use a different email.");
			
    		
    	}
    	return "register";
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}


