package unimarket.views.checkoutform;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
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
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import componenti.Carrello;
import componenti.CartaCredito;
import componenti.Cliente;
import componenti.Prodotto;

import org.vaadin.lineawesome.LineAwesomeIconUrl;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.server.VaadinSession;
import unimarket.services.CarrelloService;
import unimarket.services.ClienteService;
import unimarket.views.myview.Login;
import unimarket.views.myview.MyViewView;

@PageTitle("Checkout Form")
@Route("checkout-form")
@Menu(order = 2, icon = LineAwesomeIconUrl.CREDIT_CARD)

public class CheckoutFormView extends Div implements BeforeEnterObserver {

	// Servizi
    private final CarrelloService carrelloService;
    private final ClienteService clienteService;
     
    private Carrello cart;
    private Cliente cliente;
    
    // Variabili per l'indirizzo di spedizione
    private TextArea address;
    private TextField postalCode;
    private Checkbox rememberAddress;
    
    // Variabili per il pagamento
    private TextField cardHolderName;
    private TextField cardHolderSurname;
    private TextField cardNumber;
    private TextField securityCode;
    private Select<String> expirationMonth;
    private Select<String> expirationYear;
    private Checkbox rememberCard;
  
    // binder --> per controllare il riempimento dei campi
    private final Binder<CheckoutFormView> binder = new Binder<>(CheckoutFormView.class);
    
    private Button confirm;
    
    //Creazione istanze utente e carrello, da  modificare con i parametri corretti
    Integer idUtente = (Integer) VaadinSession.getCurrent().getAttribute("userId");
    
    public CheckoutFormView(CarrelloService carrelloService, ClienteService clienteService) {
        this.carrelloService = carrelloService;
        this.clienteService = clienteService;
        addClassNames("checkout-form-view", Display.FLEX, FlexDirection.COLUMN, Height.FULL);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Integer userId = (Integer) VaadinSession.getCurrent().getAttribute("userId");
        if (userId == null) {
            event.rerouteTo(Login.class);
            return;
        }
        
        // Recupera il Cliente 
        cliente = clienteService.getClienteById(userId);
        if (cliente == null) {
            Notification.show("Errore: cliente non trovato.", 3000, Notification.Position.MIDDLE);
            event.rerouteTo(Login.class);
            return;
        }

        cart = carrelloService.getOrCreateCarrello(userId);
        initializeCheckoutForm();
        
        // Inserisce i dati assocciati al cliente esistono già
        if (cliente.getIndirizzo() != null) {
            address.setValue(cliente.getIndirizzo());
        }
        
        // Inserisce la carta associata al cliente se esiste già
        CartaCredito cartaSalvata = clienteService.getCartaCredito(cliente.getId());
        if (cartaSalvata != null) {
            cardHolderName.setValue(cartaSalvata.getNomeIntestatario());
            cardHolderSurname.setValue(cartaSalvata.getCognomeIntestatario());
            cardNumber.setValue(cartaSalvata.getNumeroCarta());
            securityCode.setValue(cartaSalvata.getCodiceSicurezza());
        }
    }

    private void initializeCheckoutForm() {
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

        Integer userId = (Integer) VaadinSession.getCurrent().getAttribute("userId");
        Carrello cart = new Carrello(userId);

        Span headerIDCarrello = new Span("ID Carrello: " + cart.getIdCarrello());;
        Span headerIDUtente = new Span("id utente: " + userId);

        header.addClassNames(Margin.Bottom.NONE, Margin.Top.XLARGE, FontSize.XXXLARGE);
        Paragraph note = new Paragraph("Compila tutti i campi richiesti");
        note.addClassNames(Margin.Bottom.XLARGE, Margin.Top.NONE, TextColor.SECONDARY);
        checkoutForm.add(header, note);

        checkoutForm.add(createShippingAddressSection());
        checkoutForm.add(createPaymentInformationSection());
        checkoutForm.add(new Hr());
        checkoutForm.add(createFooter());
        checkoutForm.add(headerIDCarrello, headerIDUtente);

        return checkoutForm;
    }

