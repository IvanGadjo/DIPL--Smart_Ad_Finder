package smart.ad.founder.demo.application.websocket;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // * chat client will use this to connect to the server
        registry.addEndpoint("/kafka-chat").setAllowedOriginPatterns("*").withSockJS();
//        registry.addEndpoint("/kafka-chat").setAllowedOriginPatterns("http://localhost:3000/home").withSockJS();
//        registry.addEndpoint("/kafka-chat").setAllowedOrigins("http://localhost:8080/home").withSockJS();

        //  * without sockjs
//        registry.addEndpoint("/kafka-chat").setAllowedOriginPatterns("*");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");

    }

}
