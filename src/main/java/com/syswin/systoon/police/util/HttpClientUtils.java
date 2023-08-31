package com.syswin.systoon.police.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
public class HttpClientUtils {

  /**
   * @return String
   * @author zhangjian
   * @date 2017年12月28日 上午9:06:52
   * @Description: get请求
   */
  public static String doGet(String url, Map<String, String> param, Map<String, String> headerParams) {

    // 创建Httpclient对象
    CloseableHttpClient httpclient = HttpClients.createDefault();
    String resultString = "";
    CloseableHttpResponse response = null;
    try {
      // 创建uri
      URIBuilder builder = new URIBuilder(url);
      if (param != null) {
        for (String key : param.keySet()) {
          builder.addParameter(key, param.get(key));
        }
      }
      URI uri = builder.build();

      // 创建http GET请求
      HttpGet httpGet = new HttpGet(uri);
      // 创建hander参数列表
      if (headerParams != null) {
        Set<String> keySet = headerParams.keySet();
        if (keySet.size() != 0) {
          for (String string : keySet) {
            httpGet.setHeader(string, headerParams.get(string));
          }
        }
      }
      // 执行请求
      response = httpclient.execute(httpGet);
      // 判断返回状态是否为200
      if (response.getStatusLine().getStatusCode() == 200) {
        resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
      }
    } catch (Exception e) {
      log.error("执行HTTP Get请求时异常 参数:{},url:{}", param, url, e);
    } finally {
      try {
        if (response != null) {
          response.close();
        }
        httpclient.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return resultString;
  }

  /**
   * @return String
   * @author zhangjian
   * @date 2017年12月28日 上午9:08:18
   * @Description: TODO
   */
  public static String doPost(String url, Map<String, String> param, Map<String, String> headerParams) {
    // 创建Httpclient对象
    CloseableHttpClient httpClient = HttpClients.createDefault();
    CloseableHttpResponse response = null;
    String resultString = "";
    try {
      // 创建Http Post请求
      HttpPost httpPost = new HttpPost(url);
      RequestConfig requestConfig = RequestConfig.custom()
          .setConnectTimeout(5000).setConnectionRequestTimeout(5000)
          .setSocketTimeout(5000).build();
      httpPost.setConfig(requestConfig);
      // 创建参数列表
      if (param != null) {
        List<NameValuePair> paramList = new ArrayList<>();
        for (String key : param.keySet()) {
          paramList.add(new BasicNameValuePair(key, param.get(key)));
        }
        // 模拟表单
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, "utf-8");
        httpPost.setEntity(entity);
      }
      // 创建hander参数列表
      if (headerParams != null) {
        Set<String> keySet = headerParams.keySet();
        if (keySet.size() != 0) {
          for (String string : keySet) {
            httpPost.setHeader(string, headerParams.get(string));
          }
        }
      }
      // 执行http请求
      response = httpClient.execute(httpPost);
      resultString = EntityUtils.toString(response.getEntity(), "utf-8");
    } catch (Exception e) {
      log.error("执行HTTP doPost请求时异常 参数:{},url:{}", param, url, e);
    } finally {
      try {
        response.close();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

    return resultString;
  }

  /**
   * @param url 请求地址
   * @param param 请求参数
   * @param headerParams 请求头
   * @param inputStream 文件输入流
   * @param fileName 文件名称
   */
  public static String doPost4Upload(String url, Map<String, String> param, Map<String, String> headerParams,
      InputStream inputStream, String fileName) {
    // 创建Httpclient对象
    CloseableHttpClient httpClient = HttpClients.createDefault();
    CloseableHttpResponse response = null;
    String resultString = "";
    try {
      // 创建Http Post请求
      HttpPost httpPost = new HttpPost(url);
      RequestConfig requestConfig = RequestConfig.custom()
          .setConnectTimeout(5000).setConnectionRequestTimeout(5000)
          .setSocketTimeout(5000).build();
      httpPost.setConfig(requestConfig);
      // 模拟文件
      MultipartEntityBuilder mutiEntity = MultipartEntityBuilder.create();
      mutiEntity.setCharset(Charset.forName("UTF-8"));
      mutiEntity.addBinaryBody("file", inputStream, ContentType.create("multipart/form-data"), fileName);
      // 创建参数列表
      if (param != null) {
        for (String key : param.keySet()) {
          mutiEntity.addTextBody(key, param.get(key));
        }
      }
      httpPost.setEntity(mutiEntity.build());

      // 创建hander参数列表
      if (headerParams != null) {
        Set<String> keySet = headerParams.keySet();
        if (keySet.size() != 0) {
          for (String string : keySet) {
            httpPost.setHeader(string, headerParams.get(string));
          }
        }
      }
      // 执行http请求
      response = httpClient.execute(httpPost);
      resultString = EntityUtils.toString(response.getEntity(), "utf-8");
    } catch (Exception e) {
      log.error("执行HTTP doPost4Upload请求时异常 参数:{},url:{}", param, url, e);
    } finally {
      try {
        response.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return resultString;
  }


  /**
   * @return String
   * @author zhangjian
   * @date 2017年12月28日 上午9:08:54
   * @Description: TODO
   */
  public static String doPostJson(String url, String json, Map<String, String> headerParams) {
    // 创建Httpclient对象
    CloseableHttpClient httpClient = HttpClients.createDefault();
    CloseableHttpResponse response = null;
    String resultString = "";
    try {
      // 创建Http Post请求
      HttpPost httpPost = new HttpPost(url);
      RequestConfig requestConfig = RequestConfig.custom()
          .setConnectTimeout(2000).setConnectionRequestTimeout(2000)
          .setSocketTimeout(2000).build();
      //httpPost.setConfig(requestConfig);
      // 创建请求内容
      StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
      httpPost.setEntity(entity);
      // 创建hander参数列表
      if (headerParams != null) {
        Set<String> keySet = headerParams.keySet();
        if (keySet.size() != 0) {
          for (String string : keySet) {
            httpPost.setHeader(string, headerParams.get(string));
          }
        }
      }
      // 执行http请求
      response = httpClient.execute(httpPost);
      resultString = EntityUtils.toString(response.getEntity(), "utf-8");
    } catch (Exception e) {
      log.error("执行HTTP doPostJson请求时异常 参数:{},url:{}", json, url, e);
    } finally {
      try {
        response.close();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

    return resultString;
  }

  /**
   * @return String
   * @author zhangjian
   * @date 2017年12月28日 上午9:09:15
   * @Description: TODO
   */
  public static String doPut(String url, Map<String, String> param, Map<String, String> headerParams) {
    // 创建Httpclient对象
    CloseableHttpClient httpClient = HttpClients.createDefault();
    CloseableHttpResponse response = null;
    String resultString = "";
    try {
      // 创建Http Post请求
      HttpPut httpPut = new HttpPut(url);
      RequestConfig requestConfig = RequestConfig.custom()
          .setConnectTimeout(5000).setConnectionRequestTimeout(5000)
          .setSocketTimeout(5000).build();
      httpPut.setConfig(requestConfig);
      // 创建参数列表
      if (param != null) {
        List<NameValuePair> paramList = new ArrayList<>();
        for (String key : param.keySet()) {
          paramList.add(new BasicNameValuePair(key, param.get(key)));
        }
        // 模拟表单
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
        entity.setContentType("application/x-www-form-urlencoded");
        httpPut.setEntity(entity);

      }
      // 创建hander参数列表
      if (headerParams != null) {
        Set<String> keySet = headerParams.keySet();
        if (keySet.size() != 0) {
          for (String string : keySet) {
            httpPut.setHeader(string, headerParams.get(string));
          }
        }
      }
      // 执行http请求
      response = httpClient.execute(httpPut);
      resultString = EntityUtils.toString(response.getEntity(), "utf-8");
    } catch (Exception e) {
      log.error("执行HTTP doPut请求时异常 参数:{},url:{}", param, url, e);
    } finally {
      try {
        response.close();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

    return resultString;
  }

  /**
   * @return String
   * @author zhangjian
   * @date 2017年12月28日 上午9:09:48
   * @Description: TODO
   */
  public static String doPutJson(String url, String json, Map<String, String> headerParams) {
    // 创建Httpclient对象
    CloseableHttpClient httpClient = HttpClients.createDefault();
    CloseableHttpResponse response = null;
    String resultString = "";
    try {
      // 创建Http Post请求
      HttpPut httpPut = new HttpPut(url);
      RequestConfig requestConfig = RequestConfig.custom()
          .setConnectTimeout(5000).setConnectionRequestTimeout(5000)
          .setSocketTimeout(5000).build();
      httpPut.setConfig(requestConfig);
      StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
      httpPut.setEntity(stringEntity);
      // 创建hander参数列表
      if (headerParams != null) {
        Set<String> keySet = headerParams.keySet();
        if (keySet.size() != 0) {
          for (String string : keySet) {
            httpPut.setHeader(string, headerParams.get(string));
          }
        }
      }
      // 执行http请求
      response = httpClient.execute(httpPut);
      resultString = EntityUtils.toString(response.getEntity(), "utf-8");
    } catch (Exception e) {
      log.error("执行HTTP doPutJson请求时异常 参数:{},url:{}", json, url, e);
    } finally {
      try {
        response.close();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

    return resultString;
  }

}
