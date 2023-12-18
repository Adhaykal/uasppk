package com.polstat.uasppk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MeetingDetailsActivity extends AppCompatActivity {

    private String[][] meeting_details = {};

    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_details);

        btn = findViewById(R.id.buttonMDBack);
        lst = findViewById(R.id.listViewMD);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MeetingDetailsActivity.this, HomeActivity.class));
            }
        });

        Database db = new Database(getApplicationContext(), "uasppk", null, 1);
        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "").toString();
        ArrayList dbData = db.getAppointmentData(username);

        meeting_details = new String[dbData.size()][];
        for (int i = 0; i < meeting_details.length; i++) {
            meeting_details[i] = new String[7]; // Menambahkan 2 elemen baru untuk date dan time
            String arrData = dbData.get(i).toString();
            String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));
            meeting_details[i][0] = strData[0];
            meeting_details[i][1] = strData[1];
            meeting_details[i][2] = strData[2];
            meeting_details[i][3] = strData[3];
            meeting_details[i][4] = strData[4];
            meeting_details[i][5] = strData[5]; // Menambahkan date
            meeting_details[i][6] = strData[6]; // Menambahkan time
        }

        list = new ArrayList();
        for (int i = 0; i < meeting_details.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", meeting_details[i][0]);
            item.put("line2", meeting_details[i][1]);
            item.put("line4", meeting_details[i][2]);
            item.put("line3", meeting_details[i][3]);
            item.put("line5", meeting_details[i][4]);
            item.put("line6", meeting_details[i][5]);
            item.put("line7", meeting_details[i][6]);
            list.add(item);
        }

        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5", "line6", "line7"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e, R.id.line_f, R.id.line_g}
        );


        lst.setAdapter(sa);
    }
}
