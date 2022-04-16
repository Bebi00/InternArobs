package com.example.musify.mapper;

import com.example.musify.dto.AlternativeTitleDTO;
import com.example.musify.dto.AlternativeTitleNewDTO;
import com.example.musify.entities.AlternativeTitle;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AlternativeTitleMapper {
    AlternativeTitleDTO toDTO(AlternativeTitle alternativeTitle);

    AlternativeTitleDTO toDTO(AlternativeTitleNewDTO alternativeTitleNewDTO);

    AlternativeTitle toEntity(AlternativeTitleDTO alternativeTitleDTO);

    AlternativeTitle toEntity(AlternativeTitleNewDTO alternativeTitleNewDTO);

    List<AlternativeTitleDTO> toDTOs(List<AlternativeTitle> alternativeTitles);
}
