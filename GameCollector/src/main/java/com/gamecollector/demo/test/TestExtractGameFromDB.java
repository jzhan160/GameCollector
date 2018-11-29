package com.gamecollector.demo.test;

import com.gamecollector.demo.service.GameService;
import com.gamecollector.demo.service.GameServiceImpl;
import org.junit.Test;
import org.python.bouncycastle.crypto.tls.MACAlgorithm;

import java.util.TreeMap;

public class TestExtractGameFromDB {


    @Test
    public void test(){
        TreeMap<Integer,Integer> map = new TreeMap<>();
        map.put(1,3);
        map.put(2,2);
        map.put(3,1);
        for (int key: map.keySet())
            System.out.println(key);

    }
}
