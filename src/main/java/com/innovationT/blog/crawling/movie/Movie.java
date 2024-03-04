package com.innovationT.blog.crawling.movie;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Movie {
	
    private String image; //이미지
    private String subject;  // 제목
    private String url;  
    
    private String scoreNumber; // 관람평
    private String like;  //관심도
    
    private String summary; // 요약
    private String rank;  // 순위
    
    

}
