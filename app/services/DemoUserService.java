package services;
import models.Profile;
import models.User;
import play.libs.F;
import securesocial.core.BasicProfile;
import securesocial.core.PasswordInfo;
import securesocial.core.java.BaseUserService;
import securesocial.core.java.Token;
import securesocial.core.services.SaveMode;

import java.util.Date;
import java.util.List;

public class DemoUserService extends BaseUserService<User> {

    @Override
    public F.Promise<User> doSave(BasicProfile basicProfile, SaveMode saveMode) {
     User result = null;
     if(saveMode == SaveMode.SignUp()){
         Profile profile = new Profile(basicProfile);
         result = new User(profile);
         profile.user = result;
         result.save();
     }else if(saveMode == SaveMode.LoggedIn()){
         List<User> users = User.find.all();
         for(User user: users){
             for(Profile p : user.identities) {
                 if (p.authUserId.equals(basicProfile.userId()) && p.providerId.equals(basicProfile.providerId())) {
                    result = user;
                    result.lastLogin = new Date();
                    result.update();
                 }
             }
         }
     }else{
         throw new RuntimeException("Unknown mode");
     }
     return F.Promise.pure(result);
    }

    @Override
    public F.Promise<User> doLink(User user, BasicProfile basicProfile) {
        User target;
        User u = User.find.byId(user.id.toString());
        if(u == null) {
            throw new RuntimeException("Cant find user");
        }
        target = u;


        boolean linked = false;
        for(Profile p : target.identities){
            if(p.authUserId.equals(basicProfile.userId()) && p.providerId.equals(basicProfile.providerId())){
                linked = true;
            }
        }
        if(!linked) {
            target.identities.add(new Profile(basicProfile));
            target.update();
        }

        return F.Promise.pure(target);
    }

    @Override
    public F.Promise<BasicProfile> doFind(String providerId, String userId) {
        BasicProfile found = null;
        List<User> users = User.find.all();
        for(User u: users){
            for(Profile i : u.identities){
                if(i.providerId.equals(providerId) && i.authUserId.equals(userId)){
                    found = new BasicProfile(providerId , userId , null , null , null , null , null , null , null , null , null);
                    break;
                }
            }
        }
        return F.Promise.pure(found);
    }

    @Override
    public F.Promise<BasicProfile> doFindByEmailAndProvider(String s, String s1) {
        return null;
    }

    @Override
    public F.Promise<Token> doSaveToken(Token token) {
        return null;
    }

    @Override
    public F.Promise<Token> doDeleteToken(String s) {
        return null;
    }

    @Override
    public void doDeleteExpiredTokens() {

    }

    @Override
    public F.Promise<Token> doFindToken(String s) {
        return null;
    }

    @Override
    public F.Promise<PasswordInfo> doPasswordInfoFor(User user) {
        return null;
    }

    @Override
    public F.Promise<BasicProfile> doUpdatePasswordInfo(User user, PasswordInfo passwordInfo) {
        return null;
    }
}
