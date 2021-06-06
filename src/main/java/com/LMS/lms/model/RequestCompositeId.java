package com.LMS.lms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class RequestCompositeId implements Serializable {

	private String requesterMailId;
	
	private String bookName;

	private String bookAuthor;
	

	public RequestCompositeId() {
		super();
	}

	public RequestCompositeId(String requesterMailId, String bookName, String bookAuthor) {
		super();
		this.requesterMailId = requesterMailId;
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookAuthor == null) ? 0 : bookAuthor.hashCode());
		result = prime * result + ((bookName == null) ? 0 : bookName.hashCode());
		result = prime * result + ((requesterMailId == null) ? 0 : requesterMailId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RequestCompositeId other = (RequestCompositeId) obj;
		if (bookAuthor == null) {
			if (other.bookAuthor != null)
				return false;
		} else if (!bookAuthor.equals(other.bookAuthor))
			return false;
		if (bookName == null) {
			if (other.bookName != null)
				return false;
		} else if (!bookName.equals(other.bookName))
			return false;
		if (requesterMailId == null) {
			if (other.requesterMailId != null)
				return false;
		} else if (!requesterMailId.equals(other.requesterMailId))
			return false;
		return true;
	}
	
	
	
}
