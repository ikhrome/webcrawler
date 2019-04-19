package com.khromov;

public class Main {

    public static void main(String[] args) {
        Visitor visitor = new Visitor();
        visitor.search("https://e1.ru");
    }
}
