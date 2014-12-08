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
public class Plan extends org.jooq.impl.TableImpl<test.generated.tables.records.PlanRecord> {

	private static final long serialVersionUID = -1043587874;

	/**
	 * The singleton instance of <code>kursschema.plan</code>
	 */
	public static final test.generated.tables.Plan PLAN = new test.generated.tables.Plan();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<test.generated.tables.records.PlanRecord> getRecordType() {
		return test.generated.tables.records.PlanRecord.class;
	}

	/**
	 * The column <code>kursschema.plan.month</code>.
	 */
	public final org.jooq.TableField<test.generated.tables.records.PlanRecord, java.lang.Integer> MONTH = createField("month", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>kursschema.plan.idProduction</code>.
	 */
	public final org.jooq.TableField<test.generated.tables.records.PlanRecord, java.lang.Integer> IDPRODUCTION = createField("idProduction", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>kursschema.plan.count</code>.
	 */
	public final org.jooq.TableField<test.generated.tables.records.PlanRecord, java.lang.Integer> COUNT = createField("count", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>kursschema.plan.percent</code>.
	 */
	public final org.jooq.TableField<test.generated.tables.records.PlanRecord, java.lang.Double> PERCENT = createField("percent", org.jooq.impl.SQLDataType.DOUBLE.defaulted(true), this, "");

	/**
	 * Create a <code>kursschema.plan</code> table reference
	 */
	public Plan() {
		this("plan", null);
	}

	/**
	 * Create an aliased <code>kursschema.plan</code> table reference
	 */
	public Plan(java.lang.String alias) {
		this(alias, test.generated.tables.Plan.PLAN);
	}

	private Plan(java.lang.String alias, org.jooq.Table<test.generated.tables.records.PlanRecord> aliased) {
		this(alias, aliased, null);
	}

	private Plan(java.lang.String alias, org.jooq.Table<test.generated.tables.records.PlanRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, test.generated.Kursschema.KURSSCHEMA, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<test.generated.tables.records.PlanRecord> getPrimaryKey() {
		return test.generated.Keys.KEY_PLAN_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<test.generated.tables.records.PlanRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<test.generated.tables.records.PlanRecord>>asList(test.generated.Keys.KEY_PLAN_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public test.generated.tables.Plan as(java.lang.String alias) {
		return new test.generated.tables.Plan(alias, this);
	}

	/**
	 * Rename this table
	 */
	public test.generated.tables.Plan rename(java.lang.String name) {
		return new test.generated.tables.Plan(name, null);
	}
}
