package com.course.httpclient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.course.httpclient.bean.ResponseHead;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.util.Locale;
import java.util.ResourceBundle;


public class HttpUtils {

    public static String LOGIN = "/kedu-rbac/login";
    public static String ADD = "/kedu-scm/wireless/purch/workOrder/add";

    public static String token;



    //封装post请求
    public static String post(String url, JSONObject params) throws Exception{

        ResourceBundle bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        //声明一个Client对象，用来进行方法的执行
        DefaultHttpClient client = new DefaultHttpClient();

        //声明一个方法，这个方法就是post方法
        HttpPost post = new HttpPost(bundle.getString("base.url")+url);

        //设置请求头信息 设置header
        post.setHeader("content-type","application/json");
        post.setHeader("Authorization","bearer "+token);
        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(params.toString(),"utf-8");
        post.setEntity(entity);
        //声明一个对象来进行响应结果的存储
        String result;

        //执行post方法
        HttpResponse response = client.execute(post);
        //获取响应结果
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        String pretty = JSON.toJSONString(JSONObject.fromObject(result), SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat);
        //System.out.println(pretty);
        //处理结果，就是判断返回结果是否符合预期
        //将返回的响应结果字符串转化成为json对象
        return pretty;
    }

}
