package com.innovationT.blog;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.innovationT.blog.Entity.Board;
import com.innovationT.blog.Entity.Comments;
import com.innovationT.blog.Entity.Payment;
import com.innovationT.blog.board.repository.BoardRepository;
import com.innovationT.blog.board.repository.CommentsRepository;
import com.innovationT.blog.payment.repository.PaymentRepository;

@SpringBootTest
class BlogApplicationTests {
	
	@Autowired
	private BoardRepository BRepo;

	@Autowired
	private CommentsRepository CRepo;
	
	@Autowired
	private PaymentRepository PRepo;
	
	@Test
	void contextLoads() {
	}
	
	//@Test
	void testList() {
		
		//test 목록 만들기
		
		Board bVO1 = new Board();
		for (int i = 1; i < 201; i++) {
			
			bVO1.setBoNo(i);
			bVO1.setMemId("bo");
			bVO1.setBoDel("N");
			bVO1.setBoTitle("TEST"+i);
			bVO1.setBoContent("TEST 입니다!");
			bVO1.setBoDate(LocalDateTime.now());
			bVO1.setBoHit((int)(Math.random()*50+1));
			bVO1.setBoRcnt((int)(Math.random()*50+1));
			this.BRepo.save(bVO1);
		}
	}
	
	//@Test
	void testComm() {
		
		
		Optional<Board> ob=this.BRepo.findById(1);
		
		assertTrue(ob.isPresent());
		
		Board b = ob.get();
		
		for (int i = 1; i < 20; i++) {
			Comments c = new Comments();
			c.setCommContent("댓글테스트중입니다.");
			c.setBoard(b);
			c.setCommDate(LocalDateTime.now());
			c.setCommDel("N");
			c.setCommId("bo");
			this.CRepo.save(c);
		}

	}
	
	@Test
	void payment() {
		
		Payment payment = new Payment();
		
		payment.setMemId("boyoung");
		payment.setApplyNum("1");
		payment.setImpUid("1");
		payment.setMerchantUid("1");
		payment.setPgProvider("1");
		payment.setPgType("1");
		payment.setProductName("1");
		payment.setPaidAmount(10);
		payment.setPaidDate(LocalDateTime.now());
		
		this.PRepo.save(payment);
		
		
	}
	
	
	//@Test
	void list() {
		
		int page = 0;
		Pageable pageable = PageRequest.of(page, 10);
		BRepo.findAll(pageable);
		
	}
}
