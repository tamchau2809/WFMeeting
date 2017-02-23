package com.example.com08.wfmeeting;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import us.zoom.sdk.PreMeetingServiceListener;

/**
 * Created by com08 (22/02/2017).
 */

public class ScheduleActivity extends Activity implements PreMeetingServiceListener {

    EditText edMeetingName;
    TextView tvDate, tvTimeFrom, tvTimeTo;
    View lnrDatePicker, lnrTimePicker;
    Switch swHostVideo, swAttendeeVideo, swCanJoin, swPMI;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        initWidget();
        initListener();

    }

    private void initWidget()
    {
        edMeetingName = (EditText)findViewById(R.id.edScheduleName);
        tvDate = (TextView)findViewById(R.id.tvDate);
        tvTimeFrom = (TextView)findViewById(R.id.tvTimeFrom);
        tvTimeTo = (TextView)findViewById(R.id.tvTimeTo);
        lnrDatePicker = findViewById(R.id.lnrDate);
        lnrTimePicker = findViewById(R.id.lnrTimeFrom);
        swHostVideo = (Switch)findViewById(R.id.swHostVideo);
    }

    private void initListener()
    {
        swHostVideo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked)
                {
                    Toast.makeText(getBaseContext(), "asdas", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onClickDatePicker(View v)
    {
        final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog mDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                tvDate.setText(dateFormatter.format(newDate.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        mDatePickerDialog.show();
    }

    public void onClickTimeFrom(View v)
    {
        timePickerDialog(tvTimeFrom);
    }

    public void onClickTimeTo(View v)
    {
        timePickerDialog(tvTimeTo);
    }

    private void timePickerDialog(final TextView tv)
    {
        Calendar mCurrentTime = Calendar.getInstance();
        int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mCurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                tv.setText( selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    @Override
    public void onListMeeting(int i) {

    }

    @Override
    public void onScheduleMeeting(int i) {

    }

    @Override
    public void onUpdateMeeting(int i) {

    }

    @Override
    public void onDeleteMeeting(int i) {

    }
}
