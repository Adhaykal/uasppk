package com.polstat.uasppk;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ClassDetailsActivity extends AppCompatActivity {

    private String[][] class_details1 =
            {
                    {"Name: Adib Sulthon Muammal", "NIM: 222111858", "Roll No: 1", "Mobile No: 081234567890", "IPK: 3.3"},
                    {"Name: Afdatul Chofidah", "NIM: 222111858", "Roll No: 2", "Mobile No: 081234567890", "IPK: 3.3"},
                    {"Name: Aghnia Amalia", "NIM: 222111858", "Roll No: 3", "Mobile No: 081234567890", "IPK: 3.3"},
                    {"Name: Ahmad Diaz Haykal", "NIM: 222111858", "Roll No: 4", "Mobile No: 081234567890", "IPK: 3.3"},
                    {"Name: Anggy Distria Manik", "NIM: 222111858", "Roll No: 5", "Mobile No: 081234567890", "IPK: 3.3"}
            };
    private String[][] class_details2 =
            {
                    {"Name: Archangela Renata Patricia", "NIM: 222111858", "Roll No: 6", "Mobile No: 081234567890", "IPK: 3.3"},
                    {"Name: Arzuda Qolbin Mulya", "NIM: 222111858", "Roll No: 7", "Mobile No: 081234567890", "IPK: 3.3"},
                    {"Name: Atha Juni Riekawaty", "NIM: 222111858", "Roll No: 8", "Mobile No: 081234567890", "IPK: 3.3"},
                    {"Name: Baginda Sinaga", "NIM: 222111858", "Roll No: 9", "Mobile No: 081234567890", "IPK: 3.3"},
                    {"Name: Cindy Septia Trionita", "NIM: 222111858", "Roll No: 10", "Mobile No: 081234567890", "IPK: 3.3"}
            };
    private String[][] class_details3 =
            {
                    {"Name: Dilla Leonyka Putri Dewayani", "NIM: 222111858", "Roll No: 11", "Mobile No: 081234567890", "IPK: 3.3"},
                    {"Name: Dina Yanti Nainggolan", "NIM: 222111858", "Roll No: 12", "Mobile No: 081234567890", "IPK: 3.3"},
                    {"Name: Egi Nawwar Sukma", "NIM: 222111858", "Roll No: 13", "Mobile No: 081234567890", "IPK: 3.3"},
                    {"Name: Falana Rofako Hakam", "NIM: 222111858", "Roll No: 14", "Mobile No: 081234567890", "IPK: 3.3"},
                    {"Name: Farid Akbar Arifandi", "NIM: 222111858", "Roll No: 15", "Mobile No: 081234567890", "IPK: 3.3"}
            };
    private String[][] class_details4 =
            {
                    {"Name: Faris Iqbal Maulana Susanto", "NIM: 222111858", "Roll No: 16", "Mobile No: 081234567890", "IPK: 3.3"},
                    {"Name: Gita Kirana Aprillia", "NIM: 222111858", "Roll No: 17", "Mobile No: 081234567890", "IPK: 3.3"},
                    {"Name: Hans Tikynaro Manurung", "NIM: 222111858", "Roll No: 18", "Mobile No: 081234567890", "IPK: 3.3"},
                    {"Name: Irgi Fahrozi", "NIM: 222111858", "Roll No: 19", "Mobile No: 081234567890", "IPK: 3.3"},
                    {"Name: Kristhyne Panjaitan", "NIM: 222111858", "Roll No: 20", "Mobile No: 081234567890", "IPK: 3.3"}
            };
    private String[][] class_details5 =
            {
                    {"Name: M. Khusen Ali Al Anjabi", "NIM: 222111858", "Roll No: 21", "Mobile No: 081234567890", "IPK: 3.3"},
                    {"Name: Marsay Febrianto", "NIM: 222111858", "Roll No: 22", "Mobile No: 081234567890", "IPK: 3.3"},
                    {"Name: Nisa Wahidatul Hidayah", "NIM: 222111858", "Roll No: 23", "Mobile No: 081234567890", "IPK: 3.3"},
                    {"Name: Sisywa Zahra Indrasiwi", "NIM: 222111858", "Roll No: 24", "Mobile No: 081234567890", "IPK: 3.3"},
                    {"Name: Oktafianto Asset Perdana", "NIM: 222111858", "Roll No: 25", "Mobile No: 081234567890", "IPK: 3.3"}
            };

    TextView tv;
    Button btn;
    String[][] class_details = {};
    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_details);

        tv = findViewById(R.id.textViewMDTitle);
        btn = findViewById(R.id.buttonMDBack);

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if (title != null) {
            if ("Student of 2KS1".equals(title))
                class_details = class_details1;
            else if ("Student of 2KS2".equals(title))
                class_details = class_details2;
            else if ("Student of 2KS3".equals(title))
                class_details = class_details3;
            else if ("Student of 2KS4".equals(title))
                class_details = class_details4;
            else
                class_details = class_details5;
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ClassDetailsActivity.this, FindClassActivity.class));
            }
        });

        list = new ArrayList();
        for (int i = 0; i < class_details.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", class_details[i][0]);
            item.put("line2", class_details[i][1]);
            item.put("line3", class_details[i][2]);
            item.put("line4", class_details[i][3]);
            item.put("line5", class_details[i][4]);
            list.add(item);
        }

        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e}
        );

        ListView lst = findViewById(R.id.listViewMD);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(ClassDetailsActivity.this, TutoringAppointmentActivity.class);
                it.putExtra("text1", title);
                it.putExtra("text2", class_details[i][0]);
                it.putExtra("text3", class_details[i][1]);
                it.putExtra("text4", class_details[i][2]);
                it.putExtra("text5", class_details[i][3]);
                it.putExtra("text6", class_details[i][4]);
                startActivityForResult(it, 1); // Use startActivityForResult
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

            } else if (resultCode == RESULT_CANCELED) {

            }
        }
    }
}