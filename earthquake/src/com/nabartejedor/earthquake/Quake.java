package com.nabartejedor.earthquake;

import java.text.SimpleDateFormat;
import java.util.Date;



public class Quake {

	private long id;
	private String idx;
	private String place;
	private String detail;
	private String url;
	private long time;
	private double mag;
	private double lat;
	private double lng;
	
	public Quake() {
		
	}
	
	public Quake(long id, String idx, String place, String detail, String url, long time, double mag, double lat,
			double lng) {
		super();
		this.id = id;
	    this.idx = idx;
		this.place = place;
		this.detail = detail;
		this.url = url;
		this.time = time;
		this.mag = mag;
		this.lat = lat;
		this.lng = lng;
	}

	
	public String getIdx() {
		return idx;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getTime() {
		return time;
	}
	
	public void setTime(long time) {
		this.time = time;
	}
	
	public double getMag() {
		return mag;
	}

	public void setMag(double mag) {
		this.mag = mag;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}
	
	@Override
	public String toString(){
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		return place + " " + df.format(new Date(time)) + " " + mag;
	}
	
}
