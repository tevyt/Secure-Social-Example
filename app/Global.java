import play.GlobalSettings;
import securesocial.core.RuntimeEnvironment;
import services.DemoEnvironment;

public class Global extends GlobalSettings {
    private RuntimeEnvironment env = new DemoEnvironment();

    @Override
    public <A> A getControllerInstance(Class<A> controllerClass) throws Exception{
        A result;

        try{
            result = controllerClass.getDeclaredConstructor(RuntimeEnvironment.class).newInstance(env);
        }catch (NoSuchMethodException e){
            result = super.getControllerInstance(controllerClass);
        }
        return result;
    }
}