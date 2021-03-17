package au.edu.jcu.CP3406.A1.resttimer;
import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;

public class Timer {
    private int minutes, seconds;

    Timer() {minutes = seconds = 0;}

    Timer(String timeString) {
        String[] timeParts = timeString.split(":");
        this.minutes = Integer.parseInt(timeParts[0]);
        this.seconds = Integer.parseInt(timeParts[1]);
    }

    void tick(){
        seconds -= 1;
        if (seconds < 1){
            seconds = 59;
            minutes -= 1;
        }
        if (minutes > 1){
            seconds = 59;
        }
    }

    void setTime(int inputSeconds){
        int remainder = inputSeconds % 60;
        this.minutes = (inputSeconds - remainder) / 60;
        this.seconds = remainder;
        Log.d("TIME", String.format("%s minutes, %s seconds", minutes, seconds));
    }

    @SuppressLint("DefaultLocale")
    @NonNull
    @Override
    public String toString() {
        return String.format("%02d:%02d", minutes, seconds);
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

}
