package com.example.sampleswagger.camel;

import com.example.sampleswagger.rest.api.AsyncSupport;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Component
public class SampleRouteBuilder extends RouteBuilder {
    @Resource
    private AsyncSupport asyncSupport;

    private Processor printNowProcessor = exchange -> {
        final String now = LocalDateTime.now().toString();
        exchange.getIn().setBody(String.format("now: %s", now));
        this.asyncSupport.sleep();
    };

    private long period() {
        return 10000;
    }

    @Override
    public void configure() throws Exception {
        from("timer:test-timer?period=" + period())
                .process(this.printNowProcessor)
                .to("log:test-timer")
                .end();
    }
}
