/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.endpoint.MethodInvokingMessageSource;
import org.springframework.integration.http.inbound.HttpRequestHandlingMessagingGateway;
import org.springframework.integration.http.inbound.RequestMapping;

@Configuration
@EnableIntegration
public class MyConfiguration {
    @Bean
    public MessageSource<?> integerMessageSource() {
        MethodInvokingMessageSource source = new MethodInvokingMessageSource();
        source.setObject(new AtomicInteger());
        source.setMethodName("getAndIncrement");
        return source;
    }
    @Bean
    public DirectChannel inputChannel() {
        return new DirectChannel();
    }
    @Bean
    public IntegrationFlow myFlow() {
        return IntegrationFlows.from(this.integerMessageSource(), c -> 
                                                   c.poller(Pollers.fixedRate(100)))
                    .channel(this.inputChannel())
                    .filter((Integer p) -> p > 0)
                    .transform(Object::toString)
                    .channel(MessageChannels.queue())
                    .get();
    }
    
   

}
