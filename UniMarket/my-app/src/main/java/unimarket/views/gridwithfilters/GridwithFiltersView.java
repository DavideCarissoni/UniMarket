package unimarket.views.gridwithfilters;

import com.vaadin.flow.data.provider.ListDataProvider;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.impl.DSL;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import db.CreateDatabase;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jooq.generated.tables.Utente;
import jooq.generated.tables.records.UtenteRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.vaadin.lineawesome.LineAwesomeIconUrl;
import unimarket.data.SamplePerson;
import unimarket.data.SamplePersonRepository;
import unimarket.services.UtenteService;

@PageTitle("Lista Utenti")
@Route("grid-with-filters")
@Menu(order = 3, icon = LineAwesomeIconUrl.FILTER_SOLID)
@Uses(Icon.class)

public class GridwithFiltersView extends Div {

    private final UtenteService utenteService;
    private Grid<UtenteRecord> grid = new Grid<>(UtenteRecord.class);


    private Filters filters;

    @Autowired
    public GridwithFiltersView(UtenteService utenteService) {
        this.utenteService = utenteService;
        setSizeFull();
        configureGrid();
        add(grid);
        updateList();
    }

    private HorizontalLayout createMobileFilters() {
        // Mobile version
        HorizontalLayout mobileFilters = new HorizontalLayout();
        mobileFilters.setWidthFull();
        mobileFilters.addClassNames(LumoUtility.Padding.MEDIUM, LumoUtility.BoxSizing.BORDER,
                LumoUtility.AlignItems.CENTER);
        mobileFilters.addClassName("mobile-filters");

        Icon mobileIcon = new Icon("lumo", "plus");
        Span filtersHeading = new Span("Filters");
        mobileFilters.add(mobileIcon, filtersHeading);
        mobileFilters.setFlexGrow(1, filtersHeading);
        mobileFilters.addClickListener(e -> {
            if (filters.getClassNames().contains("visible")) {
                filters.removeClassName("visible");
                mobileIcon.getElement().setAttribute("icon", "lumo:plus");
            } else {
                filters.addClassName("visible");
                mobileIcon.getElement().setAttribute("icon", "lumo:minus");
            }
        });
        return mobileFilters;
    }

    public static class Filters extends Div implements Specification<SamplePerson> {

        private final TextField name = new TextField("Name");
        private final TextField surname = new TextField("Surname");  // Aggiungi il campo per il cognome
        private final TextField email = new TextField("Email");  // Aggiungi il campo per l'email
        private final TextField phone = new TextField("Phone");  // Mantieni il campo per il telefono


        public Filters(Runnable onSearch) {

            setWidthFull();
            addClassName("filter-layout");
            addClassNames(LumoUtility.Padding.Horizontal.LARGE, LumoUtility.Padding.Vertical.MEDIUM,
                    LumoUtility.BoxSizing.BORDER);
            name.setPlaceholder("First name");
            surname.setPlaceholder("Last name");
            email.setPlaceholder("Email");
            phone.setPlaceholder("Phone number");

            // Action buttons
            Button resetBtn = new Button("Reset");
            resetBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
            resetBtn.addClickListener(e -> {
                name.clear();
                surname.clear();
                email.clear();
                phone.clear();
                onSearch.run();
            });
            Button searchBtn = new Button("Search");
            searchBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            searchBtn.addClickListener(e -> onSearch.run());

            Div actions = new Div(resetBtn, searchBtn);
            actions.addClassName(LumoUtility.Gap.SMALL);
            actions.addClassName("actions");

            add(name, surname, email, phone, actions);
        }

        @Override
        public Predicate toPredicate(Root<SamplePerson> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            List<Predicate> predicates = new ArrayList<>();

            // Filtra per nome
            if (!name.isEmpty()) {
                String lowerCaseFilter = name.getValue().toLowerCase();
                Predicate firstNameMatch = criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")),
                        lowerCaseFilter + "%");
                predicates.add(firstNameMatch);
            }

            // Filtra per cognome
            if (!surname.isEmpty()) {
                String lowerCaseFilter = surname.getValue().toLowerCase();
                Predicate lastNameMatch = criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")),
                        lowerCaseFilter + "%");
                predicates.add(lastNameMatch);
            }

            // Filtra per email
            if (!email.isEmpty()) {
                String lowerCaseFilter = email.getValue().toLowerCase();
                Predicate emailMatch = criteriaBuilder.like(criteriaBuilder.lower(root.get("email")),
                        "%" + lowerCaseFilter + "%");
                predicates.add(emailMatch);
            }

            // Filtra per telefono
            if (!phone.isEmpty()) {
                String lowerCaseFilter = phone.getValue().toLowerCase();
                Predicate phoneMatch = criteriaBuilder.like(criteriaBuilder.lower(root.get("phone")),
                        "%" + lowerCaseFilter + "%");
                predicates.add(phoneMatch);
            }

            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        }


        private String ignoreCharacters(String characters, String in) {
            String result = in;
            for (int i = 0; i < characters.length(); i++) {
                result = result.replace("" + characters.charAt(i), "");
            }
            return result;
        }

        private Expression<String> ignoreCharacters(String characters, CriteriaBuilder criteriaBuilder,
                                                    Expression<String> inExpression) {
            Expression<String> expression = inExpression;
            for (int i = 0; i < characters.length(); i++) {
                expression = criteriaBuilder.function("replace", String.class, expression,
                        criteriaBuilder.literal(characters.charAt(i)), criteriaBuilder.literal(""));
            }
            return expression;
        }

    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns("id", "nome", "cognome", "numeroTelefono", "email");
    }

    private void updateList() {
        grid.setDataProvider(new ListDataProvider<>(fetchUsersFromDatabase()));
    }


    private List<UtenteRecord> fetchUsersFromDatabase() {
        try (Connection conn = CreateDatabase.getInstance().getConnection()) {
            DSLContext dsl = DSL.using(conn);
            Result<UtenteRecord> result = dsl.selectFrom(Utente.UTENTE).fetch();
            return result.stream().collect(Collectors.toList());
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
