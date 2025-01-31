package unimarket.views.checkoutform;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.Component;

import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;

import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;

import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;
import com.vaadin.flow.theme.lumo.LumoUtility.Background;
import com.vaadin.flow.theme.lumo.LumoUtility.BorderRadius;
import com.vaadin.flow.theme.lumo.LumoUtility.BoxSizing;
import com.vaadin.flow.theme.lumo.LumoUtility.Display;
import com.vaadin.flow.theme.lumo.LumoUtility.Flex;
import com.vaadin.flow.theme.lumo.LumoUtility.FlexDirection;
import com.vaadin.flow.theme.lumo.LumoUtility.FlexWrap;
import com.vaadin.flow.theme.lumo.LumoUtility.FontSize;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.theme.lumo.LumoUtility.Height;
import com.vaadin.flow.theme.lumo.LumoUtility.JustifyContent;
import com.vaadin.flow.theme.lumo.LumoUtility.ListStyleType;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.vaadin.flow.theme.lumo.LumoUtility.MaxWidth;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;
import com.vaadin.flow.theme.lumo.LumoUtility.Position;
import com.vaadin.flow.theme.lumo.LumoUtility.TextColor;

import unimarket.views.myview.MyViewView;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Checkout Form")
@Route("checkout-form")
@Menu(order = 2, icon = LineAwesomeIconUrl.CREDIT_CARD)
public class CheckoutFormView extends Div {

    public CheckoutFormView() {
        addClassNames("checkout-form-view");
        addClassNames(Display.FLEX, FlexDirection.COLUMN, Height.FULL);

        Main content = new Main();
        content.addClassNames(Display.GRID, Gap.XLARGE, AlignItems.START, JustifyContent.CENTER, MaxWidth.SCREEN_MEDIUM,
                Margin.Horizontal.AUTO, Padding.Bottom.LARGE, Padding.Horizontal.LARGE);

        content.add(createCheckoutForm());
        content.add(createAside());
        add(content);
    }

    private Component createCheckoutForm() {
        Section checkoutForm = new Section();
        checkoutForm.addClassNames(Display.FLEX, FlexDirection.COLUMN, Flex.GROW);

        H2 header = new H2("Checkout");
        header.addClassNames(Margin.Bottom.NONE, Margin.Top.XLARGE, FontSize.XXXLARGE);
        Paragraph note = new Paragraph("Compila tutti i campi richiesti");
        note.addClassNames(Margin.Bottom.XLARGE, Margin.Top.NONE, TextColor.SECONDARY);
        checkoutForm.add(header, note);

        checkoutForm.add(createPersonalDetailsSection());
        checkoutForm.add(createShippingAddressSection());
        checkoutForm.add(createPaymentInformationSection());
        checkoutForm.add(new Hr());
        checkoutForm.add(createFooter());

        return checkoutForm;
    }

    private Section createPersonalDetailsSection() {
        Section personalDetails = new Section();
        personalDetails.addClassNames(Display.FLEX, FlexDirection.COLUMN, Margin.Bottom.XLARGE, Margin.Top.MEDIUM);

        Paragraph stepOne = new Paragraph("Checkout 1/3");
        stepOne.addClassNames(Margin.NONE, FontSize.SMALL, TextColor.SECONDARY);

        H3 header = new H3("Informazioni personali");
        header.addClassNames(Margin.Bottom.MEDIUM, Margin.Top.SMALL, FontSize.XXLARGE);

        // Recupera i dati dalla sessione
        VaadinSession session = VaadinSession.getCurrent();
        String nome = (String) session.getAttribute("nome");
        String cognome = (String) session.getAttribute("cognome");
        String email = (String) session.getAttribute("email");
        String telefono = (String) session.getAttribute("telefono");

        // Imposta i valori nei campi del modulo
        TextField name = new TextField("Nome");
        name.setRequiredIndicatorVisible(true);
        name.setPattern("[\\p{L} \\-]+");
        name.setValue(nome != null ? nome : "");  // Imposta il valore

        TextField lastName = new TextField("Cognome");
        lastName.setRequiredIndicatorVisible(true);
        lastName.setPattern("[\\p{L} \\-]+");
        lastName.setValue(cognome != null ? cognome : "");  // Imposta il valore
        
        EmailField emailField = new EmailField("Email");
        emailField.setRequiredIndicatorVisible(false);
        emailField.setValue(email != null ? email : "");  // Imposta il valore

        TextField phone = new TextField("Numero di telefono");
        phone.setRequiredIndicatorVisible(false);
        phone.setPattern("[\\d \\-\\+]+");
        phone.setValue(telefono != null ? telefono : "");  // Imposta il valore

        Checkbox rememberDetails = new Checkbox("Ricorda i miei dati");
        rememberDetails.addClassNames(Margin.Top.SMALL);

        personalDetails.add(stepOne, header, name, lastName, emailField, phone, rememberDetails);
        return personalDetails;
    }

