package com.notify;

import com.notify.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class NotifyApplicationTests {
	@Autowired
	EmailService sender;

	@Test
	void emailTest(){
		sender.sendEmail("aditechhelp@gmail.com","Testing","Sending Email From Spring");
	}
	@Test
	void emailHTMLTest(){
		sender.sendEmailWithHTML("aditechhelp@gmail.com","Testing","<h1>Sending HTML From Spring</h1>");
	}
	@Test
	void testAttachmentEmail(){
		sender.sendEmailWithFile("aditechhelp@gmail.com","Email with Attachment","There is File Attached",new File("G:\\Users\\Aditya\\Pictures\\Wallpaper\\vegita.jpg"));
	}
}
