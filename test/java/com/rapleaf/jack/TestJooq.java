package com.rapleaf.jack;

import static com.rapleaf.jack.test_project.database_1.jooq.Tables.*;

import java.sql.*;

import junit.framework.TestCase;
import org.jooq.*;
import org.jooq.impl.*;
import com.rapleaf.jack.test_project.database_1.jooq.Keys;
import com.rapleaf.jack.test_project.database_1.jooq.tables.records.CommentsRecord;
import com.rapleaf.jack.test_project.database_1.jooq.tables.records.ImagesRecord;
import com.rapleaf.jack.test_project.database_1.jooq.tables.records.PostsRecord;
import com.rapleaf.jack.test_project.database_1.jooq.tables.records.SchemaMigrationsRecord;
import com.rapleaf.jack.test_project.database_1.jooq.tables.records.UsersRecord;

public class TestJooq extends TestCase {

  private DSLContext create;
  private Connection conn;

  @Override
  protected void setUp() throws Exception {
    String userName = "root";
    String password = "";
    String url = "jdbc:mysql://localhost:3306/jack_1";
    Class.forName("com.mysql.jdbc.Driver").newInstance();

    this.conn = DriverManager.getConnection(url, userName, password);
    this.create = DSL.using(conn, SQLDialect.MYSQL);
  }

  @Override
  protected void tearDown() throws Exception {
    if (conn != null) {
      try {
        conn.close();
      } catch (SQLException ignore) {
      }
    }
  }

  public void testCreate() throws Exception {

  }

  public void testRead() throws Exception {
    Result<UsersRecord> usersRecords = create.selectFrom(USERS).fetch();

    for (UsersRecord user : usersRecords) {
      Integer id = user.getId();
      String handle = user.getHandle();
      String bio = user.getBio();
      System.out.printf("User %d, Handle %s, Bio %s\n", id, handle, bio);
      Result<PostsRecord> userPosts = user.fetchChildren(Keys.POSTS_USER_ID_FK);
      for (PostsRecord post : userPosts) {
        System.out.printf("  Post %d: %s\n", post.getId(), post.getTitle());
      }
    }
  }

  public void testUpdate() throws Exception {

  }

  public void testDelete() throws Exception {

  }
}
