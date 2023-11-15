package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Getter @Setter
public class MemberForm {
    @NotNull
    private String name;

    private String city;
    private String street;
    private String zipcode;

}
