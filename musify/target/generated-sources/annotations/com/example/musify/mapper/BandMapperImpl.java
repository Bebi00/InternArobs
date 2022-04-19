package com.example.musify.mapper;

import com.example.musify.dto.BandDTO;
import com.example.musify.dto.BandNewDTO;
import com.example.musify.entities.Band;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-19T14:53:27+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class BandMapperImpl implements BandMapper {

    @Override
    public BandDTO toDTO(Band band) {
        if ( band == null ) {
            return null;
        }

        BandDTO bandDTO = new BandDTO();

        bandDTO.setId( band.getId() );
        bandDTO.setBandName( band.getBandName() );
        bandDTO.setLocation( band.getLocation() );
        bandDTO.setStartDateActivePeriod( band.getStartDateActivePeriod() );
        bandDTO.setEndDateActivePeriod( band.getEndDateActivePeriod() );

        return bandDTO;
    }

    @Override
    public Band toEntity(BandDTO bandDTO) {
        if ( bandDTO == null ) {
            return null;
        }

        Band band = new Band();

        if ( bandDTO.getId() != null ) {
            band.setId( bandDTO.getId() );
        }
        band.setBandName( bandDTO.getBandName() );
        band.setLocation( bandDTO.getLocation() );
        band.setStartDateActivePeriod( bandDTO.getStartDateActivePeriod() );
        band.setEndDateActivePeriod( bandDTO.getEndDateActivePeriod() );

        return band;
    }

    @Override
    public Band toEntityNew(BandNewDTO bandNewDTO) {
        if ( bandNewDTO == null ) {
            return null;
        }

        Band band = new Band();

        if ( bandNewDTO.getId() != null ) {
            band.setId( bandNewDTO.getId() );
        }
        band.setBandName( bandNewDTO.getBandName() );
        band.setLocation( bandNewDTO.getLocation() );
        band.setStartDateActivePeriod( bandNewDTO.getStartDateActivePeriod() );
        band.setEndDateActivePeriod( bandNewDTO.getEndDateActivePeriod() );

        return band;
    }

    @Override
    public List<BandDTO> toDTOs(List<Band> bands) {
        if ( bands == null ) {
            return null;
        }

        List<BandDTO> list = new ArrayList<BandDTO>( bands.size() );
        for ( Band band : bands ) {
            list.add( toDTO( band ) );
        }

        return list;
    }
}
