package com.rapleaf.jack.queries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rapleaf.jack.tracking.QueryStatistics;

public class RecordIterator implements Iterator<Record>, AutoCloseable {
  private static final Logger LOG = LoggerFactory.getLogger(RecordIterator.class);

  private PreparedStatement preparedStatement;
  private Set<Column> selectedColumns;

  private ResultSet resultSet;
  private boolean isNextConsumed;
  private Record next;

  private QueryStatistics queryStatistics;

  RecordIterator(PreparedStatement preparedStatement, Set<Column> selectedColumns, ResultSet resultSet) {
    this.preparedStatement = preparedStatement;
    this.selectedColumns = selectedColumns;

    this.resultSet = resultSet;
    this.isNextConsumed = true;
    this.next = null;

    this.queryStatistics = null;
  }

  void addStatistics(QueryStatistics statistics) {
    this.queryStatistics = statistics;
  }

  @Override
  public boolean hasNext() {
    if (!isNextConsumed) {
      return true;
    }

    try {
      boolean hasNext = resultSet.next();
      if (hasNext) {
        next = QueryFetcher.parseResultSet(resultSet, selectedColumns);
      }
      return hasNext;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Record next() {
    return next;
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void close() {
    try {
      preparedStatement.close();
      resultSet.close();
    } catch (SQLException e) {
      LOG.error("RecordIterator close operation failed", e);
    }
  }

  public QueryStatistics getQueryStatistics() {
    return queryStatistics;
  }

}
