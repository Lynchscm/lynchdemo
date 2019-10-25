package com.bosssoft.producer.config;

/**
 * @author Lynch
 * @date 2019/10/11 -18:22
 */
public class Sort {
    /**
     * 插入排序
     * @param a 数组
     */
    public static void insertion(Double[] a) {
        int j;
        double t ;
        for (int i = 1; i <a.length; i++) {
            t=a[i];
            j=i-1;
            while (j>=0 && t<a[j])
            {
                a[j+1]=a[j];
                j--;
            }
            a[j+1]=t;
        }
    }
}
