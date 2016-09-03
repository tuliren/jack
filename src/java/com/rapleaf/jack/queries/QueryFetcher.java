package com.rapleaf.jack.queries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLRecoverableException;
import java.sql.Timestamp;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rapleaf.jack.BaseDatabaseConnection;

public class QueryFetcher {

  private static final Logger LOG = LoggerFactory.getLogger(QueryFetcher.class);

  public static Records getQueryResults(PreparedStatement preparedStatement, Set<Column> selectedColumns, BaseDatabaseConnection dbConnection) throws SQLException {
    ResultSet resultSet = null;

    try {
      resultSet = preparedStatement.executeQuery();
      Records results = new Records();
      while (resultSet.next()) {
        Record record = parseResultSet(resultSet, selectedColumns);
        if (record != null) {
          results.addRecord(record);
        }
      }
      return results;
    } catch (SQLRecoverableException e) {
      dbConnection.resetConnection();
      throw e;
    } finally {
      try {
        if (resultSet != null) {
          resultSet.close();
        }
        preparedStatement.close();
      } catch (SQLRecoverableException e) {
        LOG.error(e.toString());
        dbConnection.resetConnection();
      } catch (SQLException e) {
        LOG.error(e.toString());
      }
    }
  }

  static Record parseResultSet(ResultSet resultSet, Set<Column> selectedColumns) throws SQLException {
    if (selectedColumns.isEmpty()) {
      return null;
    }

    Record record = new Record(selectedColumns.size());
    for (Column column : selectedColumns) {
      String sqlKeyword = column.getSqlKeyword();
      Class type = column.getType();
      Object value;

      if (type == Integer.class) {
        value = resultSet.getInt(sqlKeyword);
      } else if (type == Long.class) {
        value = resultSet.getLong(sqlKeyword);
      } else if (type == java.sql.Date.class) {
        java.sql.Date date = resultSet.getDate(sqlKeyword);
        if (date != null) {
          value = date.getTime();
        } else {
          value = null;
        }
      } else if (type == Timestamp.class) {
        Timestamp timestamp = resultSet.getTimestamp(sqlKeyword);
        if (timestamp != null) {
          value = timestamp.getTime();
        } else {
          value = null;
        }
      } else if (type == Double.class) {
        value = resultSet.getDouble(sqlKeyword);
      } else if (type == String.class) {
        value = resultSet.getString(sqlKeyword);
      } else if (type == Boolean.class) {
        value = resultSet.getBoolean(sqlKeyword);
      } else if (type == byte[].class) {
        value = resultSet.getBytes(sqlKeyword);
      } else {
        value = resultSet.getObject(sqlKeyword);
      }

      if (resultSet.wasNull()) {
        value = null;
      }

      record.addColumn(column, value);
    }
    return record;
  }
}
