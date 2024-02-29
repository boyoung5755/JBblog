package com.innovationT.blog.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.innovationT.blog.Entity.Board;
import com.innovationT.blog.Entity.Comments;
import com.innovationT.blog.board.service.Boardservice;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
	
	private final Boardservice boardservice;
	
	
	
	//대댓글 등록하기
    @PostMapping("/reply")
    @ResponseBody
    public Map<String, String> createReply(
    	@RequestBody Map<String, String> payload
    	) {
        Map<String, String> map = new HashMap<>();
        try {
            Integer commUpnum = Integer.parseInt(payload.get("commUpnum"));
            String commContent = payload.get("commContent");
            Integer boNo = Integer.parseInt(payload.get("boNo"));
            boardservice.createReply(commUpnum, commContent,boNo);
            map.put("success", "Y");
        } catch (Exception e) {
        	map.put("success", "N");
        }
        return map;
    }
	
	
	
	
	
	
	
	//댓글 수정하기
	@PostMapping("/updateComm/{commNo}")
	@ResponseBody
    public Map<String, String> updateComment(
    	@PathVariable Integer commNo
    	, @RequestBody Map<String, String> payload
    ) {
        Map<String, String> map = new HashMap<>();
        try {
            String commContent = payload.get("commContent");
            boardservice.updateComment(commNo, commContent);
            map.put("success", "Y");
        } catch (Exception e) {
        	map.put("success", "N");
        }
        return map;
    }
	
	
	
	//댓글 등록하기
	@PostMapping("/deleteComm")
	@ResponseBody
    public Map<String, String> deleteComm(
    	@RequestParam Integer commNo
    	) {
        Map<String, String> map = new HashMap<>();
        try {
        	boardservice.deleteComm(commNo);
            map.put("success", "Y");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", "N");
        }
        return map;
    }
	
	
	//댓글 등록하기
	@PostMapping("/insertComm")
	@ResponseBody
    public Map<String, String> insertComm(
    	@ModelAttribute("Comments") Comments comment
    	, @RequestParam Integer boNo
    	) {
        Map<String, String> map = new HashMap<>();
        try {
        	boardservice.saveComm(comment , boNo);
            map.put("success", "Y");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", "N");
        }
        return map;
    }
	
	
	
	
	//댓글리스트
	@GetMapping("/commList/{boNo}")
	@ResponseBody
	public Map<String, List<Comments>> retrieveCommList(
		@PathVariable Integer boNo
		){
		Map<String,List<Comments>> map = new HashMap<String, List<Comments>>();
		List<Comments> commList = boardservice.getCommList(boNo);
		map.put("commList" , commList);
		return map;
	}
	
	
	//게시글 상세보기
	@GetMapping("/detail/{boNo}")
	public String selectOneBoard(
			@PathVariable Integer boNo
			, Model model
		){

		Board board = this.boardservice.getBoard(boNo);
		model.addAttribute("detail", board);
		return "page/board/boardDetail";
	}
			
			
			
	@GetMapping
	public String boardList() {
		return "page/board/boardList";
	}
	
	
	//게시판 리스트
	@GetMapping("/list")
	@ResponseBody
	public Map<String,Page<Board>> boardList(
		@RequestParam(value = "page", defaultValue = "0") int page
		, @RequestParam(value = "sort", defaultValue = "boNo") String sort 
		, @RequestParam(value = "sortOption", defaultValue = "desc") String sortOption 
		, @RequestParam(value = "num", defaultValue = "10") int num
		) {
		
		Map<String,Page<Board>> map = new HashMap<String,Page<Board>>();
		Page<Board> paging = this.boardservice.getList(page,sort,sortOption,num);
		map.put("paging",paging);
		return map;
	}
	
	

}
