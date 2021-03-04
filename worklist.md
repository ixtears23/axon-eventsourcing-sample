## worklist 

#### 작성 시작 일시 : 2021. 3. 2.

> 늦었지만 지금부터라도 작성 시작.


- token_entry 테이블의 정체는?
- monolith에서만 projection 인가? monolith가 아니면 kafka 나 Axon Server를 사용해야 하는건가?
- replay 해보기. event store에서 값 꺼내보기
- event store에서 실제 비즈니스 로직에 맞게 값 꺼내서 로직 적용하기
- snapshot 해보기
- 굳이 QueryGateway를 써야 하는 이유가 있을까? 직접 Mybatis나 JPA를 사용해서 조회하면 안되나?
- event store에서 값을 가져올 때 식별자 ID를 Arguments로 보내는데. 
  도메인 별로는 유일하지만 모든 도메인을 통틀어서는 유일하지 않을 수 있는데 이런 경우 모든 도메인에 있는 식별자 ID에 해당하는 Event를 조회해 오는 것인가?
- Aggregate에 Identifier를 제외한 Field가 있을 필요가 있나? 단지 Aggregate 개념적으로 봤을 때 당연히 field를 가지고 있어야 하는 건가?   
- Aggregate Loading 최적화를 위해 Snapshot을 기록한다는데 실질적으로 사용은 어떻게 하는것인가? 고로 Aggregate Loading이란?
- `snapshot_event_entry` 테이블에서 `payload`컬럼과 `payload_revision`컬럼은 뭔데 값이 없지?  
- CommandModel 에서 EventStore에 새로운 키가 등록 됐는데 QueryModel에는 저장 안된 상태에서 또 저장하려고 하면  
  QueryModel 조회 시 아직 저장되지 않았으니 CommandModel에서 중복키를 저장하는 일 발생할 수 있음.  
  예) CommandModel Key등록 시 QueryModel 조회 -> Max key 10 -> 11번으로 키 등록 -> EventStore에만 저장된 상태  
  여기서 CommandModel 에서 또 Key등록 시 QueryModel 조회 -> Max Key 10 -> 여기서 12번으로 키를 등록해야 되는데  
  아직 QueryModel에 Key 등록이 안되었기 때문에 Key중복 발생 - 근데 여기서 Axon은 Aggregate의 Identifier를 고유하게 유지해서 Exception을 발생시키긴 할 듯.  
  Aggregate가 정말 살아있는 객체 상태라면.. 최신 객체를 찾아서 그 객체의 키값을 확인 하면 될 것 같긴 한대.. 너무 복잡한데..  
- 만약 CommandModel 에서는 성공하고 QueryModel에서는 실패한 경우는?  
  이럴 경우에 Event Replay를 통해서 다시 QueryModel이 Event를 획득할 수 있게 하는 것인가?  
