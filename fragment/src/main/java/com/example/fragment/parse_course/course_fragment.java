package com.example.fragment.parse_course;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fragment.MainActivity;
import com.example.fragment.R;
import com.example.fragment.parse_Tour.Adapter;
import com.example.fragment.parse_Tour.Tour;
import com.example.fragment.parse_Tour.XMLPullParserHandler;

import java.util.ArrayList;

public class course_fragment extends Fragment {
    private Context mContext;
    private Activity activity;
    MainActivity mainActivity;
    ArrayList<Course> courselist;

    com.example.fragment.parse_course.Adapter adapter;
    RecyclerView recyclerView;
    com.example.fragment.parse_course.XMLPullParserHandler xmlPullParserHandler;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext= context;
        if(context instanceof Activity){
            activity =(Activity)context;
            mainActivity = (MainActivity)getActivity();
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.course_fragment, container, false);
        recyclerView=view.findViewById(R.id.recyclerView);
        xmlPullParserHandler = new com.example.fragment.parse_course.XMLPullParserHandler();
        adapter = new com.example.fragment.parse_course.Adapter(new com.example.fragment.parse_course.Adapter.OncourseClickListener() {
            @Override
            public void onCourseClicked(Course model) {
                Toast.makeText(mainActivity, "아이템클릭", Toast.LENGTH_SHORT).show();
            }
        });

        courselist = new ArrayList<Course>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

            new Thread(new Runnable() {
            @Override
            public void run() {
                courselist = xmlPullParserHandler.parsing();
                Log.d("jmw93", String.valueOf(courselist.size()));

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setItems(courselist);

                        recyclerView.setAdapter(adapter);

                    }

                });
            }
        }).start();


        return view;
    }
}
