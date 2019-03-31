package com.abao.java8.caffeinecache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author anpei
 * @version 1.0
 * @Description: ${todo}
 * @date 2018/9/19 14:49
 */
@Slf4j
@Service
public class PersonService implements IPsersonService {
    @Resource
    private PersonRepository personRepository;

    @Override
    @CachePut(value = "people", key = "#person.id")
    public Person save(Person person) {
        Person p = personRepository.save(person);
        log.info("为id、key为：" + p.getId() + "数据做了缓存");
        return p;
    }

    @Override
    @CacheEvict(value = "people")
    public void remove(int id) {
        log.info("删除了id、key为" + id + "的数据缓存");
    }

    @Override
    @Cacheable(value = "people")
    public Person findOne(int id) {
        Optional<Person> p = personRepository.findById(id);
        log.info("为id、key为:" + p.get() + "数据做了缓存");
        return p.get();
    }
}
