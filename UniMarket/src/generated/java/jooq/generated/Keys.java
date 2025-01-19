/*
 * This file is generated by jOOQ.
 */
package jooq.generated;


import jooq.generated.tables.Admin;
import jooq.generated.tables.Prodotti;
import jooq.generated.tables.ProfiliUtente;
import jooq.generated.tables.records.AdminRecord;
import jooq.generated.tables.records.ProdottiRecord;
import jooq.generated.tables.records.ProfiliUtenteRecord;

import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in the
 * default schema.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<AdminRecord> ADMIN__PK_ADMIN = Internal.createUniqueKey(Admin.ADMIN, DSL.name("pk_admin"), new TableField[] { Admin.ADMIN.ID }, true);
    public static final UniqueKey<AdminRecord> ADMIN__UK_ADMIN_67319567 = Internal.createUniqueKey(Admin.ADMIN, DSL.name("uk_admin_67319567"), new TableField[] { Admin.ADMIN.EMAIL }, true);
    public static final UniqueKey<ProdottiRecord> PRODOTTI__PK_PRODOTTI = Internal.createUniqueKey(Prodotti.PRODOTTI, DSL.name("pk_prodotti"), new TableField[] { Prodotti.PRODOTTI.CODICE }, true);
    public static final UniqueKey<ProfiliUtenteRecord> PROFILI_UTENTE__PK_PROFILI_UTENTE = Internal.createUniqueKey(ProfiliUtente.PROFILI_UTENTE, DSL.name("pk_profili_utente"), new TableField[] { ProfiliUtente.PROFILI_UTENTE.ID }, true);
    public static final UniqueKey<ProfiliUtenteRecord> PROFILI_UTENTE__UK_PROFILI_UTENTE_9297935 = Internal.createUniqueKey(ProfiliUtente.PROFILI_UTENTE, DSL.name("uk_profili_utente_9297935"), new TableField[] { ProfiliUtente.PROFILI_UTENTE.EMAIL }, true);
}
