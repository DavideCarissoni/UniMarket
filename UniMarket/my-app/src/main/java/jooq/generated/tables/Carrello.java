/*
 * This file is generated by jOOQ.
 */
package jooq.generated.tables;


import java.util.Collection;

import jooq.generated.DefaultSchema;
import jooq.generated.tables.records.CarrelloRecord;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.PlainSQL;
import org.jooq.QueryPart;
import org.jooq.SQL;
import org.jooq.Schema;
import org.jooq.Select;
import org.jooq.Stringly;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Carrello extends TableImpl<CarrelloRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>carrello</code>
     */
    public static final Carrello CARRELLO = new Carrello();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CarrelloRecord> getRecordType() {
        return CarrelloRecord.class;
    }

    /**
     * The column <code>carrello.id_carrello</code>.
     */
    public final TableField<CarrelloRecord, Integer> ID_CARRELLO = createField(DSL.name("id_carrello"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>carrello.totale</code>.
     */
    public final TableField<CarrelloRecord, Float> TOTALE = createField(DSL.name("totale"), SQLDataType.REAL, this, "");

    /**
     * The column <code>carrello.prodotto_selezionato</code>.
     */
    public final TableField<CarrelloRecord, Integer> PRODOTTO_SELEZIONATO = createField(DSL.name("prodotto_selezionato"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>carrello.id_cliente</code>.
     */
    public final TableField<CarrelloRecord, Integer> ID_CLIENTE = createField(DSL.name("id_cliente"), SQLDataType.INTEGER.nullable(false), this, "");

    private Carrello(Name alias, Table<CarrelloRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Carrello(Name alias, Table<CarrelloRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>carrello</code> table reference
     */
    public Carrello(String alias) {
        this(DSL.name(alias), CARRELLO);
    }

    /**
     * Create an aliased <code>carrello</code> table reference
     */
    public Carrello(Name alias) {
        this(alias, CARRELLO);
    }

    /**
     * Create a <code>carrello</code> table reference
     */
    public Carrello() {
        this(DSL.name("carrello"), null);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public Identity<CarrelloRecord, Integer> getIdentity() {
        return (Identity<CarrelloRecord, Integer>) super.getIdentity();
    }

    @Override
    public Carrello as(String alias) {
        return new Carrello(DSL.name(alias), this);
    }

    @Override
    public Carrello as(Name alias) {
        return new Carrello(alias, this);
    }

    @Override
    public Carrello as(Table<?> alias) {
        return new Carrello(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Carrello rename(String name) {
        return new Carrello(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Carrello rename(Name name) {
        return new Carrello(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Carrello rename(Table<?> name) {
        return new Carrello(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Carrello where(Condition condition) {
        return new Carrello(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Carrello where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Carrello where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Carrello where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Carrello where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Carrello where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Carrello where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Carrello where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Carrello whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Carrello whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
