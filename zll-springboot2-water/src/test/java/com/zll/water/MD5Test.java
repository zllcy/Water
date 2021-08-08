package com.zll.water;

import cn.hutool.crypto.SecureUtil;
import org.junit.Test;

public class MD5Test {

    @Test
    public void test() {
        String s = SecureUtil.md5("123456");
        System.out.println(s);
    }
}
