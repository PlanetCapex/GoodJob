package org.example;

import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryRepository deliveryRepository;
    private final UserClient userClient;
    private final GoodClient goodClient;

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryDTO> getDelivery(@PathVariable("id") Long id) {
        Optional<Delivery> delivery = deliveryRepository.findById(id);
        if (delivery.isPresent()) {
            Good good = goodClient.getGoodById(delivery.get().getGoodId());
            User user = userClient.getUserById(delivery.get().getUserId());
            DeliveryDTO deliveryDTO = DeliveryDTO.builder()
                    .id(delivery.get().getId())
                    .good(good)
                    .user(user)
                    .build();
            return ResponseEntity.ok(deliveryDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Delivery> createDelivery(@RequestParam("goodId") Long goodId,
                                                   @RequestParam("userId") Long userId) {
        try {
            Good good = goodClient.getGoodById(goodId);
            User user = userClient.getUserById(userId);
        } catch (NotFoundException ex) {
            return ResponseEntity.badRequest().build();
        }

        Delivery newDelivery = Delivery.builder()
                .userId(userId)
                .goodId(goodId)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(deliveryRepository.save(newDelivery));
    }
}
