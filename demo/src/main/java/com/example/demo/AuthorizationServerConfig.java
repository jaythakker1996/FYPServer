package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

//https://jugbd.org/2017/09/19/implementing-oauth2-spring-boot-spring-security/
//https://www.youtube.com/watch?v=0pD7YeTAUkk&list=PL0SqXGoZcpU4Cz7HEjYSSF04-w3E1SCmD
//https://github.com/arocketman/Spring-oauth2-jpa-example
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	    @Autowired
	    private AuthenticationManager authenticationManager;


	    /**
	     * Setting up the endpointsconfigurer authentication manager.
	     * The AuthorizationServerEndpointsConfigurer defines the authorization and token endpoints and the token services.
	     * @param endpoints
	     * @throws Exception
	     */
	    @Override
	    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	        endpoints.authenticationManager(authenticationManager)
	        .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
	    }

	    /**
	     * Setting up the clients with a clientId, a clientSecret, a scope, the grant types and the authorities.
	     * @param clients
	     * @throws Exception
	     */
	    @Override
	    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	        clients.inMemory().withClient("my-trusted-client")
	                .authorizedGrantTypes("client_credentials", "password")
	                .authorities("ROLE_CLIENT","ROLE_TRUSTED_CLIENT").scopes("read","write","trust")
	                .resourceIds("oauth2-resource").accessTokenValiditySeconds(-1).secret("secret");
	    }

	    /**
	     * We here defines the security constraints on the token endpoint.
	     * We set it up to isAuthenticated, which returns true if the user is not anonymous
	     * @param security the AuthorizationServerSecurityConfigurer.
	     * @throws Exception
	     */
	    @Override
	    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
	        security.checkTokenAccess("isAuthenticated()").tokenKeyAccess("permitAll()");
	    }
}
