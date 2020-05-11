package com.example.uniplugin_serialport;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.taobao.weex.bridge.JSCallback;

import uni.fvv.serialport.UniSerialPort;

public class MainActivity extends AppCompatActivity {


    private UniSerialPort uniSerialPort = new UniSerialPort();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void open(View view){
        uniSerialPort.setPath("/dev/ttyS1");
        uniSerialPort.setBaudRate(9600);
        uniSerialPort.open(new JSCallback() {
            @Override
            public void invoke(Object o) {
                Log.i("open",o.toString());
            }

            @Override
            public void invokeAndKeepAlive(Object o) {

            }
        });
        uniSerialPort.onMessageHex(new JSCallback() {
            @Override
            public void invoke(Object o) {
                Log.i("rec",o.toString());
            }

            @Override
            public void invokeAndKeepAlive(Object o) {
                Log.i("rec",o.toString());
            }
        }, new JSCallback() {
            @Override
            public void invoke(Object o) {
                Log.i("send",o.toString());
            }

            @Override
            public void invokeAndKeepAlive(Object o) {
                Log.i("send",o.toString());
            }
        });
    }
    public void send(View view){
        uniSerialPort.sendHex("FF");
    }

    public void close(View view){
        uniSerialPort.close();
        boolean check = uniSerialPort.isOpen();
        Log.i("isOpen",check?"open":"close");
    }
}
