package com.gamecollector.demo.test;

import org.junit.Test;
import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HelloPython {
    @Test
    public void hello(){
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.exec("print('hello')");
    }

    @Test
    public void testPyScript(){
        PythonInterpreter interpreter = new PythonInterpreter();
        //interpreter.execfile("C:\\Users\\Administrator\\Desktop\\600\\Project\\TestJython\\test\\TestFunc.py");
        interpreter.execfile("C:\\Users\\Administrator\\Desktop\\600\\Project\\TestJython\\test\\Add.py");

        PyFunction pyFunction = interpreter.get("add", PyFunction.class);
        // 第一个参数为期望获得的函数（变量）的名字，第二个参数为期望返回的对象类型
        //PyObject pyObject = pyFunction.__call__(); // 调用函数

        int a = 5, b = 10;
        //调用函数，如果函数需要参数，在Java中必须先将参数转化为对应的“Python类型”
        PyObject pyObject = pyFunction.__call__(new PyInteger(a), new PyInteger(b));
        System.out.println("the anwser is: " + pyObject);

    }

    @Test
    public void testUsingThirdParty(){
        Process proc;
        try {
            proc = Runtime.getRuntime().exec("python C:\\Users\\Administrator\\Desktop\\600\\Project\\TestJython\\test\\Matrix.py");// 执行py文件
            //用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testRuntimeAdd(){
        int a = 18;
        int b = 23;
        try {
            String[] args = new String[] { "python", "C:\\Users\\Administrator\\Desktop\\600\\Project\\TestJython\\test\\RuntimeAdd.py", String.valueOf(a), String.valueOf(b) };
            Process proc = Runtime.getRuntime().exec(args);// 执行py文件

            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void testGetData(){
        Process proc;
        List<String> lines = new ArrayList<>();
        try {
            String[] args = new String[] { "python", "D:\\GitHub\\GameCollector\\GamesDate\\getData.py", "HITMAN" };
            proc = Runtime.getRuntime().exec(args);// 执行py文件

            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                //System.out.println(line);
                lines.add(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(lines);
    }

    @Test
    public void testNLP(){
        Process proc;
        try {
            String[] args = new String[] { "python", "D:\\GitHub\\GameCollector\\GamesDate\\nlp.py", "this is one of my favourite games of the year and it is one I would encourage any previous Final Fantasy fan to consider" };
            proc = Runtime.getRuntime().exec(args);

            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
