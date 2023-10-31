# 실전! 스프링 부트와 JPA

- **프로젝트 환경설정**
    - Model : data를 실어서 view에 넘김
    - 정적 컨텐츠 : index.html 처럼 static 폴더에 있는거 바로 띄움
    - 템플릿 엔진, 렌더링 필요한거는 templates 폴더에
    - ./h2.bat 으로 디비 실행
    - **이거 공부해야될듯?!!**
        - @Entity : @Entity가 붙은 클래스는 JPA가 관리
        - @Id @GeneratedValue
        - entity manager ??
        - Transaction???
    - 컴포넌트 스캔 → 자동으로 스프링 빈에 등록

- **질문**
    
    <aside>
    💡 **Q. ㅁㅁㅁㅁ을 채우세용**
    
    ---
    
    @ㅁㅁㅁㅁ
    
    public class HelloController {
    
            @GetMapping("hello")
    
       public String hello(Model model) {
    
            model.addAttribute("data", "hello!!");
    
            return "hello";
    
        }
    
    }
    
    - **정답!**
        
        @Controller 
        
        - **그럼 Controller의 역할은 ?!!**
            
            모델과 뷰 사이 다리역할
            
            - 그럼 스프링에서 @Controller을 쓰면 어떤 일이 ??!!
                
                스프링 시작시 객체를 생성하여 스프링 컨테이너에서 컨트롤러 관리
                
                - @Controller에 어떤 어노테이션이 있어서 가능할까??!
                    
                    @Component 가 있어서 가능하다~
                    
                    - return “hello” 는 어떤 의미? (기능?, 효과?) 일까?!
                        
                        templates의 hello.html로 간다~
                        
    
    ---
    
    **Q. 다대다 는 어떻게 바꿔서 풀어내면 좋을까??**
    
    - **정답!**
        
        엔티티를 추가해서 다대다 관계를 일대다, 다대일 관계로~
        
    </aside>
    

- **요구사항 분석**