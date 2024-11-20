package me.donggyeong.ingester.domain;

import java.time.ZonedDateTime;
import java.util.Map;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "raw_data")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RawData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;

	@Column(name = "action", nullable = false)
	private String action;

	@Column(name = "document", columnDefinition = "json")
	@Type(JsonType.class)
	private Map<String, Object> document;

	@Column(name = "is_valid", columnDefinition = "boolean default false", nullable = false)
	private Boolean isValid;

	@Column(name = "created_at", updatable = false)
	private ZonedDateTime createdAt;

	@PrePersist
	protected void onCreate() {
		createdAt = ZonedDateTime.now();
	}

	@Builder
	public RawData(String action, Map<String, Object> document, Boolean isValid) {
		this.action = action;
		this.document = document;
		this.isValid = isValid;
	}
}