/**
 * 웹 크롤링 
 */


$(function(){
    fn_newsCrawling();

    fn_movieCrawling();
  });


function fn_movieCrawling(){

    $.ajax({
        type : 'GET',
        url : '/movie',
        dataType : 'json',
        success : function(data){

            let movie = data.movieList;
            let divTag ="";

            if(movie.length != 0){
                divTag +=`
                    <div class="container-fluid">
                        <div id="content">
                            <div class="main-page">
                                <div id="main_section01" class="section main-movie">
                                    <div class="bg">
                                    <div class="bg-pattern"></div>
                                    <img src="https://img.megabox.co.kr/SharedImg/2024/02/22/CcOrVRpmn8gLchlz6KdCBPQ5Z6hprNBV_380.jpg" alt="1.jpg" onerror="noImg(this, 'main');">
                                    </div>
                                    <div class="cont-area">
                                        <div class="tab-sorting">
                                            <button type="button" class="on" sort="boxoRankList" name="btnSort">박스오피스</button>
                                            <!-- 2020.03.16. 김민영K 삭제요청.
                                            <button type="button" sort="accmAdncList" name="btnSort">누적관객순</button>
                                            <button type="button" sort="megaScoreList" name="btnSort">메가스코어순</button>
                                            -->
                                        </div>
                                        <div class="main-movie-list">
                                            <ol class="list">
                `;

                $.each(movie , function(index,v){

                    divTag +=`
                        <li name="li_boxoRankList" class="first">
                            <a href="${v.url}" class="movie-list-info" title="영화상세 보기">
                                <p class="rank">1<span class="ir">위</span></p>
                                <!-- to 개발 : alt 값에 영화 제목 출력 -->
                                <img src="${v.image}" alt="${v.subject}" class="poster" onerror="noImg(this, 'main');">
                                    <div class="wrap" style="display: none; opacity: 1;">
                                    <div class="summary"> ${v.summary}</div> 
                                    <div class="score">
                                        <div class="preview">
                                            <p class="tit">관람평</p>
                                            <p class="number">${v.scoreNumber}<span class="ir">점</span></p>
                                        </div>
                                    </div>
                                </div>
                            </a>
                            <div class="btn-util">
                                <button type="button" class="button btn-like" rpst-movie-no="24004100">
                                    <i title="보고싶어 설정 안함" class="iconset ico-heart-toggle-gray"></i>
                                        ${v.like}
                                </button>
                                <div class="case">
                                    <!-- 개봉 예매가능 기본-->
                                        <a href="javascript:moveBokdPage('24004100');" class="button gblue" title="영화 예매하기">예매</a>
                                </div>
                            </div>
                        </li>
                    `;
                })

                divTag+=`
                    </ol></div>
                        <div class="search-link">
                            <div class="cell">
                                <div class="search">
                                    <input type="text" placeholder="영화명을 입력해 주세요" title="영화 검색" class="input-text" id="movieName">
                                    <button type="button" class="btn" id="btnSearch"><i class="iconset ico-search-w"></i> 검색</button>
                                </div>
                            </div>

                            <div class="cell"><a href="/booking/timetable" title="상영시간표 보기"><i class="iconset ico-schedule-main"></i> 상영시간표</a></div>
                            <div class="cell"><a href="/movie" title="박스오피스 보기"><i class="iconset ico-boxoffice-main"></i> 박스오피스</a></div>
                            <div class="cell"><a href="/booking" title="빠른예매 보기"><i class="iconset ico-quick-reserve-main"></i> 빠른예매</a></div>
                        </div>
                        <div class="moving-mouse">
                            <!--  <i class="iconset ico-mouse"></i> -->
                            <img class="iconset" alt="" src="../../../static/pc/images/common/ico/ico-mouse.png" style="top: 10px;">
                        </div>
                    </div></div></div></div></div>
                `;
            }
            $('#movie').html(divTag);   
        },
        error: function () {
        }
    })
}



function fn_newsCrawling(){

    $.ajax({
        type: "GET",
        url: "/news",
        dataType: 'json',
        success: function (data) {

            let news = data.newsList;
            let divTag ="";

            if(news.length != 0){

                divTag +=`
                    <div>
                        <table class= "table">
                            <colgroup>
                                <col width="30%" />
                                <col width="70%" />
                            </colgroup>
                            <tr>
                                <th>이미지</th>
                                <th>제목</th>
                            </tr>
                `;

                $.each(news , function(index,v){
                    if(v.subject != null){
                        divTag+=`
                                <tr>
                                    <td><a href="${v.url}"><img src="${v.image}"></a></td>
                                    <td><a href="${v.url}"><span>${v.subject}</span></a></td>
                                    </a>
                                </tr>
                        `;
                    }
                })
                divTag+=` </table> </div> `;
                $('#news').html(divTag);    
            }else{
                divTag+=`
                    <div>
                        <table class= "table">
                        <tr>
                            <th>이미지</th>
                            <th>제목</th>
                        </tr>
                        <tr colspan="2">
                            <td>내역없음</td>
                        </tr>
                        </table>
                    </div>
                `;
                $('#news').html(divTag);    
            }
        },
        error: function () {
        }
    });
}  