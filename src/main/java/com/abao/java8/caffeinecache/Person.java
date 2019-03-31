package com.abao.java8.caffeinecache;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.springframework.cache.annotation.Cacheable;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author anpei
 * @version 1.0
 * @Description: ${todo}
 * @date 2018/9/18 18:02
 */
@Getter
@Setter
@Entity
@Table(name = "abao_person")
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
}
