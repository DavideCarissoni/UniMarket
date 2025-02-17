package unimarket.views.personform;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.ElementFactory;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import org.vaadin.lineawesome.LineAwesomeIconUrl;
import unimarket.views.myview.Login;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.UI;
import unimarket.services.UtenteService;

@PageTitle("Registrazione")
@Route("registrazione")
@Menu(order = 1, icon = LineAwesomeIconUrl.USER)
public class PersonFormView extends Composite<VerticalLayout> {

    public PersonFormView(UtenteService utenteService) {
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H3 h3 = new H3();
        FormLayout formLayout2Col = new FormLayout();
        TextField textField = new TextField(); //nome
        TextField textField2 = new TextField(); //cognome
        TextField textField3 = new TextField(); //telefono
        EmailField emailField = new EmailField(); //email
        HorizontalLayout layoutRow = new HorizontalLayout();
        Button buttonPrimary = new Button();
        Button buttonSecondary = new Button();
        PasswordField password = new PasswordField();
        PasswordField repeatPassword = new PasswordField();

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(JustifyContentMode.START);
        getContent().setAlignItems(Alignment.CENTER);

        layoutColumn2.setWidth("100%");
        layoutColumn2.setMaxWidth("800px");
        layoutColumn2.setHeight("min-content");
        layoutColumn2.add(h3);
        layoutColumn2.setAlignItems(Alignment.CENTER);
        layoutColumn2.add(formLayout2Col);
        layoutColumn2.add(layoutRow);

        h3.setText("Informazioni personali");
        h3.setWidth("100%");

        formLayout2Col.setWidth("100%");
        formLayout2Col.add(textField);
        formLayout2Col.add(textField2);
        formLayout2Col.add(textField3);
        formLayout2Col.add(emailField);
        formLayout2Col.add(password);
        formLayout2Col.add(repeatPassword);
        formLayout2Col.getElement().appendChild(ElementFactory.createBr());

        textField.setLabel("Nome");
        textField2.setLabel("Cognome");
        textField3.setLabel("Numero di telefono");
        emailField.setLabel("Email");
        password.setLabel("Password");
        repeatPassword.setLabel("Ripeti password");

        password.setWidth("100%");

        repeatPassword.setWidth("100%");

        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        layoutRow.add(buttonPrimary);
        layoutRow.add(buttonSecondary);

        buttonPrimary.setText("Salva");
        buttonPrimary.setWidth("fill");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        buttonSecondary.setText("Annulla");
        buttonSecondary.setWidth("fill");

        getContent().add(layoutColumn2);
       
        buttonPrimary.addClickListener(event -> {
        	String nome = textField.getValue();
        	String cognome = textField2.getValue();
            String email = emailField.getValue();
            String telefono = textField3.getValue();
            String passwordValue = password.getValue();
            String repeatPasswordValue = repeatPassword.getValue();
           
            if (nome.isEmpty() || cognome.isEmpty() || telefono.isEmpty() || email.isEmpty() || passwordValue.isEmpty() || repeatPasswordValue.isEmpty()) {
                Notification.show("Ci sono dei campi vuoti", 3000, Notification.Position.MIDDLE);
                return;
            }
            // Validazione email
            if (!email.contains("@") ) {
                Notification.show("Email non valida", 3000, Notification.Position.MIDDLE);
                return;
            }
            // Validazione numero di telefono (deve avere 10 cifre)
            if (!telefono.matches("\\d{10}")) {
                Notification.show("Il numero di telefono deve avere esattamente 10 cifre", 3000, Notification.Position.MIDDLE);
                return;
            }
            // Validazione password (almeno 6 caratteri, una maiuscola e un numero)
            if (!isValidPassword(passwordValue)) {
                Notification.show("La password deve essere lunga almeno 6 caratteri, contenere una maiuscola e un numero", 3000, Notification.Position.MIDDLE);
                return;
            }
            // Controllo che le password siano uguali
            if (!passwordValue.equals(repeatPasswordValue)) {
                Notification.show("Le password non coincidono", 3000, Notification.Position.MIDDLE);
                return;
            }
            if(!utenteService.isNumeroTelefonoUnique(telefono)) {
                Notification.show("Numero di telefono già in uso", 3000, Notification.Position.MIDDLE);
                return;
            }
            if (!utenteService.isEmailUnique(email)) {
                Notification.show("Email già in uso", 3000, Notification.Position.MIDDLE);
                return;
            }

            // Creazione e salvataggio del nuovo utente
            utenteService.creaAccount(nome, cognome, telefono, email, passwordValue);

            Notification.show("Utente creato con successo", 3000, Notification.Position.MIDDLE);

            // Se tutte le validazioni passano, naviga a MyView
            UI.getCurrent().navigate(Login.class);
        });
    }
    
    // Metodo per validare la password
    private boolean isValidPassword(String password) {
        return password.length() >= 6 && password.matches(".*[A-Z].*") && password.matches(".*\\d.*");
    }
}