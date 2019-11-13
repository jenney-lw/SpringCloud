package com.lw.springcloud.eurekaclient.utils.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ForEachTest {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        for (Integer i:list) {
            System.out.println(i);
        }
        System.out.println("------------------------------");

        list.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        });
        System.out.println("------------------------------");
    }

}
