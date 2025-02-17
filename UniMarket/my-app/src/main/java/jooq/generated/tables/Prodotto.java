/*
 * This file is generated by jOOQ.
 */
package jooq.generated.tables;


import java.util.Collection;

import jooq.generated.DefaultSchema;
import jooq.generated.tables.records.ProdottoRecord;

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
public class Prodotto extends TableImpl<ProdottoRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>prodotto</code>
     */
    public static final Prodotto PRODOTTO = new Prodotto();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ProdottoRecord> getRecordType() {
        return ProdottoRecord.class;
    }

    /**
     * The column <code>prodotto.codice</code>.
     */
    public final TableField<ProdottoRecord, Integer> CODICE = createField(DSL.name("codice"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>prodotto.nome</code>.
     */
    public final TableField<ProdottoRecord, String> NOME = createField(DSL.name("nome"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>prodotto.descrizione</code>.
     */
    public final TableField<ProdottoRecord, String> DESCRIZIONE = createField(DSL.name("descrizione"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>prodotto.quantità</code>.
     */
    public final TableField<ProdottoRecord, Integer> QUANTITÀ = createField(DSL.name("quantità"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>prodotto.prezzo</code>.
     */
    public final TableField<ProdottoRecord, Float> PREZZO = createField(DSL.name("prezzo"), SQLDataType.REAL.nullable(false), this, "");

    private Prodotto(Name alias, Table<ProdottoRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Prodotto(Name alias, Table<ProdottoRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>prodotto</code> table reference
     */
    public Prodotto(String alias) {
        this(DSL.name(alias), PRODOTTO);
    }

    /**
     * Create an aliased <code>prodotto</code> table reference
     */
    public Prodotto(Name alias) {
        this(alias, PRODOTTO);
    }

    /**
     * Create a <code>prodotto</code> table reference
     */
    public Prodotto() {
        this(DSL.name("prodotto"), null);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public Identity<ProdottoRecord, Integer> getIdentity() {
        return (Identity<ProdottoRecord, Integer>) super.getIdentity();
    }

    @Override
    public Prodotto as(String alias) {
        return new Prodotto(DSL.name(alias), this);
    }

    @Override
    public Prodotto as(Name alias) {
        return new Prodotto(alias, this);
    }

    @Override
    public Prodotto as(Table<?> alias) {
        return new Prodotto(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Prodotto rename(String name) {
        return new Prodotto(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Prodotto rename(Name name) {
        return new Prodotto(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Prodotto rename(Table<?> name) {
        return new Prodotto(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Prodotto where(Condition condition) {
        return new Prodotto(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Prodotto where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Prodotto where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Prodotto where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Prodotto where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Prodotto where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Prodotto where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Prodotto where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Prodotto whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Prodotto whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
