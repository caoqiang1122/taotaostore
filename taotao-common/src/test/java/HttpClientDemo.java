import com.sun.deploy.net.HttpUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

public class HttpClientDemo {

    /**
     * GET请求
     * @throws Exception
     */
    public void testHttpGet() throws Exception{
        // 第一步：把HttpClient使用的jar包添加到工程中。
        // 第二步：创建一个HttpClient的测试类
        // 第三步：创建测试方法。
        // 第四步：创建一个HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 第五步：创建一个HttpGet对象，需要制定一个请求的url
        HttpGet httpGet = new HttpGet("https://www.baidu.com/");

        // 第六步：执行请求。
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

        // 第七步：接收返回结果。HttpEntity对象
        HttpEntity httpEntity = httpResponse.getEntity();

        // 第八步：取响应的内容。
        String html = EntityUtils.toString(httpEntity);
        System.out.println("+++++++++++++++++" + html);

        // 第九步：关闭response、HttpClient
        httpResponse.close();
        httpClient.close();
    }

    public void testHttpPost() throws Exception{
        // 第一步：创建一个httpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 第二步：创建一个HttpPost对象。需要指定一个url
        HttpPost httpPost = new HttpPost("https://www.baidu.com/");

        // 第三步：创建一个list模拟表单，list中每个元素是一个NameValuePair对象
        List<NameValuePair> nameValuePairList = new ArrayList<>();
        nameValuePairList.add(new BasicNameValuePair("name","张三"));
        nameValuePairList.add(new BasicNameValuePair("pass","123456"));

        // 第四步：需要把表单包装到Entity对象中。StringEntity
        StringEntity stringEntity = new UrlEncodedFormEntity(nameValuePairList,"utf-8");
        httpPost.setEntity(stringEntity);

        // 第五步：执行请求。
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

        // 第六步：接收返回结果
        HttpEntity httpEntity = httpResponse.getEntity();
        String html = EntityUtils.toString(httpEntity);
        System.out.println("+++++++++++++++++" + html);

        // 第七步：关闭response、HttpClient
        httpResponse.close();
        httpClient.close();

    }

}
