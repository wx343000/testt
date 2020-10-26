package com.zjx;

public class TestBBChild extends TestBB {
//    private String a = "2";
//
//    public  String getaaa(){
//        return geta();
//    }
    public static void main(String[] args) {
        TestBBChild testBBChild = new TestBBChild();
//        String getaaa = testBBChild.getaaa();
//        System.out.println(getaaa);
        testBBChild.test();
    }

    @Override
    public void a() {
        System.out.println("child a");
    }
}