    private Component createShippingAddressSection() {
        Section shippingDetails = new Section();
        shippingDetails.addClassNames(Display.FLEX, FlexDirection.COLUMN, Margin.Bottom.XLARGE, Margin.Top.MEDIUM);

        Paragraph stepTwo = new Paragraph("Checkout 1/2");
        stepTwo.addClassNames(Margin.NONE, FontSize.SMALL, TextColor.SECONDARY);

        H3 header = new H3("Indirizzo di spedizione");
        header.addClassNames(Margin.Bottom.MEDIUM, Margin.Top.SMALL, FontSize.XXLARGE);

        address = new TextArea("Indirizzo");
        address.setMaxLength(200);
        address.setRequiredIndicatorVisible(true);
        address.addClassNames(Margin.Bottom.SMALL);

        postalCode = new TextField("Codice postale");
        postalCode.setRequiredIndicatorVisible(true);
        postalCode.setPattern("\\d{5}");
        postalCode.setErrorMessage("Il codice postale deve essere composto da 5 numeri");
        postalCode.addClassNames(Margin.Bottom.SMALL);
        postalCode.setReadOnly(true);
        
        rememberAddress = new Checkbox("Ricorda il mio indirizzo");

        shippingDetails.add(stepTwo, header, address, postalCode, rememberAddress);
        return shippingDetails; // Returns the correct type Component
    }

    private Component createPaymentInformationSection() {
        Section paymentInfo = new Section();
        paymentInfo.addClassNames(Display.FLEX, FlexDirection.COLUMN, Margin.Bottom.XLARGE, Margin.Top.MEDIUM);

        Paragraph stepThree = new Paragraph("Checkout 2/2");
        stepThree.addClassNames(Margin.NONE, FontSize.SMALL, TextColor.SECONDARY);

        H3 header = new H3("Informazioni pagamento");
        header.addClassNames(Margin.Bottom.MEDIUM, Margin.Top.SMALL, FontSize.XXLARGE);

        cardHolderName = new TextField("Nome intestatario");
        cardHolderName.setRequiredIndicatorVisible(true);
        cardHolderName.setPattern("[\\p{L} \\-]+");
        cardHolderName.addClassNames(Margin.Bottom.SMALL);

        cardHolderSurname = new TextField("Cognome intestatario");
        cardHolderSurname.setRequiredIndicatorVisible(true);
        cardHolderSurname.setPattern("[\\p{L} \\-]+");
        cardHolderSurname.addClassNames(Margin.Bottom.SMALL);
        
        Div subSectionOne = new Div();
        subSectionOne.addClassNames(Display.FLEX, FlexWrap.WRAP, Gap.MEDIUM);

        cardNumber = new TextField("Numero carta");
        cardNumber.setRequiredIndicatorVisible(true);
        cardNumber.setPattern("[\\d ]{12,23}");
        cardNumber.addClassNames(Margin.Bottom.SMALL);

        securityCode = new TextField("Security Code");
        securityCode.setRequiredIndicatorVisible(true);
        securityCode.setPattern("[0-9]{3,4}");
        securityCode.addClassNames(Flex.GROW, Margin.Bottom.SMALL);
        securityCode.setHelperText("What is this?");

        subSectionOne.add(cardNumber, securityCode);

        Div subSectionTwo = new Div();
        subSectionTwo.addClassNames(Display.FLEX, FlexWrap.WRAP, Gap.MEDIUM);

        expirationMonth = new Select<>();
        expirationMonth.setLabel("Mese scadenza");
        expirationMonth.setRequiredIndicatorVisible(false);
        expirationMonth.setItems("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");
        expirationMonth.setReadOnly(true);

        expirationYear = new Select<>();
        expirationYear.setLabel("Anno scadenza");
        expirationYear.setRequiredIndicatorVisible(false);
        expirationYear.setItems("25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35");
        expirationYear.setReadOnly(true);

        subSectionTwo.add(expirationMonth, expirationYear);
        
        rememberCard = new Checkbox("Ricorda la mia carta di credito");

        paymentInfo.add(stepThree, header, cardHolderName, cardHolderSurname, cardNumber, subSectionTwo, rememberCard);
        return paymentInfo;
    }

