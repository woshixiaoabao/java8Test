package com.abao.java8;

import com.abao.java8.event.eventBus.CustomEvent;
import com.abao.java8.event.eventBus.CustomEventListener;
import com.google.common.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anpei
 * @version 1.0
 * @Description: ${todo}
 * @date 2019/5/3 12:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class GuavaEventBusTests {
    @Test
    public void test() {

        EventBus eventBus = new EventBus();

        List<String> listenedMessageList = new ArrayList<>();
        CustomEventListener customEventListener = new CustomEventListener(listenedMessageList);

        eventBus.register(customEventListener);

        eventBus.post(new CustomEvent("post a custom event ---- 1"));

        eventBus.unregister(customEventListener);

        eventBus.post(new CustomEvent("post a custom event ---- 2"));

    }
}
