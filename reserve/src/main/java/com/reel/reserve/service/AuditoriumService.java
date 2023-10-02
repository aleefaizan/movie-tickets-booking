package com.reel.reserve.service;

import com.reel.reserve.exception.ResourceNotFoundException;
import com.reel.reserve.models.Auditorium;
import com.reel.reserve.repository.AuditoriumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditoriumService {

    @Autowired
    private AuditoriumRepository auditoriumRepository;

    public String addAuditorium(Auditorium auditorium) {
        auditoriumRepository.save(auditorium);
        return "Auditorium added successfully.";
    }

    public String deleteAuditorium(Long auditoriumId) throws ResourceNotFoundException {
        Auditorium auditorium = auditoriumRepository.findById(auditoriumId)
                .orElseThrow(() -> new ResourceNotFoundException("Auditorium doesn't exist."));
        auditoriumRepository.delete(auditorium);
        return "Auditorium Deleted Successfully.";
    }
}
