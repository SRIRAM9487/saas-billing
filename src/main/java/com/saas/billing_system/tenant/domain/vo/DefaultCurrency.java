package com.saas.billing_system.tenant.domain.vo;

public enum DefaultCurrency {

  INR(0, "INR", "₹"),
  USD(1, "USD", "$");

  private final int id;
  private final String code;
  private final String symbol;

  DefaultCurrency(int id, String code, String symbol) {
    this.id = id;
    this.code = code;
    this.symbol = symbol;
  }

  public int getId() {
    return id;
  }

  public String getCode() {
    return code;
  }

  public String getSymbol() {
    return symbol;
  }

  @Override
  public String toString() {
    return code;
  }

  public static DefaultCurrency fromId(int id) {
    for (DefaultCurrency currency : values()) {
      if (currency.id == id)
        return currency;
    }
    throw new IllegalArgumentException("Unknown currency id: " + id);
  }
}
