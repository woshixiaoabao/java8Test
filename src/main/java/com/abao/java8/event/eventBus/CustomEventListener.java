package com.abao.java8.event.eventBus;

import com.google.common.eventbus.Subscribe;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author anpei
 * @version 1.0
 * @Description: ${todo}
 * @date 2019/5/3 11:57
 */
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomEventListener {

    private List<String> listenedMessageList;

    @Subscribe
    public void onEvent(CustomEvent event){
        log.info("Guava EventListener listened one message : {}", event.getMessage());
    }
}
