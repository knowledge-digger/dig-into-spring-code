package dig.into.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/main.do").permitAll() // 모든 사용자가 접근 가능
                .requestMatchers("/member.do").hasAnyRole("USER", "ADMIN") // USER와 ADMIN 모두 접근 가능
                .requestMatchers("/admin.do").hasRole("ADMIN") // ADMIN만 접근 가능
                .anyRequest().authenticated() // 다른 모든 요청은 인증 필요
            )
            .formLogin(form -> form
                .permitAll() // 모든 사용자에게 허용
            )
            .logout(logout -> logout
                .permitAll()); // 로그아웃 허용
        return http.build();
    }
	
    @Bean
    protected UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        
        // 비밀번호 인코딩을 위한 PasswordEncoder
        PasswordEncoder passwordEncoder = passwordEncoder();

        // 기본 사용자 추가
        manager.createUser(User.withUsername("user")
                .password(passwordEncoder.encode("1234"))
                .roles("USER")
                .build());

        // admin 사용자 추가
        manager.createUser(User.withUsername("admin")
                .password(passwordEncoder.encode("1234"))
                .roles("ADMIN")
                .build());

        return manager;
    }
    
    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
