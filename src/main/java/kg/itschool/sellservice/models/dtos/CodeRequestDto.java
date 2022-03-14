package kg.itschool.sellservice.models.dtos;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CodeRequestDto {
    String code;
    String messeng;
}
