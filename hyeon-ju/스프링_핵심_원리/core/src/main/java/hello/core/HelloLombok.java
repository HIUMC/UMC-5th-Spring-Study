package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.lang.annotation.Target;

@Getter
@Setter
//롬복이 자동으로 변수보고 getter, setter 만들어 줌
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("sdgssge");

        String name = helloLombok.getName();
        System.out.println("helloLombok = " + helloLombok);
    }
}
