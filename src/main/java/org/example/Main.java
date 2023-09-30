package org.example;


import com.alibaba.fastjson2.JSON;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "aaaaaaaaaaaabbbbbcdd";
        String t = "abcdd";
        //System.out.print("result :"+solution.minWindow(s, t));

        //三数之和
        int[] nums = {-1,0,1,2,-1,-4};
        System.out.print("three num sum:"+ JSON.toJSON(solution.threeSum(nums)));


    }
}