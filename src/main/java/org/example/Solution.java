package org.example;

import java.util.*;

import com.alibaba.fastjson2.JSON;
import com.mysql.cj.util.StringUtils;

import static java.util.Arrays.binarySearch;

public class Solution {

    /**
     * 739. 每日温度
     * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，
     * 其中 answer[i] 是指对于第 i 天，
     * 下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int [] res = new int[temperatures.length];
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < temperatures.length; i++) {
            while( !stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]){
                int j = stack.pop();
                res[j] = i-j;
            }
            stack.push(i);
        }
        return res;

    }

    /**
     * 704. 二分查找
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0 ||target < nums[0] || target > nums[nums.length-1]){
            return -1;
        }
        //return binarySearch(nums,target,0,nums.length-1);
        int lhs = 0, rhs = nums.length-1;
        while(lhs < rhs){
            int mid = lhs + ( rhs - lhs )/2;
            if( nums[mid] < target ){
                lhs = mid + 1;
            }else {
                rhs = mid;
            }
        }
        return nums[lhs] == target ? lhs : -1;
    }

    /**
     * LCR 051. 二叉树中的最大路径和
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        postOrder(root);
        return result;

    }
    int result = Integer.MIN_VALUE;
    int postOrder(TreeNode root){
        if( root == null )return 0;
        int left = Math.max(postOrder(root.left),0);
        int right = Math.max(postOrder(root.right),0);
        result = Math.max(root.val + left + right,result);
        return root.val + Math.max(left,right);
    }
    /**
     * LCR 007. 三数之和
     * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a ，b ，c ，
     * 使得 a + b + c = 0 ？请找出所有和为 0 且 不重复 的三元组。
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if( nums.length < 3 )return result;
        Arrays.sort(nums);
        for(int left = 0; left < nums.length; left++) {
            while( left != 0 && left < nums.length && nums[left] == nums[left-1]){
                left++;
            }
            int i = left + 1, j = nums.length-1;
            while(i < j){
                int sum = nums[left] + nums[i] + nums[j];
                if( sum == 0 ){
                    result.add(Arrays.asList(nums[left],nums[i],nums[j]));
                    while( i < j && nums[i] == nums[++i]);
                    while( i < j && nums[j] == nums[--j]);
                }else if( sum > 0 ){
                    j--;
                }else{
                    i++;
                }
            }
        }
        return result;
    }

    /**
     * LCR 016. 无重复字符的最长子串
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if( StringUtils.isNullOrEmpty(s) ){
            return 0;
        }
        Map<Character,Integer> counts = new HashMap<Character,Integer>();
        int length = 0;
        for(int start = 0 , end = 0; end < s.length(); end++){
            char c = s.charAt(end);
            if(counts.containsKey(c)){
                counts.put(c,counts.get(c)+1);
            }else{
                counts.put(c,1);
            }
            while( start <= end && counts.get(c) > 1){
                char c1 = s.charAt(start);
                counts.put(c1,counts.get(c1)-1);
                start++;
            }
            length = Math.max(length,end-start+1);
        }
        return length;
    }

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
