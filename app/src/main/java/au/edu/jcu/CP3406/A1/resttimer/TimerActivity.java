package au.edu.jcu.CP3406.A1.resttimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TimerActivity extends AppCompatActivity {
    private Timer timer;
    private Handler handler;
    private boolean isRunning;
    private TextView timeText;
    private EditText timeInput;
    private EditText setInput;
    private Button toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        timeText = findViewById(R.id.timeText);
        toggle = findViewById(R.id.toggle);
        timeInput = findViewById(R.id.timeInput);
        setInput = findViewById(R.id.setInput);

        isRunning = false;
        if (savedInstanceState == null) {
            timer = new Timer();
        } else {
            timer = new Timer(savedInstanceState.getString("value"));
            boolean running = savedInstanceState.getBoolean("running");
            if (running) {
                enableTimer();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("value", timer.toString());
        outState.putBoolean("running", isRunning);
    }

    public void timerButtonClicked(View view) {
        if (isRunning) {
            disableTimer();
        } else {
            enableTimer();
        }
        resetTimer();
        updateToggleButton();

    }

    private void resetTimer() {
        timer.setTime(Integer.parseInt(timeInput.getText().toString()));
        Log.d("TIME", timeInput.getText().toString());
        timeText.setText(timer.toString());
        Log.d("TIME", timeText.getText().toString());
    }

    private void enableTimer() {
        isRunning = true;
        handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (isRunning) {
                    if (timer.getMinutes() == 0) {
                        if (timer.getSeconds() == 1) {
                            disableTimer();
                            resetTimer();
                            updateToggleButton();
                            Log.d("TIME", "OUT OF TIME");
                        }
                    }
                    timer.tick();
                    Log.d("TIME", String.format("%s minutes, %s seconds", timer.getMinutes(), timer.getSeconds()));
                    timeText.setText(timer.toString());
                    handler.postDelayed(this, 1000);
                }
            }
        });
    }

    private void disableTimer() {
        isRunning = false;
    }

    private void updateToggleButton() {
        toggle.setText(isRunning ? "Reset" : "GO");
    }
}