package com.fimepay.merchantapp.idempotency.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;



// src/main/java/com/fimepay/merchantapp/idempotency/model/IdempotencyKey.java
@Entity
@Table(name="idempotency_keys",
       uniqueConstraints=@UniqueConstraint(
         columnNames={"merchant_id","idempotency_key","http_method","request_path"}))
public class IdempotencyKey {
  @Id @GeneratedValue
  private UUID id;

  @Column(name="merchant_id", nullable=false)
  private UUID merchantId;

  @Column(name="idempotency_key", nullable=false)
  private String key;

  @Column(name="http_method", nullable=false)
  private String method;

  @Column(name="request_path", nullable=false, columnDefinition="TEXT")
  private String path;

  @Column(name="request_hash", nullable=false)
  private String requestHash;

  @Column(name="response_status", nullable=false)
  private int responseStatus;

  @Column(name="response_body", columnDefinition="TEXT")
  private String responseBody;

  @Column(name="created_at", nullable=false)
  private LocalDateTime createdAt = LocalDateTime.now();

  @Column(name="expires_at", nullable=false)
  private LocalDateTime expiresAt;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public UUID getMerchantId() {
    return merchantId;
  }

  public void setMerchantId(UUID merchantId) {
    this.merchantId = merchantId;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getRequestHash() {
    return requestHash;
  }

  public void setRequestHash(String requestHash) {
    this.requestHash = requestHash;
  }

  public int getResponseStatus() {
    return responseStatus;
  }

  public void setResponseStatus(int responseStatus) {
    this.responseStatus = responseStatus;
  }

  public String getResponseBody() {
    return responseBody;
  }

  public void setResponseBody(String responseBody) {
    this.responseBody = responseBody;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getExpiresAt() {
    return expiresAt;
  }

  public void setExpiresAt(LocalDateTime expiresAt) {
    this.expiresAt = expiresAt;
  }

  // getters / setters ...
}
