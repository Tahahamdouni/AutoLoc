package tn.esprit.tpautoloc.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.esprit.tpautoloc.domain.enums.RoleEmploye;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeDTO {
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;

    private RoleEmploye role;
    private Long agenceId;
}