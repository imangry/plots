package com.qunar.lvshichang.guava;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.junit.Test;

import java.util.Random;

public class EventBusTest {
    @Test
    public void testReceiveEvent() {
        EventBus eventBus = new EventBus("test");
        Random random = new Random();
        EventListener listener = new EventListener();
        eventBus.register(listener);
        for (int i = 0; i < 10; i++) {
            int val = random.nextInt();

            if (val % 2 == 1) {
                eventBus.post(new TestEvent(val));
            }else {
                eventBus.post(new TestEvent2("string"));
            }
        }
        eventBus.post(new TestEvent(200));
        eventBus.post(new TestEvent(100));
    }
}

class TestEvent {
    private int message;

    public TestEvent(int message) {
        this.message = message;
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }
}
class TestEvent2 {
    private String message;

    public TestEvent2(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

class EventListener {
    @Subscribe
    public void listen(TestEvent event) {
        int lastMessage = event.getMessage();
        System.out.println("message: " + lastMessage);
    }

    @Subscribe
    public void listenString(TestEvent2 event2) {
        String message = event2.getMessage();
        System.out.println("message is a string");
    }
}