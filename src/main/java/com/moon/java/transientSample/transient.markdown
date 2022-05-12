# [JAVA]

## transient

### 들어가며

- `transient` 키워드를 이해하기 위해서는 `Serialize`에 대한 이해가 필요하다.

<details><summary>Serialize</summary>

1. 직렬화란
    1. 직렬화(Serialize)
        - 자바 시스템 내부에서 사용되는 `Object` 또는 `Data`를 외부의 자바 시스템에서도 사용할 수 있도록 `byte` 형태로 데이터를 변환하는 기술
        - `JVM`(Java Virtual Machine 이하 JVM)의 메모리에 상주(힙 또는 스택)되어 있는 객체 데이터를 바이트 형태로 변환하는 기술<br><br>
    2. 역직렬화(Deserialize)
        - `byte`로 변환된 `Data`를 원래대로 `Object`나 `Data`로 변환하는 기술을 역직렬화(`Deserialize`)라고 부른다.
        - 직렬화된 바이트 형태의 데이터를 객체로 변환해서 `JVM`으로 상주시키는 형태.

2. 직렬화 (Serialize) 시작하기
    1. 직렬화 조건
        - `java.io.Serializeable` 인터페이스를 상속받은 객체는 직렬화 할 수 있는 기본 조건이다.
   ```java
   import java.io.Serializable;
   public class Member implements Serializable{
        private String name;
        private String email;
        private int age;
   
    public Member(String name, String email, int age){
        this.name = name;
        this.email = email;
        this.age = age;
   } 
    @Override
    public String toString() {
        return String.format("Member{name='%s', email='%s', age='%s'}, name, email, age");
    }    }
   ```
    2. 직렬화(Serialize) 방법
        - `java.io.ObjectOutputStream`
    ```java
    import java.io.ByteArrayOutputStream;import java.io.ObjectOutputStream;import java.util.Base64;public class sampe{
        public static void main(String[] args){
        Member member = new Member("SangMoon","deliverykim@baemin.com",25);
        byte[] serializedMember;
        try(ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()){
            try(ObjectOutputStream oos = new ObjectOutputStream(byteArrayOutputStream)){
                oos.writeObject(member);
                //serializedMember => 직렬화된 member 객체
                serializedMember = byteArrayOutputStream.toByteArray();
            }
        };
        //바이트 배열로 생성된 직렬화 데이터를 base64로 로 변환
        System.out.println(Base64.getEncoder().encodeToString(serializedMember));
        }
     }
    ```
    1. 역직렬화(Deserialize) 시작하기
        1. 역직렬화(Deserialize) 조건
            1. 직렬화 대상이 된 객체의 클래스가 클래스 패스에 존재해야 하며 `import`되어 있어야 한다.
            2. 중요한 점은 직렬화와 역직렬화를 진행하는 시스템이 서로 다를 수 있다는 것을 반드시 고려해야한다.
            3. 자바 직렬화 대상 객체는 동일한 `serialVersionUID`를 가지고 있어야 한다.
                1. `private static final long serialVersionUID = 1L;`
            4. `serialVersionUID` 이 왜 필요한지 자세한 내용은 아래에 추가한다.
        2. 역직렬화 (Deserialize) 방법
            1. `java.io.ObjectInputStream`을 사용하여 역직렬화를 진행한다.
           ```java
             import java.io.ByteArrayInputStream;
             import java.io.ObjectInputStream;
             import java.lang.reflect.Member;
             import java.util.Base64;
             public class sample{
                 public static void main(String[] args){
                   //직렬화 예제에서 생성된 base64 데이터
                   String base64member = "...생략";
                   byte[] serializedMember = Base64.getDecoder().decode(base64member);
                   try(ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(serializedMember)){
                      try(ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)){
                          Object objectMember = objectInputStream.readObject();
                          Member member = (Member) objectMember;
                          System.out.println(member);          
                             }
                      }
                 }
        
           }
           ```
        3. 다른 `Format`의 직렬화
            1. ![img.png](img.png)
                1. 직렬화 방법에는 여러 Format이 존재한다.
                    1. 표형태의 다량의 데이터를 직렬화 할대는 CSV 형태
                    2. 구조적인 데이터는 `XML`, `JSON` 형태
        4. CSV
            1. 데이터를 표현하는 가장 많이 사용 되는 방법 중 하나로 콤마(,) 기준으로 데이터를 구분하는 방법이다.
                1. Moon,SangMoonRoh,Seoul,Korea ->[Moon,SangMoonRoh,Seoul,Korea]
            2. ```java
                Member member= new Member("김배민","deliverykim@baemin.com",25);
                //member 객체를 csv로 변환
                String csv = String.format("%s,%s.%d",member.getName(), member.getEmail(),member.getAge());
                System.out.println(csv);
            ```
            3. ApacheCommons CSV
            4. opencsv
        5. JSON
            1. JSON이란?
            2. ```java
            Member member = new Member("김배민","deliverykim@baemin.com",25);
            //member 객체를 json으로 변환
            String json = string.format(
            "{\"name\":.\"%s\",\"email\":\"%s\",\"age\":%d}",
            member.getName(), member.getEmail(), member.getAge());
            System .out.println(json);
            ```
            3. Jackson
            4. Gson<br><br>

        6. 자바의 직렬화는 왜 사용하는가?<br><br>
            1. 복잡한 데이터 구조의 클래스의 객체라도 직렬화 기본 조건만 지키면 큰 작업 없이 바로 직렬화, 역직렬화가 가능하다.
            2. 데이터 타입이 자동으로 맞춰지기 때문에 관련 부분에 큰 신경을 쓰지 않아도 된다.<br><br>
        7. 어디에 사용 되는가?
            1. 서블릿세션 (Servlet Session)<
                1. 세션을 서블릿 메모리 위에서 운용한다면 직렬화를 필요로 하지 않지만, 파일로 저장하거나 세션 클러스터링, DB를 저장하는 옵션 등을 선택하게 되면 세션 자체가 직렬화가 되어 저장되어
                   전달된다.
            2. 캐시(Cache)
                1. Ehcache, Redis, Memcached 라이브러리 시스템에 많이 사용된다.<br><br>
            3. 자바 RMI(Remote Method Invocation)
                1. 원격 시스템 간의 메시지 교환을 위해서 사용하는 자바에서 지원하는 기술<br><br>
        8. 자바의 직렬화 단점?<br><br>
            1. 역직렬화시 클래스 구조 변경 문제
                1. 기존 멤버 클래스를 직렬화한다.<br>

                2. ```java
                    import java.io.Serializable;
                    public class Member implements Serializable{
                        private String name;
                        private String email;
                        private int age;
                    //생략
                    }
               ```
                3. 직렬화한 Data
                    1. `rO0ABXNyABp3b293YWhhbi5ibG9nLmV4YW0xLk1lbWJlcgAAAAAAAAABAgAESQADYWdlSQAEYWdlMkwABWVtYWlsdAASTGphdmEvbGFuZy9TdHJpbmc7TAAEbmFtZXEAfgABeHAAAAAZAAAAAHQAFmRlbGl2ZXJ5a2ltQGJhZW1pbi5jb210AAnquYDrsLDrr7w=` <br><br>
                    2. 멤버 클래스에서 속성을 추가한다.
                        1. ```java
                     import java.io.Serializable;
                     public class Member implements Serializable{
                        private String name;
                        private String email;
                        private int age;
                        // phone 속성을 추가
                        private String phone;
                     }
                     ```
                        2. 직렬화한 Data를 역직렬화하면 어떤 결과가 나올까? 결과는 `java.io.InvalidClassException`이 발생한다.
                        3. 위에서 언급했던 것처럼 직렬화 하는 시스템과 역직렬화 하는 시스템이 다른 경우에 발생하는 문제다.
                        4. 각 시스템에서 사용하고 있는 모델의 버젼 차이가 발생했을 경우에 생기는 문제다.<br><br>
                    3. 해결하기 위해서는<br>
                        1. 모델의 버젼간의 호환성을 유지하기 위해서는 `SUID(serialVersionUID)`를 정의해야 한다.
                        2. Default는 클래스의 기본 해쉬값을 사용한다.>>>>>???<br><br>
                    4. 또 다른 문제
                        1. `String` - `StringBuilder`,`int` => `long`으로 변경해도 역직렬화에서 `Exception`이 발생한다.
                        2. 자바 직렬화는 상당히 타입에 엄격하다 .
                        3. 멤버 변수가 빠지게 된다면 `Exception`대신 `null`값이 들어가는 것을 확인할 수 있다.<br><br>
                    5. 직렬화 Data Size 문제
                        1. ```java
                        {"name":"김배민","email":"deliveryKim@baemin.com","age":25}
                        serializedMember (byte size =146)
                        json (byte size = 62)
                     ```
                        2. 아주 간단한 객체의 내용도 2배이상의 차이를 확인할 수 있다.
                        3. 일반적인 메모리기반의 `Cache`에서는 `Data`를 저장할 수 있는 용량의 한계가 있기 때문에 `Json`형태와 같은 경량화된 직렬화 하는것도 좋은
                           방법이다.<br><br>
                    6. 정리하며
                        1. 외부 저장소로 저장되는 데이터는 짧은 만료시간의 데이터를 제외하고 자바 직렬화 사용을 지양한다.
                        2. 역직렬화시 반드시 예외가 생긴다는 것을 생각하고 개발해야한다.
                        3. 자주 변경되는 비즈니스적인 데이터를 자바 직렬화하지 않는다.
                        4. 긴 만료 시간을 가지는 데이터는 `JSON`등 다른 포맷을 사용하여 저장한다.

