# Spring HTTPS

Dockerfile
```
FROM openjdk:8

EXPOSE 8080
COPY target/main-0.0.1-SNAPSHOT.jar /work/
CMD java -jar /work/main-0.0.1-SNAPSHOT.jar
```

resources/application.properties
```
server.port=443
server.ssl.key-store=classpath:keystore.p12
server.ssl.key-store-password=********
server.ssl.keyStoreType=PKCS12
server.ssl.keyAlias=tomcat

http.port=8080
max_thread_size=20
server.tomcat.accept-count=600
server.tomcat.max-connections=2000
server.tomcat.max-threads=600
```

resources/keystore.p12
```
......
```

Main
```
@SpringBootApplication
@EnableCaching
public class Main {

	@Value("${http.port}")
	private Integer port;

	@Bean
	public ServletWebServerFactory servletContainer() {
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
		tomcat.addAdditionalTomcatConnectors(createStandardConnector());
		return tomcat;
	}

	private Connector createStandardConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setPort(port);
		return connector;
	}

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
```