package com.innovationT.blog.Entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;

import lombok.Data;

@Data
@Entity
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer payNo;
	
	private String impUid;  //고유ID
	
	private String memId;
	
	private int paidAmount;   // 10원 고정
	
	@CreatedDate
	private LocalDateTime paidDate;
	private String applyNum;
	
	private String merchantUid;
	
	//결제정보
	private String pgProvider;
	private String pgType;

	private String productName;
	

}
