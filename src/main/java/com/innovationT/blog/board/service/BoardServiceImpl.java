package com.innovationT.blog.board.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.innovationT.blog.Entity.Board;
import com.innovationT.blog.Entity.Comments;
import com.innovationT.blog.board.repository.BoardRepository;
import com.innovationT.blog.board.repository.CommentsRepository;
import com.innovationT.blog.utils.exception.DataNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements Boardservice {
	
	private final BoardRepository boardRepository;
	private final CommentsRepository commentsRepository;

	@Override
	public Page<Board> getList(int page , String sort , String sortOption, int num) {
		
		//정렬
		List<Sort.Order> sorts = new ArrayList<>();
		
		
		if(sort.equals("boNo")) {		//기본 작성일순
			sorts.add(Sort.Order.desc(sort));
			
		}else if(sort.equals("boHit")){
			if(sortOption.equals("desc")) {
				sorts.add(Sort.Order.desc(sort));
			}else{
				sorts.add(Sort.Order.asc(sort));
			}
			
		}else if(sort.equals("boRcnt")){
			if(sortOption.equals("desc")) {
				sorts.add(Sort.Order.desc(sort));
			}else{
				sorts.add(Sort.Order.asc(sort));
			}
		}
		
		//페이징
		Pageable pageable = PageRequest.of(page, num, Sort.by(sorts));
		
		return this.boardRepository.findAll(pageable);
		
	}

	@Override
	public Board getBoard(Integer boNo) {
		
		Optional<Board> board = this.boardRepository.findById(boNo);
		
		if (board.isPresent()) {
			Board  bo =   board.get();
			bo.setBoRcnt(bo.getBoRcnt()+1);
			this.boardRepository.save(bo);
			return bo;
		}else {
			throw new DataNotFoundException("Board not found");
		}	
	}

	@Override
	public List<Comments> getCommList(Integer boNo) {
	    List<Comments> commList = new ArrayList<>();
	    
	    Sort sort = Sort.by(Sort.Direction.DESC, "commDate");
	    commList = commentsRepository.findByBoard_BoNo(boNo , sort);
	    return commList;
	}

	@Override
	public void saveComm(Comments comment, Integer boNo) {
		Board board = boardRepository.findById(boNo)
	            .orElseThrow(() -> new IllegalArgumentException("Invalid boNo:" + boNo));
		comment.setBoard(board);
		comment.setCommDel("N");
		
		//로그인기능전 임시로 할당
		comment.setCommId("boyoung");
		
	    commentsRepository.save(comment);
	}

	@Override
	public void deleteComm(Integer commNo) {
		Comments comment = commentsRepository.findById(commNo)
				.orElseThrow(() -> new IllegalArgumentException("Invalid commNo:" + commNo));
        comment.setCommDel("Y");
        commentsRepository.save(comment);
		
	}

	@Override
	public void updateComment(Integer commNo, String commContent) {
	    try {
	        Comments comment = commentsRepository.findById(commNo)
	                .orElseThrow(() -> new IllegalArgumentException("Invalid commNo:" + commNo));
	        comment.setCommContent(commContent);
	        comment.setCommDate(LocalDateTime.now());
	        Comments updatedComment = commentsRepository.save(comment);
	        
	        if (updatedComment != null && updatedComment.getCommId() != null) {
	        	log.info("댓글이 성공적으로 수정되었습니다.");
	        } else {
	        	log.info("댓글 수정에 실패하였습니다.");
	        }
	    } catch (IllegalArgumentException e) {
	    	log.info("잘못된 댓글 번호입니다: " + commNo);
	        e.printStackTrace();
	    } catch (Exception e) {
	    	log.info("댓글 수정 중 오류가 발생했습니다.");
	        e.printStackTrace();
	    }
	}

	@Override
	public void createReply(Integer commUpnum, String commContent,Integer boNo) {
		try {
	        Comments reply = new Comments();
	        
	        Board board = boardRepository.findById(boNo)
	                .orElseThrow(() -> new IllegalArgumentException("Invalid boNo:" + boNo));
	        reply.setBoard(board);
	        
	        //로그인기능전 임시로 할당
	        reply.setCommId("boyoung");
	        
	        reply.setCommDel("N");
	        reply.setCommUpnum(commUpnum);
	        reply.setCommContent(commContent);
	        Comments savedReply = commentsRepository.save(reply);
	        
	        if (savedReply != null && savedReply.getCommId() != null) {
	        	log.info("댓글이 성공적으로 저장되었습니다.");
	        } else {
	        	log.info("댓글 저장에 실패하였습니다.");
	        }
	    } catch (Exception e) {
	        log.info("댓글 저장 중 오류가 발생했습니다.");
	        e.printStackTrace();
	    }
	}

	@Override
	public void saveBoard(Board board) {
		
		//로그인기능전 임시로 할당
		board.setMemId("boyoung");
		board.setBoDel("N");
		board.setBoDate(LocalDateTime.now());
		boardRepository.save(board);
	}

}
