package test.com.abao.java8;

import com.abao.java8.event.eventBus.CustomEvent;
import com.abao.java8.event.eventBus.CustomEventListener;
import com.google.common.eventbus.EventBus;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.ArrayList;
import java.util.List;

/**
 * GuavaEventBusTests Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>五月 3, 2019</pre>
 */
public class GuavaEventBusTestsTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: test()
     */
    @Test
    public void testTest() throws Exception {
        EventBus eventBus = new EventBus();

        List<String> listenedMessageList = new ArrayList<>();
        CustomEventListener customEventListener = new CustomEventListener(listenedMessageList);

        eventBus.register(customEventListener);

        eventBus.post(new CustomEvent("post a custom event ---- 1"));

        eventBus.unregister(customEventListener);

        eventBus.post(new CustomEvent("post a custom event ---- 2"));
    }


} 
