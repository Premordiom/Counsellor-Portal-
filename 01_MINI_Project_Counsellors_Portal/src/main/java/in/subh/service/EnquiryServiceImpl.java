package in.subh.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.subh.DTO.DeshbordResponseDTO;
import in.subh.DTO.EnqFilterDTO;
import in.subh.DTO.EnquiryDTO;
import in.subh.entity.CounsellorsEntity;
import in.subh.entity.EnquiryEntity;
import in.subh.repo.CounsellerRepo;
import in.subh.repo.EnquiryRepo;
import jakarta.persistence.Entity;
import org.springframework.data.domain.Example;

@Service
public class EnquiryServiceImpl implements EnquriyService {
	
	@Autowired
	private EnquiryRepo enqRepo;
	
	@Autowired
	private CounsellerRepo counsellorRepo;

	@Override
	public DeshbordResponseDTO getDashboardInfo(Integer counsellorId) {
		List<EnquiryEntity> enqList=enqRepo.findByCounsellorCounsellorId(counsellorId);
		
		DeshbordResponseDTO dto=new DeshbordResponseDTO();
		 dto.setTotalEnqCnt( enqList.size());
/////////////////////////////////////////////////////////////////////		 
		 int openCnt=enqList.stream()                             //
				 .filter(enq->enq.getEnqStatus().equals("OPEN")) //-Java 8 stream api fature 
				 .collect(Collectors.toList())                  //
				 .size();                                      //
////////////////////////////////////////////////////////////////		
		
		
		
		
//////////////////////////////////////////////////////////////////////////
          int enrolledCnt=enqList.stream()                             //
                 .filter(enq->enq.getEnqStatus().equals("ENROLLED"))  //-Java 8 stream api fature 
                 .collect(Collectors.toList())                       //
                 .size();                                           //
/////////////////////////////////////////////////////////////////////
          
          
          
////////////////////////////////////////////////////////////////////////
          int lostCnt=enqList.stream()                               //
                 .filter(enq->enq.getEnqStatus().equals("LOST"))    //-Java 8 stream api fature 
                 .collect(Collectors.toList())                     //
                 .size();                                         //
///////////////////////////////////////////////////////////////////

          dto.setOpenEnqCnt(openCnt);
          dto.setEnrolledEnqCnt(enrolledCnt);
          dto.setLostEnqCnt(lostCnt);
          
          
		
		
		
		 return dto;
	}

	@Override 
	public boolean addEnquiry(EnquiryDTO enqDTO,Integer counsellorId) {
		
		
		
		EnquiryEntity entity=new EnquiryEntity();
		BeanUtils.copyProperties(enqDTO, entity);
		
		Optional<CounsellorsEntity> byId=counsellorRepo.findById(counsellorId);
		if(byId.isPresent()) {
			CounsellorsEntity counsellor= byId.get();
			entity.setCounsellor(counsellor);
		}
		
		EnquiryEntity save= enqRepo.save(entity);		
		return save.getEnq_id() != null;
	}

	@Override
	public List<EnquiryDTO> getEnquiries(Integer counsellorId) {
		
		List<EnquiryDTO> enqDtoList = new ArrayList(); 
		
		List<EnquiryEntity> enqList =
				     enqRepo.findByCounsellorCounsellorId(counsellorId);
		
		for (EnquiryEntity enqEntity : enqList) {
			EnquiryDTO dto = new EnquiryDTO();
			BeanUtils.copyProperties(enqEntity, dto);
			
			enqDtoList.add(dto);

		}
		return enqDtoList;
	}

	@Override
	public List<EnquiryDTO> getEnquiries(EnqFilterDTO filterDTO, Integer counsellorId) {
		
		EnquiryEntity enqEntity = new EnquiryEntity();
		if (filterDTO.getClassmode() != null && !filterDTO.getClassmode().equals("")) {
			enqEntity.setClassMode(filterDTO.getClassmode());
		}
		
		if (filterDTO.getCourse() != null && !filterDTO.getCourse().equals("")) {
			enqEntity.setCourse(filterDTO.getCourse());
		}
		
		if (filterDTO.getEnqStatus() != null && !filterDTO.getEnqStatus().equals("")) {
			enqEntity.setEnqStatus(filterDTO.getEnqStatus());
		}
		
		CounsellorsEntity counsellor = new CounsellorsEntity();
		counsellor.setCounsellorId(counsellorId);
		enqEntity.setCounsellor(counsellor);
		
		Example<EnquiryEntity> of = Example.of(enqEntity);
		
		List<EnquiryEntity> all = enqRepo.findAll(of);
		
		List<EnquiryDTO> enqDtoList = new ArrayList<>();
		
		for (EnquiryEntity enquiryEntity : all) {
			EnquiryDTO dto = new EnquiryDTO();
			BeanUtils.copyProperties(enquiryEntity, dto);
			enqDtoList.add(dto);
		}
		
		return enqDtoList;
	
	}

	@Override
	public EnquiryDTO getEnquiryById(Integer enqId) {
	   Optional<EnquiryEntity> byId =enqRepo.findById(enqId);
		if (byId.isPresent()) {
			EnquiryEntity enqEntity = byId.get();
			EnquiryDTO dto = new EnquiryDTO();
			BeanUtils.copyProperties(enqEntity, dto);
			return dto;
		}
		return null ;
	}

}
