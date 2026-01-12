package in.subh.service;

import java.util.List;

import in.subh.DTO.CounsellorDTO;
import in.subh.DTO.DeshbordResponseDTO;
import in.subh.DTO.EnqFilterDTO;
import in.subh.DTO.EnquiryDTO;

public interface EnquriyService {

	 
	
	public DeshbordResponseDTO getDashboardInfo (Integer counsellorId);
	
	public List<EnquiryDTO> getEnquiries(Integer counsellorId);
	
	public List<EnquiryDTO> getEnquiries(EnqFilterDTO filterDTO ,Integer counsellorId);
	
	public EnquiryDTO getEnquiryById(Integer enqId);

	boolean addEnquiry(EnquiryDTO enqDTO, Integer counsellorId);
}
