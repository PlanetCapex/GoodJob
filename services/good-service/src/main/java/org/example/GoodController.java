package org.example;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/good")
@RequiredArgsConstructor
public class GoodController {

    private final GoodRepository goodRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Good>> getGood(@PathVariable("id") Long id) {
        Optional<Good> good = goodRepository.findById(id);
        if (good.isPresent()) {
            return ResponseEntity.ok(good);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Good> createGood(@RequestParam("name") String name) {
        Good newGood = Good.builder()
                .name(name)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(goodRepository.save(newGood));
    }
}
