package edu.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.twilio.Twilio;

import edu.training.configuration.TwilioConfig;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
@EnableConfigurationProperties
@EnableScheduling
public class Collection1Application {

	@Autowired
	
	private TwilioConfig twilioConfig;

	@PostConstruct
	public void setup() {
		Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
	}
	public static void main(String[] args) {
		SpringApplication.run(Collection1Application.class, args);
	}

}
