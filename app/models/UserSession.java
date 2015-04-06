package models;


import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sessions")
public class UserSession extends Model {
    @Id
    public Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

    public String device;

    public Date date;

    public UserSession(User user){
        this.user = user;
        this.date = user.lastLogin;
    }

    public static Finder<Long , UserSession> find = new Finder<Long, UserSession>(Long.class , UserSession.class);
}
