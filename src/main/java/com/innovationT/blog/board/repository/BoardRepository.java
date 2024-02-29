package com.innovationT.blog.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.innovationT.blog.Entity.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {
	
	//페이징
	Page<Board> findAll(Pageable pageable);
	
	
	
	
	



}
