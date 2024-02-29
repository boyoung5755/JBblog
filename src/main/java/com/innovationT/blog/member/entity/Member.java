package com.innovationT.blog.member.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Table(name="MEMBER")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="MEM_ID")
	private Long id;
	
	@Column(name="MEM_LOGIN_ID")
	@NotBlank
	private String loginId;
	
	@Column(name="MEM_PW")
	@NotBlank
	private String memPw;
	
	@Column(name="MEM_NM")
	@NotBlank
	private String memNm;
	
	@Column(name="MEM_BIRTH_DATE")
	@NotBlank
	private String memBirthDate;
	
	@Column(name="MEM_NICK")
	private String memNick;
	
	@Column(name="MEM_TELNO")
	@NotBlank
	private String memTelno;
	
	@Column(name="MEM_EMAIL")
	private String memEmail;
	
	@Column(name="MEM_ADRES1")
	@NotBlank
	private String memAdres1;
	
	@Column(name="MEM_ADRES2")
	private String memAdres2;
	
	@Column(name="MEM_ZIP")
	@NotBlank
	private String memZip;
	
	@Column(name="MEM_ROLE")
	private String memRole;
	
	@Column(name="MEM_USED")
	private String memUsed;
	
	@Column(name="MEM_DEL")
	private String memDel;
	
	@Column(name="MEM_IN_DATE")
	private String memInDate;
	
	@Column(name="MEM_UP_DATE")
	private String memUpDate;
	
	@Column(name="FILE_NO")
	private String fileNo;
}