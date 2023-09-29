package org.example;

import java.util.HashMap;
import java.util.Map;
import com.alibaba.fastjson2.JSON;

public class Solution {

    /**
     * 76 最小覆盖子串
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        Map<Character,Integer> counts = new HashMap<Character,Integer>();
        for(char c : t.toCharArray()){
            counts.put(c,counts.getOrDefault(c,0)+1);
        }
        int size = t.length();
        int length = Integer.MAX_VALUE;
        String ans = "";
        for(int start = 0, end = 0; end < s.length(); end++){
            if( counts.containsKey(s.charAt(end))){
                counts.put(s.charAt(end),counts.get(s.charAt(end))-1);
                if(counts.get(s.charAt(end)) >= 0){
                    size--;
                }
            }
            while( start <= end && size == 0 ){
                if( end - start + 1 < length){
                    length = end - start + 1;
                    ans = s.substring(start,end+1);
                }
                if( counts.containsKey(s.charAt(start))){
                    counts.put(s.charAt(start),counts.get(s.charAt(start))+1);
                    if(counts.get(s.charAt(start)) > 0){
                        size++;
                    }
                }
                start++;
            }
        }
        return ans;
    }

}
