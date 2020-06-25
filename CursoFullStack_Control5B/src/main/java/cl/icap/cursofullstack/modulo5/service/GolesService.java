package cl.icap.cursofullstack.modulo5.service;

import java.util.List;

import cl.icap.cursofullstack.modulo5.model.dto.GolesDto;

public interface GolesService {
	public List<GolesDto> list();
	public List<GolesDto> get(int jug_rut);
	public int insert(GolesDto golesDTO);
	public int update(GolesDto golesDTO);
	public int delete(int jug_rut);

}
