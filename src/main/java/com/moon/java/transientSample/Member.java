package com.moon.java.transientSample;

import java.io.Serializable;

public class Member implements Serializable {
    private String name;
    private String email;
    private int age;

    public static void main(String[] args) {
        Member member = new Member("노아라","nyangRoh@baemin.com",25); // Model 객체
        String serialData = serializeTest(member);// 직렬화
        deSerializeTest(serialData);// 역 직렬화
    }

    public Member(String name, String email, int age){
        this.name = name;
        this.email = email;
        this.age = age;
    }
    @Override
    public String toString(){
        return String.format("Member{name='%s', email ='%s', age='%s'}",name, email,age);
    }
}
