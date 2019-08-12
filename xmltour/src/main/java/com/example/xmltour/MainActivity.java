package com.example.xmltour;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ArrayList<Tour> tourlist = new ArrayList<>();
    public Tour tour;
    public URL imgurl;
    String serviceKey;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new Runnable() {
            @Override
            public void run() {
                parsing();
            }
        }).start();
    }

    public ArrayList<Tour> gettourlist() {
        return tourlist;

    }

    public ArrayList<Tour> parsing() {
         serviceKey = "RjzMYQORqJIq4l9YZkCCmV5mTIec%2BdJYC%2BUzK3c2Aogy4I2Y0tZnRI4292OO56Qqr%2FIMajYNHjo5M8Ayz4R05g%3D%3D";
        String urlrequest ="http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList?" +
                "ServiceKey=" +
                serviceKey +
                "&contentTypeId=32&mapX=127.048712&mapY=37.504508&radius=3000&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=P&numOfRows=12&pageNo=1";

        try {
            URL url = new URL(urlrequest);
            InputStream is = url.openStream();
            XmlPullParserFactory parserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = parserFactory.newPullParser();
            xpp.setInput(new InputStreamReader(is,"UTF-8"));

            int eventType = xpp.getEventType();

            //핵심
            while (eventType != XmlPullParser.END_DOCUMENT) {

                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        String startTag = xpp.getName();


                        if(startTag.equals("item")){
                            tour = new Tour();
                            break;
                        }

                        if(startTag.equals("addr1")){
                            xpp.next();
                            Log.d("jmw93",xpp.getText());
                            tour.setAddr1(xpp.getText());
                            break;
                        }
                        if(startTag.equals("title")){
                            xpp.next();
                            Log.d("jmw93",xpp.getText());

                            tour.setTitle(xpp.getText());
                            break;
                        }

                        if(startTag.equals("firstimage")){
                            xpp.next();
                            Log.d("jmw93",xpp.getText());

                            try {
                                imgurl = new URL(xpp.getText());
                                URLConnection conn = imgurl.openConnection();
                                conn.connect();
                                BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
                                Bitmap bitmap = BitmapFactory.decodeStream(bis);
                                bis.close();
                                tour.setBitmap(bitmap);

                            }catch (Exception e){
                                Log.d("jmw93","이미지로딩실패");
                            }
                            break;
                        }



                        break;

                    case XmlPullParser.END_TAG:
                        String endTag =xpp.getName();
                        if(endTag.equals("item")){
                            tourlist.add(tour);

                        }
                        break;

                }//switch문의 끝
                eventType = xpp.next();
            }//for문 끝

        } //try문의 끝
        catch (Exception e){
            Log.e("jmw93",e.toString()+"파싱중오류");
        }

        Log.d("jmw93","끝");
        return tourlist;
    }
}