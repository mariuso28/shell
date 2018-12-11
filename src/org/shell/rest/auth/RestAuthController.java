package org.shell.rest.auth;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.shell.rest.anon.ResultJson;
import org.shell.services.Services;
import org.shell.user.BaseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import Credentials;

@RestController
@RequestMapping("/api/a")
public class RestAuthController {

	private static final Logger log = Logger.getLogger(RestAuthController.class);

	@Autowired
	private Services services;
	
	@Autowired
	private TokenStore tokenStore;
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value="/authorize1", method=RequestMethod.POST)
	public ResultJson authorize(
			@RequestBody("credentials") Credentials credentials
			)
	String user 
	{log.info("Attempting to authorize : " + username);
			
			ResultJson result = new ResultJson();
			String path;
			path = "http://localhost:8080/shell/oauth/token";
			try
			{
				BaseUser baseUser = services.getHome().getBaseUserDao().getByEmail(username);
				log.info("Got baseuser : " + baseUser.getEmail());
			}
			catch (Exception e)
			{
				log.error("Error finding User: " + username + " not found - " + e.getMessage());
				result.fail("username or password incorrect");
				return result;
			}
			
			try {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
				MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
				map.add("grant_type", "password");
				map.add("client_id", "barClientIdPassword");
				map.add("client_secret", "secret");
				map.add("username", username);
				map.add("password", password);
				HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
				ResponseEntity<Object> responseEntity = restTemplate.exchange(path, HttpMethod.POST, entity, Object.class);
				result.success(responseEntity.getBody());
			}
			catch(HttpClientErrorException e){
				e.printStackTrace();
				if (e.getStatusCode().equals(HttpStatus.UNAUTHORIZED)){
					result.fail("username or password incorrect");
				}
				else
					result.fail(e.getMessage());
			}
			catch(Exception e){
				e.printStackTrace();
				result.fail("Unexpected error on authorize - contact support");
			}
			
			return result;
		}
	
	@RequestMapping(value="/authorize", method=RequestMethod.POST)
	public ResultJson authorize(
			@RequestParam("username") String username,
			@RequestParam("password") String password
			){
		
		log.info("Attempting to authorize : " + username);
		
		ResultJson result = new ResultJson();
		String path;
		path = "http://localhost:8080/shell/oauth/token";
		try
		{
			BaseUser baseUser = services.getHome().getBaseUserDao().getByEmail(username);
			log.info("Got baseuser : " + baseUser.getEmail());
		}
		catch (Exception e)
		{
			log.error("Error finding User: " + username + " not found - " + e.getMessage());
			result.fail("username or password incorrect");
			return result;
		}
		
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
			map.add("grant_type", "password");
			map.add("client_id", "barClientIdPassword");
			map.add("client_secret", "secret");
			map.add("username", username);
			map.add("password", password);
			HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
			ResponseEntity<Object> responseEntity = restTemplate.exchange(path, HttpMethod.POST, entity, Object.class);
			result.success(responseEntity.getBody());
		}
		catch(HttpClientErrorException e){
			e.printStackTrace();
			if (e.getStatusCode().equals(HttpStatus.UNAUTHORIZED)){
				result.fail("username or password incorrect");
			}
			else
				result.fail(e.getMessage());
		}
		catch(Exception e){
			e.printStackTrace();
			result.fail("Unexpected error on authorize - contact support");
		}
		
		return result;
	}

	@RequestMapping(value="/refreshToken", method=RequestMethod.POST)
	public ResultJson refreshToken(
			@RequestParam("refreshToken") String refreshToken,
			HttpServletRequest request
			){
		ResultJson result = new ResultJson();
		String path;
		path = "http://localhost:8080/shell/oauth/token";
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
			map.add("grant_type", "refresh_token");
			map.add("refresh_token", refreshToken);
			map.add("client_id", "barClientIdPassword");
			map.add("client_secret", "secret");
			HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
			ResponseEntity<Object> responseEntity = restTemplate.exchange(path, HttpMethod.POST, entity, Object.class);
			result.success(responseEntity.getBody());
		}
		catch(Exception e){
			e.printStackTrace();
			result.fail("Bad credentials");
		}
		return result;
	}

	@RequestMapping(value="/revokeRefreshToken", method=RequestMethod.POST)
	public ResultJson revokeRefreshToken(
			@RequestParam("refreshToken") String refreshToken
			){
		org.shell.rest.anon.ResultJson result = new org.shell.rest.anon.ResultJson();
		((JdbcTokenStore) tokenStore).removeAccessTokenUsingRefreshToken(refreshToken);
		((JdbcTokenStore) tokenStore).removeRefreshToken(refreshToken);
		result.success();
		return result;
	}
}
