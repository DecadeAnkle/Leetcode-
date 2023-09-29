package org.example;

import java.util.HashMap;
import java.util.Map;
import com.alibaba.fastjson2.JSON;

public class Solution {

    public String myMinWindow(String s, String t) {
        Map<Character,Integer> counts = new HashMap<>();
        String ans = s;
        for(char c:t.toCharArray()){
            counts.put(c,counts.getOrDefault(c,0)+1);
        }
        int length = t.length();
        for(int start = -1, end = 0; end < s.length(); end++){
            char c = s.charAt(end);
            if( counts.containsKey(c) ){
                if( start == -1 ){
                    start = end;
                }
                counts.put(c,counts.get(c)-1);
                length--;
                while( start <= end && counts.get(c) < 0 ){
                    if(counts.containsKey(s.charAt(start))){
                        counts.put(s.charAt(start),counts.get(s.charAt(start))+1);
                        length++;
                    }
                    start++;
                }
                while( start <= end && !counts.containsKey(s.charAt(start) )){
                    start++;
                }
                if( length == 0 && end - start + 1 <= ans.length()){
                    System.out.print(s.substring(start,end+1)+"\n");
                    ans = s.substring(start,end+1);
                }
            }
        }
        return ans == s ? "" : ans;
    }

}
