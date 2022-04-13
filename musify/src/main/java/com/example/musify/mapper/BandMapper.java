package com.example.musify.mapper;


import com.example.musify.dto.BandDTO;
import com.example.musify.dto.BandNewDTO;
import com.example.musify.entities.Band;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;


@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.WARN)
public interface BandMapper {
    BandDTO toDTO(Band band);

    Band toEntity(BandDTO bandDTO);

    Band toEntityNew(BandNewDTO bandNewDTO);

    List<BandDTO> toDTOs(List<Band> bands);
}
