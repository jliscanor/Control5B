package cl.icap.cursofullstack.modulo5.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.icap.cursofullstack.modulo5.model.dao.GolesDao;
import cl.icap.cursofullstack.modulo5.model.dto.GolesDto;

@Service
public class GolesServiceImpl implements GolesService {
	
	@Autowired
	GolesDao golesDAO;

	public GolesServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<GolesDto> list() {
		return golesDAO.list();
	}

	@Override
	public List<GolesDto> get(int jug_rut) {
		return golesDAO.get(jug_rut);
	}

	@Override
	public int insert(GolesDto golesDTO) {
		return golesDAO.insert(golesDTO);
	}

	@Override
	public int update(GolesDto golesDTO) {
		return golesDAO.update(golesDTO);
	}

	@Override
	public int delete(int jug_rut) {
		return golesDAO.delete(jug_rut);
	}

}
