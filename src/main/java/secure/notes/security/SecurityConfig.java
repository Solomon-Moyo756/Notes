package secure.notes.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // this should be enabled in prod, build CSRF checks
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated() //in a prod product specify which requests should be authenticated and which should be rejected
                )
                .httpBasic(Customizer.withDefaults());

        System.out.println("Custom SecurityConfig loaded"); // debug log
        return http.build();
    }
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource){
        JdbcUserDetailsManager manager =
                new JdbcUserDetailsManager(dataSource);
        if(!manager.userExists("king")){
            manager.createUser(
                    User.withUsername("king")
                            .password("{noop}king")
                            .roles("USER")
                            .build()
            );
        }

        if(!manager.userExists("admin")){
            manager.createUser(
                    User.withUsername("admin")
                            .password("{noop}admin")
                            .roles("ADMIN")
                            .build()
            );
        }
        return manager;
    }
}

