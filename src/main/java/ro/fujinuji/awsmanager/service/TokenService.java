package ro.fujinuji.awsmanager.service;

import ro.fujinuji.awsmanager.model.Token;
import ro.fujinuji.awsmanager.model.exception.AWSManagerException;

public interface TokenService {

    String saveToken(Token token) throws AWSManagerException;

    Token getTokenById(String tokenId) throws AWSManagerException;
}
