package models;


import play.db.ebean.Model;
import securesocial.core.java.Token;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity
public class LocalToken extends Model {
    @Id
    public String uuid;
    public String email;
    public Date createdAt;
    public Date expireAt;
    public boolean isSignUp;
    public static Finder<String , LocalToken> find = new Finder<String, LocalToken>(String.class , LocalToken.class);

    public LocalToken(Token token){
        this.uuid = token.uuid;
        this.email= token.email;
        this.createdAt = token.creationTime.toDate();
        this.expireAt = token.expirationTime.toDate();
        this.isSignUp = token.isSignUp;
    }
}
