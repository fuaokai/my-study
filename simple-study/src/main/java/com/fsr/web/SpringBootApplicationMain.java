package com.fsr.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@RequestMapping("/")
public class SpringBootApplicationMain {
    private static HashSet<Val<Long>> countSet = new HashSet<>();

    private ThreadLocal<Val<Long>> threadLocal = ThreadLocal.withInitial(() -> {
        Val<Long> longVal = new Val<>();
        longVal.setValue(0L);
        countSet.add(longVal);
        return longVal;
    });

    //spring 如何给每个请求分配线程
    //jdk1.8 函数式调用用法及原理
    @GetMapping("/add")
    @ResponseBody
    public void add() throws InterruptedException {
        Val<Long> newVal = threadLocal.get();
        newVal.setValue(newVal.getValue() + 1);
        threadLocal.set(newVal);
    }

    @GetMapping("/stat")
    @ResponseBody
    public Long stat() {
        return countSet.stream().map(v -> v.getValue()).reduce((a, b) -> a + b).get();
    }

    static class Val<T>{
        private T value;

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }
    }


    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplicationMain.class);
    }
}
