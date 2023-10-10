package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.core.member",                    //member 하위 패키지에서만 검색
        basePackageClasses = AutoAppConfig.class,
        excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, classes = Configuration.class)      //AppConfig의 @Configuration을 읽어 충돌을 방지하기 위함(기존 예제코드 지우기 아까워서)
)
public class AutoAppConfig {
}
