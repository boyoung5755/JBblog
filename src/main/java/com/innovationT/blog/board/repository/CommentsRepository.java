package com.innovationT.blog.board.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.innovationT.blog.Entity.Comments;

public interface CommentsRepository extends JpaRepository<Comments, Integer> {
	
	List<Comments> findByBoard_BoNo(Integer boNo, Sort sort);
	
	
	
	



}
