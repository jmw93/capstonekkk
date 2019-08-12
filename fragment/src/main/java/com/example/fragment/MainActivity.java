package com.example.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.fragment.Gps_btn3.MapsActivity;
import com.example.fragment.Naver_NAVI_btn4.MyFragment3;
import com.example.fragment.language_btn2.lang_fragment;
import com.example.fragment.language_btn2.webViewActivity;
import com.example.fragment.main_View_btn1.MainFragment;
import com.example.fragment.parse_Tour.InformActivity;
import com.example.fragment.parse_Tour.tour_fragment;

public class MainActivity extends AppCompatActivity implements onwebListener{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout button0 = (LinearLayout)findViewById(R.id.button0);
        LinearLayout button1 = (LinearLayout)findViewById(R.id.button1);
        LinearLayout button2 = (LinearLayout)findViewById(R.id.button2);
        LinearLayout button3 = (LinearLayout)findViewById(R.id.button3);
        LinearLayout button4 = (LinearLayout)findViewById(R.id.button4);
        //홈버튼
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                recyclerView.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,new MainFragment()).commit();

            }
        });
//번역기버튼
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                recyclerView.setVisibility(View.GONE);
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,new lang_fragment()).commit();
            }
        });
//관광지파싱버튼
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                recyclerView.setVisibility(View.GONE);
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,new mainViewPagerfragment()).commit();
            }
        });

        // 구글네비게이션 길찾기 버튼
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                recyclerView.setVisibility(View.GONE);
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,new MyFragment3()).commit();
            }
        });

        //SMTP 버튼
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                recyclerView.setVisibility(View.GONE);
                Intent intent = new Intent(MainActivity.this,MapsActivity.class);
                startActivity(intent);

            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,new MainFragment()).commit();
    }


    @Override
    public void sendwebView(String URL) {
        Intent intent = new Intent(this, webViewActivity.class);
        intent.putExtra("URL", URL);
        startActivity(intent);
    } // 메인화면에서 클릭시 웹 액티비티 띄워주는것

    public void sendinfodata(int contentid,int contenttypeid){
        Intent intent = new Intent(getApplicationContext(), InformActivity.class);
        intent.putExtra("contentid",contentid);
        intent.putExtra("contenttypeid",contenttypeid);
        startActivity(intent);
    }
//   public void sendwebView(String Url){
//        Intent intent = new Intent(this,webViewActivity.class);
//        intent.putExtra("URL",Url);
//        startActivity(intent);
//    }

}
