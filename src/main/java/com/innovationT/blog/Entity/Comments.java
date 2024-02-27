package com.innovationT.blog.Entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Comments {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer commNo;
	
	private String commId;
	
	@Column(columnDefinition = "TEXT")
	private String commContent;
	
	@CreatedDate
	private LocalDateTime commDate;
	
	
	@ColumnDefault("N")
	private String commDel;
	private Integer commUpnum;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bo_no") 
	private Board board;

}
