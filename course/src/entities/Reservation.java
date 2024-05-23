package entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import exceptions.DomainException;

public class Reservation {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
	
	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	public Reservation() {
	}

	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {
		Date now = new Date();
		if (checkIn.before(now) || checkOut.before(now) ) {
			throw new  DomainException("Erro in reservation: Reservation dates for updates must befuture");
		}
		if (!checkOut.after(checkIn)) {
			throw new  DomainException ("Error in reservation: Check-out date must be after check-in date");
		}
		
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckin() {
		return checkIn;
	}

	public Date getCheckout() {
		return checkOut;
	}

	
	public long duration() {
		long diff = checkOut.getTime() - checkIn.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	public void updateDates(Date checkIn, Date checkOut) {
		
		Date now = new Date();
		if (checkIn.before(now) || checkOut.before(now) ) {
			throw new  DomainException("Erro in reservation: Reservation dates for updates must befuture");
		}
		if (!checkOut.after(checkIn)) {
			throw new  DomainException ("Error in reservation: Check-out date must be after check-in date");
		}
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Room ");
		s.append(roomNumber);
		s.append(", check-in: ");
		s.append(sdf.format(checkIn));
		s.append(", check-out: ");
		s.append(sdf.format(checkOut));
		s.append(", ");
		s.append(duration());
		s.append(" nights");
		
		
		return s.toString();
	}
}
