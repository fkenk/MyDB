/**
 * This class is generated by jOOQ
 */
package test.generated;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.4.4" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Kursschema extends org.jooq.impl.SchemaImpl {

	private static final long serialVersionUID = -2023590843;

	/**
	 * The singleton instance of <code>kursschema</code>
	 */
	public static final Kursschema KURSSCHEMA = new Kursschema();

	/**
	 * No further instances allowed
	 */
	private Kursschema() {
		super("kursschema");
	}

	@Override
	public final java.util.List<org.jooq.Table<?>> getTables() {
		java.util.List result = new java.util.ArrayList();
		result.addAll(getTables0());
		return result;
	}

	private final java.util.List<org.jooq.Table<?>> getTables0() {
		return java.util.Arrays.<org.jooq.Table<?>>asList(
			test.generated.tables.Customer.CUSTOMER,
			test.generated.tables.OrderContract.ORDER_CONTRACT,
			test.generated.tables.Plan.PLAN,
			test.generated.tables.Produced.PRODUCED,
			test.generated.tables.Products.PRODUCTS,
			test.generated.tables.Sent.SENT,
			test.generated.tables.Vcustomer.VCUSTOMER,
			test.generated.tables.Vcustomer2.VCUSTOMER2);
	}
}
