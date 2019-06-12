package com.mulodo.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mulodo.exception.ResourceException;
import com.mulodo.model.Food;
import com.mulodo.model.Product;
import com.mulodo.model.User;

@RestController
//@CrossOrigin("http://localhost:8081")
@CrossOrigin
public class ResourceController {
    
	private static Map<String, Product> productRepo = new HashMap<>();
	private static List<Food> foods = new ArrayList<Food>();
	static {
		Product honey = new Product();
		honey.setId("1");
		honey.setName("Honey");
		productRepo.put(honey.getId(), honey);
		Product almond = new Product();
		almond.setId("2");
		almond.setName("Almond");
		productRepo.put(almond.getId(), almond);
		
		Food food1 = new Food("food1", "100 gr", 10, 20, 30, 40, 1, "manufacturer1");
		Food food2 = new Food("food2", "100 gr", 10, 20, 30, 40, 1, "manufacturer1");
		Food food3 = new Food("food3", "100 gr", 10, 20, 30, 40, 1, "manufacturer1");
		Food food4 = new Food("food4", "100 gr", 10, 20, 30, 40, 1, "manufacturer1");
		Food food5 = new Food("food5", "100 gr", 10, 20, 30, 40, 1, "manufacturer1");
		foods.add(food1);
		foods.add(food2);
		foods.add(food3);
		foods.add(food4);
		foods.add(food5);
	}

    @GetMapping("/user")
    public String user(Principal principal) {
        return principal.getName();
    }
    
    private HttpHeaders getHeaderWithAuthorization(String username, String password) {
    	String plainClientCredentials= username + ":" + password;
    	String base64ClientCredentials = new String(Base64.encodeBase64(plainClientCredentials.getBytes()));
    	 
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	headers.add("Authorization", "Basic " + base64ClientCredentials);
    	return headers;
    }

    // this is for testing only - not security purpose
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Object> login(@RequestBody User user) {
    	System.out.println("login is Calling");
    	System.out.println(user.getUsername() + " " + user.getPassword());
    	HashMap<String, Object> map = new HashMap<>();
    	if(!user.getUsername().equals("Cuong") || !user.getPassword().equals("123")) { 
    		//map.put("login-result", "wrong username or password");
    		throw new ResourceException(HttpStatus.UNAUTHORIZED, "wrong username or password");
    	}
    	map.put("login-result", "logged in successfully");
    	map.put("auth-headers", getHeaderWithAuthorization(user.getUsername(), user.getPassword()));
    	return new ResponseEntity<>(map, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
    	return "Hello World";
    }

	@RequestMapping(value = "/products", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public ResponseEntity<Object> getProduct() {
		System.out.println("getProduct is Calling");
		return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/foods", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public List<Food> getFoods() {
		System.out.println("getFoods is Calling");
		System.out.println(foods);
		return foods;
	}
	
	@RequestMapping(value = "/foods?limit={limit}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public List<Food> getFoodsWithinLimit(@PathVariable("limit") String limit) {
		System.out.println("getFoodsWithinLimit is Calling, limi=" + limit);
		System.out.println(foods);
		return foods; // should handle more here
	}
}