/**
 * 회원가입
 */

const token = $("meta[name='_csrf']").attr("content");
const header = $("meta[name='_csrf_header']").attr("content");


function fn_signup(){
	
	var memId = document.querySelector('#memId').value;
	var memPw = document.querySelector('#memPw').value;
	var email = document.querySelector('#email').value;
	
	$.ajax({
        type: "POST",
        url: "/member/signup",
        contentType: "application/json",
        data: JSON.stringify({
			memId: memId
			, memPw : memPw
			, email : email
		}),
		beforeSend: function(xhr) {
      	xhr.setRequestHeader(header, token);
    	},
        success: function (data) {
            if (data.success == "Y") {
                alert('회원가입이 완료되었습니다.');
                location.href ='/board/';
            } else {
                alert('회원가입이 실패했습니다.');
            }
        },
        error: function () {
            alert('회원가입처리중 오류가 발생했습니다.');
        }
    });
	
}