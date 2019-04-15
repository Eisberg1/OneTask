package com.example;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.po.Book;
import com.example.po.User;
import com.example.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplicationTests {
    @Autowired
    StringRedisTemplate srt;
    @Autowired
    UserService userService;
    private static final String COMPLEX_JSON_STR = "{\"teacherName\":\"crystall\",\"teacherAge\":27,\"course\":{\"courseName\":\"english\",\"code\":1270},\"students\":[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]}";


    @Test
    public void contextLoads() throws Exception {
        //srt.opsForValue().set("a","after");
        //System.out.println(srt.opsForValue().get("a"));
        //User user=new User();
        //user.setUserId(11);
        //user.setUserName("-a");
        //user.setUserPassword("ccc");
        //String s = JSONUtil.object2String(user);
        //System.out.println(s);
        //User u = JSONUtil.string2Object(s, User.class);
        //System.out.println(u);

        //JSONObject jsonObject = JSON.parseObject(COMPLEX_JSON_STR);
        //for (String s :jsonObject.keySet()){
        //    System.out.println(s);
        //}
        //JSONArray students = jsonObject.getJSONArray("students");
        //System.out.println(students.size());
        //System.out.println(students);

        //String s = JSON.toJSONString(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        //System.out.println(s);


        String jsonStr = "{" +
                "\"data\": {" +
                "\"studentList\": [{" +
                "\"scourse\": {" +
                "\"cname\": \"语文,数学,英语\"," +
                "\"cscore\": \"93,95,98\"" +
                "}," +
                "\"sname\": \"张三\"," +
                "\"sage\": \"20\"," +
                "\"sid\": \"101\"" +
                "}," +
                "{" +
                "\"scourse\": {" +
                "\"cname\": \"物理,化学,生物\"," +
                "\"cscore\": \"92,93,97\"" +
                "}," +
                "\"sname\": \"李四\"," +
                "\"sage\": \"30\"," +
                "\"sid\": \"102\"" +
                "}]" +
                "}," +
                "\"resultCode\": \"1\"" +
                "}";
        JSONObject json = JSON.parseObject(jsonStr);
        System.out.println(json);
        Map<String, Object> map = new HashMap();
        map = parseJSON2Map(json);
        System.out.println(map);
        System.out.println(JSON.toJSON(map));

    }
    //将json字符串转换为Map
    public static Map<String, Object> parseJSON2Map(String jsonStr) {
        JSONObject json = JSON.parseObject(jsonStr);
        Map<String, Object> map = parseJSON2Map(json);
        return map;
    }
    //将json对象转换为HashMap
    public static Map<String, Object> parseJSON2Map(JSONObject json) {
        Map<String, Object> map = new HashMap();
        // 最外层解析
        for (Object k : json.keySet()) {
            Object v = json.get(k);
            // 如果内层还是json数组的话，继续解析
            if (v instanceof JSONArray) {
                ArrayList<Map<String, Object>> list = new ArrayList();
                Iterator it = ((JSONArray) v).iterator();
                //((JSONArray)v).size()   如果不改变长度，可以使用for循环，内存按顺序排列的for循环比迭代(for each,iterator)更快;
                while (it.hasNext()) {
                    JSONObject array = (JSONObject) it.next();
                    list.add(parseJSON2Map(array));
                }
                //for (int i=0;i<((JSONArray)v).size();i++){
                //    list.add(parseJSON2Map(((JSONArray)v).getJSONObject(i)));
                //}
                map.put(k.toString(), list);
                // 如果内层是json对象的话，继续解析
            } else if (v instanceof JSONObject) {
                map.put(k.toString(), parseJSON2Map((JSONObject) v));
                // 如果内层是普通对象的话，直接放入map中
            } else {
                map.put(k.toString(), v);
            }

        }
        return map;
    }



    static int count=0;
    static int test=0;
    @Test
    public void f(){
        long s=System.currentTimeMillis();
        System.out.println(go());
        System.out.println(System.currentTimeMillis()-s);
    }
    public int go(){
        while (count<10000000){
            count++;
            if (Thread.currentThread().getStackTrace().length%1000!=0){//最大%1024
                System.out.println("此次未满2000，当前count："+count+"////"+Thread.currentThread().getStackTrace().length);
                go();
                if (Thread.currentThread().getStackTrace().length==33){
                    System.out.println("第"+test+"次释放栈帧");
                    continue;
                }else {
                    return count;//退出当前方法，释放栈帧
                }
            }else {
                test++;
                //如果是对象，就需要记录一下当前方法执行中的参数变化，然后传参再次递归
                System.out.println("第"+test+"次栈帧已经到达2000了,当前count的值:"+count);
                return count;
            }
        }
        return 0;
    }



}
