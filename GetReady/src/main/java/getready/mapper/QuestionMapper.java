package getready.mapper;

import org.mapstruct.Mapper;

import getready.dto.QuestionDto;
import getready.model.Question;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
	
	QuestionDto questionToDto(Question question);

}
