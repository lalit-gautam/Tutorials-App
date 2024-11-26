package com.tutorials.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Optional;

public class OptionalTest {
    public static void main(String[] args) {

        System.out.println(getTempClass(new TempClass(45, null)));
        System.out.println(getTempClass(new TempClass(45, "Test-1")));

    }

    public static String getTempClass(TempClass _tempClass){
        Optional<TempClass> tempClass1 = Optional.ofNullable(TempClass.builder().id(_tempClass.getId()).name(_tempClass.getName()).build());
        return tempClass1.map(TempClass::getName).orElse("Noo values present");
    }
}

@Builder
@Getter
@AllArgsConstructor
class TempClass {
    private int id;
    private String name;
}
