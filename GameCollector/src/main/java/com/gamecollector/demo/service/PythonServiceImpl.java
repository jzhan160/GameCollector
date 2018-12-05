package com.gamecollector.demo.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service("pythonService")
public class PythonServiceImpl implements PythonService {
    @Override
    public String getScoreByReview(String review) {
        Process proc;
        String score = "";
        try {
            String[] args = new String[] { "python", "D:\\GitHub\\GameCollector\\GamesDate\\nlp.py", review };
            proc = Runtime.getRuntime().exec(args);

            String line = null;
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                score+=line;
            }
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return score;
    }
}
