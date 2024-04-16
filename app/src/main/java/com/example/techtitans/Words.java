package com.example.techtitans;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Words {
    public static String string;
    private static int countWords = 0;
    private static int countRequiredWords = 0;
    private static ArrayList<String> words = new ArrayList<>();
    private static String[] requiredWords = {"Информационная", "Безопастность", "Интернет", "Мошеники"};

    public Words(String string) {
        this.string = string;
    }
    public static void sords(String ss){
        String word = "";
        for (char ch: ss.toCharArray()) {
            if (ch != ' ' && ch != ',' && ch != '.'){
                word = word + ch;
            }
            else {
                countWords+=1;
                words.add(word);
            }
        }
    }
    public static void success(){
        for (String i: words) {
            if (Stream.of(requiredWords).anyMatch(x -> x == i)){
                countRequiredWords++;
            }
        }
    }
    public static double result(){
        sords(string);
        success();
        return Ai.trainAndPredict(countWords, countRequiredWords);
    }
}
