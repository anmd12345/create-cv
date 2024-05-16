package com.tdmu.service;


public interface EmailService {

	void sendSimpleMail(String toEmail, String body, String subject);
}
