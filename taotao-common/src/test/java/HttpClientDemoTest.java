import org.junit.Test;

import static org.junit.Assert.*;

public class HttpClientDemoTest {

    @Test
    public void testHttpGet() throws  Exception{
        HttpClientDemo httpClientDemo = new HttpClientDemo();
        httpClientDemo.testHttpGet();
    }

    @Test
    public void testHttpPost() throws Exception{
        HttpClientDemo httpClientDemo = new HttpClientDemo();
        httpClientDemo.testHttpPost();
    }
}