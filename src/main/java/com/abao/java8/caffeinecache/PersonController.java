package com.abao.java8.caffeinecache;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author anpei
 * @version 1.0
 * @Description: ${todo}
 * @date 2018/9/19 15:19
 */
@RestController
@RequestMapping("/person")
public class PersonController {
    @Resource
    private IPsersonService personService;

    @PostMapping
    public Object save(@RequestBody Person person) {
        return personService.save(person);
    }

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable int id) {
        return personService.findOne(id);
    }

    @GetMapping
    public Person findOne(int id){
        return personService.findOne(id);
    }
}
