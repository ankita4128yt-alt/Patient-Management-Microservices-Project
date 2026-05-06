package com.ankita.patientservice.service;

import com.ankita.patientservice.dto.PatientRequestDTO;
import com.ankita.patientservice.dto.PatientResponseDTO;
import com.ankita.patientservice.exception.EmailAlreadyExistsException;
import com.ankita.patientservice.mapper.PatientMapper;
import com.ankita.patientservice.model.Patient;
import com.ankita.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getPatients(){
        List<Patient> patients = patientRepository.findAll();
        // now i need to convert my patients to patientDTOs
       // return patients.stream().map(patient -> PatientMapper.toDTO(patient)).toList();
        return patients.stream().map(PatientMapper::toDTO).toList();
    }

    public PatientResponseDTO createPatients(PatientRequestDTO patientRequestDTO){
        if(patientRepository.existsByEmail(patientRequestDTO.getEmail())) throw new EmailAlreadyExistsException("A patient with this email already Exists "+patientRequestDTO.getEmail());
        Patient newPatient = PatientMapper.toEntity(patientRequestDTO);
        patientRepository.save(newPatient);
        return PatientMapper.toDTO(newPatient);
    }
}
