package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable // 값 타입, 변경 불가능 하게 설계 해야함
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;

    protected Address() {   // public은 호출 가능, jpa spec 에서 protected 까지는 허용
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
