package cl.td.spingedumanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                // IMPORTANTE: Desactivar CSRF para la API para poder hacer POST/PUT sin tokens
                                .csrf(csrf -> csrf.disable())
                                .authorizeHttpRequests(auth -> auth
                                                // 1. Recursos públicos: login y archivos estáticos (CSS, JS, Imágenes)
                                                .requestMatchers("/login", "/css/**", "/js/**", "/resources/**")
                                                .permitAll()

                                                // 2. La API, La ponemos antes del anyRequest
                                                // .requestMatchers("/api/**").authenticated() // Requiere login para la
                                                // API
                                                .requestMatchers("/api/**").permitAll() // la API es pública para
                                                                                        // efectos del bootcamp

                                                // 3. Solo ADMIN puede entrar a las rutas de "nuevo" o "guardar"
                                                .requestMatchers("/cursos/nuevo/**", "/estudiantes/nuevo/**")
                                                .hasRole("ADMIN")
                                                .requestMatchers("/cursos/guardar/**", "/estudiantes/guardar/**")
                                                .hasRole("ADMIN")

                                                // Desafío sobre proyecto original ABP 6
                                                .requestMatchers("/portal/**").hasRole("STUDENT")

                                                // 4. Todo lo demás (Dashboard, ver listas, etc.) requiere cualquier
                                                // login exitoso
                                                .anyRequest().authenticated())
                                .formLogin(form -> form
                                                .loginPage("/login")
                                                .successHandler((request, response, authentication) -> {
                                                        // Lógica para decidir a dónde enviarlos
                                                        var roles = authentication.getAuthorities();
                                                        if (roles.stream().anyMatch(
                                                                        r -> r.getAuthority().equals("ROLE_STUDENT"))) {
                                                                response.sendRedirect("/portal/dashboard");
                                                        } else {
                                                                response.sendRedirect("/dashboard"); // Admin y otros
                                                                                                     // van aquí
                                                        }
                                                })
                                                .permitAll())
                                .logout(logout -> logout
                                                .logoutUrl("/logout")
                                                .logoutSuccessUrl("/login?logout") // A dónde ir al salir
                                                .permitAll());

                return http.build();
        }

        // Usuarios de prueba en memoria
        @Bean
        public UserDetailsService userDetailsService(
                        org.springframework.security.crypto.password.PasswordEncoder passwordEncoder) {
                // 1. ADMIN (admin123)
                UserDetails admin = User.builder()
                                .username("admin")
                                .password(passwordEncoder.encode("admin123"))
                                .roles("ADMIN")
                                .build();

                // 2. USER (user123) - ¡Aquí está de vuelta!
                UserDetails user = User.builder()
                                .username("user")
                                .password(passwordEncoder.encode("user123"))
                                .roles("USER")
                                .build();

                // 3. STUDENT (1588)
                UserDetails portal = User.builder()
                                .username("estudiante")
                                .password(passwordEncoder.encode("estu123"))
                                .roles("STUDENT")
                                .build();

                return new InMemoryUserDetailsManager(admin, user, portal);
        }

        @Bean
        public org.springframework.security.crypto.password.PasswordEncoder passwordEncoder() {
                return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
        }
}
