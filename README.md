# Uni-Plugin-SerialPort

**使用此插件必须root** 

基于 [F1ReKing/Android-SerialPort](https://github.com/F1ReKing/Android-SerialPort) 开发的 uni-app 插件，Android平台上的usb串口通信插件，支持串口号、波特率、数据位、校验位、停止位、流控等参数设置，能够控制数据的收发。


## uni-app

将本地插件复制到你的项目中并配置好 mainfest.json，打包自定义基座即可体验  

参考文档 [HbuilderX中使用本地插件](https://nativesupport.dcloud.net.cn/NativePlugin/use/use_local_plugin?id=%e8%8e%b7%e5%8f%96%e6%9c%ac%e5%9c%b0uni-app%e5%8e%9f%e7%94%9f%e6%8f%92%e4%bb%b6)


### 使用方法

设备必须root

#### 引用方式
```javascript
const serialPort = uni.requireNativePlugin('Fvv-UniSerialPort')
```

#### API

//获取设备信息  
**[getAllDeviceList(callback)](#getAllDeviceList)**  
**[getAllDevicePath(callback)](#getAllDevicePath)**  

//设置串口连接信息  
**[setPath(path)](#setPath)**  
**[setBaudRate(baudRate)](#setBaudRate)**  
**[setStopBits(stopBits)](#setStopBits)**  
**[setDataBits(dataBits)](#setDataBits)**  
**[setParity(parity)](#setParity)**  
**[setFlowCon(flowCon)](#setFlowCon)**  
**[setFlags(flags)](#setFlags)**  

//串口操作  
**[open(callback)](#open)**  
**[close()](#close)**  
**[isOpen()](#isOpen)**  

//接收消息，以下3个任选1个即可，多选无效  
**[onMessage(callback,sendCallback)](#onMessage)**  
**[onMessageHex(callback,sendCallback)](#onMessageHex)**  
**[onMessageASCII(callback,sendCallback)](#onMessageASCII)**

//发送消息  
**[sendBytes(bytes)](#sendBytes)**  
**[sendHex(string)](#sendHex)**  
**[sendASCII(string)](#sendASCII)**  

------------

**<span id="getAllDeviceList">获取所有设备列表</span>**  
**<font color=#ef5656 >getAllDeviceList(callback)</font>**  

参数说明  

| 参数 | 类型 | 必填 | 说明 |
| :------------: | :------------: | :------------: | :------------:|
| callback | function | 否 | 回调函数 |

callback(arr)

| 参数 | 类型 | 说明 |
| :------------: | :------------: | :------------: |
| arr | array | 设备列表 |

示例
```javascript
serialPort.getAllDeviceList(res => {
	console.log(res) //设备列表
})
```  
<p>　</p>  

**<span id="getAllDevicePath">获取所有设备路径</span>**   
**<font color=#ef5656 >getAllDevicePath(callback)</font>**  


参数说明

| 参数 | 类型 | 必填 | 说明 |
| :------------: | :------------: | :------------: | :------------:|
| callback | function | 否 | 回调函数 |

callback(arr)

| 参数 | 类型 | 说明 |
| :------------: | :------------: | :------------: |
| arr | array | 路径列表 |

示例
```javascript
serialPort.getAllDevicePath(res => {
	console.log(res) //路径列表
})
```  
<p>　</p>  

**<span id="setPath">设置路径</span>**  
**<font color=#ef5656 >setPath(path)</font>**  

参数说明

| 参数 | 类型 | 说明 |
| :------------: | :------------: | :------------: |
| path | string | 设置路径 |


示例
```javascript
serialPort.setPath('/dev/ttyS1')
```
<p>　</p>  

**<span id="setBaudRate">设置波特率</span>**  
**<font color=#ef5656 >setBaudRate(baudRate)</font>**  

参数说明

| 参数 | 类型 | 说明 |
| :------------: | :------------: | :------------: |
| baudRate | int | 设置波特率，默认115200 |


示例
```javascript
serialPort.setBaudRate(9600)
```
<p>　</p>  

**<span id="setStopBits">设置停止位</span>**  
**<font color=#ef5656 >setStopBits(stopBits)</font>**  

参数说明

| 参数 | 类型 | 说明 |
| :------------: | :------------: | :------------: |
| stopBits | int | 设置停止位 默认值为2 |


示例
```javascript
serialPort.setStopBits(2)
```
<p>　</p>  

**<span id="setDataBits">设置数据位</span>**  
**<font color=#ef5656 >setDataBits(dataBits)</font>**  

参数说明

| 参数 | 类型 | 说明 |
| :------------: | :------------: | :------------: |
| dataBits | int | 设置数据位 默认值为8 |


示例
```javascript
serialPort.setDataBits(8)
```
<p>　</p>  

**<span id="setParity">设置检验位</span>**  
**<font color=#ef5656 >setParity(parity)</font>**  

参数说明

| 参数 | 类型 | 说明 |
| :------------: | :------------: | :------------: |
| parity | int | 设置检验位 默认值为0 |


示例
```javascript
serialPort.setParity(0)
```  
<p>　</p>  

**<span id="setFlowCon">设置流控</span>**  
**<font color=#ef5656 >setFlowCon(flowCon)</font>**  

参数说明

| 参数 | 类型 | 说明 |
| :------------: | :------------: | :------------: |
| flowCon | int | 设置流控 默认值为0 |


示例
```javascript
serialPort.setFlowCon(0)
```
<p>　</p>  

**<span id="setFlags">设置标志</span>**  
**<font color=#ef5656 >setFlags(flags)</font>**  

参数说明

| 参数 | 类型 | 说明 |
| :------------: | :------------: | :------------: |
| flags | int | 设置标志 默认值为0，O_RDWR  读写方式打开 |


示例
```javascript
serialPort.setFlags(0)
```
<p>　</p>  

**<span id="open">打开串口</span>**  
**<font color=#ef5656 >open(callback)</font>**  

参数说明

| 参数 | 类型 | 必填 | 说明 |
| :------------: | :------------: | :------------: | :------------:|
| callback | function | 否 | 回调函数 |

callback(object)

| 参数 | 类型 | 说明 |
| :------------: | :------------: | :------------: |
| object.status | bool | 打开串口结果 |
| object.msg | string | 成功为空，失败为原因 |

示例
```javascript
serialPort.open(res => {
	console.log(res) //设备列表
})
```
<p>　</p>  

**<span id="close">关闭当前串口</span>**  
**<font color=#ef5656 >close()</font>**  

参数说明

无

示例
```javascript
serialPort.close()
```
<p>　</p>  

**<span id="isOpen">获取打开状态</span>**  
**<font color=#ef5656 >isOpen()</font>**  

参数说明

无

返回值

| 参数 | 类型 | 说明 |
| :------------: | :------------: | :------------: |
| isOpen | bool | 当前打开状态 |

示例
```javascript
let isOpen = serialPort.isOpen()
```
<p>　</p>  

**<span id="onMessage">监听消息 - 字节</span>**  
**<font color=#ef5656 >onMessage(callback,sendCallback)</font>**  

参数说明

| 参数 | 类型 | 必填 | 说明 |
| :------------: | :------------: | :------------: | :------------:|
| callback | function | 是 | 回调函数 |
| sendCallback | function | 否 | 回调函数 |

callback(byte)

| 参数 | 类型 | 说明 |
| :------------: | :------------: | :------------: |
| byte | byte | 收到的字节消息 |

sendCallback(byte)

| 参数 | 类型 | 说明 |
| :------------: | :------------: | :------------: |
| byte | byte | 发送的字节消息 |

示例
```javascript
serialPort.onMessage(rec => {
	console.log(rec)
},send => {
	console.log(send)
})
```
<p>　</p>  

**<span id="onMessageHex">监听消息 - 十六进制</span>**  
**<font color=#ef5656 >onMessageHex(callback,sendCallback)</font>**  

参数说明

| 参数 | 类型 | 必填 | 说明 |
| :------------: | :------------: | :------------: | :------------:|
| callback | function | 是 | 回调函数 |
| sendCallback | function | 否 | 回调函数 |

callback(byte)

| 参数 | 类型 | 说明 |
| :------------: | :------------: | :------------: |
| hex | string | 收到的十六进制消息 |

sendCallback(byte)

| 参数 | 类型 | 说明 |
| :------------: | :------------: | :------------: |
| hex | string | 发送的十六进制消息 |

示例
```javascript
serialPort.onMessageHex(rec => {
	console.log(rec)
},send => {
	console.log(send)
})
```
<p>　</p>  

**<span id="onMessageASCII">监听消息 - ASCII</span>**  
**<font color=#ef5656 >onMessageASCII(callback,sendCallback)</font>**  

参数说明

| 参数 | 类型 | 必填 | 说明 |
| :------------: | :------------: | :------------: | :------------:|
| callback | function | 是 | 回调函数 |
| sendCallback | function | 否 | 回调函数 |

callback(byte)

| 参数 | 类型 | 说明 |
| :------------: | :------------: | :------------: |
| ascii | string | 收到的ASCII消息 |

sendCallback(byte)

| 参数 | 类型 | 说明 |
| :------------: | :------------: | :------------: |
| hex | string | 发送的ASCII消息 |

示例
```javascript
serialPort.onMessageASCII(rec => {
	console.log(rec)
},send => {
	console.log(send)
})
```
<p>　</p>  

**<span id="sendBytes">发送字节消息</span>**  
**<font color=#ef5656 >sendBytes(bytes)</font>**  

参数说明

| 参数 | 类型 | 说明 |
| :------------: | :------------: | :------------: |
| bytes | array | 发送的字节消息 |


示例
```javascript
let bytes = []
bytes[0] = 1
bytes[1] = 2
serialPort.sendBytes(bytes)
```
<p>　</p>  

**<span id="sendHex">发送十六进制消息</span>**  
**<font color=#ef5656 >sendHex(string)</font>**  

参数说明

| 参数 | 类型 | 说明 |
| :------------: | :------------: | :------------: |
| string | string | 发送的十六进制消息 |


示例
```javascript
serialPort.sendHex("1AA1")
```
<p>　</p>  

**<span id="sendASCII">发送ASCII消息</span>**  
**<font color=#ef5656 >sendASCII(string)</font>**  

参数说明

| 参数 | 类型 | 说明 |
| :------------: | :------------: | :------------: |
| string | string | 发送的ASCII消息 |


示例
```javascript
serialPort.sendASCII("hello Fvv")
```


## android

已集成离线打包及插件开发环境，可以使用离线打包或生成自定义基座来调试插件和uni-app项目。  

使用android studio导入此工程，run 'app' 即可体验！  

参考文档 [集成uni-app项目测试插件](https://nativesupport.dcloud.net.cn/NativePlugin/course/android?id=%e9%9b%86%e6%88%90uni-app%e9%a1%b9%e7%9b%ae%e6%b5%8b%e8%af%95%e6%8f%92%e4%bb%b6)  

参考文档 [离线打包制作自定义基座](https://ask.dcloud.net.cn/article/35482)

## 学习uni-app原生插件开发

[Android uni-app原生插件开发教程](https://nativesupport.dcloud.net.cn/NativePlugin/course/android)  

