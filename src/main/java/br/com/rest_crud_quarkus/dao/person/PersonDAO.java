package br.com.rest_crud_quarkus.dao.person;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import br.com.rest_crud_quarkus.dao.common.GenericDAOInterface;
import br.com.rest_crud_quarkus.dao.common.ConvertDAODTO;
import br.com.rest_crud_quarkus.dao.common.GenericDAO;
import br.com.rest_crud_quarkus.dto.common.GenericDTO;
import br.com.rest_crud_quarkus.dto.person.PersonDTO;
import br.com.rest_crud_quarkus.models.LogEntity;
import br.com.rest_crud_quarkus.models.Person;

@Transactional
@RequestScoped
//@Stateless
//Quarkus doesnt allow EJB, so you have to use transactional instead
public class PersonDAO extends GenericDAO implements Serializable {//, GenericDAOInterface<PersonDTO, Person>{

	private static final long serialVersionUID = -6321526905232236895L;
	
	public void save(PersonDTO personDTO) throws PersonDAOException{
		Person person = convertFromDTO(personDTO);
		
		if(person.getIdPerson() == null) {
			persist(person);
		}else {
			merge(person);
		}
	}
	
	public List<PersonDTO> listAll() {
		List<Person> listPerson = listAll(Person.class);
		List<PersonDTO> listPersonDTO = new ArrayList<PersonDTO>();
		for (Person person : listPerson) {
			listPersonDTO.add(convertFromEntity(person));
		}
		
		return listPersonDTO;
	}

//	@Override
	public Person convertFromDTO(PersonDTO dto) {
		Person person = new Person();
		person.setIdPerson(dto.getIdPerson());
		person.setCpf(dto.getCpf());
		person.setDateBirth(dto.getDateBirth());
		person.setEmail(dto.getEmail());
		person.setName(dto.getName());
		person.setPhone(dto.getPhone());
		person.setLogEntity(ConvertDAODTO.convertLogDTOToLogEntity(dto.getLogDTO()));
		
		return person;
	}

//	@Override
	public PersonDTO convertFromEntity(Person entity) {
		PersonDTO personDTO = new PersonDTO(
				entity.getIdPerson(), entity.getCpf(), entity.getDateBirth(), entity.getEmail(),
				entity.getName(), entity.getPhone()
		);
		personDTO.setLogDTO(ConvertDAODTO.convertLogEntityToLogDTO(entity.getLogEntity()));
		
		return personDTO;
	}

}
