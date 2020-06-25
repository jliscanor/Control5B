package cl.icap.cursofullstack.modulo5.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import cl.icap.cursofullstack.modulo5.model.dto.GolesDto;
import cl.icap.cursofullstack.modulo5.service.GolesService;


@RestController
@RequestMapping(value="/goles")
public class GolesController {
	
	@Autowired
	GolesService golesService;
	
	@RequestMapping(value="/list")
	public @ResponseBody List<GolesDto> ajaxList(HttpServletRequest req, HttpServletResponse res) {
		return golesService.list();
	}
		
		
	@RequestMapping(value="/get")
	public List<GolesDto> ajaxGet(HttpServletRequest req, HttpServletResponse res) {
		List<GolesDto> goles = golesService.get(Integer.parseInt(req.getParameter("jug_rut")));
		return goles;
		}
	
	@RequestMapping(value="/insert")
	public @ResponseBody int ajaxInsert(HttpServletRequest req, HttpServletResponse res) {
		int rows=0;
		String requestData;
		try { 
			requestData = req.getReader().lines().collect(Collectors.joining());
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			GolesDto golesDto = gson.fromJson(requestData, GolesDto.class);
			rows = golesService.insert(golesDto);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
			return rows;
	}
	
	@RequestMapping(value="/update")
	public @ResponseBody int ajaxUpdate(HttpServletRequest req, HttpServletResponse res) {
		int rows=0;
		String requestData;
		try { 
			requestData = req.getReader().lines().collect(Collectors.joining());
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			GolesDto golesDto = gson.fromJson(requestData, GolesDto.class);
			rows = golesService.update(golesDto);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
			return rows;
	}
	
	@RequestMapping(value="/delete")
	public @ResponseBody int ajaxDelete(HttpServletRequest req, HttpServletResponse res) {
		int rows=0;
		try {
			rows = golesService.delete(Integer.parseInt(req.getParameter("jug_rut")));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return rows;
	}
}
