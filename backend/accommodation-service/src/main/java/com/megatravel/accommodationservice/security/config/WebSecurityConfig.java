package com.megatravel.accommodationservice.security.config;

import com.megatravel.accommodationservice.security.CustomUserDetailsService;
import com.megatravel.accommodationservice.security.TokenUtils;
import com.megatravel.accommodationservice.security.auth.RestAuthenticationEntryPoint;
import com.megatravel.accommodationservice.security.auth.TokenAuthenticationFilter;
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
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;


@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled=true, proxyTargetClass = true)
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
					.antMatchers("/accSecurity/**").permitAll()
				.antMatchers("/h2-console/**").permitAll()
				.antMatchers("/ws/**").permitAll()
				.antMatchers("/accommodations/**").permitAll()
				.antMatchers("/amenities/**").permitAll()
				// svaki zahtev mora biti autorizovan
				.anyRequest().authenticated().and()
				// presretni svaki zahtev filterom
				.addFilterBefore(new TokenAuthenticationFilter(tokenUtils, jwtUserDetailsService), BasicAuthenticationFilter.class);
			
			http.headers().contentSecurityPolicy("script-src 'self' https://localhost:4200; object-src https://localhost:4200");
		}
		
		
		// Generalna bezbednost aplikacije
		@Override
		public void configure(WebSecurity web) throws Exception {
			// TokenAuthenticationFilter ce ignorisati sve ispod navedene putanje
			web.ignoring().antMatchers(HttpMethod.POST, "/accSecurity/setAuthentication");
			web.ignoring().antMatchers(HttpMethod.GET, "/test/authTest");
			web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js");
			
			//coa
			web.ignoring().antMatchers(HttpMethod.POST, "/accommodations/**");
			web.ignoring().antMatchers(HttpMethod.GET, "/accommodations/**");
			web.ignoring().antMatchers(HttpMethod.DELETE, "/accommodations/**");
			
			
			web.ignoring().antMatchers(HttpMethod.POST, "/amenities/**");
			web.ignoring().antMatchers(HttpMethod.GET, "/amenities/**");
			web.ignoring().antMatchers(HttpMethod.DELETE, "/amenities/**");
			
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
