package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService(); //자기 자신을 내부의 private으로 하나 가지고 있는데, static으로 가지고 있음
    //클래스 레벨에 올라 가기 때문에 딱 하나만 존재 하게 됨
    //자기 자신을 실행한 후, 객체를 생성한 다음에 instance에 참조를 넣어둔다
    //자기 자신 인스턴스 객체를 하나 딱 생성하여 instance 안에만 들어가 있는 것

    public static SingletonService getInstance() {
        return instance; //instance의 참조를 꺼낼 수 있는 유일한 존재
    }

    private SingletonService() { //private 생성자로 SingletonSerive를 여러개 만드는 것 금지시켜 버림
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

    /*
    public static void main(String[] arg) { 이렇게 다른 곳에서 new SingletonSerive를 두개 만들어 버릴 수도 있어서 private 생성자를 사용한다
        SingletonService singletonService = new SingletonService();
    }
     */

}
