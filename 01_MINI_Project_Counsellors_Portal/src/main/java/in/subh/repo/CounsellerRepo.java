 package in.subh.repo;

import in.subh.entity.CounsellorsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CounsellerRepo 
                  extends JpaRepository<CounsellorsEntity, Integer> {
	
	public CounsellorsEntity findByEmailAndPwd(String email, String password);
	public CounsellorsEntity findByEmail(String email );
	public CounsellorsEntity findByNameAndEmailAndPwdAndPhone
	         (String name,String email, String pwd, String phone);
}
