package com.mulodo.websecurityapp;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin("http://localhost:8081")
@CrossOrigin
public class ResourceController {

    @GetMapping("/user")
    public String user(Principal principal) {
        return principal.getName();
    }
    
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
    	return "Hello World";
    }
    
	private static Map<String, Product> productRepo = new HashMap<>();
	static {
		Product honey = new Product();
		honey.setId("1");
		honey.setName("Honey");
		productRepo.put(honey.getId(), honey);
		Product almond = new Product();
		almond.setId("2");
		almond.setName("Almond");
		productRepo.put(almond.getId(), almond);
	}

	@RequestMapping(value = "/products", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public ResponseEntity<Object> getProduct() {
		System.out.println("getProduct is Calling");
		return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
	}
}