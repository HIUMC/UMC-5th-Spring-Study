package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE) //TYPE이면 클래스 레벨에 붙는 것
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {
}
