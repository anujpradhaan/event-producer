package com.eventchase.producer.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private AddressType addressType;
	private String line1;
	private String line2;
	private String city;
	private String state;
	private String country;

	@ManyToOne
	@JoinColumn
	private Recipient recipient;

	public enum AddressType {
		BILLING, DELIVERY
	}
}
