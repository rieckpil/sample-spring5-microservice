package de.rieckpil.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import de.rieckpil.services.CountryService;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringJUnitWebConfig
public class CountryControllerTest {

	private MockMvc mockMvc;

	private CountryService mockedService = Mockito.mock(CountryService.class);

	@BeforeEach
	private void setup(WebApplicationContext wac) {
		this.mockMvc = MockMvcBuilders.standaloneSetup(new CountryController(mockedService)).build();
	}

	@Test
	private void getAccount() throws Exception {

		when(mockedService.getAllCountries()).thenReturn(null);

		this.mockMvc.perform(get("/api/countries/").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(status().isOk()).andExpect(content().contentType("application/json"));
	}

}
