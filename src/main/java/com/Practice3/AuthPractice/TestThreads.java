//package com.Practice3.AuthPractice;
//
////class A implements Runnable{
////
////    @Override
////    public void run() {
////        for(int i=0;i<5;i++){
////            System.out.println("Be");
////            try {
////                Thread.sleep(10);
////            } catch (InterruptedException e) {
////                throw new RuntimeException(e);
////            }
////        }
////    }
////}
////class B implements Runnable{
////
////    @Override
////    public void run() {
////        for(int i=0;i<5;i++){
////            System.out.println("Focused");
////            try {
////                Thread.sleep(10);
////            } catch (InterruptedException e) {
////                throw new RuntimeException(e);
////            }
////        }
////    }
////}
//
//
//class Counter{
//    int count=0;
//    public synchronized void counter(){
//        count++;
//    }
//}
//class TestThreads {
//    public static void main(String[] args){
//
//        Counter c = new Counter();
//        Runnable a = ()-> {
//                for(int i=0;i<1000;i++){
//                    c.counter();
//                }
//            };
//        Runnable b = ()-> {
//                for(int i=0;i<1000;i++){
//                    c.counter();
//                }
//            };
//
//        Thread t1 = new Thread(a);
//        Thread t2 = new Thread(b);
//
//        t1.start();
//        t2.start();
//
//        try {
//            t1.join();
//            t2.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(c.count);
//
//
//    }
//}
