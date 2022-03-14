package kg.itschool.sellservice.models.dtos;

import kg.itschool.sellservice.models.entities.OperationDetails;
import lombok.Data;

import java.util.List;
@Data
public class OperationResponseDto {
    private List<OperationResponseObj> OperationResponseObjList;
    private double sum;
}
