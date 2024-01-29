package ba.ibu.edu.bemytech.rest.configuration;


import ba.ibu.edu.bemytech.core.service.UserService;
import ba.ibu.edu.bemytech.rest.filters.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.authentication.AuthenticationProvider;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .requestMatchers(HttpMethod.GET, "/api/products/").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/products/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/products/add-product").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/products/{id}").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/products/{id}").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/orders/").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/orders/add-order").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/orders/order-by-id/{userId}").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/orders/order-by-username/{username}").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/orders/{id}").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/orders/{id}").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/orders/{id}").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/contact-forms/").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/contact-forms/add-contact-form").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/users/").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/users/{id}").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/users/user-by-id/{userId}").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/users/user-by-username/{username}").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/users/add-users").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/users/{id}").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/users/{id}").authenticated()
                        .anyRequest().permitAll())
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService.userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }

}
