package com.innovationT.blog.board.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.innovationT.blog.Entity.Board;
import com.innovationT.blog.Entity.Comments;

public interface Boardservice {
	
	/**
	 * 게시판 목록을 위한 페이징 
	 * @param page
	 * @param sort
	 * @param sortOption
	 * @param num
	 * @return
	 */
	public Page<Board> getList(int page, String sort , String sortOption,int num);

	/**
	 * 하나의 게시글을 확인하기 위한 상세정보
	 * @param boNo
	 * @return
	 */
	public Board getBoard(Integer boNo);

	/**
	 * 댓글 리스트
	 * @param boNo
	 * @return
	 */
	public List<Comments> getCommList(Integer boNo);

	/**
	 * 댓글등록하기
	 * @param comment
	 */
	public void saveComm(Comments comment , Integer boNo);

	/**
	 * 댓글삭제하기
	 * @param commNo
	 */
	public void deleteComm(Integer commNo);

	/**
	 * 댓글 수정하기
	 * @param commNo
	 * @param commContent
	 */
	public void updateComment(Integer commNo, String commContent);

	/**
	 * 대댓글 등록하기
	 * @param commUpnum
	 * @param commContent
	 */
	public void createReply(Integer commUpnum, String commContent, Integer boNo);

	/**
	 * 게시글 저장하기
	 * @param board
	 */
	public void saveBoard(Board board);

	
}

