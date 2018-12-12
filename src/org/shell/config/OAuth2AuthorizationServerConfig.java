package org.shell.config;

import java.util.Arrays;

import javax.sql.DataSource;

import org.shell.config.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{

	@Autowired
	private DataSource dataSource;
	@Autowired
	private TokenStore tokenStore;
	@Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
        	.allowFormAuthenticationForClients()
	        .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/web/anon/logon"))
	        .tokenKeyAccess("permitAll()")
	        .checkTokenAccess("isAuthenticated()")
        ;
    }
	
	@Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients
			.jdbc(dataSource)
		;
	}
	
	@Bean
	public TokenEnhancer tokenEnhancer(){
		return new CustomTokenEnhancer();
	}
	
	@SuppressWarnings("unused")
	private WebResponseExceptionTranslator webResponseExceptionTranslator(){
		return new WebResponseExceptionTranslator() {
			
			@Override
			public ResponseEntity<OAuth2Exception> translate(Exception e)
					throws Exception {
				e.printStackTrace();
				return null;
			}
		};
	}
	
	@Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer()));
		endpoints
			.tokenStore(tokenStore)
			.tokenEnhancer(tokenEnhancerChain)
			.userDetailsService(customUserDetailsService)
			.authenticationManager(authenticationManager)
			.prefix("/")
//			.accessTokenConverter(accessTokenConverter())
//			.exceptionTranslator(webResponseExceptionTranslator())
		;
		
    }
	
}