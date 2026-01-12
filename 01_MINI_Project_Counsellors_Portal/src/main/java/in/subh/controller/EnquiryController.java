package in.subh.controller;

import in.subh.service.EnquriyService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import in.subh.DTO.*;

@Controller
public class EnquiryController {

    @Autowired
    private EnquriyService enqService;

    @GetMapping("/enquiry-page")
    public String loadEnqPage(Model model) {

        EnquiryDTO enqDTO = new EnquiryDTO();
        model.addAttribute("enquiry", enqDTO);

        return "add-enquiry";
    }

    @PostMapping("/add-enquiry")
    public String addEnquiry(HttpServletRequest req,
                             @ModelAttribute("enquiry") EnquiryDTO enquiry,
                             Model model) {

        HttpSession session = req.getSession(false);

        // 🔴 FIX: prevent NullPointerException if session expired or not created
        if (session == null) {
            return "redirect:/";
        }

        Integer cid = (Integer) session.getAttribute("counsellorId");

        // 🔴 FIX: prevent passing null counsellorId to service
        if (cid == null) {
            return "redirect:/";
        }

        boolean status = enqService.addEnquiry(enquiry, cid);

        if (status) {
            model.addAttribute("smsg", "Enquiry Saved");
        } else {
            model.addAttribute("emsg", "Failed to save enquiry");
        }

        return "add-enquiry";
    }

    @GetMapping("/view-enq")
    public String getEnquiries(HttpServletRequest req, Model model) {

        HttpSession session = req.getSession(false);

        // 🔴 FIX: session safety check (teacher app guarantees this, yours doesn’t)
        if (session == null) {
            return "redirect:/";
        }

        Integer cid = (Integer) session.getAttribute("counsellorId");

        // 🔴 FIX: counsellorId safety check
        if (cid == null) {
            return "redirect:/";
        }

        List<EnquiryDTO> enquiries = enqService.getEnquiries(cid);
        model.addAttribute("enquiries", enquiries);

        EnquiryDTO searchFormDto = new EnquiryDTO();
        model.addAttribute("filterDto", searchFormDto);

        return "viewenquiry";
    }
}
