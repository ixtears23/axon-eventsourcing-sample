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
- Aggregate에 Field가 있을 필요가 있나?   
- Aggregate Loading 최적화를 위해 Snapshot을 기록한다는데 실질적으로 사용은 어떻게 하는것인가? 고로 Aggregate Loading이란?
- `snapshot_event_entry` 테이블에서 `payload`컬럼과 `payload_revision`컬럼은 뭔데 값이 없지?  