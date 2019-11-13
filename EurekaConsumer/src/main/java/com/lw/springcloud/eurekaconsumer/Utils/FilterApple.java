package com.lw.springcloud.eurekaconsumer.Utils;

import com.lw.springcloud.eurekaconsumer.Biz.entity.Apple;

import java.util.ArrayList;
import java.util.List;

public class FilterApple {

    public interface AppleFilter {
        boolean filter(Apple apple);
    }

    public static List<Apple> findApple(List<Apple> apples, AppleFilter appleFilter) {
        List<Apple> list = new ArrayList<>();

        for (Apple apple : apples) {
            if (appleFilter.filter(apple)) {
                list.add(apple);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        List<Apple> list = new ArrayList<>();
        Apple apple1 = new Apple("red", 100);
        Apple apple2 = new Apple("blue", 110);
        Apple apple3 = new Apple("green", 120);
        Apple apple4 = new Apple("red", 128);
        Apple apple5 = new Apple("green", 130);
        Apple apple6 = new Apple("green", 150);
        list.add(apple1);
        list.add(apple2);
        list.add(apple3);
        list.add(apple4);
        list.add(apple5);
        list.add(apple6);

        List<Apple> lambdaResult = findApple(list, (Apple apple) -> {
            return apple.getColor().equals("green");
        });
        System.out.println(lambdaResult);
    }
}
