package com.xml.MegaTravelMBA.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.mobile.device.DeviceHandlerMethodArgumentResolver;
import org.springframework.mobile.device.DeviceResolverHandlerInterceptor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.xml.MegaTravelMBA.security.TokenUtils;
import com.xml.MegaTravelMBA.security.auth.RestAuthenticationEntryPoint;
import com.xml.MegaTravelMBA.security.auth.TokenAuthenticationFilter;
import com.xml.MegaTravelMBA.service.CustomUserDetailsService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

		// Implementacija PasswordEncoder-a koriscenjem BCrypt hashing funkcije.
		// BCrypt po defalt-u radi 10 rundi hesiranja prosledjene vrednosti.
		@Bean
		public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}

		@Autowired
		private CustomUserDetailsService jwtUserDetailsService;
		
		// Neautorizovani pristup zastcenim resursima
		@Autowired
		private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
		
		@Autowired
		TokenUtils tokenUtils;
		
		@Bean
		@Override
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}

		// Definisemo nacin autentifikacije
		@Autowired
		public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
		}
		
		
		// Definisemo prava pristupa odredjenim URL-ovima
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
			
				// komunikacija izmedju klijenta i servera je stateless
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				
				// za neautorizovane zahteve posalji 401 gresku
				.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
				
				// svim korisnicima dopusti da pristupe putanjama /auth/** i /h2-console/**
				// POTREBNO DA SVAKO UBACI KOJE PUTANJE SU DOSTUPNE SVIM KORISNICIMA, BEZ OBZIRA NA ULOGU
				.authorizeRequests()
				.antMatchers("/auth/**").permitAll()
				.antMatchers("/users/deleteUser").hasAuthority("DELETE_USER")
				.antMatchers("/h2-console/**").permitAll()
				// svaki zahtev mora biti autorizovan
				.anyRequest().authenticated().and()
				// presretni svaki zahtev filterom
				.addFilterBefore(new TokenAuthenticationFilter(tokenUtils, jwtUserDetailsService), BasicAuthenticationFilter.class);
			
			http.headers().contentSecurityPolicy("script-src 'self' https://trustedscripts.example.com; object-src https://trustedplugins.example.com; report-uri /csp-report-endpoint/");
		}
		
		
		// Generalna bezbednost aplikacije
		@Override
		public void configure(WebSecurity web) throws Exception {
			// TokenAuthenticationFilter ce ignorisati sve ispod navedene putanje
			web.ignoring()
			.antMatchers(HttpMethod.POST, "/auth/login");
			web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js");
		}
		
		@Bean
		public DeviceResolverHandlerInterceptor 
		        deviceResolverHandlerInterceptor() {
		    return new DeviceResolverHandlerInterceptor();
		}

		@Bean
		public DeviceHandlerMethodArgumentResolver 
		        deviceHandlerMethodArgumentResolver() {
		    return new DeviceHandlerMethodArgumentResolver();
		}

		@Override
		public void addInterceptors(InterceptorRegistry registry) {
		    registry.addInterceptor(deviceResolverHandlerInterceptor());
		}

		@Override
		public void addArgumentResolvers(
		        List<HandlerMethodArgumentResolver> argumentResolvers) {
		    argumentResolvers.add(deviceHandlerMethodArgumentResolver());
		}
	
}
