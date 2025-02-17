/*
 * This file is generated by jOOQ.
 */
package jooq.generated.tables;


import java.util.Collection;

import jooq.generated.DefaultSchema;
import jooq.generated.tables.records.CarrelloProdottoRecord;

import org.jooq.Condition;
import org.jooq.Field;
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
public class CarrelloProdotto extends TableImpl<CarrelloProdottoRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>carrello_prodotto</code>
     */
    public static final CarrelloProdotto CARRELLO_PRODOTTO = new CarrelloProdotto();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CarrelloProdottoRecord> getRecordType() {
        return CarrelloProdottoRecord.class;
    }

    /**
     * The column <code>carrello_prodotto.codice</code>.
     */
    public final TableField<CarrelloProdottoRecord, Integer> CODICE = createField(DSL.name("codice"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>carrello_prodotto.id_carrello</code>.
     */
    public final TableField<CarrelloProdottoRecord, Integer> ID_CARRELLO = createField(DSL.name("id_carrello"), SQLDataType.INTEGER, this, "");

    private CarrelloProdotto(Name alias, Table<CarrelloProdottoRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private CarrelloProdotto(Name alias, Table<CarrelloProdottoRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>carrello_prodotto</code> table reference
     */
    public CarrelloProdotto(String alias) {
        this(DSL.name(alias), CARRELLO_PRODOTTO);
    }

    /**
     * Create an aliased <code>carrello_prodotto</code> table reference
     */
    public CarrelloProdotto(Name alias) {
        this(alias, CARRELLO_PRODOTTO);
    }

    /**
     * Create a <code>carrello_prodotto</code> table reference
     */
    public CarrelloProdotto() {
        this(DSL.name("carrello_prodotto"), null);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public CarrelloProdotto as(String alias) {
        return new CarrelloProdotto(DSL.name(alias), this);
    }

    @Override
    public CarrelloProdotto as(Name alias) {
        return new CarrelloProdotto(alias, this);
    }

    @Override
    public CarrelloProdotto as(Table<?> alias) {
        return new CarrelloProdotto(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public CarrelloProdotto rename(String name) {
        return new CarrelloProdotto(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public CarrelloProdotto rename(Name name) {
        return new CarrelloProdotto(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public CarrelloProdotto rename(Table<?> name) {
        return new CarrelloProdotto(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public CarrelloProdotto where(Condition condition) {
        return new CarrelloProdotto(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public CarrelloProdotto where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public CarrelloProdotto where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public CarrelloProdotto where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public CarrelloProdotto where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public CarrelloProdotto where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public CarrelloProdotto where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public CarrelloProdotto where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public CarrelloProdotto whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public CarrelloProdotto whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
