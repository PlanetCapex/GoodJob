package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "good")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Good {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int cost;

}
