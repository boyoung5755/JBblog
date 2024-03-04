package com.innovationT.blog.crawling.movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
	
	
	private static String Movie_URL="https://www.megabox.co.kr/";

	
    @PostConstruct
    public List<Movie> getMovieDatas() throws IOException {
        List<Movie> movieList = new ArrayList<>();
        Document document = Jsoup.connect(Movie_URL).get();

        Elements contents = document.select("div ol.list li");
        
        for (Element content : contents) {
            Movie movie = Movie.builder()
                    .image(content.select("img.poster").attr("src")) // 이미지
                    .subject(content.select("img.poster").attr("alt"))		// 제목
                    .url(content.select("a").attr("abs:href"))		// 링크
                    .like(content.select("button.btn-like").text())		// 좋아요
                    .scoreNumber(content.select("div.preview p.number").text())		//관람평수 
                    .summary(content.select("div.summary").text())		//요약
                    .rank(content.select("p.rank").text())
                    .build();
            movieList.add(movie);
        }    
        return movieList;
    }
	
}
