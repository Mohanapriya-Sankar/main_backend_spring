package edu.training.service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import edu.training.configuration.TwilioConfig;
import edu.training.dto.OtpRequest;
import edu.training.dto.OtpResponseDto;
import edu.training.dto.OtpStatus;
import edu.training.dto.OtpValidationRequest;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SmsService {
	
	@Autowired
	private TwilioConfig twilioConfig;
    Map<String, String> otpMap = new HashMap<>();


	public OtpResponseDto sendSMS(OtpRequest otpRequest) {
		OtpResponseDto otpResponseDto = null;
		String otp=null;
		try {
//			String pnumber= "+91"+otpRequest.getPhoneNumber();
//			System.out.println(pnumber);
//			PhoneNumber to = new PhoneNumber(pnumber);
			PhoneNumber to = new PhoneNumber(otpRequest.getPhoneNumber());//to
			PhoneNumber from = new PhoneNumber(twilioConfig.getPhoneNumber()); // from
			otp = generateOTP();
			String otpMessage = "Dear Customer , Your OTP is  " + otp + " for sending sms through Spring boot application. Thank You.";
			Message message = Message
			        .creator(to, from,
			                otpMessage)
			        .create();
			otpMap.put(otpRequest.getUsername(), otp);
			otpResponseDto = new OtpResponseDto(true,otpMessage,Long.parseLong(otp));
		} catch (Exception e) {
			e.printStackTrace();
			otpResponseDto = new OtpResponseDto(false,e.getMessage(),Long.parseLong(otp));
		}
		return otpResponseDto;
	}
	
	public String validateOtp(OtpValidationRequest otpValidationRequest,long otp) {
		
        if (otpValidationRequest.getOtpNumber().equals(otp)) {
            
            return "OTP is valid!";
        } else {
            return "OTP is invalid!";
        }
	}
	
	private String generateOTP() {
        return new DecimalFormat("000000")
                .format(new Random().nextInt(999999));
    }
	

}