    private Footer createFooter() {
        Footer footer = new Footer();
        footer.addClassNames(Display.FLEX, AlignItems.CENTER, JustifyContent.BETWEEN, Margin.Vertical.MEDIUM);

        Button cancel = new Button("Annulla ordine");
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        confirm = new Button("Conferma", new Icon(VaadinIcon.LOCK));
        confirm.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
        confirm.setEnabled(false);
        
        // Listener per il bottone "Conferma"
        confirm.addClickListener(event -> {
        	if (binder.validate().isOk()) {
        		boolean rememberSelectedAddress = rememberAddress.getValue();
        		boolean rememberSelectedCard = rememberCard.getValue();     	
        	   
        		String indirizzoInserito = address.getValue();
        		String nomeIntestatario = cardHolderName.getValue();
        		String cognomeIntestatario = cardHolderSurname.getValue();
        		String numeroCarta = cardNumber.getValue();
        		String codiceSicurezza = securityCode.getValue();
            
        		if (rememberSelectedAddress) {
        			cliente.setIndirizzo(indirizzoInserito);
        			clienteService.salvaCliente(cliente);
        		}
        	
        		if (rememberSelectedCard) {
        			CartaCredito nuovaCarta = new CartaCredito(numeroCarta, codiceSicurezza, nomeIntestatario, cognomeIntestatario);
        			clienteService.aggiungiCartaCredito(cliente, nuovaCarta, true);
        		}
        	
        		Notification.show("Ordine confermato! Grazie per il tuo acquisto.", 3000, Notification.Position.MIDDLE);

        		// Naviga alla homepage 
        		UI.getCurrent().navigate(MyViewView.class);
        		
        	} else {
        		Notification.show("Compila tutti i campi obbligatori prima di confermare.");
        	}
        });
        
        // Aggiungi il listener di clic per il pulsante "Annulla ordine"
        cancel.addClickListener(event -> {
            Dialog confirmationDialog = new Dialog();
    
            Paragraph confirmationMessage = new Paragraph("Sei sicuro di voler annullare l'ordine?");
            confirmationDialog.add(confirmationMessage);
    
            Button confirmButton = new Button("Sì", e -> {
            // Se confermato, navighiamo alla pagina di my-view
            UI.getCurrent().navigate(MyViewView.class); 
                confirmationDialog.close(); 
            });
    
            Button cancelButton = new Button("No", e -> {
                // Se annullato, chiudiamo solo il dialogo
                confirmationDialog.close();
            });
    
            // Aggiungiamo i pulsanti
            confirmationDialog.add(confirmButton, cancelButton);
            confirmationDialog.open();

        });
        
        footer.add(cancel, confirm);
        return footer;
    }

    private Aside createAside() {
        Aside aside = new Aside();
        aside.addClassNames(Background.CONTRAST_5, BoxSizing.BORDER, Padding.LARGE, BorderRadius.LARGE, Position.STICKY);
        
        Header headerSection = new Header();
        headerSection.addClassNames(Display.FLEX, AlignItems.CENTER, JustifyContent.BETWEEN, Margin.Bottom.MEDIUM);
        
        H3 header = new H3("Riepilogo ordine");
        header.addClassNames(Margin.NONE);
        
        headerSection.add(header);
        
		Carrello carrello = carrelloService.getCarrello(idUtente);
        List<Prodotto> prodotti = carrello.getProdotti();
        
        UnorderedList ul = new UnorderedList();
        ul.addClassNames(ListStyleType.NONE, Margin.NONE, Padding.NONE, Display.FLEX, FlexDirection.COLUMN, Gap.MEDIUM);

        for (Prodotto prodotto : prodotti) {
        	ul.add(createListItem(
        			prodotto.getNome() + "", 
        			prodotto.getDescrizione() + "", 
        			prodotto.getPrezzo() + ""));
        	
        }        
        
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
    
    // abilita il pulsante conferma se sono validi i campi
    private void configureBinder() {
        binder.forField(address)
            .asRequired("L'indirizzo è obbligatorio")
            .bind(instance -> instance.address.getValue(), (instance, value) -> instance.address.setValue(value));

        binder.forField(postalCode)
            .asRequired("Il codice postale è obbligatorio")
            .withValidator(value -> value.matches("\\d{5}"), "Il codice postale deve essere di 5 cifre")
            .bind(instance -> instance.postalCode.getValue(), (instance, value) -> instance.postalCode.setValue(value));

        binder.forField(cardHolderName)
            .asRequired("Il nome dell'intestatario è obbligatorio")
            .bind(instance -> instance.cardHolderName.getValue(), (instance, value) -> instance.cardHolderName.setValue(value));

        binder.forField(cardHolderSurname)
            .asRequired("Il cognome dell'intestatario è obbligatorio")
            .bind(instance -> instance.cardHolderSurname.getValue(), (instance, value) -> instance.cardHolderSurname.setValue(value));

        binder.forField(cardNumber)
            .asRequired("Il numero della carta è obbligatorio")
            .withValidator(value -> value.replaceAll(" ", "").matches("\\d{12,23}"), "Numero carta non valido")
            .bind(instance -> instance.cardNumber.getValue(), (instance, value) -> instance.cardNumber.setValue(value));

        binder.forField(securityCode)
            .asRequired("Il codice di sicurezza è obbligatorio")
            .withValidator(value -> value.matches("\\d{3,4}"), "Il codice di sicurezza deve avere 3 o 4 cifre")
            .bind(instance -> instance.securityCode.getValue(), (instance, value) -> instance.securityCode.setValue(value));

        // Abilita/disabilita il pulsante "Conferma" in base alla validità del form
        binder.addStatusChangeListener(event -> confirm.setEnabled(binder.isValid()));
    }

} 