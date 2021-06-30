package ro.fujinuji.awsmanager.utils;

import ro.fujinuji.awsmanager.model.Token;

public class TokenPlaceHolderReplacer implements PlaceholderReplacer {

    private Token token;

    public TokenPlaceHolderReplacer(Token token) {
        this.token = token;
    }

    @Override
    public String replace(String email) {
        email = email.replace("{token}", token.getTokenId());
        return email;
    }
}
