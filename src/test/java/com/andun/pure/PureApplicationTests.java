package com.andun.pure;

import com.andun.PureApplication;
import com.andun.platform.dao.DaoTest;
import com.andun.platform.pojo.po.UT;
import com.andun.platform.pojo.vo.UserInfoVo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PureApplication.class)
class PureApplicationTests {
    @Autowired
    DaoTest daoTest;

    Logger logger = LoggerFactory.getLogger(PureApplicationTests.class);

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Test
    void contextLoads() throws Exception {
        /*

        Map<String, Object> indata = new HashMap();
        indata.put("berthstatus", 1);
        indata.put("berthcode", "22333");
        indata.put("parkcode", "6688");
        Map<String, Object> param = new HashMap();
        param.put("indata",indata);

        JSONObject obj = new JSONObject(indata);
        String r = URLEncoder.encode(obj.toString(), "UTF-8");
        String reqUrl = "http://192.168.17.35:8888/getParkInfo?jsonString=" + r;
        System.out.println("请求参数:" + reqUrl);
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        String res = null;
        HttpGet httpGet = new HttpGet(reqUrl);
        System.out.println("executing request" + httpGet.getRequestLine());
        try {
            client = HttpClients.createDefault();
            response = client.execute(httpGet);

            if (response.getStatusLine().getStatusCode() == 200) {

                String result = EntityUtils.toString(response.getEntity());

                System.out.println("executing result---连接正常" + result);
            } else {
                System.out.println("executing result---服务器连接异常");
            }


        } catch (Exception e) {
            System.out.println("Exception================" + e.toString());
        } finally {
            if (response != null) {
                response.close();
            }
            if (client != null) {
                client.close();
            }
        }
         */

        //List<DoctorDetailedInfo> doctorDetailedInfos = daoTest.queryCertainDoctorDetailedInfo(200);
        //System.out.println(doctorDetailedInfos);
        logger.info("bacak run");
        System.out.println(stringRedisTemplate.opsForValue().get("my-key"));

         UserInfoVo userInfoVo = daoTest.selectManagerByAccount("18653186188");
        System.out.println(userInfoVo);

    }
    @Test
    void t1(){


        List<String> dates1 = new ArrayList<>();
        List<String> dates2 = new ArrayList<>();
        //2月天数据 组
        for (int i = 0; i < 6; i++) {
            int t=24+i;
            dates1.add("2020-02-"+""+t+" ");
        }
        //3月天数据
        for (int i = 0; i < 31; i++) {
            int t=1+i;
            dates2.add("2020-03-"+""+t+" ");
        }

        List<String> s1S = new ArrayList<>();
        List<String> s1E = new ArrayList<>();
        //小时容器
        for (int i = 0; i <24 ; i++) {
            int t=0+i;
            if(t<10){
                s1S.add("0"+t+":00:00");
                s1E.add("0"+t+":59:59");
            }
            else{
                s1S.add(t+":00:00");
                s1E.add(t+":59:59");
            }
            t=0;
        }
        //计数器
        int []count = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23};
        //获取2月 数据
        for (int i = 0; i <dates1.size() ; i++) {
            for (int j = 0; j < s1S.size(); j++) {
                String s1 = dates1.get(i)+s1S.get(j);
                String s2 = dates1.get(i)+s1E.get(j);
                int sum = daoTest.DaySum(s1,s2);
                if(0==j){count[0]=count[0]+sum;}if(1==j){count[1]=count[1]+sum;}if(2==j){count[2]=count[2]+sum;}
                if(3==j){count[3]=count[3]+sum;}if(0==j){count[4]=count[4]+sum;}if(5==j){count[5]=count[5]+sum;}
                if(4==j){count[4]=count[4]+sum;}if(5==j){count[5]=count[5]+sum;}if(6==j){count[6]=count[6]+sum;}
                if(7==j){count[7]=count[7]+sum;}if(8==j){count[8]=count[8]+sum;}if(9==j){count[9]=count[9]+sum;}
                if(10==j){count[10]=count[10]+sum;}if(11==j){count[11]=count[11]+sum;}if(11==j){count[11]=count[11]+sum;}
                if(12==j){count[12]=count[12]+sum;}if(13==j){count[13]=count[0]+sum;}if(14==j){count[14]=count[14]+sum;}
                if(15==j){count[15]=count[15]+sum;}if(16==j){count[16]=count[16]+sum;}if(17==j){count[17]=count[17]+sum;}
                if(18==j){count[18]=count[18]+sum;}if(19==j){count[19]=count[19]+sum;}if(20==j){count[20]=count[20]+sum;}
                if(21==j){count[21]=count[21]+sum;}if(22==j){count[22]=count[22]+sum;}if(23==j){count[23]=count[23]+sum;}
            }
        }

