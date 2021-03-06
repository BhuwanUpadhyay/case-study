/*
 * This file is generated by jOOQ.
 */
package io.github.bhuwanupadhyay.casestudy.sales.jooq.tables;

import io.github.bhuwanupadhyay.casestudy.sales.jooq.Keys;
import io.github.bhuwanupadhyay.casestudy.sales.jooq.Public;
import io.github.bhuwanupadhyay.casestudy.sales.jooq.tables.records.SaleOrdersRecord;
import org.jooq.Record;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SaleOrders extends TableImpl<SaleOrdersRecord> {

	/**
	 * The reference instance of <code>public.sale_orders</code>
	 */
	public static final SaleOrders SALE_ORDERS = new SaleOrders();
	private static final long serialVersionUID = -589228716;
	/**
	 * The column <code>public.sale_orders.id</code>.
	 */
	public final TableField<SaleOrdersRecord, Long> ID = createField(DSL.name("id"),
			org.jooq.impl.SQLDataType.BIGINT.nullable(false).identity(true), this, "");
	/**
	 * The column <code>public.sale_orders.order_id</code>.
	 */
	public final TableField<SaleOrdersRecord, String> ORDER_ID = createField(DSL.name("order_id"),
			org.jooq.impl.SQLDataType.VARCHAR(36).nullable(false), this, "");
	/**
	 * The column <code>public.sale_orders.customer_id</code>.
	 */
	public final TableField<SaleOrdersRecord, String> CUSTOMER_ID = createField(DSL.name("customer_id"),
			org.jooq.impl.SQLDataType.VARCHAR(36), this, "");
	/**
	 * The column <code>public.sale_orders.status</code>.
	 */
	public final TableField<SaleOrdersRecord, String> STATUS = createField(DSL.name("status"),
			org.jooq.impl.SQLDataType.VARCHAR(100), this, "");
	/**
	 * The column <code>public.sale_orders.cancellation_reason</code>.
	 */
	public final TableField<SaleOrdersRecord, String> CANCELLATION_REASON = createField(DSL.name("cancellation_reason"),
			org.jooq.impl.SQLDataType.VARCHAR(255), this, "");
	/**
	 * The column <code>public.sale_orders.created_at</code>.
	 */
	public final TableField<SaleOrdersRecord, LocalDateTime> CREATED_AT = createField(DSL.name("created_at"),
			org.jooq.impl.SQLDataType.LOCALDATETIME, this, "");

	/**
	 * Create a <code>public.sale_orders</code> table reference
	 */
	public SaleOrders() {
		this(DSL.name("sale_orders"), null);
	}

	/**
	 * Create an aliased <code>public.sale_orders</code> table reference
	 */
	public SaleOrders(String alias) {
		this(DSL.name(alias), SALE_ORDERS);
	}

	/**
	 * Create an aliased <code>public.sale_orders</code> table reference
	 */
	public SaleOrders(Name alias) {
		this(alias, SALE_ORDERS);
	}

	private SaleOrders(Name alias, Table<SaleOrdersRecord> aliased) {
		this(alias, aliased, null);
	}

	private SaleOrders(Name alias, Table<SaleOrdersRecord> aliased, Field<?>[] parameters) {
		super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
	}

	public <O extends Record> SaleOrders(Table<O> child, ForeignKey<O, SaleOrdersRecord> key) {
		super(child, key, SALE_ORDERS);
	}

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<SaleOrdersRecord> getRecordType() {
		return SaleOrdersRecord.class;
	}

	@Override
	public Schema getSchema() {
		return Public.PUBLIC;
	}

	@Override
	public Identity<SaleOrdersRecord, Long> getIdentity() {
		return Keys.IDENTITY_SALE_ORDERS;
	}

	@Override
	public UniqueKey<SaleOrdersRecord> getPrimaryKey() {
		return Keys.PK_SALE_ORDERS;
	}

	@Override
	public List<UniqueKey<SaleOrdersRecord>> getKeys() {
		return Arrays.<UniqueKey<SaleOrdersRecord>>asList(Keys.PK_SALE_ORDERS, Keys.SALE_ORDERS_ORDER_ID_KEY);
	}

	@Override
	public SaleOrders as(String alias) {
		return new SaleOrders(DSL.name(alias), this);
	}

	@Override
	public SaleOrders as(Name alias) {
		return new SaleOrders(alias, this);
	}

	/**
	 * Rename this table
	 */
	@Override
	public SaleOrders rename(String name) {
		return new SaleOrders(DSL.name(name), null);
	}

	/**
	 * Rename this table
	 */
	@Override
	public SaleOrders rename(Name name) {
		return new SaleOrders(name, null);
	}

	// -------------------------------------------------------------------------
	// Row6 type methods
	// -------------------------------------------------------------------------

	@Override
	public Row6<Long, String, String, String, String, LocalDateTime> fieldsRow() {
		return (Row6) super.fieldsRow();
	}

}
