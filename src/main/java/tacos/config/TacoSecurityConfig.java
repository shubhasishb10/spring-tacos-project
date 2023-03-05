package tacos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * @author Shubhasish
 */
@Configuration
@EnableWebSecurity
public class TacoSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // In-Memory Authentication
        /*auth.inMemoryAuthentication()
                .withUser("buzz")
                .password("infinity")
                .authorities("ROLE_USER")
                .and()
                .withUser("woody")
                .password("bullseye")
                .authorities("ROLE_USER");*/

        // JDBC Authentication
        /*auth.jdbcAuthentication()
                .dataSource(dataSource).passwordEncoder(NoOpPasswordEncoder.getInstance());*/

        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/design", "/orders/**", "/home").hasRole("USER")
                .antMatchers("/*", "/**").permitAll()
                .and()
                .csrf()
                .disable()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/design", true)
                .and()
                .logout()
                .logoutSuccessUrl("/login");
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


}
