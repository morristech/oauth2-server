package com.clouway.oauth2;

import com.clouway.oauth2.http.Request;
import com.clouway.oauth2.http.Response;

import static com.google.common.io.BaseEncoding.base64;

/**
 * AuthorizationHeaderCredentialsRequest is a request which is decoding Authorization header into ClientCredentials
 * pair.
 *
 * @author Miroslav Genov (miroslav.genov@clouway.com)
 */
class AuthorizationHeaderCredentialsRequest implements InstantaneousRequest {

  private final ClientRequest clientRequest;

  AuthorizationHeaderCredentialsRequest(ClientRequest clientRequest) {
    this.clientRequest = clientRequest;
  }

  @Override
  public Response handleAsOf(Request request, DateTime instantTime) {
    ClientCredentials clientCredentials = decodeCredentials(request);
    return clientRequest.handleAsOf(request, clientCredentials, instantTime);
  }

  private ClientCredentials decodeCredentials(Request request) {
    String[] credentials = decodeAuthorizationHeader(request).split(":");

    String clientId = credentials[0];
    String clientSecret = credentials[1];

    return new ClientCredentials(clientId, clientSecret);
  }

  private String decodeAuthorizationHeader(Request request) {
    String authHeader = request.header("Authorization");

    String credentials = authHeader.substring(6);

    return new String(base64().decode(credentials));
  }
}