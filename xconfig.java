package xutils.xconfig;

import java.util.*;
import java.io.*;
import java.nio.*;
import java.nio.file.*;
import java.nio.charset.*;

public class xconfig {

        private HashMap<String, String> props = new HashMap<String, String>();
        private HashMap<Integer, String> lines = new HashMap<Integer, String>();
        private HashMap<String, Integer> lineTrace = new HashMap<String, Integer>();
        private String fileDir = "";

        public xconfig(String fileDir){
                this.fileDir = fileDir;

                makeProps();
        }

        public void makeProps(){
                try{
                    int LinesCount = 1;
                    List<String> props = Files.readAllLines(Paths.get(this.fileDir));
                    for(String item:props){
                        lines.put(LinesCount, item);
                        if(!item.startsWith("#") && item.contains("=")){
                                String key=item.substring(0,item.indexOf("="));
                                String value=item.substring(key.length()+1);
                                this.props.put(key,value);
                                this.lineTrace.put(key,LinesCount);
                        }
                        LinesCount++;
                    }
                }catch (NoSuchFileException e){
                        // do nothing
                }catch (IOException e){
                        e.printStackTrace();
                }
        }

        public String get(String key){
                return this.props.get(key);
        }

        public String get(String key, String default_value){
                return this.props.getOrDefault(key, default_value);
        }

        public ArrayList<String> getLines(){
                ArrayList<String> returnArr = new ArrayList<String>();
                for(int i:this.lines.keySet()){
                  returnArr.add(this.lines.get(i));
                }
                return returnArr;
        }

        public void put(String key, String value){
                this.set(key, value);
        }

        public void set(String key, String value){
                this.props.put(key, value);
                if(this.lineTrace.getOrDefault(key,-1)==-1){
                  this.lines.put(this.lines.size()+1, key+"="+value);
                }else{
                  this.lines.put(this.lineTrace.get(key), key+"="+value);
                }
        }

        public void newCommentLine(String comment){
                this.lines.put(this.lines.size()+1, "#"+comment);
        }

        public void save(){
                this.saveTo(this.fileDir);
        }

        public void saveTo(String path){
                try{
                  Path p = Paths.get(path);
                  if(Files.exists(p)){
                    Files.delete(p);
                  }
                  for(String i:getLines()){
                    Files.write(p, new String(i+"\n").getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                  }
                }catch(Exception e){
                  //do nothing
                }
        }

        public Set<String> keySet(){
                return this.props.keySet();
        }

}
