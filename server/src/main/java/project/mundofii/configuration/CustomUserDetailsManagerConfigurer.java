package project.mundofii.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.ProviderManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsManagerConfigurer<B extends ProviderManagerBuilder<B>>
extends UserDetailsManagerConfigurer<B, CustomUserDetailsManagerConfigurer<B>> {
	
	@Autowired
	public CustomUserDetailsManagerConfigurer(CustomUserDetailsManager customUserDetailsManager) {
		super(customUserDetailsManager);
	}

}
