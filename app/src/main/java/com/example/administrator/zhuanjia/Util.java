package com.example.administrator.zhuanjia;

/**
 * Created by Administrator on 2016/11/16.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class Util {

    public List<Map<String, Object>> getInformation(String jonString)
            throws Exception {

       /* JSONObject jsonObject = new JSONObject(jonString);
       // JSONObject retData = jsonObject.getJSONObject("retData");
        List<Map<String, Object>> all = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("currentCity", jsonObject.getString("currentCity"));
      //  map.put("weather", retData.optString("weather"));
        //map.put("temp", retData.optString("temp"));
        //map.put("l_temp", retData.optString("l_tmp"));
       // map.put("h_temp", retData.optString("h_tmp"));
        all.add(map);

        return all;*/
        JSONObject dataOfJson =new JSONObject(jonString);
        JSONArray results=dataOfJson.getJSONArray("results");
        JSONObject results0=results.getJSONObject(0);
        JSONArray index = results0.getJSONArray("index");

        JSONObject index0 = index.getJSONObject(0);//穿衣
        JSONObject index1 = index.getJSONObject(1);//洗车
        JSONObject index2 = index.getJSONObject(2);//感冒
        JSONObject index3 = index.getJSONObject(3);//运动
        JSONObject index4 = index.getJSONObject(4);//紫外线强度

        JSONArray weather_data = results0.getJSONArray("weather_data");//weather_data中有四项
        JSONObject weather_data0 = weather_data.getJSONObject(0);//今天
        JSONObject weather_data1 = weather_data.getJSONObject(1);//明天
        JSONObject weather_data2 = weather_data.getJSONObject(2);//后天
        JSONObject weather_data3 = weather_data.getJSONObject(3);//大后天
        List<Map<String, Object>> all = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("location",results0.getString("currentCity"));
        map.put("pm25",results0.getString("pm25"));
        map.put("chuanyi",index0.getString("des"));
        map.put("xiche",index1.getString("des"));
        map.put("ganmao",index2.getString("des"));
        map.put("yundong",index3.getString("des"));
        map.put("ziwaixian",index4.getString("des"));
        map.put("date",weather_data0.getString("date"));
        map.put("weather",weather_data0.getString("weather"));
        map.put("wind",weather_data0.getString("wind"));
        map.put("temperature",weather_data0.getString("temperature"));
        map.put("date1",weather_data1.getString("date"));
        map.put("weather1",weather_data1.getString("weather"));
        map.put("temperature1",weather_data1.getString("temperature"));
        map.put("date2",weather_data2.getString("date"));
        map.put("weather2",weather_data2.getString("weather"));
        map.put("temperature2",weather_data2.getString("temperature"));
        map.put("date3",weather_data3.getString("date"));
        map.put("weather3",weather_data3.getString("weather"));
        map.put("temperature3",weather_data3.getString("temperature"));
        all.add(map);
        return all;

    }
    public String getinformation(String jonString) throws Exception{
        JSONObject dataOfJson =new JSONObject(jonString);
        JSONArray results=dataOfJson.getJSONArray("results");
        JSONObject results0=results.getJSONObject(0);
        String location = results0.getString("currentCity");
        return location;
    }

}
