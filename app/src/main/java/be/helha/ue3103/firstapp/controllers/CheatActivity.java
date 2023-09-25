package be.helha.ue3103.firstapp.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import be.helha.ue3103.firstapp.R;

public class CheatActivity extends AppCompatActivity {

    public static final String EXTRA_ANSWER_SHOWN = "EXTRA_ANSWER_SHOWN";
    public static String ANSWER_EXTRA = "ANSWER_EXTRA";
    private boolean mAnswerIsTrue;
    private TextView mAnswerTextView;
    private Button mCheatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mAnswerIsTrue = getIntent().getBooleanExtra(ANSWER_EXTRA, false);
        mAnswerTextView = findViewById(R.id.answer_text_view);
        mCheatButton = findViewById(R.id.show_answer_button);

        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnswerTextView.setText(mAnswerIsTrue ? R.string.true_button : R.string.false_button);

                Intent data = new Intent();
                data.putExtra(EXTRA_ANSWER_SHOWN, true);
                CheatActivity.this.setResult(RESULT_OK, data);
            }
        });
    }
}