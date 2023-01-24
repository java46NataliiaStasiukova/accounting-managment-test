package telran.test.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class TestSecurityConfiguration {
@Bean
@Order(Ordered.HIGHEST_PRECEDENCE)
SecurityFilterChain testSecurityConfigure(HttpSecurity http) throws Exception {
	http
	.csrf()
	.disable()
	.authorizeHttpRequests(requests -> 
		requests.requestMatchers("/test").permitAll()
		.requestMatchers(HttpMethod.POST).hasRole("ADMIN")
		.requestMatchers(HttpMethod.PUT).hasRole("ADMIN_UPDATER")
		.requestMatchers(HttpMethod.DELETE).hasRole("ADMIN_REMOVER")
		.anyRequest().authenticated()
	)
	.httpBasic();
return http.build();
}
}
