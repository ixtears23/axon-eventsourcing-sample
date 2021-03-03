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



#### @Aggregate 클래스에 @AggregateIdentifier는 하나만 있어야 함.
두개 이상이면 아래 오류 발생  
has more than one identifier member, while only a single member is allowed.  
번역 > 두 개 이상의 식별자 멤버가 있지만 단일 멤버 만 허용됩니다.



#### Command 'com.ibdata.eventsourcing.acc.resolution.coreapi.command.SaveExpenditureResolutionCommand' resulted in org.axonframework.modelling.command.AggregateNotFoundException(The aggregate was not found in the event store)Command 'com.ibdata.eventsourcing.acc.resolution.coreapi.command.SaveExpenditureResolutionCommand' resulted in org.axonframework.modelling.command.AggregateNotFoundException(The aggregate was not found in the event store)
NoArgumentsContructor를 만들지 않고, 생성자 CommandHandler를 만들지 않아서 발생함.
둘중에 하나의 문제로 발생한 것 같음. 어쨌든 둘다 문제를 일으킴.

#### Invalid command. It does not identify the target aggregate. Make sure at least one of the fields or methods in the [SaveExpenditureResolutionCommand] class contains the @TargetAggregateIdentifier annotation and that it returns a non-null value.
Command에 @TargetIdentifier를 붙이지 않았더니 발생함.


#### Command 'com.ibdata.eventsourcing.acc.resolution.coreapi.command.SaveExpenditureResolutionCommand' resulted in org.axonframework.messaging.annotation.MessageHandlerInvocationException(Error handling event of type [class com.ibdata.eventsourcing.acc.resolution.coreapi.event.ExpenditureResolutionChangedEvent] in aggregate)
2021-02-26
- 해결
@Aggregate 객체에서 Command 객체를 Arguments로 받는 생성자를 @CommandHandler 로 부여하니 문제가 해결 됨.  
결국 Command객체를 Arguments로 받는 CommandHandler 생성자가 있어야 함.  


#### Command 'com.ibdata.eventsourcing.acc.resolution.coreapi.command.SaveExpenditureResolutionCommand' resulted in org.axonframework.messaging.annotation.MessageHandlerInvocationException(Error handling event of type [class com.ibdata.eventsourcing.acc.resolution.coreapi.event.ExpenditureResolutionChangedEvent] in aggregate)
2021-02-26  
- 문제  
바로 위의 방법으로 해결된 줄 알았는데  
@CommandHandler는 타지만 동일한 오류 메세지 발생함.  
CommandGateway.send를 호출하고 나서 오류가 발생됨.  
의심되는 부분은 @AggregateIdentifier 필드가 null이 여서 발생하는 문제 같음.
- 해결
@AggregateMember 에 List 타입 멤버를 선언했는데, 초기화를 하지 않은상태에서 List.add를 실행했더니 발생 함.
List 관련 에러가 발생했다면 금방 찾았을텐데 @CommandHandler 에서 오류가 발생하니 Axon Exception 메세지를 뿌려줘서
한참 찾은듯..
위의 상황으로 볼때 위 에러는 @CommandHandler 메서드 안에서 오류발생 시 발생되는 Exception이 아닐까 싶다.
  
#### Mybatis Mapper를 @Aggregate에 객체에 주입하는 방법
2021-02-26
- 문제  
뿐만 아니라 @Aggregate 객체에 Spring Bean 또한 @Autowired로 주입되지 않음.
- 해결  
@CommandHandler 주석이 붙어 있는 Aggreagte 생성자에 Arguments로 받아서 멤버필드에 값을 할당하면 주입됨.
  

#### (conn=3097) Duplicate entry '20210302000001-0' for key 'UK8s1f994p4la2ipb13me2xqm1w'
#### Command 'com.ibdata.eventsourcing.acc.resolution.coreapi.command.SaveExpenditureResolutionCommand' resulted in org.axonframework.eventsourcing.eventstore.EventStoreException(Cannot reuse aggregate identifier [202132000001] to create aggregate [ExpenditureResolutionAggregate] since identifiers need to be unique.)
2021-03-02
- 일부 번역 
  집계 식별자 [202132000001]을 (를) 재사용하여 집계 [ExpenditureResolutionAggregate]를 생성 할 수 없습니다. 식별자는 고유해야하기 때문입니다.  
  
- 추측  
`@CommandHandler` 애너테이션이 붙은 생성자에서 객체 생성이 아닌 수정 작업을 하니 오류 발생.  
  어떤 원리에 의해 Unique Key를 할당하는지는 확실하지 않지만 domain_event_entry테이블에 insert 할 때 동일한 identifierId로 이한 Sql 오류 발생


