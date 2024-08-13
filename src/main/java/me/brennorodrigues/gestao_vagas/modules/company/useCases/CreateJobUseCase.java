package me.brennorodrigues.gestao_vagas.modules.company.useCases;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.brennorodrigues.gestao_vagas.exceptions.ForeignIdNotFoundExeption;
import me.brennorodrigues.gestao_vagas.modules.company.entities.JobEntity;
import me.brennorodrigues.gestao_vagas.modules.company.repositories.JobRepository;

@Service
public class CreateJobUseCase {
    
    @Autowired
    private JobRepository jobRepository;
    
    public JobEntity execute(JobEntity jobEntity) {
        if(!this.jobRepository.findByCompanyId(jobEntity.getCompanyId()).isPresent()) {
            throw new ForeignIdNotFoundExeption();
        }
        return this.jobRepository.save(jobEntity);
    }
}
