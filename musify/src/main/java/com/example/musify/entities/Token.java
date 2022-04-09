package com.example.musify.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tokens")
public class Token {

  @Id
  @GeneratedValue
  private long tokenId;

  private String token;
  private java.sql.Timestamp expiryDate;

  @ManyToOne(fetch = FetchType.LAZY)
  private User user;

  public Token(long tokenId, String token, Timestamp expiryDate) {
    this.tokenId = tokenId;
    this.token = token;
    this.expiryDate = expiryDate;
  }

  public long getTokenId() {
    return tokenId;
  }

  public void setTokenId(long tokenId) {
    this.tokenId = tokenId;
  }


  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }


  public java.sql.Timestamp getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(java.sql.Timestamp expiryDate) {
    this.expiryDate = expiryDate;
  }

}
