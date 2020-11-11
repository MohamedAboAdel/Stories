package com.mohamed.stories;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Stotry extends AppCompatActivity {
    com.mohamed.stories.Database database = new Database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stotry);
        android.widget.TextView textView = findViewById(com.mohamed.stories.R.id.story);
        android.content.Intent intent = getIntent();
        String title = intent.getStringExtra("TITLE");
        String full_Story = database.get_Full_Story(title);
        textView.setText(full_Story);
        this.setTitle(title);
    }
}