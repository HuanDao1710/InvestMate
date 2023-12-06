package vn.edu.hust.investmate.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
	@Column(name="created_date")
	@CreatedDate
	protected Date createdDate;
	@Column(name = "modified_date")
	@LastModifiedDate
	protected Date modifiedDate;
	@Column(name = "created_by")
	protected String createdBy;
	@Column(name="modified_by")
	protected String modifiedBy;
}
