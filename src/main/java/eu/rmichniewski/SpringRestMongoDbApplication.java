package eu.rmichniewski;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.ServiceInstance;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


//@EnableDiscoveryClient
@SpringBootApplication
public class SpringRestMongoDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestMongoDbApplication.class, args);
	}
}
//@RestController
//class ServiceInstanceRestController {
//
//	@Autowired
//	private DiscoveryClient discoveryClient;
//
//	@RequestMapping("/service-instances/{applicationName}")
//	public List<ServiceInstance> serviceInstancesByApplicationName(
//			@PathVariable String applicationName) {
//		return this.discoveryClient.getInstances(applicationName);
//	}
//}