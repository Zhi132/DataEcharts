package com.kmeans;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;


public class KmeansTest {
    public static void main(String[] args) throws IOException {
        FileUtil fileUtil = new FileUtil();
        DataTest data = new DataTest();
        Kmeans2 kmeans = new Kmeans2(2, fileUtil.getBeans(data.Data()),"x","y");
        HashMap<Bean, LinkedList<Bean>> res = kmeans.start();
        System.out.println(res);
    }




}
