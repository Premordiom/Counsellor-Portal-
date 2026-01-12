package in.subh.entity;

import java.util.List;

import in.subh.entity.EnquiryEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="counsellors_tbl")
@Getter
@Setter

public class CounsellorsEntity {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer counsellorId;
	private String name;
	private String email;
	private String pwd;
	private String phone;
	
	@OneToMany(mappedBy = "counsellor",cascade = CascadeType.ALL )
	private List<EnquiryEntity> enquiries;
}
