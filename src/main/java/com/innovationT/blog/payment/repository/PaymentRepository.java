package com.innovationT.blog.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.innovationT.blog.Entity.Payment;

public interface PaymentRepository  extends JpaRepository<Payment, Integer>{

	
	//결제 디비 저장 성공여부를 알기위한 구매 ID 조회
	@Query("select count(merchant_uid)  from payment p where merchantUid= :merchantUid")
	int searchID(@Param("merchantUid") String merchantUid);
	
}
