package com.mainclass.controllers;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Optional;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import com.mainclass.OrderServiceApplication;
import com.mainclass.entities.Order;
import com.mainclass.repositories.OrderRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(value = OrderController.class, secure = false)
public class OrderControllerTest{

	@Autowired
	MockMvc mockMvc;
	
	@InjectMocks
	private OrderController orderController;
	
	@MockBean
	private OrderRepository orderRepository;
	
	private static final Long ORDER_ID = Long.valueOf(1);
	
	@Value("${spring.datasource.url}")
	private String datasource_url;
	
	@Before
	public void setUp() throws Exception{
		
	}
	
	@Test
	public void testSeeSpringBootAdminUrl() throws Exception{		
		
		mockMvc.perform(get("/seeSpringDatasourceUrl"))
			.andExpect(status().isOk())
			.andExpect(content().string("spring datasource url => "+datasource_url));
	}
	
	@Test
	public void testGetOrder() throws Exception{
		Order order = new Order();
		order.setId(1);
		order.setActive(true);
		order.setName("TestName");
		order.setPrice(1000);
		
		Optional<Order> orderOpt = Optional.of(order);		
		
		when(orderRepository.findById(ORDER_ID)).thenReturn(orderOpt);
		
		MvcResult mvcResult = mockMvc.perform(get("/orders/1").accept(MediaType.APPLICATION_JSON)).andReturn();
		
		System.out.println(mvcResult);
		verify(orderRepository).findById(Mockito.anyLong());
	}
	
	@Test
	public void testAddOrder() throws Exception{
		String json = "{\n" + 
				"  \"active\": true,\n" + 
				"  \"id\": 0,\n" + 
				"  \"name\": \"TestName\",\n" + 
				"  \"price\": 1000\n" + 
				"}";
		
		mockMvc.perform(post("/addOrder")
					.contentType(MediaType.APPLICATION_JSON)
					.content(json)
					)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.active", Matchers.is(true)))
				.andExpect(jsonPath("$.name", Matchers.is("TestName")))
				.andExpect(jsonPath("$.price", Matchers.is(1000)))
				.andExpect(jsonPath("$.*", Matchers.hasSize(4)));
	}
}
