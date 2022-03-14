package kg.itschool.sellservice.services.impl;


import kg.itschool.sellservice.dao.RequestRepo;
import kg.itschool.sellservice.mappers.CodeMapper;
import kg.itschool.sellservice.models.dtos.CodeEntitiesDto;
import kg.itschool.sellservice.models.entities.Requests;
import kg.itschool.sellservice.services.CodeService;
import kg.itschool.sellservice.services.RequestService;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl implements RequestService {
    private RequestRepo requestRepo;

    public RequestServiceImpl(RequestRepo requestRepo) {
        this.requestRepo = requestRepo;
    }

    @Override
    public void saveRequest(CodeEntitiesDto codeEntitiesDto, boolean b) {
        Requests requests = new Requests();
        requests.setSuccess(b);
        requests.setCodes(CodeMapper.INSTANCE.CodeEntitiesDtoToCodes(codeEntitiesDto));
        requests = requestRepo.save(requests);
    }

    @Override
    public int countOfError(CodeEntitiesDto codeEntitiesDto) {
        int count = requestRepo.getCount(CodeMapper.INSTANCE.CodeEntitiesDtoToCodes(codeEntitiesDto));
        return count;
    }
}
