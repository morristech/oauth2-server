package com.example.auth.memory;

import com.example.auth.core.Clock;
import com.example.auth.core.Interval;
import com.example.auth.core.TokenGenerator;
import com.example.auth.core.TokenRepository;
import com.example.auth.core.TokenRepositoryVerificationContractTest;

/**
 * @author Ivan Stefanov <ivan.stefanov@clouway.com>
 */
public class InMemoryTokenRepositoryVerificationTest extends TokenRepositoryVerificationContractTest {
  @Override
  protected TokenRepository create(TokenGenerator tokenGenerator, Clock clock, Interval interval) {
    return new InMemoryTokenRepository(tokenGenerator, clock, interval);
  }
}