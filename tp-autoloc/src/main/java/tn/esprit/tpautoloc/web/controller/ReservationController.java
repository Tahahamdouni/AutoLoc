package tn.esprit.tpautoloc.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpautoloc.service.IReservationService;
import tn.esprit.tpautoloc.web.dto.ReservationRequestDTO;
import tn.esprit.tpautoloc.web.dto.ReservationResponseDTO;
import tn.esprit.tpautoloc.web.validator.ReservationRequestValidator;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final IReservationService reservationService;
    private final ReservationRequestValidator reservationRequestValidator;

    @GetMapping
    public List<ReservationResponseDTO> findAll() {
        return reservationService.findAll();
    }

    @GetMapping("/{id}")
    public ReservationResponseDTO findById(@PathVariable Long id) {
        return reservationService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(
            @Valid @RequestBody ReservationRequestDTO requestDTO,
            BindingResult result) {

        reservationRequestValidator.validate(requestDTO, result);

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(
                    result.getFieldErrors().stream()
                            .map(error -> error.getField() + " : " + error.getDefaultMessage())
                            .toList()
            );
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.create(requestDTO));
    }

    @PutMapping("/{id}")
    public ReservationResponseDTO update(@PathVariable Long id, @RequestBody ReservationRequestDTO requestDTO) {
        return reservationService.update(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        reservationService.deleteById(id);
    }
}