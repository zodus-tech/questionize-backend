package com.zodus.questionize.customuuid;

import com.github.f4b6a3.uuid.UuidCreator;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class CustomUUIDGenerator implements IdentifierGenerator {
  @Override
  public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
    return UuidCreator.getTimeOrderedEpoch();
  }
}
