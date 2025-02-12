package unimarket.views.myview;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;
import componenti.Carrello;
import componenti.Prodotto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

import unimarket.factories.CarrelloFactory;
import unimarket.services.CarrelloService;
import unimarket.services.ProdottoService;
import unimarket.views.checkoutform.CheckoutFormView;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;

@PageTitle("Homepage")
@Route("Home")
@Menu(order = 0, icon = LineAwesomeIconUrl.PENCIL_RULER_SOLID)
public class MyViewView extends Composite<VerticalLayout> {

    private final CarrelloFactory carrelloFactory;
    private final ProdottoService prodottoService;
    private final CarrelloService carrelloService;

    @Autowired
    public MyViewView(CarrelloFactory carrelloFactory, ProdottoService prodottoService, CarrelloService carrelloService) {
        this.carrelloFactory = carrelloFactory;
        this.prodottoService = prodottoService;
        this.carrelloService = carrelloService;

        setupUI();
    }

    private void setupUI() {
        HorizontalLayout layoutRow = new HorizontalLayout();
        HorizontalLayout headerLayout = new HorizontalLayout();
        H1 title = new H1();
        RouterLink routerLink = new RouterLink();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        Select<SampleItem> select = new Select<>();
        MultiSelectComboBox<SampleItem> multiSelectComboBox = new MultiSelectComboBox<>();
        MultiSelectListBox<SampleItem> textItems = new MultiSelectListBox<>();
        VerticalLayout layoutColumn3 = new VerticalLayout();
        Hr hr = new Hr();

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().add(layoutRow, layoutRow2);

        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("100px");
        layoutRow.add(headerLayout);

        title.setText("UniMarket");
        title.getStyle().set("margin", "0");
        title.getStyle().set("flex-grow", "1");
        title.getStyle().set("text-align", "center");

        // crea il pulsante carrello
        Button cartButton = new Button(new Icon(VaadinIcon.CART));
        cartButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cartButton.getStyle().set("margin-left", "auto");
        cartButton.getStyle().set("width", "60px");
        cartButton.getStyle().set("height", "60px");
        cartButton.getStyle().set("padding", "0");
        cartButton.getStyle().set("min-width", "unset");
        
        // Manda all'interfaccia di checkout se cliccato
        cartButton.addClickListener(event -> {
        	getUI().ifPresent(ui -> ui.navigate(CheckoutFormView.class));
        });
        
        headerLayout.setWidth("100%");
        headerLayout.setAlignItems(Alignment.CENTER);
        headerLayout.getStyle().set("display", "flex");
        headerLayout.getStyle().set("justify-content", "center");
        headerLayout.add(title, cartButton, routerLink);

        layoutRow2.addClassName(Gap.MEDIUM);
        layoutRow2.setWidth("100%");
        layoutRow2.getStyle().set("flex-grow", "1");
        layoutRow2.add(layoutColumn2, layoutColumn3);

        layoutColumn2.setWidth("200px");
        layoutColumn2.getStyle().set("flex-grow", "1");
        layoutColumn2.add(select, multiSelectComboBox, textItems);

        select.setLabel("Select");
        select.setWidth("130px");
        setSelectSampleData(select);

        multiSelectComboBox.setLabel("Multi-Select Combo Box");
        multiSelectComboBox.setWidth("130px");
        setMultiSelectComboBoxSampleData(multiSelectComboBox);

        textItems.setWidth("130px");
        setMultiSelectListBoxSampleData(textItems);

        layoutColumn3.addClassName(Padding.SMALL);
        layoutColumn3.setWidth("3%");
        layoutColumn3.getStyle().set("flex-grow", "1");
        layoutColumn3.add(hr);

        Integer userId = (Integer) VaadinSession.getCurrent().getAttribute("userId");
        if (userId == null) {
            Notification.show("Errore: utente non autenticato!", 3000, Notification.Position.MIDDLE);
            return;
        }

        Carrello cart = carrelloFactory.creaCarrello(userId);

        List<Prodotto> prodotti = prodottoService.getAllProdotti();
        FlexLayout layoutColumn4 = new FlexLayout();
        layoutColumn4.setWidthFull();
        layoutColumn4.getStyle()
                .set("flexWrap", "wrap")
                .set("justifyContent", "space-evenly")
                .set("gap", "10px");

        Map<Button, Integer> quantityMap = new HashMap<>();
        
        // Crea un box per ogni prodotto presente
        for (Prodotto prodotto : prodotti) {
            VerticalLayout boxLayout = new VerticalLayout();
            boxLayout.setHeight("350px");
            boxLayout.setWidth("100%");
            boxLayout.getStyle()
                    .set("flex", "1 1 200px")
                    .set("max-width", "250px")
                    .set("min-width", "230px")
                    .set("border-radius", "15px")
                    .set("border", "1px solid #ccc")
                    .set("background-color", "#8ba6cc")
                    .set("padding", "15px")
            		.set("box-shadow", "2px 2px 5px rgba(0,0,0,0.1");

            boxLayout.setAlignItems(Alignment.CENTER);
            boxLayout.setJustifyContentMode(JustifyContentMode.BETWEEN);

            Button buttonPrimary = new Button(new Icon(VaadinIcon.CART));
            buttonPrimary.getStyle().set("min-width", "65px");
            buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

            HorizontalLayout quantitySelector = createQuantitySelector(quantityMap, buttonPrimary);
            
            // Contenitore per i testi
            VerticalLayout contentContainer = new VerticalLayout();
            contentContainer.setWidthFull();
            contentContainer.setPadding(false);
            contentContainer.setSpacing(false);
            contentContainer.getStyle().set("flex-grow", "1");
            
            // Crea componente immagine
            Image image = new Image("media/icon.jpg", "Product Image");
            image.setWidth("100%");
            
            // Crea span per il nome
            Span name = new Span(prodotto.getNome());
            name.getStyle().set("font-weight", "bold");
            
            name.getStyle().set("font-weight", "bold"); 
            
            Span description = new Span(prodotto.getDescrizione());
            description.getStyle()
            	.set("font-size", "12px") 
            	.set("color", "#fff") 
            	.set("text-align", "center")
            	.set("max-height", "40px") 
            	.set("overflow", "hidden") // Nasconde il testo in eccesso
            	.set("margin-bottom", "2px");

            Span price = new Span(String.format("$%.2f", prodotto.getPrezzo()));
            price.getStyle().set("color", "green").set("font-size", "14px");

            // Aggiunge i componenti nel contenitore di testo
            contentContainer.add(name, description, price);
            
            // Aggiungi il selettore di quantità            
            HorizontalLayout actionLayout = new HorizontalLayout(quantitySelector, buttonPrimary);
            
            actionLayout.setWidthFull();
            actionLayout.setSpacing(true);
            actionLayout.setAlignItems(Alignment.CENTER);
            actionLayout.getStyle().set("margin-top", "5px");
            
            // Imposta crescita degli elementi per mantenere il pulsante in fondo
            boxLayout.setFlexGrow(1, contentContainer);
            boxLayout.setFlexGrow(0, buttonPrimary);
            
            // Add the components to the box layout
            boxLayout.add(image, contentContainer, actionLayout);
            layoutColumn4.add(boxLayout);
            
            // Se cliccato il pulsante 🛒 aggiunge al carrello i prodotti selezionati
            buttonPrimary.addClickListener(event -> {
                int selectedQuantity = quantityMap.get(buttonPrimary);
                if (selectedQuantity < prodottoService.getQuantitaById(prodotto.getCodice())) {
                	carrelloService.aggiungiProdotto(cart, prodotto, selectedQuantity);
                	prodottoService.modificaQuantita(prodotto.getCodice(), selectedQuantity);
                }else {
                    // Mostra un messaggio di errore se la quantità è maggiore di quella disponibile
                    Notification.show("Errore: La quantità selezionata (" + selectedQuantity + ") è maggiore di quella disponibile (" 
                    		+ prodottoService.getQuantitaById(prodotto.getCodice()) + ").", 5000, Notification.Position.MIDDLE);
                }
            }
            );
        }

        layoutRow2.add(layoutColumn4);
    }

