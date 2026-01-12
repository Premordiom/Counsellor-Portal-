package in.subh.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import in.subh.entity.CounsellorsEntity;

@Entity
@Table(name="enquires_tbl")
@Getter
@Setter

public class EnquiryEntity {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer enq_id;
	private String stuName;
	private String stuPhn;
	private String classMode;
	private String course;
	private String enqStatus;
	
	@ManyToOne
	@JoinColumn(name= "counsellor_id")
    private CounsellorsEntity counsellor;
}
