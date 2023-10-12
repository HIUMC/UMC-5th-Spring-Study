package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextinfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + " object = " + bean); // soutv
        }
    }

    @Test
    @DisplayName("어플리케이션 빈 출력하기")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);//getBeanDefinition 빈에 대한 메타데이터 정보

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {// Role은 크게 3가지가 있는데, 주로 쓰는 것은 ROLE_APPLICATION.
                //이것은 스프링의 내부 뭔가를 하기 위해서 등록된 빈들이 아니라 내가 어플리케이션을 주로 개발하기 위해서 등록한 빈들. 외부 라이브러리 등
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + " object = " + bean); // soutv
            }
        }
    }
}
