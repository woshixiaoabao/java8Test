package com.abao.java8.event.eventBus;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author anpei
 * @version 1.0
 * @Description: ${todo}
 * @date 2019/5/3 11:56
 */
@AllArgsConstructor
@Data
public class CustomEvent {
    private String message;
}
