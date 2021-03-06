package org.ednovo.gooru.mail.controller;

import javax.mail.Address;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ednovo.gooru.mail.domain.MailDO;
import org.ednovo.gooru.mail.handler.MailHandler;
import org.ednovo.gooru.mail.serializer.JsonDeserializer;
import org.ednovo.gooru.mail.serializer.MailSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MailRestController extends MailSerializer {
	
	@Autowired
	private MailHandler mailHandler;

	@RequestMapping(method = RequestMethod.POST, value = "/send-mail")
	public ModelAndView sendMail(HttpServletRequest request,@RequestBody String data,HttpServletResponse response) throws Exception {		
		MailDO mail = buildMailFromInputParameters(data);
		if (mail != null) {
			String[] to = mail.getRecipient().split(",");
			Address[] address = new Address[to.length];
			for (int i = 0; i < to.length; i++) {
				address[i] = new InternetAddress(to[i]);
			}
			mail.setAddress(address);
			mailHandler.sendMail(mail, (System.currentTimeMillis() + 1000 * 60 * 5));
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		return toModelAndView("Your message has been sent successfully");
	}
	
	private MailDO buildMailFromInputParameters(String json) {
		return JsonDeserializer.deserialize(json, MailDO.class);
	}

}

