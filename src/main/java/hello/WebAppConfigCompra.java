package hello;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import model.Cliente;

import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.dsl.http.Http;
import org.springframework.integration.gateway.MessagingGatewaySupport;
import org.springframework.integration.http.inbound.HttpRequestHandlingMessagingGateway;
import org.springframework.integration.http.inbound.RequestMapping;
import org.springframework.integration.http.support.DefaultHttpHeaderMapper;
import org.springframework.integration.json.JsonToObjectTransformer;
import org.springframework.integration.mapping.HeaderMapper;

@Configuration
public class WebAppConfigCompra {

    @Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
		factory.setPort(9000);
		factory.setSessionTimeout(10, TimeUnit.MINUTES);
		return factory;
	}

	@Bean
	public ExpressionParser parser() {
		return new SpelExpressionParser();
	}

	@Bean
	public HeaderMapper<HttpHeaders> headerMapper() {
		return new DefaultHttpHeaderMapper();
	}
	

        @Bean
	public MessagingGatewaySupport httpGetGateCompra() {
		HttpRequestHandlingMessagingGateway handler = new HttpRequestHandlingMessagingGateway();
		handler.setRequestMapping(createMapping(new HttpMethod[] { HttpMethod.GET }, "/compra"));
		handler.setHeaderMapper(headerMapper());

		return handler;
	}

	private RequestMapping createMapping(HttpMethod[] method, String... path) {
		RequestMapping requestMapping = new RequestMapping();
		requestMapping.setMethods(method);
		requestMapping.setProduces("application/json");
		requestMapping.setPathPatterns(path);

		return requestMapping;
	}

        
        @Bean
	public IntegrationFlow httpGetFlowCompra() {

		
		return IntegrationFlows.from(httpGetGateCompra())
				.channel("httpGetChannelCompra")
				.handle(Http.outboundGateway("https://servicogerentefornecedor.herokuapp.com/compra")
	                    .charset("UTF-8")
	                    .httpMethod(HttpMethod.GET)
	                    .expectedResponseType(String.class))
				.transform(new JsonToObjectTransformer(ArrayList.class))
				.get();
	                    
	}
        
}
