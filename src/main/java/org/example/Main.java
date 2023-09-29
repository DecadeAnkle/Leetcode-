package org.example;


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "aaaaaaaaaaaabbbbbcdd";
        String t = "abcdd";
        System.out.print("result :"+solution.myMinWindow(s, t));
    }
}