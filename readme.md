# 지출결의 prototype? demo?



### package 구조

domain > 하위domain >
- controller
- service
- domain
- entity
- vo
- dto
- mapper


### vo
 - getter만 가지고 있음.
 - 변경불가. immutable
 - View -> Controller(무조건 확정)
 - Controller -> Service(무조건 확정)

### dto
 - Service -> Controller
 - Controller -> View

### entity
- Service -> Mapper  
CUD : 정확히 Table Entity로 가능  
R 은 불가

- Mapper -> Service
R 인 경우만 존재  
Table Entity로 불가 부수적으로 추가되어야 함.  

> !!!!!어차피 별도로 추가되는 항목만 Entity에 추가적으로 넣어주면 되는 것 같음...


### 이전에 가지고 있던 고민
> !!! 여기서 생각할 것은. 위 방식대로 하면 DB에서 항목을 가공해서 가져오지 않겠다는 것임.  
그 부분을 어떻게 할 지 생각해야 함.  
예를들어 DB에서 Rank(순위)를 가져오는 경우  
rank라는 컬럼은 존재하지 않음. 이런경우 entity 객체에 테이블 컬럼이 아닌 임의의 필드를 추가로 만들어 줘야 함.  
이럴 경우 어떻게 처리할지 생각해야 함.  



### @AggregateMember, @EntityId
자식 Entity를 선언하는 필드에 @AggregateMember 를 붙인다.  
메세지 핸들러를 검사해야하는 클래스가 포함되어 있음을 알림.  
필드와 메서드에 배치할 수 있다.  
자식 클래스의 식별자에 @EntityId 를 붙인다.  

> ??? 이해가 안감..  
> 메시지가 라우팅되어야하는 엔터티를 찾는 데 사용되는 페이로드의 속성은 기본적으로 @EntityId 주석 필드의 이름으로 설정됩니다.


> 모든 CommandHandler를 루트에 배치하면 AggregateRoot에 많은 수의 메서드가 생성되는 경우가 많지만  
> 대부분은 단순히 기본 엔터티 중 하나로 호출을 전달합니다.  
> Axon이 이러한 어노테이션이있는 메소드를 찾으려면 AggregateRoot에서 엔티티를 선언하는 필드를 @AggregateMember로 표시해야합니다.

AggreagateMember를 사용하는 이유와 AggregateMember 관련 내용
- Aggregate가 자식 Entity를 가져갸 하는 경우  
- Aggregate에 너무 많은 메서드를 갖게 될 수 있으므로 책임을 자식과 분리  
- 자식Entity에 해당하는 CommandHandler를 찾기 위함.  
- 자식Entity의 CommandHandler를 수행하기 위해서는 Command객체에 자식 Entity의 Routing Key인 EntityId 필드와 동일한 이름의 필드를 가져야 함.

> CommandHandler 고려 사항  
> 각 Command에는 Aggregate에 정확히 하나의 Handler가 있어야 함.  
> 동일한 Command 유형을 처리하는 @CommandHandler를 사용하여 여러 Entity에 @CommandHandler Annotation을 달 수 없다. 

> 부모와 자식간의 연결되는 Key는 없어도 되는가. @AggregateMember를 통해서 객체간 연과관계만 갖으면 되는 것 같기도 하고..




> 자식 Entity의 생성은 상위 Aggregate에서 발생함. List타입인 경우 add를 통해 자식 Entity 생성
> 그렇기 때문에 자식 Entity에서는 AggregateRoot와는 다르게 생성자 CommandHandelr를 사용할 수 없다.  

> Entity의 @EventSourcingHandler는 수신된 이벤트가 실제 Entity에 속하는지 확인하는 유효성 검사를 수행한다.  
> 동일한 유형의 다른 Entity 인스턴스에서도 처리되기 때문에 유효성 검사가 필요하다.  

---


### @QueryHandler
`@QueryHandler`의 경우도 `@CommandHandler`나 `@EventHandler`와 동일하게 첫번째 Arguments 타입을 찾는다.  
`QueryGateway.query('첫번째Args', '두번째Args');`
 - 첫번째Args : `@QueryHandelr`의 첫번째 Arguments 타입과 일치하는 `@QueryHandelr`를 찾는다. 
 - 두번째Args : `@QueryHandler`의 return 타입과 일치해야 한다.


### EventStore
- EventStore에서는 Domain_Event_Entry 테이블의 Event를 획득한다.



