package project.dao.entity;

import java.math.BigDecimal;
import java.time.Instant;

public class Resort {
	private final Long id;
	private final String name;
	private final String description;
	private final String location;
	private final String howToGetThere;
	private final BigDecimal resortFee;
	private final BigDecimal cottageFee;
	private final BigDecimal poolFee;
	private final String resortImage;
	private final String poolImage;
	private final String cottageImage;
	private final int townid;
	private final Long userid;
	private final Instant createdAt;
	private final Instant updatedAt;
	
	public Resort(Long id, String name, String description, String location, String howToGetThere, BigDecimal resortFee,
			BigDecimal cottageFee, BigDecimal poolFee, String resortImage, String poolImage, String cottageImage,
			int townid, Long userid, Instant createdAt, Instant updatedAt) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.location = location;
		this.howToGetThere = howToGetThere;
		this.resortFee = resortFee;
		this.cottageFee = cottageFee;
		this.poolFee = poolFee;
		this.resortImage = resortImage;
		this.poolImage = poolImage;
		this.cottageImage = cottageImage;
		this.townid = townid;
		this.userid = userid;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getLocation() {
		return location;
	}

	public String getHowToGetThere() {
		return howToGetThere;
	}

	public BigDecimal getResortFee() {
		return resortFee;
	}

	public BigDecimal getCottageFee() {
		return cottageFee;
	}

	public BigDecimal getPoolFee() {
		return poolFee;
	}

	public String getResortImage() {
		return resortImage;
	}

	public String getPoolImage() {
		return poolImage;
	}

	public String getCottageImage() {
		return cottageImage;
	}

	public int getTownid() {
		return townid;
	}

	public Long getUserid() {
		return userid;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}
}
