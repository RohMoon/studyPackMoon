package com.moon.java.wrapperClass;
// 문자열을 기본 타입 값으로 변환
public class WrapperClassSample {
    public static void main(String[] args) {
        String str = "10";
        String str2 = "10.5";
        String str3 = "true";

        byte b= Byte.parseByte(str);
        int i = Integer.parseInt(str);
        short s = Short.parseShort(str);
        long l = Long.parseLong(str);
        float f = Float.parseFloat(str2);
        double d = Double.parseDouble(str2);
        boolean bool = Boolean.parseBoolean(str3);

        System.out.println("문자열 byte 값 변환 : "+b);
        System.out.println("문자열 int 값 변환 : "+i);
        System.out.println("문자열 short 값 변환 : "+s);
        System.out.println("문자열 long 값 변환 : "+l);
        System.out.println("문자열 float 값 변환 : "+f);
        System.out.println("문자열 double 값 변환 : "+d);
        System.out.println("문자열 boolean 값 변환 : "+bool);
    }
}
