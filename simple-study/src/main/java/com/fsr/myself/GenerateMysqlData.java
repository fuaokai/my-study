package com.fsr.myself;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.UUID;

public class GenerateMysqlData {
    public static void main(String[] args) {
        try {
            FileWriter w = new FileWriter("D:/1.txt");
            BufferedWriter bw = new BufferedWriter(w);
            for (int i = 1; i <= 10000000; i++) {
                String uuid = UUID.randomUUID().toString();
                bw.write(i + "," + uuid + "," + (char)i + "\n");
            }
            bw.close();
            w.close();
            System.out.println("执行完毕");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