    private Component createShippingAddressSection() {
        Section shippingDetails = new Section();
        shippingDetails.addClassNames(Display.FLEX, FlexDirection.COLUMN, Margin.Bottom.XLARGE, Margin.Top.MEDIUM);

        Paragraph stepTwo = new Paragraph("Checkout 2/3");
        stepTwo.addClassNames(Margin.NONE, FontSize.SMALL, TextColor.SECONDARY);

        H3 header = new H3("Indirizzo di spedizione");
        header.addClassNames(Margin.Bottom.MEDIUM, Margin.Top.SMALL, FontSize.XXLARGE);

        TextArea address = new TextArea("Indirizzo");
        address.setMaxLength(200);
        address.setRequiredIndicatorVisible(true);
        address.addClassNames(Margin.Bottom.SMALL);

        Div subSection = new Div();
        subSection.addClassNames(Display.FLEX, FlexWrap.WRAP, Gap.MEDIUM);

        TextField postalCode = new TextField("Codice postale");
        postalCode.setRequiredIndicatorVisible(true);
        postalCode.setPattern("\\d{5}");
        postalCode.setErrorMessage("Il codice postale deve essere composto da 5 numeri");
        postalCode.addClassNames(Margin.Bottom.SMALL);

        TextField city = new TextField("Provincia");
        city.setRequiredIndicatorVisible(true);
        city.addClassNames(Flex.GROW, Margin.Bottom.SMALL);

        Select<String> province = new Select<>();
        province.setLabel("Provincia");
        province.setRequiredIndicatorVisible(true);
        province.setItems("Agrigento", "Alessandria", "Ancona", "Aosta", "Arezzo", "Ascoli Piceno", "Asti", "Avellino", "Bari", "Barletta-Andria-Trani",
            "Belluno", "Benevento", "Bergamo", "Biella", "Bologna", "Bolzano", "Brescia", "Brindisi", "Cagliari", "Campobasso",
            "Caserta", "Catania", "Catanzaro", "Chieti", "Cremona", "Crotone", "Cuneo", "Enna", "Fermo", "Ferrara", "Firenze",
            "Foggia", "Forlì-Cesena", "Genova", "Gorizia", "Grosseto", "Imperia", "Isernia", "La Spezia", "L'Aquila", "Latina",
            "Lecce", "Lecco", "Livorno", "Lodi", "Lucca", "Macerata", "Mantova", "Massa-Carrara", "Matera", "Milano", "Modena",
            "Napoli", "Novara", "Nuoro", "Olbia-Tempio", "Padova", "Palermo", "Parma", "Pavia", "Perugia", "Pesaro e Urbino",
            "Pescara", "Piacenza", "Pisa", "Pistoia", "Potenza", "Prato", "Ragusa", "Ravenna", "Reggio Calabria", "Reggio Emilia",
            "Rieti", "Rimini", "Roma", "Salerno", "Sassari", "Savona", "Siena", "Siracusa", "Sondrio", "Taranto", "Teramo", "Terni",
            "Torino", "Trapani", "Trento", "Varese", "Venezia", "Verbania", "Verona", "Vibo Valentia", "Vicenza", "Viterbo");
        province.addClassNames(Margin.Bottom.SMALL);

        subSection.add(postalCode, province);

        Checkbox rememberAddress = new Checkbox("Ricorda il mio indirizzo");

        shippingDetails.add(stepTwo, header, address, subSection, rememberAddress);
        return shippingDetails;
    }

    private Component createPaymentInformationSection() {
        Section paymentInfo = new Section();
        paymentInfo.addClassNames(Display.FLEX, FlexDirection.COLUMN, Margin.Bottom.XLARGE, Margin.Top.MEDIUM);

        Paragraph stepThree = new Paragraph("Checkout 3/3");
        stepThree.addClassNames(Margin.NONE, FontSize.SMALL, TextColor.SECONDARY);

        H3 header = new H3("Informazioni pagamento");
        header.addClassNames(Margin.Bottom.MEDIUM, Margin.Top.SMALL, FontSize.XXLARGE);

        TextField cardHolder = new TextField("Intestatario");
        cardHolder.setRequiredIndicatorVisible(true);
        cardHolder.setPattern("[\\p{L} \\-]+");
        cardHolder.addClassNames(Margin.Bottom.SMALL);

        Div subSectionOne = new Div();
        subSectionOne.addClassNames(Display.FLEX, FlexWrap.WRAP, Gap.MEDIUM);

        TextField cardNumber = new TextField("Numero carta");
        cardNumber.setRequiredIndicatorVisible(true);
        cardNumber.setPattern("[\\d ]{12,23}");
        cardNumber.addClassNames(Margin.Bottom.SMALL);

        TextField securityCode = new TextField("Security Code");
        securityCode.setRequiredIndicatorVisible(true);
        securityCode.setPattern("[0-9]{3,4}");
        securityCode.addClassNames(Flex.GROW, Margin.Bottom.SMALL);
        securityCode.setHelperText("What is this?");

        subSectionOne.add(cardNumber, securityCode);

        Div subSectionTwo = new Div();
        subSectionTwo.addClassNames(Display.FLEX, FlexWrap.WRAP, Gap.MEDIUM);

        Select<String> expirationMonth = new Select<>();
        expirationMonth.setLabel("Mese scadenza");
        expirationMonth.setRequiredIndicatorVisible(true);
        expirationMonth.setItems("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");

        Select<String> expirationYear = new Select<>();
        expirationYear.setLabel("Anno scadenza");
        expirationYear.setRequiredIndicatorVisible(true);
        expirationYear.setItems("25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35");

        subSectionTwo.add(expirationMonth, expirationYear);

        paymentInfo.add(stepThree, header, cardHolder, cardNumber, subSectionTwo);
        return paymentInfo;
    }

