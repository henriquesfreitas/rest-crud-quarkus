package br.com.rest_crud_quarkus.dao.common;

import br.com.rest_crud_quarkus.dto.common.LogDTO;
import br.com.rest_crud_quarkus.models.LogEntity;

public class ConvertDAODTO {
	
	public static LogDTO convertLogEntityToLogDTO(LogEntity logEntity) {
		return new LogDTO(
				logEntity.getCreation(), logEntity.getIdCreator(), logEntity.getChange(), logEntity.getIdChanger(),
				logEntity.isDisabled(), logEntity.getIdDeactivator()
		);
	}
	
	public static LogEntity convertLogDTOToLogEntity(LogDTO logDTO) {
		if(logDTO == null)
			return new LogEntity();
		
		LogEntity logEntity = new LogEntity();
		logEntity.setCreation(logDTO.getCreation());
		logEntity.setIdCreator(logDTO.getIdCreator());
		logEntity.setChange(logDTO.getChange());
		logEntity.setIdChanger(logDTO.getIdChanger());
		logEntity.setDisabled(logDTO.isDisabled());
		logEntity.setIdDeactivator(logDTO.getIdDeactivator());
		
		return logEntity;
	}

}
