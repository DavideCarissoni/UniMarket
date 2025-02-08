package unimarket.views.myview;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.ScrollOptions;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.vaadin.lineawesome.LineAwesomeIconUrl;
import unimarket.services.UtenteService;

@PageTitle("My View2")
@Route("")
@Menu(order = 5, icon = LineAwesomeIconUrl.PENCIL_RULER_SOLID)
public class Login extends Composite<VerticalLayout> {

    private final UtenteService utenteService;

    public Login(UtenteService utenteService) {
        this.utenteService = utenteService;

        LoginForm loginForm = new LoginForm();

        getContent().setWidth("100%");
        getContent().setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        getContent().setAlignItems(FlexComponent.Alignment.CENTER);
        getContent().add(loginForm);

        loginForm.addLoginListener(event -> {
            String email = event.getUsername();
            String password = event.getPassword();

            if (utenteService.login(email, password)) {
                Notification.show("Login riuscito!", 3000, Notification.Position.MIDDLE);
                Integer userId = utenteService.getUserIdByEmail(email);
                VaadinSession.getCurrent().setAttribute("userId", userId);
                UI.getCurrent().navigate(MyViewView.class);
            } else {
                Notification.show("Email o password errati", 3000, Notification.Position.MIDDLE);
                loginForm.setError(true);
            }
        });
    }
}
