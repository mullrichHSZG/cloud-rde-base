package org.bissis.controllers;

import org.bissis.domain.Customer;
import org.bissis.domain.Product;
import org.bissis.services.CustomerService;
import org.bissis.services.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * @author Markus Ullrich
 */
public class CustomerControllerTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void listCustomers() throws Exception {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer());
        customers.add(new Customer());
        customers.add(new Customer());

        when(customerService.listAll()).thenReturn((List) customers);

        mockMvc.perform(get("/customer/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/list"))
                .andExpect(model().attribute("customers", hasSize(3)));
    }

    @Test
    public void showCustomer() throws Exception {
        Integer id = 1;

        String first = "First";
        String last = "Last";
        String zip = "01234";
        String email = "first@last.com";
        String addressLineOne = "Main Boulevard 12";
        String addressLineTwo = "Sub Area 1";
        String phoneNumber = "123-456789";
        String city = "Kansas";
        String state = "Illinois";

        Customer expectedCustomer = new Customer();
        expectedCustomer.setId(id);
        expectedCustomer.setFirstName(first);
        expectedCustomer.setLastName(last);
        expectedCustomer.setZipCode(zip);
        expectedCustomer.setEmail(email);
        expectedCustomer.setAddressLineOne(addressLineOne);
        expectedCustomer.setAddressLineTwo(addressLineTwo);
        expectedCustomer.setPhoneNumber(phoneNumber);
        expectedCustomer.setCity(city);
        expectedCustomer.setState(state);

        when(customerService.getById(id)).thenReturn(expectedCustomer);

        mockMvc.perform(get("/customer/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/show"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)))
                .andExpect(model().attribute("customer", hasProperty("id", is(id))))
                .andExpect(model().attribute("customer", hasProperty("firstName", is(first))))
                .andExpect(model().attribute("customer", hasProperty("lastName", is(last))))
                .andExpect(model().attribute("customer", hasProperty("email", is(email))))
                .andExpect(model().attribute("customer", hasProperty("phoneNumber", is(phoneNumber))))
                .andExpect(model().attribute("customer", hasProperty("addressLineOne", is(addressLineOne))))
                .andExpect(model().attribute("customer", hasProperty("addressLineTwo", is(addressLineTwo))))
                .andExpect(model().attribute("customer", hasProperty("city", is(city))))
                .andExpect(model().attribute("customer", hasProperty("state", is(state))))
                .andExpect(model().attribute("customer", hasProperty("zipCode", is(zip))));
    }

    @Test
    public void newCustomer() throws Exception {

        verifyZeroInteractions(customerService);

        mockMvc.perform(get("/customer/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customerform"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));

    }

    @Test
    public void saveOrUpdate() throws Exception {
        Integer id = 1;

        String first = "First";
        String last = "Last";
        String zip = "01234";
        String email = "first@last.com";
        String addressLineOne = "Main Boulevard 12";
        String addressLineTwo = "Sub Area 1";
        String phoneNumber = "123-456789";
        String city = "Kansas";
        String state = "Illinois";

        Customer expectedCustomer = new Customer();
        expectedCustomer.setId(id);
        expectedCustomer.setFirstName(first);
        expectedCustomer.setLastName(last);
        expectedCustomer.setZipCode(zip);
        expectedCustomer.setEmail(email);
        expectedCustomer.setAddressLineOne(addressLineOne);
        expectedCustomer.setAddressLineTwo(addressLineTwo);
        expectedCustomer.setPhoneNumber(phoneNumber);
        expectedCustomer.setCity(city);
        expectedCustomer.setState(state);

        when(customerService.saveOrUpdate(ArgumentMatchers.any())).thenReturn(expectedCustomer);

        mockMvc.perform(post("/customer")
        .param("id", "1")
        .param("firstName", first)
        .param("lastName", last)
        .param("email", email)
        .param("phoneNumber", phoneNumber)
        .param("state", state)
        .param("city", city)
        .param("addressLineOne", addressLineOne)
        .param("addressLineTwo", addressLineTwo)
        .param("zipCode", zip))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customer/show/1"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)))
                .andExpect(model().attribute("customer", hasProperty("id", is(id))))
                .andExpect(model().attribute("customer", hasProperty("firstName", is(first))))
                .andExpect(model().attribute("customer", hasProperty("lastName", is(last))))
                .andExpect(model().attribute("customer", hasProperty("email", is(email))))
                .andExpect(model().attribute("customer", hasProperty("phoneNumber", is(phoneNumber))))
                .andExpect(model().attribute("customer", hasProperty("addressLineOne", is(addressLineOne))))
                .andExpect(model().attribute("customer", hasProperty("addressLineTwo", is(addressLineTwo))))
                .andExpect(model().attribute("customer", hasProperty("city", is(city))))
                .andExpect(model().attribute("customer", hasProperty("state", is(state))))
                .andExpect(model().attribute("customer", hasProperty("zipCode", is(zip))));

        //verify properties of bound object
        ArgumentCaptor<Customer> boundProduct = ArgumentCaptor.forClass(Customer.class);
        verify(customerService).saveOrUpdate(boundProduct.capture());

        assertEquals(id, boundProduct.getValue().getId());
        assertEquals(first, boundProduct.getValue().getFirstName());
        assertEquals(last, boundProduct.getValue().getLastName());
        assertEquals(email, boundProduct.getValue().getEmail());
        assertEquals(phoneNumber, boundProduct.getValue().getPhoneNumber());
        assertEquals(addressLineOne, boundProduct.getValue().getAddressLineOne());
        assertEquals(addressLineTwo, boundProduct.getValue().getAddressLineTwo());
        assertEquals(city, boundProduct.getValue().getCity());
        assertEquals(state, boundProduct.getValue().getState());
        assertEquals(zip, boundProduct.getValue().getZipCode());
    }

    @Test
    public void editCustomer() throws Exception {
        Integer id = 1;

        String first = "First";
        String last = "Last";
        String zip = "01234";
        String email = "first@last.com";
        String addressLineOne = "Main Boulevard 12";
        String addressLineTwo = "Sub Area 1";
        String phoneNumber = "123-456789";
        String city = "Kansas";
        String state = "Illinois";

        Customer expectedCustomer = new Customer();
        expectedCustomer.setId(id);
        expectedCustomer.setFirstName(first);
        expectedCustomer.setLastName(last);
        expectedCustomer.setZipCode(zip);
        expectedCustomer.setEmail(email);
        expectedCustomer.setAddressLineOne(addressLineOne);
        expectedCustomer.setAddressLineTwo(addressLineTwo);
        expectedCustomer.setPhoneNumber(phoneNumber);
        expectedCustomer.setCity(city);
        expectedCustomer.setState(state);

        when(customerService.getById(id)).thenReturn(expectedCustomer);

        mockMvc.perform(get("/customer/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customerform"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)))
                .andExpect(model().attribute("customer", hasProperty("id", is(id))))
                .andExpect(model().attribute("customer", hasProperty("firstName", is(first))))
                .andExpect(model().attribute("customer", hasProperty("lastName", is(last))))
                .andExpect(model().attribute("customer", hasProperty("email", is(email))))
                .andExpect(model().attribute("customer", hasProperty("phoneNumber", is(phoneNumber))))
                .andExpect(model().attribute("customer", hasProperty("addressLineOne", is(addressLineOne))))
                .andExpect(model().attribute("customer", hasProperty("addressLineTwo", is(addressLineTwo))))
                .andExpect(model().attribute("customer", hasProperty("city", is(city))))
                .andExpect(model().attribute("customer", hasProperty("state", is(state))))
                .andExpect(model().attribute("customer", hasProperty("zipCode", is(zip))));
    }

    @Test
    public void deleteCustomer() throws Exception {
        Integer id = 1;

        mockMvc.perform(get("/customer/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customer/list"));

        verify(customerService, times(1)).delete(id);

    }

}