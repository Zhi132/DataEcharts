package com.kmeans;

import java.util.HashMap;
import java.util.Map;

public class Bean {
    /**
         * 属性名和其值的关系
         * 花萼->长度
         * 花茎->长度
     */
    private Map<String, Double> attrValue = new HashMap<String, Double>();
    private int age;
    
    
    public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Map<String, Double> getAttrValue(){
        return attrValue;
    }

    public void setAttrValue(Map<String, Double> attrValue) {
        this.attrValue = attrValue;
    }

    @Override
    public String toString() {
        return "kmeans.FreItemset{" +
                "attrValue=" + attrValue +
                '}';
    }
}
