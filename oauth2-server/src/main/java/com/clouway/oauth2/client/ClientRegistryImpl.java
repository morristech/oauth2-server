package com.clouway.oauth2.client;

import com.clouway.oauth2.token.TokenGenerator;
import com.google.inject.Inject;

/**
 * @author Mihail Lesikov (mlesikov@gmail.com)
 */
class ClientRegistryImpl implements ClientRegistry {

  private TokenGenerator tokenGenerator;
  private ClientRepository repository;

  @Inject
  public ClientRegistryImpl(TokenGenerator tokenGenerator, ClientRepository repository) {
    this.tokenGenerator = tokenGenerator;
    this.repository = repository;
  }

  @Override
  public RegistrationResponse register(RegistrationRequest request) {
    String id = tokenGenerator.generate();
    String secret = tokenGenerator.generate();

    Client client = new Client(id, secret, request.name, request.url, request.description, request.redirectURI);

    repository.save(client);

    return new RegistrationResponse(id, secret);
  }

}