package com.vidarramdal.graphql;

import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Function;

public class HandlekurvRepository {

    public HandlekurvRepository() {
    }

    public static class Join {
        String tableName;
        String foreignKeyColumn;
        String primaryKeyColumn;

        public Join(String tableName, String foreignKeyColumn, String primaryKeyColumn) {
            this.tableName = tableName;
            this.foreignKeyColumn = foreignKeyColumn;
            this.primaryKeyColumn = primaryKeyColumn;
        }
    }
}
