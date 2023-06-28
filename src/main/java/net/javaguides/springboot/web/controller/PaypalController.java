package net.javaguides.springboot.web.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import net.javaguides.springboot.model.PaypalOrder;
import net.javaguides.springboot.service.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PaypalController {

	@Autowired
	PaypalService service;

	public static final String SUCCESS_URL = "pay/success";
	public static final String CANCEL_URL = "pay/cancel";

	@GetMapping("/paypal")
	public String home_paypal() {
		return "home_paypal";
	}

	@PostMapping("/pay")
	public String payment(@ModelAttribute("order") PaypalOrder paypalOrder) {
		try {
			Payment payment = service.createPayment(paypalOrder.getPrice(), paypalOrder.getCurrency(), paypalOrder.getMethod(),
					paypalOrder.getIntent(), paypalOrder.getDescription(), "http://localhost:9090/" + CANCEL_URL,
					"http://localhost:9090/" + SUCCESS_URL);
			for(Links link:payment.getLinks()) {
				if(link.getRel().equals("approval_url")) {
					return "redirect:"+link.getHref();
				}
			}
			
		} catch (PayPalRESTException e) {
		
			e.printStackTrace();
		}
		return "redirect:/";
	}
	
	 @GetMapping(value = CANCEL_URL)
	 public String cancelPay() {
	        return "cancel";
	    }

		@GetMapping(value = SUCCESS_URL)
	    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
	        try {
	            Payment payment = service.executePayment(paymentId, payerId);
	            System.out.println(payment.toJSON());
	            if (payment.getState().equals("approved")) {
	                return "success";
	            }
	        } catch (PayPalRESTException e) {
	         System.out.println(e.getMessage());
	        }
	        return "redirect:/";
	    }

}
