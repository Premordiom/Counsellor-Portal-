package in.subh.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.subh.entity.CounsellorsEntity;
import in.subh.entity.EnquiryEntity;

public interface EnquiryRepo 
             extends JpaRepository<EnquiryEntity, Integer>  {
	
	public List<EnquiryEntity> findByCounsellorCounsellorId(Integer counsellorId);
	
	
	
	
}
