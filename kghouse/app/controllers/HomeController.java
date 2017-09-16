
package controllers;
import actors.MessageActor;
import data.LoginForm;
import play.Configuration;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.LegacyWebSocket;
import play.mvc.Result;
import play.mvc.Results;
import play.mvc.WebSocket;
import views.html.index;
import views.html.login;
import javax.inject.Inject;
import java.util.Objects;public class HomeController extends Controller {    
   @Inject
   Configuration configuration;    
   @Inject
   FormFactory formFactory;    
   public Result index() {
      return ok(views.html.index.render());
   }    
   public Result login() {
      return ok(views.html.login.render(""));
   }    
   public Result authenticate(){
      Form<LoginForm> loginFormForm = formFactory.form(LoginForm.class).bindFromRequest();
      if(Objects.equals(configuration.getString("app.user.username"), loginFormForm.get().getUsername()) &&
              Objects.equals(configuration.getString("app.user.password"), loginFormForm.get().getPassword())){
          return ok();
      }
      return Results.unauthorized();
   }    
   public LegacyWebSocket<String> chatSocket() {
      return WebSocket.withActor(MessageActor::props);
   }
}