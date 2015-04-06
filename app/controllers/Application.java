package controllers;


import models.User;
import play.mvc.*;

import securesocial.core.RuntimeEnvironment;
import securesocial.core.java.SecureSocial;
import securesocial.core.java.SecuredAction;
import views.html.*;

public class Application extends Controller {

    private RuntimeEnvironment<User> env;
    public Application(RuntimeEnvironment<User> env){
        this.env = env;
    }

    @SecuredAction
    public static Result index() {
        User user = (User)ctx().args.get(SecureSocial.USER_KEY);

        return ok(index.render(user,user.identities.get(0), SecureSocial.env()));
    }

    @SecuredAction
    public static Result link(){
        return ok("Your accounts have been linked");
    }

}
