package hello.core.singleton;

public class SingletonService {
    
    //SingletonService 생성
    private static final SingletonService instance = new SingletonService();
    
    //return instance를 함으로써 SingletonService를 return
    public static SingletonService getInstance(){
        return instance;
    }
    
    //해당 생성자는 private이므로 외부에서 호출 x =>  static으로 선언된 객체를 getInstnace로 불러와야함
    private SingletonService(){
        
    }
    
    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
