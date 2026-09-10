package it.uniroma3.siw.authentication;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

import org.springframework.security.config.Customizer;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final DataSource dataSource;

    public SecurityConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        manager.setUsersByUsernameQuery(
                "SELECT username, password, 1 as enabled FROM credentials WHERE username = ?");
        manager.setAuthoritiesByUsernameQuery(
                "SELECT username, role FROM credentials WHERE username = ?");
        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain configure(final HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(authorize -> authorize
                .requestMatchers(
                        "/",
                        "/index",
                        "/register",
                        "/css/**",
                        "/images/**",
                        "/assets/**",
                        "/favicon.ico",
                        "/favicon.svg",
                        "/icons.svg",
                        "/rest/**"
                ).permitAll()

                .requestMatchers(HttpMethod.GET, "/annunci", "/annunci/search").permitAll()
                .requestMatchers(RegexRequestMatcher.regexMatcher(HttpMethod.GET, "^/annunci/[0-9]+$")).permitAll()
                .requestMatchers(RegexRequestMatcher.regexMatcher(HttpMethod.GET, "^/annunci/[0-9]+/immagine$")).permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")

                .anyRequest().authenticated()
        );

        httpSecurity.formLogin(form -> form
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error=true")
        );

        httpSecurity.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .clearAuthentication(true)
                .permitAll()
        );
        
        httpSecurity.cors(Customizer.withDefaults());

        return httpSecurity.build();
    }
}