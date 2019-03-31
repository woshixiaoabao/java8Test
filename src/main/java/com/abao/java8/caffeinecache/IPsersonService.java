package com.abao.java8.caffeinecache;

/**
 * @author anpei
 * @version 1.0
 * @Description: ${todo}
 * @date 2018/9/19 14:49
 */
public interface IPsersonService {
    Person save(Person person);

    void remove(int id);

    Person findOne(int id);
}
