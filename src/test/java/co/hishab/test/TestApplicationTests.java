package co.hishab.test;

import co.hishab.test.controllers.ApiController;
import co.hishab.test.dtos.CreatePlayer;
import co.hishab.test.services.GameService;
import co.hishab.test.services.PlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class TestApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ApiController apiController;

	@Autowired
	GameService gameService;

	@Autowired
	PlayerService playerService;

	@Test
	void contextLoads() {
		Assertions.assertNotEquals(apiController, null);
	}

	@Test
	void playerCountShouldHaveBeOne() throws Exception {
		mockMvc.perform(
				post("/player")
				.contentType("application/json")
						.content(objectMapper.writeValueAsString(new CreatePlayer("Suben", 28f)))
		).andExpect(MockMvcResultMatchers.status().isOk());
		Assertions.assertEquals(this.playerService.state().size(), 1);
	}
	@Test
	void playerCountShouldHaveBeTwo() throws Exception {
		mockMvc.perform(
				post("/player")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(new CreatePlayer("Sakib", 28f)))
		).andExpect(MockMvcResultMatchers.status().isOk());
		Assertions.assertEquals(this.playerService.state().size(), 2);
	}

}
