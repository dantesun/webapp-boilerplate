package io.github.dantesun.petclinic.data.entities;

import org.apache.ibatis.type.Alias;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.*;
import java.time.Instant;

/**
 * Created by dsun on 15/3/2.
 */
@Alias("javaTimeInstant")
public class InstantTypeHandler extends BaseTypeHandler<Instant> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Instant parameter, JdbcType jdbcType) throws SQLException {
        ps.setTimestamp(i, Timestamp.from(parameter));
    }

    @Override
    public Instant getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return getInstant(rs.getTimestamp(columnName));
    }

    @Override
    public Instant getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return getInstant(rs.getTimestamp(columnIndex));
    }

    private Instant getInstant(Timestamp ts) {
        return null == ts ? null : ts.toInstant();
    }

    @Override
    public Instant getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return getInstant(cs.getTimestamp(columnIndex));
    }
}
