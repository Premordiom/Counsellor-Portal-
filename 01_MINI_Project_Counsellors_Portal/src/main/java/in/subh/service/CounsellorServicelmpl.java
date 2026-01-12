package in.subh.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.subh.DTO.CounsellorDTO;
import in.subh.repo.CounsellerRepo;
import in.subh.entity.CounsellorsEntity;
@Service 
public class CounsellorServicelmpl implements CounsellorService {
	
	@Autowired
	private CounsellerRepo counsellorRepo;

	@Override
	public CounsellorDTO login(CounsellorDTO counsellorDTO) {
		
		CounsellorsEntity counsellorEntity= counsellorRepo.findByEmailAndPwd
				(counsellorDTO.getEmail(), counsellorDTO.getPwd());
		
		if(counsellorEntity != null) {
			
			CounsellorDTO dto= new CounsellorDTO();
			BeanUtils.copyProperties(counsellorEntity, dto);
			return dto;
		}
		
		return null;
	}

	@Override
	public boolean uniqueEmailCheck(String email) {
		CounsellorsEntity entity=counsellorRepo.findByEmail(email);
	    return entity== null;
	}

	@Override
	public boolean register(CounsellorDTO counsellorDTO) {
		
		CounsellorsEntity entity = new CounsellorsEntity();
		
		BeanUtils.copyProperties(counsellorDTO, entity);
		CounsellorsEntity saveEntity=counsellorRepo.save(entity);
		
		return null != saveEntity.getCounsellorId();
	}
	
	 

}
