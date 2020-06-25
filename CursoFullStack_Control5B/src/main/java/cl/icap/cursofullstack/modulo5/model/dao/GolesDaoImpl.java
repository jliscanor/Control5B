package cl.icap.cursofullstack.modulo5.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cl.icap.cursofullstack.modulo5.model.dto.GolesDto;

@Repository
@Transactional
public class GolesDaoImpl implements GolesDao {
	private String insert = "INSERT INTO GOLES VALUES(?,?)";
	private String select = "SELECT FROM GOLES WHERE jug_rut=?";
	private String update = "UPDATE GOLES SET jug_rut=?,gol_cantidad=? WHERE jug_rut=?";
	private String delete = "DELETE GOLES WHERE jug_rut=?";
	private String list = "SELECT * FROM GOLES";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public GolesDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<GolesDto> list() {
		List<GolesDto> listGoles = jdbcTemplate.query(list, BeanPropertyRowMapper.newInstance(GolesDto.class));
		return listGoles;
	}

	@Override
	public List<GolesDto> get(int jug_rut) {
		Object[] args = {jug_rut};
	    List<GolesDto> golesDTO;
	    String get = null;
	    
	    try {
			golesDTO = jdbcTemplate.query(get,args,BeanPropertyRowMapper.newInstance(GolesDto.class));
	    } catch (EmptyResultDataAccessException e) {
	    	golesDTO=null;
	    	e.printStackTrace();
	    } catch (Exception e) {
	    	golesDTO=null;
	    	e.printStackTrace();
	    }
		return golesDTO;
	}

	@Override
	public int insert(GolesDto golesDTO) {
		int rows = 0;
		Object args[] = {
				golesDTO.getJug_rut(),
				golesDTO.getGol_cantidad(),
		};
		
		try {
		rows = jdbcTemplate.update(insert, args);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public int update(GolesDto golesDTO) {
		int rows = 0;
		Object args[] = {
				golesDTO.getJug_rut(),
				golesDTO.getGol_cantidad(),
				golesDTO.getJug_rut()
		};
		
		try {
		rows = jdbcTemplate.update(update, args);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public int delete(int jug_rut) {
		int rows=0;
		Object args[] = {jug_rut};
		
		try {
		rows = jdbcTemplate.update(delete, args);
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return rows;
	}

}
