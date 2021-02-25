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

