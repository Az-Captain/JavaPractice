package com.daily;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * Optional 中filer的栗子
 *
 * @author Az
 * @date 2025/2/4
 */
public class FilterOptionalDemo {
    public static void main(String[] args) {
        String password = "12345666";
        Optional<String> optional = Optional.ofNullable(password);
        // 密码的长度进行检查
        System.out.println(optional.filter(it -> it.length() > 6).isPresent());

        // 额外添加两个条件 密码长度大于6，并且小于10
//        Predicate<String> len6 = pwd -> pwd.length() > 6;
        Predicate<String> len6 = pwd -> pwd.length() > 6;
        Predicate<String> len10 = pwd -> pwd.length() < 10;
        System.out.println(optional.filter(len6.and(len10)).isPresent());
    }
}
