package net.guides.springboot2.springboot2jpacrudexample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.example.SpringDemoBot.Application;
import com.example.SpringDemoBot.model.Match;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MatchControllerIntegrationTest {
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void testGetAllDesmonds() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/desmonds",
				HttpMethod.GET, entity, String.class);
		
		assertNotNull(response.getBody());
	}

	@Test
	public void testGetDesmondById() {
		Match match = restTemplate.getForObject(getRootUrl() + "/desmonds/1", Match.class);
		System.out.println(match.getData());
		assertNotNull(match);
	}

	@Test
	public void testCreateDesmond() {
		Match match = new Match();
		match.setData("19.01.2022");
		match.setRating(1.5f);
		match.setSmokeKill(8);
		match.setOpenKill(8);
		match.setThreeKill(8);
		match.setFourKill(8);
		match.setAce(8);
		match.setFlash(8);
		match.setTrade(8);
		match.setWallbang(8);
		match.setClutchOne(8);
		match.setClutchTwo(8);
		match.setClutchThree(8);
		match.setClutchFour(8);
		match.setClutchFive(8);

		ResponseEntity<Match> postResponse = restTemplate.postForEntity(getRootUrl() + "/desmonds", match, Match.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdateDesmond() {
		int id = 1;
		Match match = restTemplate.getForObject(getRootUrl() + "/desmonds/" + id, Match.class);
		match.setData("admin1222");
		match.setRating(1.9f);

		restTemplate.put(getRootUrl() + "/desmonds/" + id, match);

		Match updatedMatch = restTemplate.getForObject(getRootUrl() + "/desmonds/" + id, Match.class);
		assertNotNull(updatedMatch);
	}

	@Test
	public void testDeleteDesmond() {
		int id = 2;
		Match match = restTemplate.getForObject(getRootUrl() + "/desmonds/" + id, Match.class);
		assertNotNull(match);

		restTemplate.delete(getRootUrl() + "/desmonds/" + id);

		try {
			match = restTemplate.getForObject(getRootUrl() + "/desmonds/" + id, Match.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}
}
