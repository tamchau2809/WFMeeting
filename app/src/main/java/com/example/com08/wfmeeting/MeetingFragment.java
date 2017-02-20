package com.example.com08.wfmeeting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import us.zoom.sdk.MeetingOptions;
import us.zoom.sdk.MeetingService;
import us.zoom.sdk.ZoomSDK;

/**
 * Created by com08 (20/02/2017).
 */

public class MeetingFragment extends Fragment
{
    View rootView;
    ListView lvOptions;
    AdapterView.OnItemClickListener listenerLv;
    ListviewCustomAdapter adapter;

    String[] text = {"Tham Gia Cuộc Họp", "Mở Cuộc Họp", "Tạo Lịch Cuộc Họp"};
    int[] img = {R.drawable.join, R.drawable.host, R.drawable.schedule};

    private final static int STYPE = MeetingService.USER_TYPE_ZOOM;
    public final static String USER_ID = "O13ULLASSvCamYNF8Vijtw";
    public final static String ZOOM_TOKEN = "LxSY6J8ehNk2iOXhp40dNMtPOkU0MR9riVre5cDCqWM.BgIgYnYxa1hzdWw1b3Q0dlZTNmlyU0VzUkxNL21ydzRNVHRAYjkyZGY3MzYyZDVjOGY1NjZmMTdiY2ZiMjBkNDgwYjg4NjFlZDA0NWMxN2IyMmJhMGJiZjQ1ZjI1YTM0NzJmNAA";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        setHasOptionsMenu(false);
        rootView = inflater.inflate(R.layout.activity_meeting, container, false);

        initWiget();
        initEvent();

        adapter = new ListviewCustomAdapter(getContext(), text, img);
        lvOptions.setAdapter(adapter);
        lvOptions.setOnItemClickListener(listenerLv);

        return rootView;
    }

    public void initWiget()
    {
        lvOptions = (ListView)rootView.findViewById(R.id.lvOption);
    }

    public void initEvent()
    {
        listenerLv = new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                switch(position)
                {
                    case 0:

                        break;
                    case 1:
                        StartInstantMeeting();
                        break;
                    case 2:

                    default:
                        break;
                }
            }

        };
    }

    private void StartInstantMeeting()
    {

    }
}
