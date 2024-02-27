package com.innovationT.blog.payment.contoller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.innovationT.blog.payment.service.PaymentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
	
	
	private final PaymentService paymentService;
	
	
	@ResponseBody
	@PostMapping("/savePayment")
	public Map<String, String> savePayment(@RequestBody Map<String, Object> payload){
	    Map<String, String> map = new HashMap<>();
	    paymentService.savePayment(payload);
	    return map;
	}

}
