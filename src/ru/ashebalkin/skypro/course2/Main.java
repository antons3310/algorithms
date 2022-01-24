package ru.ashebalkin.skypro.course2;

import ru.ashebalkin.skypro.course2.service.StringList;
import ru.ashebalkin.skypro.course2.service.StringListImpl;

public class Main {

    public static void main(String[] args) {

        StringList text = new StringListImpl();

        text.add("1");
        text.add("2");
        text.add("3");
        text.add("4");
        text.add("5");
        text.add("6");
        text.add("7");

        text.add(0,"8");
        text.set(2,"1111");
        text.remove("1111");

        System.out.println("text.lastIndexOf(\"6\") = " + text.lastIndexOf("6"));
        System.out.println("text.IndexOf(\"6\") = " + text.indexOf("6"));

        System.out.println("text.contains(\"4\") = " + text.contains("44"));

        text.clear();

    }
}
