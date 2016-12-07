//package org.nhnnext.web;
//
//import java.security.Principal;
//import java.util.ArrayList;
//import java.util.Map;
//
//import org.nhnnext.domain.SecurityUser;
//import org.nhnnext.repository.CourseRepository;
//import org.nhnnext.repository.LectureRepository;
//import org.nhnnext.repository.LessonRepository;
//import org.nhnnext.repository.SecurityUserRepository;
//import org.nhnnext.repository.UserRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.authentication.TestingAuthenticationProvider;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//import lombok.RequiredArgsConstructor;
//
//@RequiredArgsConstructor
//@RestController
//public class TestController {
//
//	private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);
//
//	private final UserRepository userRepository;
//	private final LectureRepository lectureRepository;
//	private final CourseRepository courseRepository;
//	private final LessonRepository lessonRepository;
//	private final SecurityUserRepository securityUserRepository;
//
//	@PreAuthorize("isAuthenticated()")
//	@GetMapping("/api/user")
//	public Object userInfo(@AuthenticationPrincipal Principal principal) {
//		System.out.println(principal);
//		return principal;
//	}
//
//	public Map requestUser(String accesstoken) {
//		System.out.println(accesstoken);
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//		headers.set("Authorization", "token " + accesstoken);
//		headers.set("Accept", "application/json");
//		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
//		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(
//				parameters, headers);
//		RestTemplate restTemplate = new RestTemplate();
//		return restTemplate.exchange("https://api.github.com/user", HttpMethod.GET, requestEntity, Map.class).getBody();
//	}
//
//	private TestingAuthenticationProvider authenticationProvider = new TestingAuthenticationProvider();;
//
//	// @PostMapping("/login")
//	@GetMapping("/api/login")
//	public Object create(@RequestParam String code, @RequestParam String state) {
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//		// headers.set("Authorization", "Bearer " + accessToken);
//		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
//		parameters.add("client_id", "47dfd22871c67b6bbf77");
//		parameters.add("client_secret", "0b5ac3ede0e5dc3f0bdd6e2aaa383a17a62cfa39");
//		parameters.add("code", code);
//		parameters.add("state", state);
//		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(
//				parameters, headers);
//		RestTemplate restTemplate = new RestTemplate();
//		// return
//		// restTemplate.exchange("https://github.com/login/ouath/access_token",
//		// HttpMethod.POST, requestEntity, Map.class).getBody();
//
//		Map resp = restTemplate
//				.exchange("https://github.com/login/oauth/access_token", HttpMethod.POST, requestEntity, Map.class)
//				.getBody();
//		if (resp.containsKey("error")) {
//			return resp;
//		}
//
//		Map userData = this.requestUser((String) resp.get("access_token"));
//
//		// Map userData = new HashMap<>();
//		// userData.put("test", "test");
//
//		// UsernamePasswordAuthenticationToken token = new
//		// UsernamePasswordAuthenticationToken("test", "test");
//		// TestingAuthenticationToken token = new
//		// TestingAuthenticationToken(userData, "test");
//		// Authentication authentication =
//		// this.authenticationProvider.authenticate(token);
//		Authentication authentication = new UsernamePasswordAuthenticationToken(userData, "secret", new ArrayList<>());
//		System.out.println(authentication);
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//
//		return userData;
//	}
//
//	@GetMapping("/api/test")
//	public String createTestData() {
//		SecurityUser user = new SecurityUser("testuser", "password");
//		securityUserRepository.save(user);
//
//		SecurityUser user2 = new SecurityUser("teacher", "password", "ROLE_INSTRUCTOR");
//		securityUserRepository.save(user2);
//
//		SecurityUser user3 = new SecurityUser("teacher2", "testpass", "ROLE_INSTRUCTOR");
//		securityUserRepository.save(user3);
//
//		return "ok!";
//	}
//}
