package pers.hd.simplepro.server.jpa.jdbc;

import java.util.List;
import java.util.Map;

public interface JdbcQueryService {

    List<Map<Object, Object>> find(String workspaceId, String sql);

    Integer update(String workspaceId, String sql, List<Object> params);

    Integer update(String workspaceId, String sql);

    Integer insert(String workspaceId, String sql, List<Object> params);

    Integer insert(String workspaceId, String sql);

    Boolean delete(String wokspaceId, String sql, List<Object> params);

    Boolean delete(String workspaceId, String sql);
}
