package project.mundofii.configuration;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import project.mundofii.domain.Authentication;
import project.mundofii.domain.Person;
import project.mundofii.repository.AuthenticationRepository;
import project.mundofii.repository.PersonRepository;

@Component
public class CustomUserDetailsManager  implements UserDetailsManager{

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private AuthenticationRepository authenticationRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Person person = personRepository.findByEmail(username);
		
		if(person == null) {
			return null;
		}
			
		List<Authentication> authentication = authenticationRepository.findByPerson(person);
		
		if(authentication == null || authentication.size() == 0) {
			return null;
		}
		
		Authentication lastAuth = authentication.get(authentication.size() - 1);
		
		return new UserDetails() {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isEnabled() { return true; }
			
			@Override
			public boolean isCredentialsNonExpired() { return true; }
			
			@Override
			public boolean isAccountNonLocked() { return true; }
			
			@Override
			public boolean isAccountNonExpired() { return true; }
			
			@Override
			public String getUsername() {
				return person.getEmail();
			}
			
			@Override
			public String getPassword() {
				return lastAuth.getDateForPasswordFormatted();
			}
			
			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() { return null; }
			
		};
	}

	@Override
	public void createUser(UserDetails user) {}

	@Override
	public void updateUser(UserDetails user) {}

	@Override
	public void deleteUser(String username) {}

	@Override
	public void changePassword(String oldPassword, String newPassword) {}

	@Override
	public boolean userExists(String username) { return false; }

}
