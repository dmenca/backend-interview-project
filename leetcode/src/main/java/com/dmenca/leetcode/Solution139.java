package com.dmenca.leetcode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution139 {
    // 以动态规划的思路，dp[i]表示s前i个字符串s[0,i-1]是否能被空格拆分若干个字典中出现的单词
    // 从前往后计算考虑转移方程，每次转移的时候我们需要枚举包含位置 i−1i-1i−1 的最后一个单词，看它是否出现在字典中以及除去这部分的字符串是否合法即可
    // 因此可以将dp[i]转换成 d[i]=d[j] && check(s[j,i-1])
    // 在代码中每次向后计算一个位置的单词的dp时 都需要从0遍历到当前位置去判断d[j] && check(s[j,i-1])是否能够出现合法的 如果可以，证明当前
    // d[j]可以访问
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> sets = new HashSet(wordDict);
        boolean dp[] = new boolean[s.length()+1];
        dp[0]=true;
        // 外循环去计算每个dp的值
        for(int i=1;i<=s.length();i++){
            // 内循环针对每个当前的单词位置，都要从头开始遍历 通过每个位置的dp 跟check字典是否包含j到i的字符串来判断是否满足合法
            for(int j=0;j<i;j++){
                if(dp[j]==true && sets.contains(s.substring(j,i))){
                    dp[i]=true;
                }
            }
        }
        return dp[s.length()];
    }
}
