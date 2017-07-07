package com.project.android.marrigelaws;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Created by 13zqn on 2017/7/3.
 */

public class HttpUtil {
    public static HttpClient httpClient = new DefaultHttpClient();
    public static final String BASE_URLOne = "Http://";
    public static final String BASE_URLTwo = "Http://192.168.1.100:8080/dianshang/select_servlet";
    public static final String BASE_URLThree = "Http://10.129.4.45/";
    public static final String BASE_URLFour = "Http://192.168.1.100:8080/dianshang/register_servlet";

    public static String postRequest(final String url,
                                     final Map<String,String> rawParams)throws Exception
    {
        FutureTask<String> task = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                //创建Post对象
                HttpPost post = new HttpPost(url);


                List<NameValuePair> Params = new ArrayList<NameValuePair>();//若传递参数较多的话可以对传递的参数进行封装
                for (String key : rawParams.keySet())
                {
                    //封装请求参数
                    Params.add(new BasicNameValuePair(key,rawParams.get(key)));
                }

                post.setEntity(new UrlEncodedFormEntity(Params,"utf-8"));
                HttpResponse httpResponse = httpClient.execute(post);
                if (httpResponse.getStatusLine().getStatusCode() == 200)
                {
                    String result = EntityUtils.toString(httpResponse.getEntity());
                    return result;
                }
                return null;
            }
        });
        new Thread(task).start();
        return task.get();
    }
}

