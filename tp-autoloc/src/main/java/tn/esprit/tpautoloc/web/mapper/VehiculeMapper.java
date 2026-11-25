package tn.esprit.tpautoloc.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.tpautoloc.domain.Vehicule;
import tn.esprit.tpautoloc.web.dto.VehiculeDTO;

@Mapper(componentModel = "spring")
public interface VehiculeMapper {

    @Mapping(target = "agenceId", source = "agence.idAgence")
    VehiculeDTO toDto(Vehicule vehicule);

    @Mapping(target = "agence", ignore = true)
    @Mapping(target = "reservations", ignore = true)
    @Mapping(target = "maintenances", ignore = true)
    Vehicule toEntity(VehiculeDTO dto);
}
