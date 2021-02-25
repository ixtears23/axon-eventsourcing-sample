package com.ibdata.eventsourcing.acc.preregi.controlller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PreRegistrationController {

    /**
     * 사전등록내용검사(공통)
     * INPUT 비용코드, 결의금액
     * RETURN
     *  - Type: Object
     *  - Data: Pass여부, Message(Success/Fail)
     *
     *  supplementation > 이거 하나로 퉁 칠까?
     */


    /**
     * 사전등록내용검사 - 회의비, 세미나, 식대, 재료구입비, 전문가활용비
     * INPUT 비용코드
     * RETURN
     *  - Type: Object
     *  - Data: Pass여부, Message(Success/Fail)
     *
     *  supplementation > 지금 하나로 기술했지만 비용별로 다 쪼개는 건 어떨려나
     */

    /**
     * 사전등록내용검사(연구시설장비비)
     * INPUT 비용코드, 결의금액
     * RETURN
     *  - Type: Object
     *  - Data: Pass여부, Message(Success/Fail)
     */
}
