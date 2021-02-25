package com.ibdata.eventsourcing.acc.card.controlller;

public class CardController {

    /**
     * 카드승인내역목록조회
     * INPUT 카드번호, 카드회사
     * RETURN
     *   - Type: List
     *   - Data: 카드번호, 카드회사, 승인번호, 승인금액
     *
     * supplementation > 카드 승인일시 및 시간이 있으면 좋을 듯
     */


    /**
     * 신용카드 검증
     * 검증 목록
     *  - 카드 승인번호 필수
     *  - 서명된 신용카드만 지출결의 가능
     *  INPUT 카드번호, 카드회사, 카드 승인번호
     *  RETURN
     *   - Type: Object
     *   - Data: Pass여부, Message(Success/Fail)
     *
     * supplementation > [기 지출된 카드 승인번호 입력 불가] 는 지출 Domain에서 처리
     */
}