        //获取3月 数据
        for (int i = 0; i <dates2.size() ; i++) {
            for (int j = 0; j < s1S.size(); j++) {
                String s1 = dates2.get(i)+s1S.get(j);
                String s2 = dates2.get(i)+s1E.get(j);
                int sum = daoTest.DaySum(s1,s2);
                if(0==j){count[0]=count[0]+sum;}if(1==j){count[1]=count[1]+sum;}if(2==j){count[2]=count[2]+sum;}
                if(3==j){count[3]=count[3]+sum;}if(0==j){count[4]=count[4]+sum;}if(5==j){count[5]=count[5]+sum;}
                if(4==j){count[4]=count[4]+sum;}if(5==j){count[5]=count[5]+sum;}if(6==j){count[6]=count[6]+sum;}
                if(7==j){count[7]=count[7]+sum;}if(8==j){count[8]=count[8]+sum;}if(9==j){count[9]=count[9]+sum;}
                if(10==j){count[10]=count[10]+sum;}if(11==j){count[11]=count[11]+sum;}if(11==j){count[11]=count[11]+sum;}
                if(12==j){count[12]=count[12]+sum;}if(13==j){count[13]=count[0]+sum;}if(14==j){count[14]=count[14]+sum;}
                if(15==j){count[15]=count[15]+sum;}if(16==j){count[16]=count[16]+sum;}if(17==j){count[17]=count[17]+sum;}
                if(18==j){count[18]=count[18]+sum;}if(19==j){count[19]=count[19]+sum;}if(20==j){count[20]=count[20]+sum;}
                if(21==j){count[21]=count[21]+sum;}if(22==j){count[22]=count[22]+sum;}if(23==j){count[23]=count[23]+sum;}
            }
        }

        for (int i = 0; i < count.length; i++) {
            System.out.println(count[i]);
        }
        System.out.println("over");

    }
    @Test
    void  t2(){
        List<String> dates2S = new ArrayList<>();
        List<String> dates2E = new ArrayList<>();
        List<Integer> COUNT = new ArrayList<>();
        //3月天数据
        for (int i = 0; i < 7; i++) {
            int t = 1 + i;
            dates2S.add("2020-04-" + "" + t + " 00:00:00");
            dates2E.add("2020-04-" + "" + t + " 23:59:59");
        }
        for (int i = 0; i <dates2E.size(); i++) {
            COUNT.add(daoTest.dateCount2(dates2S.get(i),dates2E.get(i)));
            System.out.println("dates:"+dates2S.get(i));
            System.out.println("count:"+daoTest.dateCount2(dates2S.get(i),dates2E.get(i)));
        }
        //for (int i = 0; i < COUNT.size(); i++) {
        //    System.out.println(COUNT.get(i));
        //}

    }
    @Test
    void  t3(){
        //月独活
        List<String> userArr = daoTest.dateCount3();
        // 用户 月独活详情
        HashMap<String,List<UT>> UTmap = new HashMap<>();
        HashMap<String,Integer> UTmap2 = new HashMap<>();

        List<String> userArr2 = new ArrayList<>();
        // 活跃天数 =>10的用户
        for (int i = 0; i < userArr.size(); i++) {
            String s = userArr.get(i);
            List<UT> ut = new ArrayList<>();
            ut =  daoTest.UTQuery(s);
            UTmap.put(s,ut);

            if(ut.size()>=10){
                userArr2.add(s);
                String ss = daoTest.userName(s);
                Integer ii = ut.size();
                UTmap2.put(ss,ii);
            }
        }

        for (int i = 0; i < userArr.size(); i++) {
            String s = userArr.get(i);
            String ss = daoTest.userName(s);
            System.out.println("wearID:"+s +" name:"+ss);
            System.out.println(UTmap.get(s));
            System.out.println("---------");
        }

        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=");

        for (int i = 0; i < userArr2.size(); i++) {
            String s = userArr2.get(i);
            String ss = daoTest.userName(s);
            System.out.println("wid:"+s+" name:"+ss);
            System.out.println(UTmap2.get(ss));
            System.out.println("---------");
        }
        System.out.println();
        System.out.println("over");
    }
}
