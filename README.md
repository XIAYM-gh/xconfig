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
 而不是:

 ```properties
 key = value
 ```

### 方法列表
 - get(key,default) : 返回key的值，如果没有则返回default(default 可选)
 - set(key,value) : 设置key的值，如果有相同key会覆盖
 - remove(key) : 如果存在key，则删除这个key
 - has(key) : 检查是否有这个key 返回 **boolean**
 - save() : 保存到原文件，如果未指定原文件则创建这样的文件: **xconfig-save-(创建时间毫秒).properties**
 - save(path) : 保存到path路径
 - newLine(str) : 向当前的最后一行添加任意内容 (如果是K-V需要下一次加载才能生效)
 - newCommentLine(comment) : 向当前的最后一行添加注释

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
     //x.save("save.properties");

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
