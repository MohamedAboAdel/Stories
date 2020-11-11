package com.mohamed.stories;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private android.widget.ListView listView;
    com.mohamed.stories.Database db = new com.mohamed.stories.Database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.mohamed.stories.R.layout.activity_main);
        copyDatabase(this);
        listView = findViewById(com.mohamed.stories.R.id.listView);
        java.io.File file = getApplicationContext().getDatabasePath(db.DB_NAME);
        if (false == file.exists()) {
            db.getReadableDatabase();
            if (copyDatabase(this)) {
                android.widget.Toast.makeText(this, "تم نسخ قاعدة البيانات بنجاح", android.widget.Toast.LENGTH_LONG).show();
            } else {
                android.widget.Toast.makeText(this, "خطأ في نسخ قاعدة البيانات", android.widget.Toast.LENGTH_LONG).show();

            }
        }
        java.util.ArrayList listTitle = db.get_All_Titles();
        android.widget.ArrayAdapter arrayAdapter = new android.widget.ArrayAdapter(this, com.mohamed.stories.R.layout.my_list_story, com.mohamed.stories.R.id.txtTitel, listTitle);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(android.widget.AdapterView<?> parent, android.view.View view, int position, long id) {
                String title = String.valueOf(parent.getItemAtPosition(position));
                android.content.Intent intent = new android.content.Intent(MainActivity.this, Stotry.class);
                intent.putExtra("TITLE", title);
                startActivity(intent);
            }
        });

    }

    public boolean copyDatabase(android.content.Context context) {
        try {
            java.io.InputStream inputStream = context.getAssets().open(db.DB_NAME);
            String outFileName = db.DB_LOCATION + db.DB_NAME;
            java.io.OutputStream outputStream = new java.io.FileOutputStream(outFileName);
            byte[] buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}