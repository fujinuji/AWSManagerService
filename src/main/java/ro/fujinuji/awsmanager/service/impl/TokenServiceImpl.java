package ro.fujinuji.awsmanager.service.impl;

import org.springframework.stereotype.Service;
import ro.fujinuji.awsmanager.model.Token;
import ro.fujinuji.awsmanager.model.exception.AWSManagerException;
import ro.fujinuji.awsmanager.repository.TokenRepository;
import ro.fujinuji.awsmanager.service.TokenService;

@Service
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;

    public TokenServiceImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public String saveToken(Token token) throws AWSManagerException {
        return tokenRepository.save(token).getTokenId();
    }

    @Override
    public Token getTokenById(String tokenId) throws AWSManagerException {
        return tokenRepository.getOne(tokenId);
    }
}
