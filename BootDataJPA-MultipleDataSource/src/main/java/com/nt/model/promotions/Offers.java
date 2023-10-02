package com.nt.model.promotions;

import java.time.LocalDateTime;

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
@Table(name="MDS_OFFERS")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Offers {
	@Id
	@GeneratedValue
	private Integer offerId;
	@Nonnull
	@Column(length = 20)
	private String offerName;
	@Nonnull
	@Column(length = 20)
	private String offerCode;
	@Nonnull
	private Double discountPercentage;
	@Nonnull
	private LocalDateTime expDate;
}
