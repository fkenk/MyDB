/**
 * This class is generated by jOOQ
 */
package test.generated.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.4.4" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Sent extends org.jooq.impl.TableImpl<test.generated.tables.records.SentRecord> {

	private static final long serialVersionUID = -585667088;

	/**
	 * The singleton instance of <code>kursschema.sent</code>
	 */
	public static final test.generated.tables.Sent SENT = new test.generated.tables.Sent();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<test.generated.tables.records.SentRecord> getRecordType() {
		return test.generated.tables.records.SentRecord.class;
	}

	/**
	 * The column <code>kursschema.sent.idsent</code>.
	 */
	public final org.jooq.TableField<test.generated.tables.records.SentRecord, java.lang.Integer> IDSENT = createField("idsent", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>kursschema.sent.date</code>.
	 */
	public final org.jooq.TableField<test.generated.tables.records.SentRecord, java.sql.Date> DATE = createField("date", org.jooq.impl.SQLDataType.DATE, this, "");

	/**
	 * The column <code>kursschema.sent.idProduction</code>.
	 */
	public final org.jooq.TableField<test.generated.tables.records.SentRecord, java.lang.Integer> IDPRODUCTION = createField("idProduction", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>kursschema.sent.count</code>.
	 */
	public final org.jooq.TableField<test.generated.tables.records.SentRecord, java.lang.Integer> COUNT = createField("count", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>kursschema.sent.numOrder</code>.
	 */
	public final org.jooq.TableField<test.generated.tables.records.SentRecord, java.lang.Integer> NUMORDER = createField("numOrder", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * Create a <code>kursschema.sent</code> table reference
	 */
	public Sent() {
		this("sent", null);
	}

	/**
	 * Create an aliased <code>kursschema.sent</code> table reference
	 */
	public Sent(java.lang.String alias) {
		this(alias, test.generated.tables.Sent.SENT);
	}

	private Sent(java.lang.String alias, org.jooq.Table<test.generated.tables.records.SentRecord> aliased) {
		this(alias, aliased, null);
	}

	private Sent(java.lang.String alias, org.jooq.Table<test.generated.tables.records.SentRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, test.generated.Kursschema.KURSSCHEMA, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<test.generated.tables.records.SentRecord> getPrimaryKey() {
		return test.generated.Keys.KEY_SENT_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<test.generated.tables.records.SentRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<test.generated.tables.records.SentRecord>>asList(test.generated.Keys.KEY_SENT_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<test.generated.tables.records.SentRecord, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<test.generated.tables.records.SentRecord, ?>>asList(test.generated.Keys.FK_SENT_PRODUCTS1, test.generated.Keys.FK_SENT_ORDER_CONTRACT1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public test.generated.tables.Sent as(java.lang.String alias) {
		return new test.generated.tables.Sent(alias, this);
	}

	/**
	 * Rename this table
	 */
	public test.generated.tables.Sent rename(java.lang.String name) {
		return new test.generated.tables.Sent(name, null);
	}
}
