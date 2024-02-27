package com.innovationT.blog.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innovationT.blog.Entity.Payment;

public interface PaymentRepository  extends JpaRepository<Payment, Integer>{

}
