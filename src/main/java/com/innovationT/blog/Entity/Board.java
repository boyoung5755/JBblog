package com.innovationT.blog.Entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Board {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer boNo;
	
	private String memId;
	private String boTitle;
	
	@Column(columnDefinition = "TEXT")
	private String boContent;
	
	@CreatedDate
	private LocalDateTime boDate;
	
	private int boHit;
	private String boDel;
	private String fileNo;
	
	private int boRcnt;
	
	@CreatedDate
	private LocalDateTime boSetDate;
	
	@JsonIgnore
	@OneToMany(mappedBy = "board",cascade = CascadeType.REMOVE)
	private List<Comments> commentList;
	
	
	
}
