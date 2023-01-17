package com.crud.pojo;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "domainevents")
public class Domainevents {
	private String _id;
	private String aggregateIdentifier;
	private String type;
	private String sequenceNumber;
	private String serializedPayload;
	private String timestamp;
	private String payloadType;
	private String payloadRevision;
	private String serializedMetaData;
	private String eventIdentifier;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getAggregateIdentifier() {
		return aggregateIdentifier;
	}

	public void setAggregateIdentifier(String aggregateIdentifier) {
		this.aggregateIdentifier = aggregateIdentifier;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(String sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public String getSerializedPayload() {
		return serializedPayload;
	}

	public void setSerializedPayload(String serializedPayload) {
		this.serializedPayload = serializedPayload;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getPayloadType() {
		return payloadType;
	}

	public void setPayloadType(String payloadType) {
		this.payloadType = payloadType;
	}

	public String getPayloadRevision() {
		return payloadRevision;
	}

	public void setPayloadRevision(String payloadRevision) {
		this.payloadRevision = payloadRevision;
	}

	public String getSerializedMetaData() {
		return serializedMetaData;
	}

	public void setSerializedMetaData(String serializedMetaData) {
		this.serializedMetaData = serializedMetaData;
	}

	public String getEventIdentifier() {
		return eventIdentifier;
	}

	public void setEventIdentifier(String eventIdentifier) {
		this.eventIdentifier = eventIdentifier;
	}

	@Override
	public String toString() {
		return "Domainevents : [_id=" + _id + ", aggregateIdentifier=" + aggregateIdentifier + ", type=" + type
				+ ", sequenceNumber=" + sequenceNumber + ", serializedPayload=" + serializedPayload + ", timestamp="
				+ timestamp + ", payloadType=" + payloadType + ", payloadRevision=" + payloadRevision
				+ ", serializedMetaData=" + serializedMetaData + ", eventIdentifier=" + eventIdentifier + "]" + "\n</br>";
	}
}
