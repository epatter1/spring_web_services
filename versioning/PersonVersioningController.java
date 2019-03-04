package com.samplespring.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//versioning my API
/**
 * types of versioning
 * Media type versioning --- GitHub 
 * Custom headers versioning --- Microsoft
 * 
 * NOTE: the two types above are complicated to cache request because they have same URI, 
 * tough to send it via browser requiring a plugin and knowhow to configure an HTTP request header
 * and send the request out. Also, more legwork to generate API documentation.
 * 
 * URI versioning --- Twitter 
 * Request Header versioning --- Amazon 
 * 
 * NOTE: the two types above are better for generating API docs.
 *
 * Before you build your API, have your versioning strategy ready.
 * Factors to consider for versioning API:
 * URI Pollution
 * Misuse of HTTP Headers
 * Caching
 * Can I execute the request on the browser
 * How is API documentation generated
 * Remember - there is no 'perfect' solution
 * 
 */

@RestController
public class PersonVersioningController {
	
	//URI versioning
	@GetMapping("v1/person")
	public PersonV1 personV1() {
		return new PersonV1("Bob Charlie");
	}
	@GetMapping("v2/person")
	public PersonV2 personV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
	//Request Parameter versioning
	@GetMapping(value="/person/param", params="version=1")
	public PersonV1 paramV1() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(value="/person/param", params="version=2")
	public PersonV2 paramV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	//Header versioning
	@GetMapping(value="/person/header", headers="X-API-VERSION=1")
	public PersonV1 headerV1() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(value="/person/header", headers="X-API-VERSION=2")
	public PersonV2 headerV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	//sent as ACCEPT header (AKA ACCEPT Header or MIME type versioning)
	@GetMapping(value="/person/produces", produces="application/vnd.company.app-v1+json")
	public PersonV1 producesV1() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(value="/person/produces", produces="application/vnd.company.app-v2+json")
	public PersonV2 producesV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
}
