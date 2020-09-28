package com.fsr.design.behavior.state;

import lombok.Data;

@Data
public class Work {
    private int hour;
    private boolean finish;

    public void problem(int hour) {
        if (hour < 12) {
            System.out.println(String.format("上午上班，精神百倍， 当前时间%s", hour));
        }
        if (hour >= 12 && hour < 13) {
            System.out.println(String.format("中午犯困， 当前时间%s", hour));
        }

        if (hour >= 13 && hour < 17) {
            System.out.println(String.format("状态还行， 当前时间%s", hour));
        } else {
            if (finish) {
                System.out.println(String.format("任务完成，当前时间%s", hour));
            } else {
                if (hour >= 17 && hour < 22) {
                    System.out.println(String.format("精力下降，被迫加班， 当前时间%s", hour));
                } else {
                    System.out.println(String.format("开始加班，当前时间%s", hour));
                }
            }
        }

    }
}
