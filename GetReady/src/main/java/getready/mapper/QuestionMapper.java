package getready.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import getready.dto.QuestionDto;
import getready.model.Label;
import getready.model.Question;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
	
	@Mapping(source = "labels", target = "labels", qualifiedByName = "toStringList")
	QuestionDto questionToDto(Question question);

	
	@Mapping(target = "labels", ignore = true)
	@Mapping(target = "id", ignore = true)
	Question dtoToQuestion(QuestionDto dto);
	
	@Named("toStringList")
	public static List<String> toStringList(List<Label> labelList) {
		return labelList.stream()
				.map(label -> label.getName())
				.collect(Collectors.toList());
	}

	List<QuestionDto> questionsToDtos(List<Question> questions);
}
