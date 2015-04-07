package controllers;


import models.User;
import models.UserSession;
import models.Profile;
import play.mvc.*;

import securesocial.core.RuntimeEnvironment;
import securesocial.core.java.SecureSocial;
import securesocial.core.java.SecuredAction;
import views.html.*;

import java.util.List;

public class Application extends Controller {

    private RuntimeEnvironment<User> env;
    public Application(RuntimeEnvironment<User> env){
        this.env = env;
    }

    @SecuredAction
    public static Result index() {
        String message = flash("success");
        if(message == null){
            message = "Welcome";
        }
        User user = (User)ctx().args.get(SecureSocial.USER_KEY);
        List<UserSession> sessions = UserSession.find.all();
        boolean stored = false;
        for(UserSession s: sessions){
            if(s.user.id.equals(user.id) && s.date.equals(user.lastLogin)){
                stored = true;
                break;
            }
        }
        if(!stored){
            UserSession session = new UserSession(user);
            session.device = request().getHeader("User-Agent");
            user.sessions.add(session);
            user.update();
        }
        return ok(index.render(message ,user,user.identities.get(0), SecureSocial.env()));
    }

    @SecuredAction
    public static Result link(){
        flash("success" , "Your accounts have been linked");
        return redirect("/");
    }

    public static Result sessions(){
        return ok(sessions.render(UserSession.find.all()));
    }

    
    public static Result users(){
        return ok(users.render(User.find.all()));
    }

    public static Result profiles(){
        return ok(profiles.render(Profile.find.all()));
    }

}
