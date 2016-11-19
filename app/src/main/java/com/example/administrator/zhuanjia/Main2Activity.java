package com.example.administrator.zhuanjia;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    public static final int SHOW_RESPONSE = 0;
    private EditText etcityname;
    private Button sousuo;
    private TextView city,
    pm25, chuanyi,xiche,ganmao,yundong,ziwaixian,temperature,wind,date;
    private TextView diertian,tdiertian,
    disantian,tdisantian,disitian,tdisitian;
    private ImageView imgtop,imdiertian,imdisantian,imdisitian;
    private TextView wdiertian,wdisantian,wdisitian;

    private Handler handler=new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_RESPONSE:
                    String response = (String) msg.obj; // 在这里进行UI操作，将结果显示到界面上
                    Util util = new Util();
                    try {
                        List<Map<String, Object>> all = util
                                .getInformation(response);
                        Iterator<Map<String, Object>> iterator = all.iterator();
                        while (iterator.hasNext()) {
                            Map<String, Object> map = iterator.next();
                            //Log.d("天气", map.get("weather").toString());
                            city.setText(map.get("location")
                                    .toString());
                            backgrand(imgtop,map.get("weather")
                                    .toString());
                            pm25.setText(map.get("pm25").toString());
                            chuanyi.setText(map.get("chuanyi").toString());
                            xiche.setText(map.get("xiche").toString());
                            ganmao.setText(map.get("ganmao").toString());
                            yundong.setText(map.get("yundong").toString());
                            ziwaixian.setText(map.get("ziwaixian").toString());
                            wind.setText(map.get("wind").toString());
                            temperature.setText(map.get("temperature").toString());
                            date.setText(map.get("date").toString());

                            diertian.setText(map.get("date1").toString());
                            tdiertian.setText(map.get("temperature1").toString());
                            disantian.setText(map.get("date2").toString());
                            tdisantian.setText(map.get("temperature2").toString());
                            disitian.setText(map.get("date3").toString());
                            tdisitian.setText(map.get("temperature3").toString());
                            wdiertian.setText(map.get("weather1").toString());
                            wdisantian.setText(map.get("weather2").toString());
                            wdisitian.setText(map.get("weather3").toString());
                            backgrand(imdiertian,map.get("weather1")
                                    .toString());
                            backgrand(imdisantian,map.get("weather2")
                                    .toString());
                            backgrand(imdisitian,map.get("weather3")
                                    .toString());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            } }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city);
        sousuo = (Button) findViewById(R.id.sousuo);
        etcityname= (EditText) findViewById(R.id.etcityname);
        city = (TextView) findViewById(R.id.city);
        pm25 = (TextView) findViewById(R.id.pm25);
        chuanyi = (TextView) findViewById(R.id.chuanyi);
        xiche = (TextView) findViewById(R.id.xiche);
        ganmao = (TextView) findViewById(R.id.ganmao);
        yundong = (TextView) findViewById(R.id.yundong);
        ziwaixian = (TextView) findViewById(R.id.ziwaixian);
        wind = (TextView) findViewById(R.id.wind);
        temperature = (TextView) findViewById(R.id.temperature);
        date = (TextView) findViewById(R.id.date);

        diertian = (TextView) findViewById(R.id.diertian);
        disantian = (TextView) findViewById(R.id.disantian);
        disitian = (TextView) findViewById(R.id.disitian);
        tdiertian = (TextView) findViewById(R.id.tdiertian);
        tdisantian = (TextView) findViewById(R.id.tdisantian);
        tdisitian = (TextView) findViewById(R.id.tdisitian);
        wdiertian = (TextView) findViewById(R.id.wdiertian);
        wdisantian = (TextView) findViewById(R.id.wdisantian);
        wdisitian = (TextView) findViewById(R.id.wdisitian);
        imgtop= (ImageView) findViewById(R.id.imgtop);
        imdiertian= (ImageView) findViewById(R.id.imdiertian);
        imdisantian= (ImageView) findViewById(R.id.imdisantian);
        imdisitian= (ImageView) findViewById(R.id.imdisitian);
        sousuo.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sousuo) {
            sendRequestWithHttpURLConnection();
        }
    }

    private void sendRequestWithHttpURLConnection() { // 开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                StringBuilder cityname=new StringBuilder();
                try {
                    cityname.append("http://api.map.baidu.com/telematics/v3/weather?location=").
                            append(URLEncoder.encode(etcityname.getText().toString(),"UTF-8")).
                            append("&mcode=20:40:90:AE:E8:19:FD:95:21:F2:CD:D5:72:D3:EE:CB:38:F9:B7:9C;com.example.administrator.zhuanjia&output=json&ak=P0SE4gI053GYaVRONbWwR8Bz13a5OwiC");
                    URL url = new URL(cityname.toString());
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream(); // 下面对获取到的输入流进行读取
                     BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    Message message = new Message();
                    message.what = SHOW_RESPONSE;
                   // 将服务器返回的结果存放到Message中
                    message.obj = response.toString();
                    handler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    //if (connection != null) {
                      //  connection.disconnect();
                    //}
                }
            }
        }).start();
    }
    public void backgrand(ImageView imageView,String weather){
        if(weather.contains("雪")){
         imageView.setBackgroundResource(R.drawable.daxue);
        }else if(weather.contains("雨")){
            imageView.setBackgroundResource(R.drawable.dayu);
        }else if(weather.contains("晴")){
            imageView.setBackgroundResource(R.drawable.qing);
        }else {
            imageView.setBackgroundResource(R.drawable.dayduoyun);
        }
    }


                }
