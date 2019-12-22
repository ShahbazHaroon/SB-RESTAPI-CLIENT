package com.ubaidsample.springboot;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.web.client.RestTemplate;
import com.ubaidsample.springboot.model.User;

public class SpringBootRestTestClient {

	public static final String REST_SERVICE_URI = "http://localhost:8095/SpringBootRestApi/api";

	// - - - - - - - - - - GET - Retrieve All Users
	@SuppressWarnings("unchecked")
	private static void listAllUsers() {
		
		System.out.println("Testing listAllUsers API-----------");

		RestTemplate restTemplate = new RestTemplate();
		
		List<LinkedHashMap<String, Object>> usersMap = restTemplate.getForObject(REST_SERVICE_URI + "/user/",
				List.class);

		if (usersMap != null) {
			
			for (LinkedHashMap<String, Object> map : usersMap) {
				
				System.out.println("User : id=" + map.get("id") + ", Name=" + map.get("name") + ", Age="
						+ map.get("age") + ", Salary=" + map.get("salary"));
				;
			}
		} else {
			
			System.out.println("No user exist----------");
		}
	}

	// - - - - - - - - - - GET - Retrieve Single User
	private static void getUser() {
		
		System.out.println("Testing getUser API----------");
		
		RestTemplate restTemplate = new RestTemplate();
		
		User user = restTemplate.getForObject(REST_SERVICE_URI + "/user/1", User.class);
		
		System.out.println(user);
	}

	// - - - - - - - - - - POST - Create a User
	private static void createUser() {
		
		System.out.println("Testing create User API----------");
		
		RestTemplate restTemplate = new RestTemplate();
		
		User user = new User(0, "Test User - 5", 90, 190000);
		
		URI uri = restTemplate.postForLocation(REST_SERVICE_URI + "/user/", user, User.class);
		
		System.out.println("Location : " + uri.toASCIIString());
	}

	// - - - - - - - - - - PUT - Update a User
	private static void updateUser() {
		
		System.out.println("Testing update User API----------");
		
		RestTemplate restTemplate = new RestTemplate();
		
		User user = new User(1, "Test User - 1", 50, 150000);
		
		restTemplate.put(REST_SERVICE_URI + "/user/1", user);
		
		System.out.println(user);
	}

	// - - - - - - - - - - DELETE - Delete a User
	private static void deleteUser() {
		
		System.out.println("Testing delete User API----------");
		
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.delete(REST_SERVICE_URI + "/user/3");
	}

	// - - - - - - - - - - DELETE - Delete All Users
	private static void deleteAllUsers() {
		
		System.out.println("Testing all delete Users API----------");
		
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.delete(REST_SERVICE_URI + "/user/");
	}

	public static void main(String args[]) {
		
		listAllUsers();
		
		getUser();
		
		createUser();
		
		listAllUsers();
		
		updateUser();
		
		listAllUsers();
		
		deleteUser();
		
		listAllUsers();
		
		deleteAllUsers();
		
		listAllUsers();
	}
}