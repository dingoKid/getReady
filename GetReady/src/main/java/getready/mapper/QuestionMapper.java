package getready.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import getready.dto.QuestionDto;
import getready.model.Question;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
	
	@Mapping(target = "labels", ignore = true)
	QuestionDto questionToDto(Question question);

	
	@Mapping(target = "labels", ignore = true)
	@Mapping(target = "id", ignore = true)
	Question dtoToQuestion(QuestionDto dto);
}
