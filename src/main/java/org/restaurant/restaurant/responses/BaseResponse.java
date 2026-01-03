package org.restaurant.restaurant.responses;

import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
