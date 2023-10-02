package com.nt.model.prod;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="MDS_PRODUCT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Product {
	@Id
	@GeneratedValue
	private Integer pid;
	@Nonnull
	@Column(length = 20)
	private String pname;
	@Nonnull
	private Double price;
	@Nonnull
	private Double qty;
}
