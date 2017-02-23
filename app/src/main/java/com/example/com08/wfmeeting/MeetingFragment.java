package com.example.com08.wfmeeting;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import us.zoom.sdk.InstantMeetingOptions;
import us.zoom.sdk.JoinMeetingOptions;
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
    private final static String DISPLAY_NAME = "WorldMeeting";

    int[] img = {R.drawable.join, R.drawable.instant, R.drawable.schedule};

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

        String[] text = {getActivity().getString(R.string.join_meeting), getActivity().getString(R.string.start_instant_meeting), getActivity().getString(R.string.schedule_meeting)};
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
                        showDialog();
                        break;
                    case 1:
                        StartInstantMeeting();
                        break;
                    case 2:
                        Intent intent1 = new Intent(getContext(), ScheduleActivity.class);
                        startActivity(intent1);
                    default:
                        break;
                }
            }

        };
    }

    public void showDialog()
    {
        LayoutInflater factory = LayoutInflater.from(getContext());
        @SuppressLint("InflateParams") final View alertDialogView = factory.inflate(R.layout.join_meeting_dialog, null);
        final EditText edMeetingID = (EditText) alertDialogView.findViewById(R.id.edMeetingID);
        final EditText edPass = (EditText) alertDialogView.findViewById(R.id.edMeetingIDPass);
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle("Please Enter Meeting ID:")
                .setView(alertDialogView)
                .setPositiveButton(
                        android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                            }
                        })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .create();
        alertDialog.setCancelable(false);
        alertDialog.show();

        View btnTest = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String meetingNo = edMeetingID.getText().toString().trim();
                String meetingPassword = edPass.getText().toString().trim();

                if(meetingNo.length() == 0) {
                    Toast.makeText(getContext(), "You need to enter a meeting number which you want to join.", Toast.LENGTH_LONG).show();
                }

                ZoomSDK zoomSDK = ZoomSDK.getInstance();

                if(!zoomSDK.isInitialized()) {
                    Toast.makeText(getContext(), "ZoomSDK has not been initialized successfully", Toast.LENGTH_LONG).show();
                    return;
                }

                MeetingService meetingService = zoomSDK.getMeetingService();

                JoinMeetingOptions opts = new JoinMeetingOptions();
                int ret = meetingService.joinMeeting(getContext(), meetingNo, DISPLAY_NAME, meetingPassword, opts);

                Log.e("Chau", "onClickBtnLoginUserJoin, ret=" + ret);
            }
        });
    }

    private void StartInstantMeeting()
    {
        ZoomSDK zoomSDK = ZoomSDK.getInstance();

        if(!zoomSDK.isInitialized()) {
            Toast.makeText(getContext(), "ZoomSDK has not been initialized successfully", Toast.LENGTH_LONG).show();
            return;
        }
        MeetingService meetingService = zoomSDK.getMeetingService();
        InstantMeetingOptions opts = new InstantMeetingOptions();
        int ret = meetingService.startInstantMeeting(getContext(), opts);
        Log.e("StartMeeting", String.valueOf(ret));
    }
}
