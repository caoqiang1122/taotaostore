package com.taotao.common.bean;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Title TODO
 * @Description: TODO
 * @Author caoqiang
 * @Date 2019/4/17 14:48
 * @Version 1.0
 */
public class TestDemo {

    public static void main(String[] args) {
//        crawler("https://sso.asiainfo.com/login?service=https%3A%2F%2Fkaragw.asiainfo.com%2Fxin%2Fsso%2Fcallback%2FeyJzY29wZSI6ImFsbCIsInJlc3BvbnNlX3R5cGUiOiJjb2RlIiwic3RhdGUiOiJvYSIsInJlZGlyZWN0X3VyaSI6Imh0dHA6Ly93b3JrLmFzaWFpbmZvLmNvbSIsImNsaWVudF9pZCI6IjY0YTk0YmY1OGFkMjQ5MTJhNzYyNzk2ZWUyOWMwNGUyIn0%3D");
        crawler("https://hao.360.cn/");
        EnumContant[] enumContants = EnumContant.values();
        for (EnumContant enumContant : enumContants){
            System.out.println(enumContant.name());
            System.out.println(enumContant.ordinal());
            System.out.println(enumContant);
            System.out.println("------------------------------");


            if (enumContant.getCode() == 1){
                System.out.println(enumContant.getDescription());
                System.out.println(enumContant.getMessage());
            }else {
                System.out.println(enumContant.getDescription());
                System.out.println(enumContant.getMessage());
            }
        }

    }

    public static void crawler(String startUrls){
        List<String> listOfPendingUrls = new ArrayList<>();
        List<String> listOfTraversedUrls = new ArrayList<>();

        //将起始的url添加到改该列表中
        listOfPendingUrls.add(startUrls);
        while (!listOfPendingUrls.isEmpty() && listOfPendingUrls.size() <= 100 ){
            //从该列表中移除一个url
            String urlString = listOfPendingUrls.remove(0);
            if (!listOfTraversedUrls.contains(urlString)){
                listOfTraversedUrls.add(urlString);
                System.out.println("Craw==" + urlString);

                for (String s : getSubUrls(urlString)){
                    if (!listOfTraversedUrls.contains(s)){
                        listOfPendingUrls.add(s);
                    }
                }
            }
        }
    }

    /**
     * 读取该url页面，并且对页面中包含的url进行如下操作
     * @param urlString
     * @return
     */
    public static List<String> getSubUrls(String urlString){
        List<String> stringList = new ArrayList<>();
        try {
            URL url = new URL(urlString);
            //从 URL 连接读入的输入流
            Scanner scanner = new Scanner(url.openStream());
            int current = 0;
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                //返回指定字符串在此字符串中第一处出现处的索引，从指定索引开始
                current = line.indexOf("http:",current);
                while (current > 0){
                    int endIndex = line.indexOf("\"",current);
                    if (endIndex > 0){
                        stringList.add(line.substring(current,endIndex));
                        current = line.indexOf("http:",endIndex);
                    }else {
                        current = -1;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringList;
    }
}
