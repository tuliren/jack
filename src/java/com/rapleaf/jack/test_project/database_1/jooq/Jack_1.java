/**
 * This class is generated by jOOQ
 */
package com.rapleaf.jack.test_project.database_1.jooq;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.4.4" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Jack_1 extends org.jooq.impl.SchemaImpl {

	private static final long serialVersionUID = -237769899;

	/**
	 * The singleton instance of <code>jack_1</code>
	 */
	public static final Jack_1 JACK_1 = new Jack_1();

	/**
	 * No further instances allowed
	 */
	private Jack_1() {
		super("jack_1");
	}

	@Override
	public final java.util.List<org.jooq.Table<?>> getTables() {
		java.util.List result = new java.util.ArrayList();
		result.addAll(getTables0());
		return result;
	}

	private final java.util.List<org.jooq.Table<?>> getTables0() {
		return java.util.Arrays.<org.jooq.Table<?>>asList(
			com.rapleaf.jack.test_project.database_1.jooq.tables.Comments.COMMENTS,
			com.rapleaf.jack.test_project.database_1.jooq.tables.Images.IMAGES,
			com.rapleaf.jack.test_project.database_1.jooq.tables.Posts.POSTS,
			com.rapleaf.jack.test_project.database_1.jooq.tables.SchemaMigrations.SCHEMA_MIGRATIONS,
			com.rapleaf.jack.test_project.database_1.jooq.tables.Users.USERS);
	}
}
