- `**스프링 2차 확인 질문[스프링 입문]**`
    <aside>
    💡 다음과 같은 상황을 서술하시오. (정해진 답은 없고, 공부 복습 차원)
    
    1.  **GetMapping(”hello”)의 의미?  해당 화면을 보고 싶으면 어떤 url로 접속하면 되는지?**
        
        localhost:8080/hello
        
    2.  **model은 뭐하러 쓰는지?** (자유롭게 서술하지만,    1. 모델을 굳이 선언해서 사용하는 이유,  2. “data”를 html파일에서 사용하는 방법 을 포함하여.)
        
        캡슐화 → 밖에서 접근을 못하게 하고, 바뀌지 않게 보호를 해야 한다.
        
        view 와 controller 가 model 을 임의로 조작할 수 없다.
        
    3.  **return “hello”; 를 하는 이유?**
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/f1912130-0409-4e90-a90f-6091ae253e73/ee334fa5-467b-461a-890c-8663089046b5/Untitled.png)
    
     반환형이 string 이기도 하고, view 에 data 를 반환해주기 위해.
    
    4. **만약 GetMapping 밑에@ResponseBody를 삽입한다면**  `localhost:8080/hello-string`**에 접속 시 무엇이 출력 될 것인가?**   모르겠다면 진행해보자.  현재로써 뷰리졸버 등등 어려운 용어는 절대 외울 필요 없다.
    
     ![Untitled](https://github.com/Iaminjae/Test/assets/126381645/5311446c-6f35-49c4-890e-426e7d12185a)
    
    파라미터로 받은 name 의 값이 hello 뒤에 나오게 된다.
    
    </aside>
    
    <aside>
    💡 회원 리스트 html에서 아래 사진의 실행결과를 출력해서 붙여넣기하시오
    
    **members 리스트는 강의대로 사용해도 좋고, 현 umc 서버 멤버 이름을 사용한다면 더 좋음.**
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/f1912130-0409-4e90-a90f-6091ae253e73/08f473c7-d79d-4fc6-ad09-1f18497ad6cc/Untitled.png)
    
    </aside>
    
    <aside>
    💡 아래 사진의 클래스를 스프링 빈으로 등록하고 싶다면 어떤 어노테이션을 사용하면 되는가?
    
    1. @Controller
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/f1912130-0409-4e90-a90f-6091ae253e73/261eeb17-aceb-40f2-9194-8aa41ed6236d/Untitled.png)
    
    1. @Repository
    
   ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/f1912130-0409-4e90-a90f-6091ae253e73/c4cfba2f-5abe-40cb-a5c5-2409e77e5d0a/Untitled.png)
    
    이런 어노테이션을 왜 사용하는지 이해가 안될텐데 그냥 써야된다는 것만 알아둬도 충분하다.  ~~(약간 설명 주자면 음 스프링 실행시에  스프링한테  리포지토리로 이걸 쓸거다  우리가 말해주는 느낌?)~~
    
    </aside>
    
    <aside>
    💡 JPA란?  (직접 구글링, 매우 중요함. DB에 넣고 출력할 때 무조건 JPA 다 사용할 예정.   지금은 어지럽겠지만 개념만 알아두자. 왜쓰는지만.)
    1. **JPA를 굳이 왜 쓰지?** (구글링 그대로 받아들이기보다 그냥 자신의 이해내용을 막 적기)
    2. jpql이란?  **필수아님. →** 가능하다면 sql로 “select * from user where id = 1” 을 jpql로 바꿔볼 수 있을까?
    
    쿼리를 대신 만들어서 날려줌. DB 마다 쿼리문의 문법이 조금씩 다른데 JPA 가 알아서 자동으로 쿼리문을 적절하게 날려줌. 
    
    </aside>
