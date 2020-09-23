package com.fsr.design.behavior.state;

/**
 * Created by ProblemState
 *
 * @author Aokai.Fu
 * @version v1.0
 * @apiNote ProblemState
 * @create 2020/9/23 10:21
 */
public class ProblemState {

    static boolean WORK_FINISHED = false;
    public static void problem(int hour) {
        if (hour < 12) {
            System.out.println(String.format("上午上班，精神百倍， 当前时间%s", hour));
        }
        if (hour >= 12 && hour < 13) {
            System.out.println(String.format("中午犯困， 当前时间%s", hour));
        }

        if (hour >= 13 && hour < 17) {
            System.out.println(String.format("状态还行， 当前时间%s", hour));
        } else {
            if (WORK_FINISHED) {
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

    public void problem1(int age) {
        if (age < 10) {
            System.out.println("小朋友");
        }
        if (age >= 10 && age < 20) {
            System.out.println("青少年");
        }

        if (age >= 20 && age < 30) {
            System.out.println("青年人");
        }

        if (age >= 30 && age < 40) {
            System.out.println("中青年");
        }

        if (age >= 40 && age < 50) {
            System.out.println("中年人");
        }

        if (age >= 50 && age < 60) {
            System.out.println("中老年");
        }

        if (age >= 60 ) {
            System.out.println("老年人");
        }

    }
}
