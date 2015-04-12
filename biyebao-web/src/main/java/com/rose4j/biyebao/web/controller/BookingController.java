package com.rose4j.biyebao.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rose4j.biyebao.booking.service.BookingService;
import com.rose4j.biyebao.domain.Booking;

@Controller
@RequestMapping("/")
public class BookingController extends BaseController {
	@Autowired
	private BookingService bookingService;

	@RequestMapping(value = "booking/request.html")
	public String requestForHandler(Model model, Booking booking,
			HttpServletRequest request) {
		booking.setBookingTime(new Date());
		booking.setIp(getRemoteAddress(request));
		return "booking/apply";
	}

	@RequestMapping(value = "booking/apply.html")
	public String applyForHandler(Model model, Booking booking,
			HttpServletRequest request) {
		booking.setBookingTime(new Date());
		booking.setIp(getRemoteAddress(request));
		booking.setVersion(0);
		boolean status = bookingService.postBooking(booking);
		model.addAttribute("status", status);
		return "booking/success";
	}

	@RequestMapping(value = "booking/success.html")
	public String applyForHandler(Model model) {
		return "booking/success";
	}
}
