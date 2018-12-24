package com.example.demo;

import com.example.demo.Ai.AiSimpleService;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

public class AiTest extends AiDemoApplicationTests {

    @Autowired
    private AiSimpleService aiSimpleService;

    final static List<String> contents = Lists.newArrayList(
            "【电耗】基本都是北京市内用车，拥堵严重，平均时速20公里左右，百公里电耗26度，很满意了，适合室内通勤，成本非常低，如果家里有自己的充电桩，使用ES8真的特别方便，通勤舒服，带朋友家人出行空间也大，各种配置齐全，使用成本比我的奥迪S3也就是6分之1",
            "整车整体的驾驶感受，加速直接反应快，底盘舒适性很高，车内空间充裕，配置很高，集运动型和实用性为一体的高性能电动车。同时，蔚来的全方位服务领先业界，真正的让客户省心，舒心，所有事项都以客户为中心考虑，赞。",
            "软件还是有些不太稳定，一些功能没有开放，期待后续FOTA升级到更完善的软件版本",
            "空间是es8比较优...",
            "7人用得少，关键时刻可以应急，但是5人空间太好，比我原来的3系好太多，3系后排空间虽然大，但是坐3个人还是变扭，这个不会，中间也很舒服，全真皮包裹；坐5个人的时候后备箱储物空间超大，这次十一去景德镇地区旅游帮上了大忙。"

    );

    @Test
    public void test() {
        for (String content : contents) {
            aiSimpleService.commentTag(content);
        }
    }

}
