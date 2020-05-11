package uni.fvv.serialport;

import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

import java.io.File;
import java.util.List;

import me.f1reking.serialportlib.SerialPortHelper;
import me.f1reking.serialportlib.entity.Device;
import me.f1reking.serialportlib.listener.IOpenSerialPortListener;
import me.f1reking.serialportlib.listener.ISerialPortDataListener;
import me.f1reking.serialportlib.listener.Status;

public class UniSerialPort extends WXModule {

    private SerialPortHelper serialPortHelper = new SerialPortHelper();

    //run ui thread
    @JSMethod(uiThread = true)
    public void test(){
        Toast.makeText(mWXSDKInstance.getContext(),"d fvv",Toast.LENGTH_LONG).show();
    }

    //设置串口
    //run ui thread
    @JSMethod(uiThread = true)
    public void setPath(String port){
        this.serialPortHelper.setPort(port);
    }

    //设置波特率
    //run ui thread
    @JSMethod(uiThread = true)
    public void setBaudRate(Integer baudRate){
        this.serialPortHelper.setBaudRate(baudRate);
    }

    //设置停止位
    //run ui thread
    @JSMethod(uiThread = true)
    public void setStopBits(Integer stopBits){
        this.serialPortHelper.setStopBits(stopBits);
    }

    //设置数据位
    //run ui thread
    @JSMethod(uiThread = true)
    public void setDataBits(Integer dataBits){
        this.serialPortHelper.setDataBits(dataBits);
    }

    //设置检验位
    //run ui thread
    @JSMethod(uiThread = true)
    public void setParity(Integer parity){
        this.serialPortHelper.setParity(parity);
    }

    //设置流控
    //run ui thread
    @JSMethod(uiThread = true)
    public void setFlowCon(Integer flowCon){
        this.serialPortHelper.setFlowCon(flowCon);
    }

    //设置标志
    //run ui thread
    @JSMethod(uiThread = true)
    public void setFlags(Integer flags){
        this.serialPortHelper.setFlags(flags);
    }

    //获取所有串口
    //run ui thread
    @JSMethod(uiThread = true)
    public void getAllDeviceList(JSCallback callback){
        List<Device> list = this.serialPortHelper.getAllDevices();
        if(callback != null){
            callback.invoke(list);
        }
    }

    //获取所有路径
    //run ui thread
    @JSMethod(uiThread = true)
    public void getAllDevicePath(JSCallback callback){
        String[] list = this.serialPortHelper.getAllDeicesPath();
        if(callback != null){
            callback.invoke(list);
        }
    }

    //打开串口
    //run ui thread
    @JSMethod(uiThread = true)
    public void open(final JSCallback callback){
        serialPortHelper.setIOpenSerialPortListener(new IOpenSerialPortListener(){
            public void onSuccess(final File device){
                JSONObject data = new JSONObject();
                data.put("status",true);
                data.put("msg","");
                callback.invoke(data);
            }
            public void onFail(final File device, final Status status){
                JSONObject data = new JSONObject();
                data.put("status",false);
                data.put("msg",status);
                callback.invoke(data);
            }
        });
        try{
            serialPortHelper.open();
        }catch (Exception e){
            JSONObject data = new JSONObject();
            data.put("status",false);
            data.put("msg","NO_READ_WRITE_PERMISSION");
            callback.invoke(data);
        }
    }

    //消息监听,字节
    //run ui thread
    @JSMethod(uiThread = true)
    public void onMessage(final JSCallback callback,final JSCallback sendCallback){
        this.serialPortHelper.setISerialPortDataListener(new ISerialPortDataListener() {
            @Override
            public void onDataReceived(byte[] bytes) {
                callback.invokeAndKeepAlive(bytes);
            }

            @Override
            public void onDataSend(byte[] bytes) {
                if(sendCallback != null){
                    sendCallback.invokeAndKeepAlive(bytes);
                }
            }
        });
    }

    //消息监听,十六进制
    //run ui thread
    @JSMethod(uiThread = true)
    public void onMessageHex(final JSCallback callback,final JSCallback sendCallback){
        this.serialPortHelper.setISerialPortDataListener(new ISerialPortDataListener() {
            @Override
            public void onDataReceived(byte[] bytes) {
                callback.invokeAndKeepAlive(byteToHex(bytes));
            }

            @Override
            public void onDataSend(byte[] bytes) {
                if(sendCallback != null){
                    sendCallback.invokeAndKeepAlive(byteToHex(bytes));
                }
            }
        });

    }

    //消息监听,字符串
    //run ui thread
    @JSMethod(uiThread = true)
    public void onMessageASCII(final JSCallback callback,final JSCallback sendCallback){
        this.serialPortHelper.setISerialPortDataListener(new ISerialPortDataListener() {
            @Override
            public void onDataReceived(byte[] bytes) {
                callback.invokeAndKeepAlive(new String(bytes));
            }

            @Override
            public void onDataSend(byte[] bytes) {
                if(sendCallback != null){
                    sendCallback.invokeAndKeepAlive(new String(bytes));
                }
            }
        });
    }

    //字节转16进制
    //run ui thread
    @JSMethod(uiThread = false)
    public static String byteToHex(byte[] bytes){
        String strHex = "";
        StringBuilder sb = new StringBuilder("");
        for (int n = 0; n < bytes.length; n++) {
            strHex = Integer.toHexString(bytes[n] & 0xFF);
            sb.append((strHex.length() == 1) ? "0" + strHex : strHex); // 每个字节由两个字符表示，位数不够，高位补0
        }
        return sb.toString().trim();
    }

    //串口状态
    //run ui thread
    @JSMethod(uiThread = false)
    public boolean isOpen(){
        return this.serialPortHelper.isOpen();
    }

    //关闭串口
    //run ui thread
    @JSMethod(uiThread = true)
    public void close(){
        this.serialPortHelper.close();
    }

    //发送字节消息
    //run ui thread
    @JSMethod(uiThread = true)
    public void sendBytes(byte[] bytes){
        this.serialPortHelper.sendBytes(bytes);
    }

    //发送十六进制消息
    //run ui thread
    @JSMethod(uiThread = true)
    public void sendHex(String hex){
        this.serialPortHelper.sendHex(hex);
    }

    //发送字符串消息
    //run ui thread
    @JSMethod(uiThread = true)
    public void sendASCII(String string){
        this.serialPortHelper.sendTxt(string);
    }

}
