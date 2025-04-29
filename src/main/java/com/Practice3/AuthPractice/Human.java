//package com.Practice3.AuthPractice;
//
//public class Human{
//    int age;
//    String name;
//
//    public Human(int age, String name) {
//        this.age = age;
//        this.name = name;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Human)) return false;
//        Human h = (Human) o;
//        return age == h.age && name.equals(h.name);
//    }
//
//    @Override
//    public int hashCode() {
//        return age + name.hashCode();  // Simple hash
//    }
//
//    @Override
//    public String toString() {
//        return name + " (" + age + ")";
//    }
//
//}
