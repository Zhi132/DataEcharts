package com.kmeans;

import java.util.*;

/**
 * 1. 随机选择k个质心
 * 2. 计算每个对象与k个聚类中心的距离
 * 3. 把每个对象分配给距离最近的聚类中心
 *
 * 4. 将每个类别中所有对象所对应的均值作为该类别的聚类中心
 * 重复3，4
 */
public class Kmeans2 {

    /**
     * 聚类中心的个数
     */
    private static int k;

    /**
     * 聚类中心
     */
    private static List<Bean> clusterCenterList;

    /**
     * 每个聚类中心的map
     */
    private static HashMap<Bean,LinkedList<Bean>> clusterCenterDataMap;

    private static HashMap<Bean,Bean> newClusterCenter;

    /**
     * 对某几个属性进行聚类
     */
    private static String[] clusterAttr;

    /**
         * 数据
     */
    private static List<Bean> dataList;

    /**
     *
     * @param k 聚类中心的个数
     * @param dataList 数据链表
     * @param clusterAttr 对哪个属性进行聚类
     */
    public Kmeans2(int k, List<Bean> dataList, String... clusterAttr){
        this.k = k;
        // 初始化聚类中心的map
        this.clusterCenterDataMap = new HashMap<Bean,LinkedList<Bean>>(k);
        this.newClusterCenter = new HashMap<Bean,Bean>();
        this.dataList = dataList;
        this.clusterAttr = clusterAttr;
    }

    public HashMap<Bean,LinkedList<Bean>> start(){
        randomSelectCenter();
        for (Bean bean: dataList) {
            calculateDistance(bean);
        }
        int i = 0;
        while (true){
            newClusterCenter.clear();
            // 找新的聚类中心
            for (Bean bean: clusterCenterList) {
                List<Bean> beanList = clusterCenterDataMap.get(bean);
                Bean newBean = new Bean();
                Map<String,Double> map = new HashMap<>();
                for (String string: clusterAttr) {
                    double sum = 0;
                    for (Bean bean2: beanList) {
                        sum += bean2.getAttrValue().get(string);
                    }
                    map.put(string, sum/beanList.size());
                }
                newBean.setAttrValue(map);
                newClusterCenter.put(bean, newBean);
            }

            double sum = 0; // 总误差
            for (Bean oldBean: clusterCenterList) {
                Bean newBean = newClusterCenter.get(oldBean);
                for (String s: clusterAttr) {
                    double newAttr = newBean.getAttrValue().get(s);
                    double oldAttr = oldBean.getAttrValue().get(s);
                    sum += Math.abs(newAttr - oldAttr);
                }
            }
            if (sum < 0.0001){
                break;
            }

            // 把新的聚类中心添加到 clusterCenterList 中
            clusterCenterList.clear();
            clusterCenterDataMap.clear();
            for (Map.Entry<Bean,Bean> entry:newClusterCenter.entrySet()) {
                clusterCenterList.add(entry.getValue());
                clusterCenterDataMap.put(entry.getValue(),new LinkedList<>() );
            }

            for (Bean bean: dataList) {
                calculateDistance(bean);
            }

            i++;
        }
        return clusterCenterDataMap;
    }


    // 从dataList中随机选出k个聚类中心
    public final void randomSelectCenter(){
        this.clusterCenterList = selectCenterAlgorithm(this.dataList, this.k);
        for (Bean bean:this.clusterCenterList) {
            LinkedList list = new LinkedList<>();
            list.add(bean);
            this.clusterCenterDataMap.put(bean, list);
        }
    }

    /**
         * 选择聚类中心的算法
     * @param dataList 从该链表中找到k个聚类中心
     * @return
     */
    public List<Bean> selectCenterAlgorithm(List<Bean> dataList,int k){
        List<Bean> beanList = new ArrayList<>();
        int i = 0;
        while (i < k){
            Double ran = Math.random()*dataList.size();
            int x = (int) Math.ceil(ran);
            Bean bean = dataList.get(x);
            beanList.add(bean);
            dataList.remove(bean);
            i++;
        }
        return beanList;
    }

    // 计算该点和各个聚类中心的距离
    public final void calculateDistance(Bean bean){
        double[][] data = new double[2][clusterAttr.length];
        for (int i = 0; i < clusterAttr.length; i++){
            if (bean.getAttrValue().containsKey(clusterAttr[i])){
                Double value = bean.getAttrValue().get(clusterAttr[i]);
                data[0][i] = value;
            }else{
                throw new RuntimeException("the attribute is not exist: " + clusterAttr[i]);
            }
        }

        Bean minDistanceBean = null;
        double minDistance = Double.MAX_VALUE;
        for (int i = 0; i < clusterCenterList.size(); i++){
            Bean centerBean = clusterCenterList.get(i);
            for (int j = 0; j < clusterAttr.length; j++){
                Double value = centerBean.getAttrValue().get(clusterAttr[j]);
                data[1][j] = value;
            }
            // 计算距离
            double distance = calculateDistanceAlgorithm(data);

            if (distance < minDistance){
                minDistance = distance;
                minDistanceBean = centerBean;
            }
        }
        clusterCenterDataMap.get(minDistanceBean).add(bean);
    }

    /**
     * 1 1 1 1 1 1 1
     * 2 2 2 2 2 2 2
     * 0行是元数据
     * 1行是聚类中心数据
         * 默认欧式距离
     * @param data n维数组
     * @return 距离
     */
    public double calculateDistanceAlgorithm(double[][] data){
        double sum = 0;
        for (int i = 0; i < data[0].length; i++){
            sum += Math.pow(data[0][i] - data[1][i], 2);
        }
        return Math.sqrt(sum);
    }

    /**
     * 把该点添加到和它最近的聚类中心的链表中
     * @param centerBean 聚类中心
     * @param bean
     */
    public final void addBeanToCenterDataList(Bean centerBean, Bean bean){
        clusterCenterDataMap.get(centerBean).add(bean);
    }

}
