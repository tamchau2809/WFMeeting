package com.example.com08.wfmeeting;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by com08 (22/02/2017).
 */

public class CustomDatePicker extends LinearLayout {

    TextView tvLabel, tvDate;
    SimpleDateFormat dateFormatter;
    private DatePickerDialog mDatePickerDialog;
    public CustomDatePicker(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.custom_date_picker, this);

        tvDate = (TextView)this.findViewById(R.id.tvLabelDatePicker);
        tvLabel = (TextView)this.findViewById(R.id.tvDatePicker);

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        Calendar calendar = Calendar.getInstance();
        mDatePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                tvDate.setText(dateFormatter.format(newDate.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        tvDate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatePickerDialog.show();
            }
        });
    }

    public CustomDatePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
