package com.eventchase.producer.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
@Data
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String orderId;

	@OneToOne(cascade = CascadeType.ALL)
	private Recipient recipient;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
	private List<Product> products;

}
