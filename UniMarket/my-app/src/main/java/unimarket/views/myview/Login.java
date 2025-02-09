package unimarket.views.myview;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinSession;
import org.vaadin.lineawesome.LineAwesomeIconUrl;
import unimarket.services.UtenteService;
import unimarket.views.personform.PersonFormView;

@PageTitle("Login")
@Route("")
public class Login extends Composite<VerticalLayout> {

    private final UtenteService utenteService;

    public Login(UtenteService utenteService) {
        this.utenteService = utenteService;
    
        // Layout principale
        VerticalLayout loginBox = new VerticalLayout();
        loginBox.getStyle().set("border", "1px solid #ccc"); // Stile del box
        loginBox.getStyle().set("padding", "20px");
        loginBox.getStyle().set("border-radius", "5px");
        loginBox.setWidth("300px");

        // Campi di input
        TextField emailField = new TextField("Email");
        PasswordField passwordField = new PasswordField("Password");

        // Gestione errori
        Paragraph errorMessage = new Paragraph();
        errorMessage.getStyle().set("color", "red"); 
        errorMessage.setVisible(false);
        
        // Pulsante di login
        Button loginButton = new Button("Login", event -> {
            String email = emailField.getValue();
            String password = passwordField.getValue();

            if (utenteService.login(email, password)) {
                Notification.show("Login riuscito!", 3000, Notification.Position.MIDDLE);
                Integer userId = utenteService.getUserIdByEmail(email);
                VaadinSession.getCurrent().setAttribute("userId", userId);
                UI.getCurrent().navigate(MyViewView.class);
                
            } else {
                Notification.show("Email o password errati", 3000, Notification.Position.MIDDLE);
                errorMessage.setText("Email o password errati");
                errorMessage.setVisible(true);            }
        });
        
        // Messaggio di registrazione
        Paragraph registerMessage = new Paragraph("Non hai un account? ");
        RouterLink registerLink = new RouterLink("Registrati qui", PersonFormView.class);
        registerMessage.add(registerLink);

        loginBox.add(emailField, passwordField, loginButton, errorMessage, registerMessage);
        loginBox.setAlignItems(FlexComponent.Alignment.CENTER);

        // Aggiungi il box al layout principale
        getContent().setWidth("100%");
        getContent().setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        getContent().setAlignItems(FlexComponent.Alignment.CENTER);
        getContent().add(loginBox);
    
    }

       /*
        // backend

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
    */
}
