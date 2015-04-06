package models;


import play.db.ebean.Model;
import securesocial.core.BasicProfile;

import javax.persistence.*;

@Entity
@Table(name = "profiles")
public class Profile extends Model {

    @Id
    @GeneratedValue
    public Long id;

    public String providerId;
    public String authUserId;
    public String firstName;
    public String lastName;
    public String fullName;
    public String email;
    public String avatarUrl;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

    public Profile(BasicProfile profile){
        this.providerId = profile.providerId();
        this.authUserId  = profile.userId();
        if(profile.firstName().isDefined())
            firstName = profile.firstName().get();

        if(profile.lastName().isDefined())
            lastName  = profile.lastName().get();
        if(profile.fullName().isDefined())
            fullName = profile.fullName().get();
        if(profile.email().isDefined())
            email = profile.email().get();
        if(profile.avatarUrl().isDefined())
            avatarUrl = profile.avatarUrl().get();


    }
}
