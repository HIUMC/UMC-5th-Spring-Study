package dbpractice.dbhomework.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CustomerForm {

    @NotEmpty(message = "고객 이름은 필수입니다.")
    private String name;

    private String city;
    private String street;
    private String zipcode;
}
