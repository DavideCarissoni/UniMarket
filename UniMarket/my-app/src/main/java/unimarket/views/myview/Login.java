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
import org.vaadin.lineawesome.LineAwesomeIconUrl;
import unimarket.services.UtenteService;

@PageTitle("My View2")
@Route("my-view")
@Menu(order = 5, icon = LineAwesomeIconUrl.PENCIL_RULER_SOLID)
public class Login extends Composite<VerticalLayout> {

    private final UtenteService utenteService;

    public Login(UtenteService utenteService) {
        this.utenteService = utenteService;

        LoginForm loginForm = new LoginForm();
        loginForm.setI18n(createItalianLoginI18n());

        getContent().setWidth("100%");
        getContent().setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        getContent().setAlignItems(FlexComponent.Alignment.CENTER);
        getContent().add(loginForm);

        loginForm.addLoginListener(event -> {
            String email = event.getUsername();
            String password = event.getPassword();

            if (utenteService.login(email, password)) {
                Notification.show("Login riuscito!", 3000, Notification.Position.MIDDLE);
                UI.getCurrent().navigate(MyViewView.class);
            } else {
                Notification.show("Email o password errati", 3000, Notification.Position.MIDDLE);
                loginForm.setError(true);
            }
        });
    }

    private LoginI18n createItalianLoginI18n() {
        LoginI18n i18n = LoginI18n.createDefault();
        i18n.getForm().setUsername("Email");
        i18n.getForm().setPassword("Password");
        i18n.getForm().setSubmit("Accedi");
        i18n.getForm().setForgotPassword("Password dimenticata?");
        i18n.getErrorMessage().setTitle("Errore di accesso");
        i18n.getErrorMessage().setMessage("Controlla email e password e riprova.");
        return i18n;
    }
}
