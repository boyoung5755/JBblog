package com.innovationT.blog.crawling.movie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MovieController {
	
	@Autowired
	private MovieService movieService;

	@GetMapping("/movie")
	@ResponseBody
	public Map<String, List<Movie>> movie() throws Exception{
		Map<String, List<Movie>> map = new HashMap<>();
	    List<Movie> movieList = movieService.getMovieDatas();
	    map.put("movieList", movieList);
	    return map;
	}
	
}
