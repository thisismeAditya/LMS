package com.LMS.lms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class RequestIssueCompositeId implements Serializable {

	private String memberMailId;
	
	private String bookName;

	private String bookAuthor;

	public RequestIssueCompositeId() {
		super();
	}

	public RequestIssueCompositeId(String memberMailId, String bookName, String bookAuthor) {
		super();
		this.memberMailId = memberMailId;
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookAuthor == null) ? 0 : bookAuthor.hashCode());
		result = prime * result + ((bookName == null) ? 0 : bookName.hashCode());
		result = prime * result + ((memberMailId == null) ? 0 : memberMailId.hashCode());
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
		RequestIssueCompositeId other = (RequestIssueCompositeId) obj;
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
		if (memberMailId == null) {
			if (other.memberMailId != null)
				return false;
		} else if (!memberMailId.equals(other.memberMailId))
			return false;
		return true;
	}
	
	
}
