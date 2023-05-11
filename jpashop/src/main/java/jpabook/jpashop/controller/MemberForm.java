package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberForm {

    @NotEmpty(message = "회원 이름은 필수 입니다")    //입력 필수, javax를 통해 spring이 validation을 해줌
    private String name;
    private String city;
    private String street;
    private String zipcode;
}
