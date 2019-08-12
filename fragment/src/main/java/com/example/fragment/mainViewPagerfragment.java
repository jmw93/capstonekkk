package com.example.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class mainViewPagerfragment extends Fragment {


    public mainViewPagerfragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_view_pagerfragment, container, false);
        ViewPager viewPager = view.findViewById(R.id.viewPager);
        MypagerAdapter mypagerAdapter = new MypagerAdapter(getFragmentManager());
        viewPager.setAdapter(mypagerAdapter);
        return view;
    }

}
