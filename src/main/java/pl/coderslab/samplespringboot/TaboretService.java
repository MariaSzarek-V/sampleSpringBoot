package pl.coderslab.samplespringboot;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaboretService {
    private final TaboretRepository taboretRepository;

    public TaboretService(TaboretRepository taboretRepository) {
        this.taboretRepository = taboretRepository;
    }

    public List<Taboret>  findAll() {
        return taboretRepository.findAll();
    }
}