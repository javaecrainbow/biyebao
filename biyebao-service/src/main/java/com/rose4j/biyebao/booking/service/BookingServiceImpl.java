package com.rose4j.biyebao.booking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rose4j.biyebao.booking.dao.BookingMapper;
import com.rose4j.biyebao.domain.Booking;

@Service
public class BookingServiceImpl implements BookingService {
	@Autowired
	private BookingMapper bookingMapper;

	@Override
	public boolean postBooking(Booking b) {
		try {
			bookingMapper.apply(b);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
