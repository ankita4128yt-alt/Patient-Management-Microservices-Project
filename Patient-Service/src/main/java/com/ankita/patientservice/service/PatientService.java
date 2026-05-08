package com.ankita.patientservice.service;

import com.ankita.patientservice.dto.PatientRequestDTO;
import com.ankita.patientservice.dto.PatientResponseDTO;
import com.ankita.patientservice.exception.EmailAlreadyExistsException;
import com.ankita.patientservice.exception.PatientNotFoundException;
import com.ankita.patientservice.mapper.PatientMapper;
import com.ankita.patientservice.model.Patient;
import com.ankita.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public PatientResponseDTO updatePatient(UUID id , PatientRequestDTO patientRequestDTO){
        Patient patient = patientRepository.findById(id).orElseThrow(()->new PatientNotFoundException("Patient not found with ID: " + id));
        if(patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail().toString(),id)){
            throw new EmailAlreadyExistsException("A patient with this email already Exists "+patientRequestDTO.getEmail());
        }
        patient.setName(patientRequestDTO.getName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));

        Patient updatedPatient = patientRepository.save(patient);
        return PatientMapper.toDTO(updatedPatient);
    }

    public void deletePatient(UUID id){
        patientRepository.deleteById(id);
    }
}
