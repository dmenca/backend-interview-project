package com.dmenca.java.basic.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

public class HelloWorldTest {
    /**
     * 1.定义需要被保护的资源
     * 2.定义保护的规则
     * @param args
     */
    public static void main(String[] args) {
        initFlowRules();
        while (true) {
            // 定义被保护的资源片段
            try (Entry entry = SphU.entry("HelloWorld")) {
                // 被保护的业务逻辑
                System.out.println("hello world");
            } catch (BlockException ex) {
                // 资源访问阻止后，执行的逻辑
                // 处理被流控的逻辑
                System.out.println("blocked!");
            }

            // SphU.entry 需要和 entry.exit()配对使用 否则会导致调用链记录异常
            Entry entry = null;
            try{
                entry = SphU.entry("hhh");
                //被保护的业务逻辑
                System.out.println("hhh");
            }catch (BlockException ex) {
                System.out.println("hhh block exception");
            }finally {
                entry.exit();
            }
        }
    }
    private static void initFlowRules(){
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("HelloWorld");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(20);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }
}
