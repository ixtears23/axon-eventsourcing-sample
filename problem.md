# 문제

## Message
종류 : Command, Event, Query
메세지는 변결 불가능해야 한다.
Java에서는 final 속성과 Getter 노출 또는 java14 레코드 사용


## Command 안에 객체가 있는 경우는 어떤 경우지...




## Command Case
1. UI 버튼 클릭을 명령으로 볼 것인가
2. UI 버튼 클릭으로 들어와서 여러 이벤트에 전달하기 전 세부적으로 명령을 나눌 것 인가

#### 우선 해보기 1번 Case

> 명령은 거절당할 수 있다. 요청일 뿐이다.  
> 이벤트는 이미 발생한 일이기 때문에 동사로 표현되어야 하고 실행되어야 한다.


## Event Case
#### 이벤트는 나눠보자




## Aggregate Case
1. 지출결의 Aggregate 하나 안에서 모든 걸 다 처리 하기
2. 지출결의 Aggregate, 예산편성 Aggregate, (어찌보면 소메뉴 하나당 Aggregate를 두는 방식)  
이럴 겨우 회계 서비스는 지출발의 Aggregate, 지출결의 Aggregate, 등등
   
#### 우선 해보기 1번 Case



### @Aggregate 클래스에 @AggregateIdentifier는 하나만 있어야 함.
두개 이상이면 아래 오류 발생  
has more than one identifier member, while only a single member is allowed.  
번역 > 두 개 이상의 식별자 멤버가 있지만 단일 멤버 만 허용됩니다.
