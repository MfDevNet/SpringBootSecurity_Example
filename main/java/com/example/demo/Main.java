package com.example.demo;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> list= Arrays.asList(
          "1","2","3"
        );


        System.out.println(list);

        list.add(list.size(),"4");
        System.out.println(list);

    }
}
