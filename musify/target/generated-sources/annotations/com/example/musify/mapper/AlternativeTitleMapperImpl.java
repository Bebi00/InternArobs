package com.example.musify.mapper;

import com.example.musify.dto.AlternativeTitleDTO;
import com.example.musify.dto.AlternativeTitleNewDTO;
import com.example.musify.entities.AlternativeTitle;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-16T16:37:49+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class AlternativeTitleMapperImpl implements AlternativeTitleMapper {

    @Override
    public AlternativeTitleDTO toDTO(AlternativeTitle alternativeTitle) {
        if ( alternativeTitle == null ) {
            return null;
        }

        AlternativeTitleDTO alternativeTitleDTO = new AlternativeTitleDTO();

        alternativeTitleDTO.setId( alternativeTitle.getId() );
        alternativeTitleDTO.setAlternativeTitle( alternativeTitle.getAlternativeTitle() );
        alternativeTitleDTO.setLanguage( alternativeTitle.getLanguage() );

        return alternativeTitleDTO;
    }

    @Override
    public AlternativeTitleDTO toDTO(AlternativeTitleNewDTO alternativeTitleNewDTO) {
        if ( alternativeTitleNewDTO == null ) {
            return null;
        }

        AlternativeTitleDTO alternativeTitleDTO = new AlternativeTitleDTO();

        alternativeTitleDTO.setId( alternativeTitleNewDTO.getId() );
        alternativeTitleDTO.setAlternativeTitle( alternativeTitleNewDTO.getAlternativeTitle() );
        alternativeTitleDTO.setLanguage( alternativeTitleNewDTO.getLanguage() );

        return alternativeTitleDTO;
    }

    @Override
    public AlternativeTitle toEntity(AlternativeTitleDTO alternativeTitleDTO) {
        if ( alternativeTitleDTO == null ) {
            return null;
        }

        AlternativeTitle alternativeTitle = new AlternativeTitle();

        alternativeTitle.setId( alternativeTitleDTO.getId() );
        alternativeTitle.setAlternativeTitle( alternativeTitleDTO.getAlternativeTitle() );
        alternativeTitle.setLanguage( alternativeTitleDTO.getLanguage() );

        return alternativeTitle;
    }

    @Override
    public AlternativeTitle toEntity(AlternativeTitleNewDTO alternativeTitleNewDTO) {
        if ( alternativeTitleNewDTO == null ) {
            return null;
        }

        AlternativeTitle alternativeTitle = new AlternativeTitle();

        alternativeTitle.setId( alternativeTitleNewDTO.getId() );
        alternativeTitle.setAlternativeTitle( alternativeTitleNewDTO.getAlternativeTitle() );
        alternativeTitle.setLanguage( alternativeTitleNewDTO.getLanguage() );

        return alternativeTitle;
    }

    @Override
    public List<AlternativeTitleDTO> toDTOs(List<AlternativeTitle> alternativeTitles) {
        if ( alternativeTitles == null ) {
            return null;
        }

        List<AlternativeTitleDTO> list = new ArrayList<AlternativeTitleDTO>( alternativeTitles.size() );
        for ( AlternativeTitle alternativeTitle : alternativeTitles ) {
            list.add( toDTO( alternativeTitle ) );
        }

        return list;
    }
}
