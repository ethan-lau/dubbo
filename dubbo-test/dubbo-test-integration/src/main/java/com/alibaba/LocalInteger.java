package com.alibaba;

/**
 * User: liuhanlong
 * Email: liuhanlong3304@126.com
 * Time: 16/12/5 下午10:13
 */
public class LocalInteger {
    private static ThreadLocal<LocalInteger> local = new ThreadLocal<LocalInteger>() {
        @Override
        protected LocalInteger initialValue() {
            return new LocalInteger();
        }
    };

    public static LocalInteger get() {
        return local.get();
    }
}
