package kg.itschool.sellservice.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class UserProductInfoDto {
    List<UserProductInfoObj> userProductInfoObjList;
    private Double sum;
}
