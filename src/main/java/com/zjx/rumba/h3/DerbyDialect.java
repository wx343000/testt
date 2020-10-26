package com.zjx.rumba.h3;

public class DerbyDialect extends org.hibernate.dialect.DerbyDialect {
  public DerbyDialect() {
    super();
  }

  @Override
  public boolean supportsLimit() {
    return true;
  }

  @Override
  public boolean supportsLimitOffset() {
    return true;
  }

  @Override
  public boolean supportsVariableLimit() {
    return true;
  }

  @Override
  public boolean useMaxForLimit() {
    return false;
  }

  @Override
  public String getLimitString(String sql, boolean hasOffset) {
    return new StringBuffer(sql.length() + 30).append(sql).append(
        (hasOffset ? " offset ? rows" : "") + " fetch next ? rows only").toString();
  }

  // @Override
  // public String getLimitString(String sql, int offset, int limit) {
  // return new StringBuffer(sql.length() + 50).append(sql).append(
  // (offset > 0 ? " offset " + offset + " rows" : "") + " fetch next " + limit
  // + " rows only").toString();
  //
  // }
  //
}
