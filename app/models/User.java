package models;


import play.db.ebean.Model;


import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@Entity
@Table(name = "users")
public class User extends Model {

    @Id
    @GeneratedValue
    public Long id;

    public Date lastLogin;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public List<Profile> identities;

    public User(Profile profile){

        identities = new ArrayList<Profile>();
        this.identities.add(profile);
        lastLogin = new Date();
    }

    public static Finder<String ,User> find = new Finder<String, User>(String.class, User.class);


}
