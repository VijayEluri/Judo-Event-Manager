/*
 * EventManager
 * Copyright (c) 2008-2017 James Watmuff & Leonard Hall
 *
 * This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package au.com.jwatmuff.eventmanager.db.sqlite;

import au.com.jwatmuff.eventmanager.db.PoolDAO;
import au.com.jwatmuff.eventmanager.model.vo.Player;
import au.com.jwatmuff.eventmanager.model.vo.Pool;
import au.com.jwatmuff.eventmanager.model.vo.Pool.Place;
import au.com.jwatmuff.eventmanager.util.CompositeSqlParameterSource;
import au.com.jwatmuff.genericdb.distributed.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

/**
 *
 * @author James
 */
public class SQLitePoolDAO implements PoolDAO {
    private final SimpleJdbcTemplate template;
    
    private static final String
            ID_FIELD = "id",
            LOCKED_STATUS_FIELD = "locked_status",
            DESCRIPTION_FIELD = "description",
            SHORT_NAME_FIELD = "short_name",
            MAXIMUM_AGE_FIELD = "max_age",
            MINIMUM_AGE_FIELD = "min_age",
            MAXIMUM_WEIGHT_FIELD = "max_weight",
            MINIMUM_WEIGHT_FIELD = "min_weight",
            MAXIMUM_GRADE_FIELD = "max_grade",
            MINIMUM_GRADE_FIELD = "min_grade",
            GENDER_FIELD = "gender",
            MATCH_TIME_FIELD = "match_time",
            MINIMUM_BREAK_TIME_FIELD = "min_break_time",
            GOLDEN_SCORE_TIME_FIELD = "golden_score_time",
            TEMPLATE_NAME_FIELD = "template_name",
            PLACES_FIELD = "places",
            DRAW_POOLS_FIELD = "draw_pools",
            VALID_FIELD = "is_valid",
            TIMESTAMP_FIELD = "last_updated";
    
    /** Creates a new instance of SQLitePoolDAO */
    public SQLitePoolDAO(SimpleJdbcTemplate template) {
        this.template = template;
    }
    
    private static ParameterizedRowMapper<Pool> mapper =
            new ParameterizedRowMapper<Pool>() {
        @Override
        public Pool mapRow(ResultSet rs, int rowNum) throws SQLException {
            Pool.LockedStatus lockedStatus = Pool.LockedStatus.valueOf(rs.getString(LOCKED_STATUS_FIELD));
            Pool p = new Pool(lockedStatus);

            p.setID(rs.getInt(ID_FIELD));
            p.setDescription(rs.getString(DESCRIPTION_FIELD));
            p.setShortName(rs.getString(SHORT_NAME_FIELD));
            p.setMaximumAge(rs.getInt(MAXIMUM_AGE_FIELD));
            p.setMinimumAge(rs.getInt(MINIMUM_AGE_FIELD));
            p.setMaximumWeight(rs.getDouble(MAXIMUM_WEIGHT_FIELD));
            p.setMinimumWeight(rs.getDouble(MINIMUM_WEIGHT_FIELD));
            p.setMaximumGrade(Player.Grade.fromString(rs.getString(MAXIMUM_GRADE_FIELD)));
            p.setMinimumGrade(Player.Grade.fromString(rs.getString(MINIMUM_GRADE_FIELD)));
            p.setGender(Player.Gender.fromString(rs.getString(GENDER_FIELD)));
            p.setMatchTime(rs.getInt(MATCH_TIME_FIELD));
            p.setMinimumBreakTime(rs.getInt(MINIMUM_BREAK_TIME_FIELD));
            p.setGoldenScoreTime(rs.getInt(GOLDEN_SCORE_TIME_FIELD));
            p.setTemplateName(rs.getString(TEMPLATE_NAME_FIELD));
            p.setPlaces(placesFromString(rs.getString(PLACES_FIELD)));
            p.setDrawPools(drawPoolsFromString(rs.getString(DRAW_POOLS_FIELD)));
            p.setValid(rs.getBoolean(VALID_FIELD));
            p.setTimestamp(new Timestamp(rs.getDate(TIMESTAMP_FIELD).getTime()));
            return p;
        }
    };
    
    @Override
    public Collection<Pool> findAll() {
        final String sql = "SELECT * FROM pool WHERE is_valid ORDER BY description ASC";
        return template.query(sql, mapper);
    }
    
    @Override
    public Collection<Pool> findWithPlayers() {
        final String sql = "SELECT pool.* FROM pool, player_has_pool WHERE pool.id = pool_id AND pool.is_valid AND player_has_pool.is_valid GROUP BY pool.id";
        return template.query(sql, mapper);
    }
    
