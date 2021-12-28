## XConfig
Java平台上一个简单的配置支持库

### 特色
 - 灵感来源于 Properties (无任何Properties引用)
 - 允许保留非配置内容(例如注释)
 - 单类，轻量
 - 自由添加注释行

### 需求
 由于使用了 Java New I/O ， 此项目至少需要 Java 8 才能运行。

### 一些限制
 您必须使用这样的行来作为配置，否则无法被正确读取:<br>
 ```properties
 key=value
 ```
 <br>
 而不是:<br>
 ```properties
 key = value
 ```

### 使用
 在项目中引用:<br>
 ```java
 import xutils.xconfig.xconfig;
 ```
 <br>
 简单示例:<br>
 ```java
 import xutils.xconfig.xconfig;

 public class simpletest {
   public static void main(String[] args){
     //创建xconfig 类
     //参数 1 (String path): 配置文件的位置
     xconfig x = new xconfig("test.properties");
     x.set("key1","valueWrong");
     x.put("key2","value2");
     x.set("key1","valueRight");
     x.newCommentLine("这是一条注释~");
     x.save();
     //x.saveTo("save.properties");

     System.out.println(x.get("key1"));
     System.out.println(x.get("key3","key3 不存在!"));
   }
 }
 ```
 <br>
 使用 for/foreach:<br>
 ```java
 for(String item:x.keySet()){
   System.out.println("Key: "+item+" Value":x.get(item));
 }
 ```
