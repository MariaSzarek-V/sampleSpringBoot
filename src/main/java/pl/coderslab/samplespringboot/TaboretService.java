package pl.coderslab.samplespringboot;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaboretService {
    private final TaboretRepository taboretRepository;

    public TaboretService(TaboretRepository taboretRepository) {
        this.taboretRepository = taboretRepository;
    }

    public List<Taboret> findAll() {
        return taboretRepository.findAll();
    }


    public Taboret save(Taboret taboret) {
        return taboretRepository.save(taboret);
    }

    public Optional<Taboret> findById(Long id) {
        return taboretRepository.findById(id);
    }
}