package com.hgs.zjcp;

import com.google.gson.Gson;
import com.hgs.zjcp.data.MobCook;
import com.hgs.zjcp.data.MobCookDetail;
import com.hgs.zjcp.utils.net.NetAction;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testRandom() {
        //for(int i =0;i<50;i++)
        //System.out.println("..."+NetAction.newAction().randomMenuId());//ok
        String s = "{\"msg\":\"success\",\"result\":{\"ctgIds\":[\"0010001009\",\"0010001025\"],\"ctgTitles\":\"汤粥,煮\",\"menuId\":\"00100010090000027149\",\"name\":\"银鱼冬瓜汤\",\"recipe\":{\"img\":\"http://f2.mob.com/null/2015/08/19/1439999320656.jpg\",\"ingredients\":\"[\\\"银鱼冬瓜汤材料：冬瓜，银鱼，香葱。 银鱼冬瓜汤调味料：盐，鸡精。\\\"]\",\"method\":\"[{\\\"img\\\":\\\"http://f2.mob.com/null/2015/08/19/1439999320931.jpg\\\",\\\"step\\\":\\\"1.1、将冬瓜洗净去皮，切薄片。香葱切末。银鱼泡发好。\\\"},{\\\"img\\\":\\\"http://f2.mob.com/null/2015/08/19/1439999321112.jpg\\\",\\\"step\\\":\\\"2.2、坐锅热油，下银鱼炒断生，盛起备用。\\\"},{\\\"img\\\":\\\"http://f2.mob.com/null/2015/08/19/1439999321276.jpg\\\",\\\"step\\\":\\\"3.3、另起锅炒冬瓜，加盐炒入味。\\\"},{\\\"img\\\":\\\"http://f2.mob.com/null/2015/08/19/1439999321543.jpg\\\",\\\"step\\\":\\\"4.4、下银鱼，葱花炒匀。加2碗水，大火烧开，改小火煮10分钟。\\\"},{\\\"img\\\":\\\"http://f2.mob.com/null/2015/08/19/1439999321832.jpg\\\",\\\"step\\\":\\\"5.5、待冬瓜成透明色，加盐，鸡精调味，即可出锅。\\\"}]\",\"sumary\":\"\",\"title\":\"银鱼冬瓜汤怎么做\"},\"thumbnail\":\"http://f2.mob.com/null/2015/08/19/1439999273575.jpg\"},\"retCode\":\"200\"}";
        Gson gson = new Gson();
        MobCook mobCook = gson.fromJson(s, MobCook.class);
        //System.out.println(mobCook);
        MobCookDetail result = mobCook.getResult();
        System.out.println(mobCook.getResult().getCtgTitles()+"   "+mobCook.getResult().getName()+"  "+result.getThumbnail()+"  "+result.getRecipe().getTitle()
        +"   "+result.getRecipe().getIngredients()+"   "+result.getRecipe().getMethod()+"");
    }


}