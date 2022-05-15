#[JAVA] 
## 데이터 타입 - Integer 와 int 의 차이
### Primitive 자료형 - Wrapper 클래스 관계

 - `int` : `primitive` 자료형 (long, double, float... )
   - 산술 연산이 가능하다.
   - `null`로 초기화를 할 수 없다.<br><br>
 - `Integer` : `Wrapper` 클래스 => 한 객체를 의미
 - `unBoxing`을 하지 않으면 산술 연산이 불가능 하지만, `null` 값을 처리할 수 있다.
 - `null `값 처리가 용이하기 때문에 `SQL`과 연동할 경우에 처리를 원활하게 할 수 있다.
 - `DB` 에서 자료형이 정수형이지만 `null`값이 필요한 경우 `VO`에서 `Integer`를 사용할 수 있음.
--------
 ### int와 Integer간의 변환
- `Boxing`과 `Unboxing`이라고 한다.<br><br>
- Boxing: `Primitive` 자료형 ==> `Wrapper` 클래스
- Unboxing : `Wrapper` 클래스 ==> `Primitive` 자료형

```java
//Integer i 를 int i로 == Unboxing
    int i = ii.intValue();

//int형 i를 Integer i fh == Boxing
    Interger ii = new Integer(i);
```
`valueOf()`와  `parseInt()`의 차이

`Intger.valueOf(String)` : `Integer` 클래스를 리턴하기 때문에 산술 연산을 할 수 없다.<br><br>
`Integer.parseInt(String)` : int 형을 리턴하기 때문에 산술 연산을 할 수 있다.