    record SampleItem(String value, String label, Boolean disabled) {
    
    }

    private HorizontalLayout createQuantitySelector(Map<Button, Integer> quantityMap, Button buttonPrimary) {
        HorizontalLayout quantityLayout = new HorizontalLayout();
        quantityLayout.setAlignItems(Alignment.CENTER); 
        quantityLayout.setSpacing(false);  

        // Pulsante per diminuire la quantità
        Button decreaseButton = new Button("-");
        decreaseButton.getStyle().set("min-width", "30px");

        // Pulsante per aumentare la quantità
        Button increaseButton = new Button("+");
        increaseButton.getStyle().set("min-width", "30px");

        // Span per visualizzare la quantità corrente
        Span quantityLabel = new Span("1");  
        quantityLabel.getStyle().set("margin", "0 10px");
        
        // Aggiungi i componenti al layout
        quantityLayout.add(decreaseButton, quantityLabel, increaseButton);

        quantityMap.put(buttonPrimary, 1);
        
        // Gestisci gli eventi dei pulsanti
        decreaseButton.addClickListener(event -> {
        	int currentQuantity = quantityMap.get(buttonPrimary);
            if (currentQuantity > 1) {
                quantityMap.put(buttonPrimary, currentQuantity - 1);
                quantityLabel.setText(String.valueOf(currentQuantity - 1));
            }
        });

        increaseButton.addClickListener(event -> {
        	 int currentQuantity = quantityMap.get(buttonPrimary);
             quantityMap.put(buttonPrimary, currentQuantity + 1);
             quantityLabel.setText(String.valueOf(currentQuantity + 1));
        });

        return quantityLayout;
    }
    
