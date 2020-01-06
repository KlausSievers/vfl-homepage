/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.siewares.vfl.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 *
 * @author Klaus
 */
@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  DataSource dataSource;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .authorizeRequests()
            .antMatchers("/index.html", "/", "/login", "/user").permitAll()
            .antMatchers("/magazine").permitAll()       
            .antMatchers("/admin/**", "/views/admin/**/*.html").authenticated()
            .antMatchers("/wiki/**").authenticated()
            .antMatchers("/calendar/**").authenticated()
            .antMatchers("/admin/1/**").hasRole("1. MANNSCHAFT")
            .antMatchers("/admin/2/**").hasRole("2. MANNSCHAFT")
            .antMatchers("/admin/3/**").hasRole("3. MANNSCHAFT")
            .antMatchers("/admin/ah/**").hasRole("ALTE HERREN")
            .antMatchers("/admin/da/**").hasRole("FRAUENMANNSCHAFT")
            .antMatchers("/admin/a/**").hasRole("A-JUGEND (U19)")
            .antMatchers("/admin/a1/**").hasRole("A1-JUGEND (U19)")
            .antMatchers("/admin/a2/**").hasRole("A2-JUGEND (U19)")
            .antMatchers("/admin/b/**").hasRole("B-JUGEND (U17)")
            .antMatchers("/admin/b1/**").hasRole("B1-JUGEND (U17)")
            .antMatchers("/admin/b2/**").hasRole("B2-JUGEND (U17)")
            .antMatchers("/admin/c/**").hasRole("C2-JSG Kirchhel. / Grafenw. (U15)")
            .antMatchers("/admin/d/**").hasRole("D-JUGEND (U13)")
            .antMatchers("/admin/d1/**").hasRole("D1-JUGEND (U13)")
            .antMatchers("/admin/d2/**").hasRole("D2-JUGEND (U13)")
            .antMatchers("/admin/e/**").hasRole("E-JUGEND (U11)")
            .antMatchers("/admin/e1/**").hasRole("E1-JUGEND (U11)")
            .antMatchers("/admin/e2/**").hasRole("E2-JUGEND (U11)")
            .antMatchers("/admin/f1/**").hasRole("F-JUGEND (U9)")
            .antMatchers("/admin/f1/**").hasRole("F1-JUGEND (U9)")
            .antMatchers("/admin/f2/**").hasRole("F2-JUGEND (U9)")
            .antMatchers("/admin/g/**").hasRole("G-JUGEND (U7)")
            .antMatchers("/admin/u17/**").hasRole("U17-Juniorinnen")
            .and().formLogin().usernameParameter("username").passwordParameter("password") 
            .and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    
    http.headers().frameOptions().sameOrigin();
  }
  
  @Bean
  public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }
  
  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.jdbcAuthentication().passwordEncoder(passwordEncoder()).dataSource(dataSource)
            .usersByUsernameQuery(
                    "select username, password, enabled from user where username=?")
            .authoritiesByUsernameQuery(
                    "Select username, role from (\n"
                    + "	select username as username, role as role from user_role  r inner join user u on u.id = r.user\n"
                    + "	union\n"
                    + "	select u.username as username, 'MANAGEMENT' as role from user u inner join management m on u.id = m.user\n"
                    + "    union\n"
                    + "    SELECT u.username as username, 'TRAINER' as role from user u inner join trainer_team t on u.id = t.trainer\n"
                    + "	union\n"
                    + "    SELECT u.username as username, UPPER(te.name) as role from user u \n"
                    + "        inner join trainer_team tt on  u.id = tt.trainer \n"
                    + "        inner join team te on tt.team = te.id\n"
                    + "	) as t \n"
                    + "where username = ?");
  }
}
