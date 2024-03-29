/**
 * import 결제
 */

function fn_payment(amount , subOption) {

  var IMP = window.IMP; // 생략가능
  IMP.init('imp83445163'); 
  // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
  // i'mport 관리자 페이지 -> 내정보 -> 가맹점식별코드
  IMP.request_pay({
      pg: 'kakaopay', // version 1.1.0부터 지원.
      /* 
          'kakaopay':카카오페이, 
          html5_inicis':이니시스(웹표준결제)
              'nice':나이스페이
              'jtnet':제이티넷
              'uplus':LG유플러스
              'danal':다날
              'payco':페이코
              'syrup':시럽페이
              'paypal':페이팔
              html5_inicis.INIpayTest
          */

      pay_method: 'card',
      /* 
          'samsung':삼성페이, 
          'card':신용카드, 
          'trans':실시간계좌이체,
          'vbank':가상계좌,
          'phone':휴대폰소액결제 
      */

      merchant_uid: 'merchant_' + new Date().getTime(),
      /* 
          merchant_uid에 경우 
          https://docs.iamport.kr/implementation/payment
          위에 url에 따라가시면 넣을 수 있는 방법이 있습니다.
          참고하세요. 
       */

      name: subOption, //결제창에서 보여질 이름
      amount: amount,  //가격 
      buyer_email: 'iamport@siot.do',
      buyer_name: '김보영',
      buyer_tel: '010-1234-5678',
      buyer_addr: '서울특별시 강남구 삼성동',
      buyer_postcode: '123-456',
      m_redirect_url: 'https://www.yourdomain.com/payments/complete'
      /*  
          모바일 결제시,
          결제가 끝나고 랜딩되는 URL을 지정 
          (카카오페이, 페이코, 다날의 경우는 필요없음. PC와 마찬가지로 callback함수로 결과가 떨어짐) 
          */
    }, function (rsp) {
        console.log(rsp);
        if (rsp.success) {
            var msg = '결제가 완료되었습니다.';
            msg += '고유ID : ' + rsp.imp_uid;
            msg += '상점 거래ID : ' + rsp.merchant_uid;
            msg += '결제 금액 : ' + rsp.paid_amount;
            msg += '카드 승인번호 : ' + rsp.apply_num;
            
        //ajax->db에 저장	
        
          $.ajax({
        
            type: "POST",
            url: "/payment/savePayment",
            contentType: "application/json",
            data: JSON.stringify({
              impUid : rsp.imp_uid
              , paidAmount : rsp.amount
              , applyNum : rsp.apply_num
              , merchantUid : rsp.merchant_uid
              , pgProvider :  rsp.pg_provider
              , pgType :  rsp.pg_type
              , productName : rsp.name
          }),
             success: function (data) {
            },
            error: function () {
            }
      })
           
        } else {
            var msg = '결제에 실패하였습니다.';
            msg += '에러내용 : ' + rsp.error_msg;
        }
        alert(msg);
    });
}
