package com.example.myapplication.ui.details;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.domain.Note;

import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Calendar;
import java.util.Locale;


public class NoteContentFragment extends Fragment {
    private static final String KEY_NOTE = "KEY_NOTE";


    public static NoteContentFragment getInstance(Note note) {
        NoteContentFragment fragment = new NoteContentFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_NOTE, note);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Note note = getArguments().getParcelable(KEY_NOTE);

        Calendar calendar = note.getCalendar();
        int cYear = calendar.get(Calendar.YEAR);
        int cMonth = calendar.get(Calendar.MONTH);
        int cDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        int cHourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int cMinute = calendar.get(Calendar.MINUTE);

        EditText headNote = view.findViewById(R.id.content_note_head);
        EditText contentNote = view.findViewById(R.id.note_content);
        EditText dateView = view.findViewById(R.id.edit_text_date);
        EditText timeView = view.findViewById(R.id.edit_text_time);
        Button bntDate = view.findViewById(R.id.btn_date);
        Button btnText = view.findViewById(R.id.btn_time);


        bntDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(), (view1, year, month, dayOfMonth) -> {
                    String formatDate = "dd.MM.yyyy";
                    calendar.set(year, month, dayOfMonth);
                    note.setDate(calendar.getTime());

                    updateDateText(dateView, calendar, formatDate);
                }, cYear, cMonth, cDayOfMonth);
                datePickerDialog.show();
            }
        });

        btnText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(requireContext(), (TimePickerDialog.OnTimeSetListener) (view12, hourOfDay, minute) -> {
                    calendar.set(hourOfDay, minute);
                    String formatTime = "hh:mm";
                    note.setDate(calendar.getTime());
                    updateDateText(timeView, calendar,formatTime );
                    },  cHourOfDay, cMinute, false);

                 timePickerDialog.show();
            }
        });


        headNote.setText(note.getHead());
        contentNote.setText(note.getContent());

    }

    private void updateDateText(EditText dateView, Calendar calendar, String formatDate) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatDate, Locale.getDefault());

        dateView.setText(simpleDateFormat.format(calendar.getTime()));
    }


}
