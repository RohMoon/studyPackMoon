package com.moon.java.transientSample;

public class Main {
    public static void main(String[] args) {
        Member member = new Member("노아라","nyangRoh@baemin.com",25); // Model 객체
        String serialData = serializeTest(member);// 직렬화
        deSerializeTest(serialData);// 역 직렬화
    }
}
