package hello.core;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CoreApplicationTests {

	@Test
	void contextLoads() {
	}
	
	//Spring이라 실행시간 다른 것에 비해 오래 걸림. But 다른 라이브러리 많이 들어가고 하면 훨씬 오래 걸림.
	//단위테스트를 잘 만드는 것이 엄청나게 중요하다!!!
	//여기서 단위테스트란 스프링이나 이런 컨테이너의 도움 없이 그냥 순수하게 자바 코드로 테스트 하는 것
}
