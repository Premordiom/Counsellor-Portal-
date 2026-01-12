package in.subh.service;

import in.subh.DTO.CounsellorDTO;

public interface CounsellorService {
	
	//for valid login return id, for invalid return 0
		public CounsellorDTO login(CounsellorDTO counsellorDTO);
		
		//for unique email return true, for duplicate return false
		public boolean uniqueEmailCheck(String email);
		
		public  boolean register(CounsellorDTO counsellorDTO); 

}
