/**
 * <pre>
 * 회원가입 JS
 * </pre>
 * @author 김재성
 * @since 2024. 02. 26.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 *    수정일          수정자            수정내용
 * ----------     --------    ----------------------
 * 2024.02.26.     김재성       		최초작성
 * Copyright (c) 2024 by INNOVATION-T All right reserved
 * </pre>
 */

$(function(){
	
$("#signUpFormTag").on("submit",function(e){
		//alert("확인");
		e.preventDefault();
		
		let formData = $(this).serialize();
		
		console.log("폼 입력값 : ",formData);
		
		$.ajax({
			url : "/login/signup",
			method : "POST",
			data : formData ,
			dataType : 'json',
//			contentType : 'application/json',
			success : function(resp){
				if(resp=="success"){
					alert("회원가입 등록이 완료 되었습니다. !!");
					location.href="/";
				}else if(resp=="fail"){
					alert("아이디가 중복 됩니다. 다시 작성해주세요 !");
				}else{
					alert("회원가입 등록이 실패 되었습니다. !!");
					location.reload();
				}
			},
			error : function(xhr, status,err){
				console.log("상태 : ", status);
				console.log("에러 : ", err);
				alert("잘못된 요청 발생 !");
			}
		});
	});
}) ;