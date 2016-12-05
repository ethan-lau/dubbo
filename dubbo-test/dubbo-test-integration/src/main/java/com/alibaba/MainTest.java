package com.alibaba;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: liuhanlong
 * Email: liuhanlong3304@126.com
 * Time: 16/12/5 下午10:11
 */
public class MainTest {

    public static void main(String[] args) {
        for (int i = 0; i < 10 ; i++) {
            new Thread() {
                @Override
                public void run() {
                    LocalInteger local = LocalInteger.get();
                    System.out.println(local);
                }
            }.start();
        }
    }
}
