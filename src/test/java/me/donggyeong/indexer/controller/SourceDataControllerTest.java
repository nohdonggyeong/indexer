package me.donggyeong.indexer.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import me.donggyeong.indexer.dto.SourceDataRequest;
import me.donggyeong.indexer.dto.SourceDataResponse;
import me.donggyeong.indexer.enums.Action;
import me.donggyeong.indexer.service.SourceDataService;

@WebMvcTest(SourceDataController.class)
class SourceDataControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SourceDataService sourceDataService;

	@Autowired
	private ObjectMapper objectMapper;

	private static final String API_BASE_PATH = "/api/v1/source-data";
	private static final String JSON_ROOT_PATH = "$";
	private static final String JSON_FIRST_ELEMENT_PATH = "$[0]";

	@Test
	void createSourceData() throws Exception {
		// given
		when(sourceDataService.createSourceData(any(SourceDataRequest.class))).thenReturn(mock(SourceDataResponse.class));
		Map<String, Object> data = new HashMap<>();
		data.put("category", "test-category");
		data.put("title", "test-title");
		data.put("description", "test-description");
		SourceDataRequest sourceDataRequest = new SourceDataRequest(Action.CREATE, "hub", 1L, data);

		// when
		mockMvc.perform(
			post(UriComponentsBuilder.fromPath(API_BASE_PATH).toUriString())
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(sourceDataRequest))
		)
		// then
			.andExpect(status().isCreated())
			.andExpect(jsonPath(JSON_ROOT_PATH).isNotEmpty());
		verify(sourceDataService, times(1)).createSourceData(any(SourceDataRequest.class));
	}
}