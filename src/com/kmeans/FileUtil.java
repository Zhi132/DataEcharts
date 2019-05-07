package com.kmeans;

import java.util.ArrayList;
import java.util.HashMap;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.net.URL;
//import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.Model.User;


public class FileUtil{
//    private String fileName;
//    private File file;
    
//    public FileUtil(String fileName){
//        this.fileName = fileName;
//    }
    
    public List<Bean> getBeans(List<User> userlist){
    	System.out.println(userlist);
//        loadFile();
//        try {
//            return parseFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
    	List<Bean> list = new ArrayList<Bean>();
    	List<Node> nodelist = new ArrayList<Node>();
    	Process process = new Process();
    	nodelist = process.Pretreatment(userlist);
    	for(Node node : nodelist) {
    		Bean bean = new Bean();
    		Map<String, Double> temp = new HashMap<String, Double>();
    		temp.put("x", (double)node.getX());
    		temp.put("y", (double)node.getY());
    		bean.setAge(node.getAge());
    		bean.setAttrValue(temp);
    		list.add(bean);
    	}
    	return list;
    }

//    public void loadFile(){
//        URL url = this.getClass().getClassLoader().getResource(fileName);
//        this.file = new File(url.getFile());
//    }
//
//    public List<Bean> parseFile() throws IOException {
//        FileReader fileReader = new FileReader(file);
//        BufferedReader bufferedReader = new BufferedReader(fileReader);
//        List<Bean> beanList = new LinkedList<>();
//        String string = null;
//        while ((string = bufferedReader.readLine()) != null){
//            beanList.add(this.parseString(string));
//        }
//        return beanList;
//    }
//
//    private Bean parseString(String string) {
//        try {
//            String[] splits = string.split(",");
//            Bean bean = new Bean();
//            for (int i = 0; i < splits.length; i++){
//                Double data = 0d;
//                try {
//                    data = Double.parseDouble(splits[i]);
//                }catch (Exception e){
//
//                }
//                bean.getAttrValue().put("len"+i, data);
//            }
//            return bean;
//        }catch (Exception e){
//            e.printStackTrace();
//            System.err.println("数据格式不对！！！！！！！！！！！");
//        }
//        return null;
//    }

}