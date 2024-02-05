package com.switchwon.payment.domain.common;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

	/**
	 * 생성일시
	 */
	@CreatedDate
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdDateTime;

	/**
	 * 생성자 아이디
	 */
	@Column(name = "created_by", nullable = false, updatable = false)
	private Long createdBy = 0L;

	/**
	 * 수정일시
	 */
	@LastModifiedDate
	@Column(name = "modified_at", nullable = false)
	private LocalDateTime modifiedDateTime;

	/**
	 * 수정자 아이디
	 */
	@Column(name = "modified_by", nullable = false)
	private Long modifiedBy = 0L;

}