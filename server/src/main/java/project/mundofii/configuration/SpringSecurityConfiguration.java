package project.mundofii.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@SuppressWarnings("deprecation")
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@SuppressWarnings("rawtypes")
	private @Autowired CustomUserDetailsManagerConfigurer customUserDetailsManagerConfigurer;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.sessionManagement()
        .		sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
				.antMatchers(HttpMethod.POST,"/people/**").permitAll()
				.antMatchers(HttpMethod.POST,"/person/**").permitAll()
				.antMatchers(HttpMethod.GET,"/person/**").permitAll()
				.antMatchers(HttpMethod.GET,"/fiis**").permitAll()
				.antMatchers(HttpMethod.POST,"/fiis**").permitAll()
				.antMatchers(HttpMethod.GET,"/fiis/**").permitAll()
				.antMatchers(HttpMethod.POST,"/authentication**").permitAll()
				.antMatchers("/**").authenticated()
				.and()
					.httpBasic()
				.and()
					.csrf().disable();
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.apply(customUserDetailsManagerConfigurer).passwordEncoder(NoOpPasswordEncoder.getInstance());
	}
}
