package com.sltc.solicitudes;

import com.sltc.solicitudes.Solicitud;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class SolicitudRepository {
    private final JdbcTemplate jdbc;
    private final SimpleJdbcInsert inserter;

    public SolicitudRepository(JdbcTemplate jdbc, DataSource ds) {
        this.jdbc = jdbc;
        this.inserter = new SimpleJdbcInsert(ds)
                .withTableName("solicitudes")
                .usingGeneratedKeyColumns("id");
    }

    private final RowMapper<Solicitud> mapper = new RowMapper<>() {
        @Override
        public Solicitud mapRow(ResultSet rs, int rowNum) throws SQLException {
            Solicitud s = new Solicitud();
            s.setId(rs.getLong("id"));
            s.setCostoEstimatado(rs.getDouble("costo_estimatado"));
            s.setCostoFinal(rs.getDouble("costo_final"));
            s.setTiempoEstimado(rs.getInt("tiempo_estimado"));
            s.setTiempoReal(rs.getInt("tiempo_real"));
            s.setEstado(rs.getString("estado"));
            long idCli = rs.getLong("id_cliente");
            if (!rs.wasNull()) s.setIdCliente(idCli);
            long idCont = rs.getLong("id_contenedor");
            if (!rs.wasNull()) s.setIdContenedor(idCont);
            return s;
        }
    };

    public List<Solicitud> findAll() {
        return jdbc.query("SELECT id, costo_estimatado, costo_final, tiempo_estimado, tiempo_real, estado, id_cliente, id_contenedor FROM solicitudes", mapper);
    }

    public Optional<Solicitud> findById(Long id) {
        List<Solicitud> list = jdbc.query("SELECT id, costo_estimatado, costo_final, tiempo_estimado, tiempo_real, estado, id_cliente, id_contenedor FROM solicitudes WHERE id = ?", mapper, id);
        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }

    public Solicitud save(Solicitud s) {
        Map<String, Object> values = new HashMap<>();
        values.put("costo_estimatado", s.getCostoEstimatado());
        values.put("costo_final", s.getCostoFinal());
        values.put("tiempo_estimado", s.getTiempoEstimado());
        values.put("tiempo_real", s.getTiempoReal());
        values.put("estado", s.getEstado());
        values.put("id_cliente", s.getIdCliente());
        values.put("id_contenedor", s.getIdContenedor());
        Number key = inserter.executeAndReturnKey(values);
        s.setId(key.longValue());
        return s;
    }

    public int update(Solicitud s) {
        return jdbc.update(
            "UPDATE solicitudes SET costo_estimatado = ?, costo_final = ?, tiempo_estimado = ?, tiempo_real = ?, estado = ?, id_cliente = ?, id_contenedor = ? WHERE id = ?",
            s.getCostoEstimatado(), s.getCostoFinal(), s.getTiempoEstimado(), s.getTiempoReal(), s.getEstado(), s.getIdCliente(), s.getIdContenedor(), s.getId()
        );
    }

    public int delete(Long id) {
        return jdbc.update("DELETE FROM solicitudes WHERE id = ?", id);
    }
}