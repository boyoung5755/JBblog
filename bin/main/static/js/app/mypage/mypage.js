/**
 * 
 */

var aaa;
function testfn(){
    
    $.ajax({
        url: "/mypage/test",
        type: "get",
        async: false,
        success: function(res){
            alert(res);
            aaa = res; 
        },
        error: function(){

        }
    })
    alert(aaa)
}

/*
async  : true -> 비동기 처리임으로 alert(aaa)가 undefined로 먼저 뜨고
서버에서 보낸 '안녕'이라는 alert는 두번째로 뜨게된다.

async  : false -> 동기 처리임으로 순차적으로 실행 
먼저 '안녕'이라는 alert가 뜨고 
그다음으로 alert(aaa)에서도 '안녕'이라고 뜨게된다!

이렇게하면 깜빡거림 없이 데이터를 변경할수 있다.

*/

// 이미지 미리보기
// if (userImage) {
//     imageTest.onchange = () => {
//         if (imageTest.files[0]) {
//             userImage.src = window.URL.createObjectURL(imageTest.files[0]);
//         }
//     };
//  }