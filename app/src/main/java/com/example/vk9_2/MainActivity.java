package com.example.vk9_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TimePicker;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity{
    Spinner spinner = null;
    Button timebutton = null;
    CalendarView calendarView = null;
    EditText editText = null;
    ListView listView = null;
    int hour, minute;
    int year, month, day;
    String[] theaterlist = {"Espoo Omena", "Espoo Sello", "Helsinki Itis", "Helsinki Kinopalatsi",
            "Helsinki Maxim", "Helsinki Tennispalatsi", "Vantaa Flamingo", "Jyväskylä Fantasia", "Kuopio Scala",
            "Lahti Kuvapalatsi", "Lappeenranta Strand", "Oulu Plaza", "Pori Promenadi", "Tampere Cine Atlas",
            "Tampere Plevna", "Turku Kinopalatsi"};
    String[] movies = {"movie1", "movie2", "movie3"};

    ArrayAdapter<Theaterinfo> adapter = null;
    ArrayAdapter<String> adapter2 = null;
    ArrayList<Theaterinfo> arrayList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (Spinner) findViewById(R.id.spinner);
        timebutton = (Button) findViewById(R.id.timebutton);
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        editText = (EditText) findViewById(R.id.editText);
        listView = (ListView) findViewById(R.id.listView);

        readXML();
        spinner();
        calendarView();
        listView();
        editText();

    }

    public void spinner(){

        adapter = new ArrayAdapter<Theaterinfo>(this, android.R.layout.simple_spinner_item, arrayList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        //editTheaterInfo();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    /*public void editTheaterInfo(){
        Theaterinfo theaterInfo = (Theaterinfo) spinner.getSelectedItem();
        String name = theaterInfo.getName();
        int ID = theaterInfo.getID();
    } */

    //datepicker??
    public void calendarView(){
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                year=i;
                month=i1+1;
                day=i2;
                //System.out.println(day+"."+month+"."+year);
            }
        });
    }

    public void timePicker(View v){
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                hour = i;
                minute = i1;
                timebutton.setText(String.format(Locale.getDefault(), "%02d:%02d",hour,minute));
            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, hour, minute, true);
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }

    //edittext
    //nimi.setontextchangelastener(new NiMi.OntextchangeListener)
    public void listView(){
        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, movies);
        listView.setAdapter(adapter2);
    }


    public void editText(){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                adapter2.getFilter().filter(editable);
            }
        });
    }

    public void readXML(){
        //kutsutaan mainclass readXML ja saadaan lista elokuvateat.

        arrayList = new ArrayList<Theaterinfo>();
        Mainclass mainclass = new Mainclass();
        arrayList = mainclass.readXML();
    }
}


