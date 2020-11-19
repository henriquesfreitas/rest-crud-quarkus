package br.com.rest_crud_quarkus.dao.common;

import javax.ejb.Stateless;

import br.com.rest_crud_quarkus.dto.common.GenericDTO;
import br.com.rest_crud_quarkus.models.GenericEntity;

//@Stateless
public interface GenericDAOInterface<DTO extends GenericDTO, ENT extends GenericEntity> {
	
	public abstract ENT convertFromDTO(DTO dto);
	public abstract DTO convertFromEntity(ENT entity);

}
