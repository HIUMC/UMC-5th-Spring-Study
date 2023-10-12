package hello.core;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {
    private String name;
    private Long age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();

        helloLombok.setName("이름");
        helloLombok.setAge(10L);

        System.out.println(helloLombok);
    }
}
