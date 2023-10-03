package hello.core.singleton;

import java.security.PrivilegedAction;

public class SingleTonService {

    private static final SingleTonService instance = new SingleTonService();          //static 영역에 singletonservice하나만 올라감

    public static SingleTonService getInstance() {
        return instance;
    }

    private SingleTonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}
