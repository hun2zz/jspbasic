package com.jsp.chap05;

import java.util.List;

public class Test {

    public static void main(String[] args) {

        JdbcBasic jdbcBasic = new JdbcBasic();
//        jdbcBasic.insert(new Person(2,"홍길동" , 30));
//        jdbcBasic.insert(new Person(3,"이순신" , 30));
//        jdbcBasic.insert(new Person(4,"김청당" , 30));
//        jdbcBasic.insert(new Person(5,"청심이" , 30));
//        jdbcBasic.update(3, "김도라에몽", 90);

        List<Person> people =  jdbcBasic.findAll();
        System.out.println("people = " + people);
    }
}
