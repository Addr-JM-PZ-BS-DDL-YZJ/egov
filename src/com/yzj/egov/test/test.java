package com.yzj.egov.test;

/**
 * @author YZJ
 * @version 1.0
 * @date 2023/2/13 12:54
 * egov
 * @since 1.0
 */
public class test {
    public static void main(String[] args) {
        System.out.println(Test.test(1024,256));
    }
}

class Test{
    public static boolean test(Integer a,Integer b){
        Integer c =128;
        return (c=a/b)==4?true:false;

    }
}