    private void setSelectSampleData(Select<SampleItem> select) {
        List<SampleItem> sampleItems = new ArrayList<>();
        sampleItems.add(new SampleItem("first", "In arrivo...", Boolean.TRUE));
        sampleItems.add(new SampleItem("second", "In arrivo...", Boolean.TRUE));
        sampleItems.add(new SampleItem("third", "In arrivo...", Boolean.TRUE));
        sampleItems.add(new SampleItem("fourth", "In arrivo...", Boolean.TRUE));
        select.setItems(sampleItems);
        select.setItemLabelGenerator(item -> ((SampleItem) item).label());
        select.setItemEnabledProvider(item -> !Boolean.TRUE.equals(((SampleItem) item).disabled()));
    }

    private void setMultiSelectComboBoxSampleData(MultiSelectComboBox<SampleItem> multiSelectComboBox) {
        List<SampleItem> sampleItems = new ArrayList<>();
        sampleItems.add(new SampleItem("first", "In arrivo...", Boolean.TRUE));
        sampleItems.add(new SampleItem("second", "In arrivo...", Boolean.TRUE));
        sampleItems.add(new SampleItem("third", "In arrivo...", Boolean.TRUE));
        sampleItems.add(new SampleItem("fourth", "In arrivo...", Boolean.TRUE));
        multiSelectComboBox.setItems(sampleItems);
        multiSelectComboBox.setItemLabelGenerator(item -> ((SampleItem) item).label());
    }

    private void setMultiSelectListBoxSampleData(MultiSelectListBox<SampleItem> multiSelectListBox) {
        List<SampleItem> sampleItems = new ArrayList<>();
        sampleItems.add(new SampleItem("first", "In arrivo...", Boolean.TRUE));
        sampleItems.add(new SampleItem("second", "In arrivo...", Boolean.TRUE));
        sampleItems.add(new SampleItem("third", "In arrivo...", Boolean.TRUE));
        sampleItems.add(new SampleItem("fourth", "In arrivo...", Boolean.TRUE));
        multiSelectListBox.setItems(sampleItems);
        multiSelectListBox.setItemLabelGenerator(item -> ((SampleItem) item).label());
        multiSelectListBox.setItemEnabledProvider(item -> !Boolean.TRUE.equals(((SampleItem) item).disabled()));
    }

}
