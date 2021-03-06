/**
 * This class is generated by jOOQ
 */
package test.generated.tables.records;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.4.4" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CustomerRecord extends org.jooq.impl.UpdatableRecordImpl<test.generated.tables.records.CustomerRecord> implements org.jooq.Record5<java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String> {

	private static final long serialVersionUID = 1294597737;

	/**
	 * Setter for <code>kursschema.customer.idCustomer</code>.
	 */
	public void setIdcustomer(java.lang.Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>kursschema.customer.idCustomer</code>.
	 */
	public java.lang.Integer getIdcustomer() {
		return (java.lang.Integer) getValue(0);
	}

	/**
	 * Setter for <code>kursschema.customer.Name</code>.
	 */
	public void setName(java.lang.String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>kursschema.customer.Name</code>.
	 */
	public java.lang.String getName() {
		return (java.lang.String) getValue(1);
	}

	/**
	 * Setter for <code>kursschema.customer.Adress</code>.
	 */
	public void setAdress(java.lang.String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>kursschema.customer.Adress</code>.
	 */
	public java.lang.String getAdress() {
		return (java.lang.String) getValue(2);
	}

	/**
	 * Setter for <code>kursschema.customer.Phone</code>.
	 */
	public void setPhone(java.lang.String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>kursschema.customer.Phone</code>.
	 */
	public java.lang.String getPhone() {
		return (java.lang.String) getValue(3);
	}

	/**
	 * Setter for <code>kursschema.customer.Banking_Account</code>.
	 */
	public void setBankingAccount(java.lang.String value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>kursschema.customer.Banking_Account</code>.
	 */
	public java.lang.String getBankingAccount() {
		return (java.lang.String) getValue(4);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Record1<java.lang.Integer> key() {
		return (org.jooq.Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record5 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row5<java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String> fieldsRow() {
		return (org.jooq.Row5) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row5<java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String> valuesRow() {
		return (org.jooq.Row5) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field1() {
		return test.generated.tables.Customer.CUSTOMER.IDCUSTOMER;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field2() {
		return test.generated.tables.Customer.CUSTOMER.NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field3() {
		return test.generated.tables.Customer.CUSTOMER.ADRESS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field4() {
		return test.generated.tables.Customer.CUSTOMER.PHONE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field5() {
		return test.generated.tables.Customer.CUSTOMER.BANKING_ACCOUNT;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value1() {
		return getIdcustomer();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value2() {
		return getName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value3() {
		return getAdress();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value4() {
		return getPhone();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value5() {
		return getBankingAccount();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CustomerRecord value1(java.lang.Integer value) {
		setIdcustomer(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CustomerRecord value2(java.lang.String value) {
		setName(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CustomerRecord value3(java.lang.String value) {
		setAdress(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CustomerRecord value4(java.lang.String value) {
		setPhone(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CustomerRecord value5(java.lang.String value) {
		setBankingAccount(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CustomerRecord values(java.lang.Integer value1, java.lang.String value2, java.lang.String value3, java.lang.String value4, java.lang.String value5) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached CustomerRecord
	 */
	public CustomerRecord() {
		super(test.generated.tables.Customer.CUSTOMER);
	}

	/**
	 * Create a detached, initialised CustomerRecord
	 */
	public CustomerRecord(java.lang.Integer idcustomer, java.lang.String name, java.lang.String adress, java.lang.String phone, java.lang.String bankingAccount) {
		super(test.generated.tables.Customer.CUSTOMER);

		setValue(0, idcustomer);
		setValue(1, name);
		setValue(2, adress);
		setValue(3, phone);
		setValue(4, bankingAccount);
	}
}
