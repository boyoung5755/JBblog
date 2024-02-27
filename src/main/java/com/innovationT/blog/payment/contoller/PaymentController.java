package com.innovationT.blog.payment.contoller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.innovationT.blog.Entity.Payment;
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
	    try {
	    	paymentService.savePayment(payload);
	    	map.put("success", "Y");
	    }catch (Exception e) {
	    	map.put("success", "N");
		}
	    return map;
	}
	
	
	
	//결제목록 
	@GetMapping("/listPage")
	public String paymentList(Model model) {
		
		List<Payment> payment = paymentService.getAllPayments();
		model.addAttribute("payment", payment);
		return "page/payment/paymentList";
	}
	
	
}