### @EventHandler vs @EventSourcingHandler
[AxonIQ](https://discuss.axoniq.io/t/differences-between-eventhandler-and-eventsourcinghandler/2432/2)

> EvnetSourcingHandler는 Command사이드의 Aggregate에 있고  
> EventHandler는 일반적으로 Event가 발생한 후 수행할 작업을 지시하기 위해 Query side에 있음.    

> EventSourcingHandler가 실행을 완료하면  
> 이벤트가 EventBus에 게시되고  
> EventHandlers에 의해 포착됨.  
  
> 이벤트는 일반적으로 과거 시제로 명명되기 때문에 EventSourcingHandler를 현재로 생각하고  
> EventHandler는 이벤트가 과거에 존재했으면 실행된다.  


> (사실 단일 모듈 구조에서 EventHandlers 사용을 정당화하는 경우는 거의 없음).  

### CommandGateway vs CommandBus
[AxonIQ](https://discuss.axoniq.io/t/difference-between-command-bus-and-command-gateway/1297)  
> Command는 항상 CommandBus를 통해서 전달 됨.  
> 어떤 것을 사용하는지는 중요하지 않음.  
> CommandGateway를 사용하는 쪽으로 가자...  


### 유효성 검사
[Google그룹-@Aggregate: business validation on the handler or listener?](https://groups.google.com/g/axonframework/c/PGWXtdfLWl0)  
유효성 검사 방법에는 3가지 정도 있다.  
- HandlerInterceptor
- 외부 CommandHandler
- Aggregate안에서 Validation  

> validator가 repository(Database) 를 호출하지 않는 유효성 검사인 경우는 Aggregate안에 확실히 존재해야 한다.  
> 유효성검사가 repository를 호출해야 한다면 Aggregate를 Blocking(차단)해야 한다고 생각하지 않음.  
> 이런 경우는 Controller와 Aggregate 사이에 Service를 두고 Service에서 유효성 검사를 처리하도록 해도 됨.  
 


[Google그룹-Good practice for contextual business logic](https://groups.google.com/g/axonframework/c/4zy-E1GCY4A)  
의견을 올린 사람의 견해 - 이 사람은 4번째 옵션 @Controller와 @Aggregate 사이에 @Service를 두고 그곳에서 validation 하는 방법이 좋다 생각함.   
> 1. Aggreagte안에 외부 참조를 호출하는 종속성을 추가하면 문제?가 있고 단위테스트를 어렵게 만든다.  
> 2. Aggregate 앞에 유효성 검사 전용 CommandHandler를 두는 방법 : 설계 측면에서 더 좋을 수 있지만 2개의 명령이 필요하기 떄문에 구현하기 무거움.  
> 3. CommandHandlerInterceptor에서 유효성 검사 : 보안과 같은 비 기능적 요구사항에 사용하는 사례가 더 적합하다 생각  
> 4. Aggregate 앞에있는 표준 자바 클래스 (예 : Spring을 사용하여 @Service로 주석 처리 된 클래스) 사용: 해당 클래스는 REST 인터페이스와 Aggregate 사이의 중간 역할을합니다.  

아래는 답변  
> 질문에 답하기 위해는 비즈니스 논리 유효성 검사가 포함된 두가지 시나리오가 필요함.  
- 1. 유효성 검사가 아웃 바운드 호출 (database/REST 등)을 수행합니까?  
- 2. Aggregate의 상태와 들어오는 명령을 기반으로 유효성 검사를 수행할 수 있습니까?  
    
> 1번 시나리오의 경우 CommandBus에 명령을 publishing 하는 Service에 validation을 수행하는 것이 좋음.  
> 위에서 말한 4번 옵션 @Controller와 @Aggregate 사이에 @Service를 두는 것이 좋음.  
> 2번 시나리오의 경우 Aggregate 자체에서 가장 잘 동작함.  

> 그리고 해당 유효성 검사를 (상태 비 저장) 서비스에 배치하기로 결정한 경우  
> Axons ParameterResolver가 해당 서비스를 @CommandHandler 함수의 매개 변수로 쉽게 해결하도록 할 수 있습니다.  
> 간단히 말해서, 시나리오 2의 경우, 당신의 제안 중 옵션 1을 선택하겠습니다.  

[AxonIQ-일관성 검증 설정](https://axoniq.io/blog-overview/set-based-validation?utm_campaign=Blog%20promotion&utm_source=twitter&utm_medium=social&utm_content=set%20based%20validation)  