</details>

### Java Transient 이란?

- `transient`는 `Serialize`하는 과정에 제외하고 싶은 경우 선언하는 키워드이다.

### 왜(Why)필요할까요?

- 패스워드와 같은 보안정보가 직렬화(Serialize)과정에서 제외하고 싶은 경우에 적용한다.
- 다양한 이유로 데이터를 전송 하고 싶지 않을 때 선언할 수 있다.

#### Example

- Model

```java
import java.io.Serializable;

class Member implements Serializable {

    private String name;
    private String email;
    private int age;

    public Member(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("Member{name='%s', email ='%s', age='%s'}", name, email, age);
    }
}
```

- Main

```java

class sample {
    public static void main(String[] args) {
        Member member = new Member("노아라", "nyangRoh@baemin.com", 25); // Model 객체
        String serialData = serializeTest(member);// 직렬화
        deSerializeTest(serialData);// 역 직렬화
    }
}
```

- result
  ![img_1.png](img_1.png)<br><br>
  이름에 `trasient keyword`를 추가하면 어떻게 될까?

```java
   import java.io.Serializable;

class Member implements Serializable {
    private transient String name;
    private String email;
    private int age;
}
```
<br>
- 결과를 확인해보면 `field`는 유지되지만 `null`값이 대입되는 것을 확인할 수 있었다.<br>

![img_2.png](img_2.png)<br><br>
### 주의해야할 점
   - 적용하는 Data에 대한 이해가 필요하다.
   - Data를 제외하였을 경우 서비즈 장애에 이상이 없는지에 대한 고려.