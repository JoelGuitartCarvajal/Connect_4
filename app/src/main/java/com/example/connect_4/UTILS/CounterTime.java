package com.example.connect_4.UTILS;
import android.os.CountDownTimer;

public class CounterTime extends CountDownTimer {
    private long timeToFinish;
    private Board board;

    CounterTime(long millisInFuture, long countDownInterval, Board board) {
        super(millisInFuture, countDownInterval);
        this.board = board;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        this.timeToFinish = millisUntilFinished;
    }

    @Override
    public void onFinish() {
        board.timeToEnd = true;
    }

    public long getTime(){
        return timeToFinish;
    }
}
