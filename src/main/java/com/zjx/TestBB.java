package com.zjx;

import javax.annotation.PostConstruct;

public class TestBB {
    //    private String a = "1";
//
//    public String geta() {
//        return a;
//    }
    public void test() {
        a();
        b();
        c();
    }

    public void a() {
        System.out.println("a");
    }
    public void b() {
        System.out.println("b");
    }
    public void c() {
        System.out.println("c");
    }
}