    private Footer createFooter() {
        Footer footer = new Footer();
        footer.addClassNames(Display.FLEX, AlignItems.CENTER, JustifyContent.BETWEEN, Margin.Vertical.MEDIUM);
    
        Button cancel = new Button("Annulla ordine");
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
    
        Button pay = new Button("Conferma", new Icon(VaadinIcon.LOCK));
        pay.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
    
        // Listener per il bottone "Conferma"
        pay.addClickListener(event -> {
            // Aggiungi un messaggio di conferma
            Notification.show("Ordine confermato! Grazie per il tuo acquisto.", 3000, Notification.Position.MIDDLE);
    
            // Naviga alla homepage (ad esempio "myview")
            UI.getCurrent().navigate(MyViewView.class);
        });
    
        footer.add(cancel, pay);
    
        // Aggiungi il listener di clic per il pulsante "Annulla ordine"
        cancel.addClickListener(event -> {
            // Creiamo un dialogo di conferma
            Dialog confirmationDialog = new Dialog();
    
            // Aggiungiamo un messaggio di conferma
            Paragraph confirmationMessage = new Paragraph("Sei sicuro di voler annullare l'ordine?");
            confirmationDialog.add(confirmationMessage);
    
            // Creiamo il pulsante "Sì"
            Button confirmButton = new Button("Sì", e -> {
                // Se confermato, navighiamo alla pagina di my-view
                UI.getCurrent().navigate(MyViewView.class); // Cambia 'MyViewView' con la tua vista
                confirmationDialog.close(); // Chiudiamo il dialogo
            });
    
            // Creiamo il pulsante "No"
            Button cancelButton = new Button("No", e -> {
                // Se annullato, chiudiamo solo il dialogo
                confirmationDialog.close();
            });
    
            // Aggiungiamo i pulsanti nel dialogo
            confirmationDialog.add(confirmButton, cancelButton);
    
            // Mostriamo il dialogo
            confirmationDialog.open();
        });
    
        // Aggiungi il pulsante alla tua UI
        return footer;
    }
    
    private Aside createAside() {
        Aside aside = new Aside();
        aside.addClassNames(Background.CONTRAST_5, BoxSizing.BORDER, Padding.LARGE, BorderRadius.LARGE, Position.STICKY);

        Header headerSection = new Header();
        headerSection.addClassNames(Display.FLEX, AlignItems.CENTER, JustifyContent.BETWEEN, Margin.Bottom.MEDIUM);

        H3 header = new H3("Riepilogo ordine");
        header.addClassNames(Margin.NONE);

        Button edit = new Button("Modifica");
        edit.addThemeVariants(ButtonVariant.LUMO_SMALL);

        headerSection.add(header, edit);

        UnorderedList ul = new UnorderedList();
        ul.addClassNames(ListStyleType.NONE, Margin.NONE, Padding.NONE, Display.FLEX, FlexDirection.COLUMN, Gap.MEDIUM);

        ul.add(createListItem("da correggere", "With poca voglia di farlo io", "420.69"));
        ul.add(createListItem("Vanilla blueberry cake", "With blueberry jam", "$8.00"));
        ul.add(createListItem("Vanilla pastry", "With wholemeal flour", "$5.00"));

        aside.add(headerSection, ul);
        return aside;
    }

    private ListItem createListItem(String primary, String secondary, String price) {
        ListItem item = new ListItem();
        item.addClassNames(Display.FLEX, JustifyContent.BETWEEN);

        Div subSection = new Div();
        subSection.addClassNames(Display.FLEX, FlexDirection.COLUMN);

        subSection.add(new Span(primary));
        Span secondarySpan = new Span(secondary);
        secondarySpan.addClassNames(FontSize.SMALL, TextColor.SECONDARY);
        subSection.add(secondarySpan);

        Span priceSpan = new Span(price);

        item.add(subSection, priceSpan);
        return item;
    }
}  