    @Override
    public Collection<Pool> findWithLockedStatus(Pool.LockedStatus lockedStatus) {
        final String sql = "SELECT * FROM pool WHERE locked_status = ? AND is_valid ORDER BY description ASC";
        return template.query(sql, mapper, lockedStatus);
    }
    
    @Override
    public Pool findByDescription(String name) {
        try {
            final String sql = "SELECT * FROM pool WHERE description = ?";
            return template.queryForObject(sql, mapper, name);
        } catch(EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    @Override
    public Collection<Pool> findForSession(int sessionID) {
        final String sql = "SELECT pool.* FROM pool, session_has_pool WHERE session_has_pool.pool_id = pool.id AND pool.is_valid AND session_has_pool.is_valid AND session_has_pool.session_id = ? GROUP BY pool.id, pool.description ORDER BY pool.description";
        return template.query(sql, mapper, sessionID);
    }

    @Override
    public Collection<Pool> findWithoutSession() {
        final String sql = "SELECT * FROM pool WHERE id NOT IN (SELECT pool_id FROM session_has_pool, session WHERE session.is_valid AND session_has_pool.is_valid AND session.id = session_id) AND locked_status = 'FIGHTS_LOCKED' AND is_valid ORDER BY pool.description";
        return template.query(sql, mapper);
    }
    
    @Override
    public Pool get(Object id) {
        try {
            final String sql = "SELECT * FROM pool WHERE id = ?";
            return template.queryForObject(sql, mapper, id);
        } catch(EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    @Override
    public void add(Pool p) {
        final String sql = "INSERT INTO pool (id, description, short_name, max_age, min_age, max_weight, min_weight, max_grade, min_grade, gender, match_time, min_break_time, golden_score_time, template_name, places, draw_pools, locked_status, is_valid, last_updated) VALUES (:ID, :description, :shortName, :maximumAge, :minimumAge, :maximumWeight, :minimumWeight, :maximumGrade, :minimumGrade, :gender, :matchTime, :minimumBreakTime, :goldenScoreTime, :templateName, :places, :drawPools, :lockedStatus, :valid, :timestamp);";
        SqlParameterSource params = getSqlParams(p);
        template.update(sql, params);
    }
    
    @Override
    public void update(Pool p) {
        final String sql = "UPDATE pool SET description=:description, short_name=:shortName, max_age=:maximumAge, min_age=:minimumAge, max_weight=:maximumWeight, min_weight=:minimumWeight, max_grade=:maximumGrade, min_grade=:minimumGrade, gender=:gender, match_time=:matchTime, min_break_time=:minimumBreakTime, golden_score_time=:goldenScoreTime, template_name=:templateName, places=:places, draw_pools=:drawPools, locked_status=:lockedStatus, is_valid=:valid, last_updated=:timestamp WHERE id=:ID";
        SqlParameterSource params = getSqlParams(p);
        template.update(sql, params);
    }
    
    @Override
    public void delete(Pool p) {
        throw new RuntimeException("Delete not supported");
    }

    @Override
    public Class<Pool> getDataClass() {
        return Pool.class;
    }

    private static SqlParameterSource getSqlParams(Pool p) {
        SqlParameterSource bean = new BeanPropertySqlParameterSource(p);
        SqlParameterSource map = new MapSqlParameterSource()
                .addValue("places", placesToString(p.getPlaces()))
                .addValue("drawPools", drawPoolsToString(p.getDrawPools()));

        return new CompositeSqlParameterSource(map, bean);
    }

    private static List<Place> placesFromString(String placeCodes) {
        List<Place> places = new ArrayList<Place>();
        if(!placeCodes.isEmpty()) {
            for(String code : placeCodes.split(",")) {
                String[] pair = code.split(":");
                Place p = new Place();
                p.name = pair[0];
                p.code = pair[1];
                places.add(p);
            }
        }
        return places;
    }

     private static String placesToString(List<Place> places) {
         String s = "";
         for(Place place : places) {
             if(!s.equals("")) s += ",";
             s += place.name + ":" + place.code;
         }
         return s;
     }

    private static Map<Integer, Integer> drawPoolsFromString(String drawPoolString) {
        Map<Integer, Integer> drawPools = new HashMap<Integer, Integer>();
        if(!drawPoolString.isEmpty()) {
            for(String entry : drawPoolString.split(",")) {
                String[] pair = entry.split(":");
                drawPools.put(Integer.valueOf(pair[0]), Integer.valueOf(pair[1]));
            }
        }
        return drawPools;
    }

    private static String drawPoolsToString(Map<Integer, Integer> drawPools) {
        String s = "";
        for(Map.Entry<Integer,Integer> entry : drawPools.entrySet()) {
            if(!s.equals("")) s += ",";
            s += entry.getKey() + ":" + entry.getValue();
        }
        return s;
    }
}
