package com.digitalhouse.obtenerdiploma.service;

import com.digitalhouse.obtenerdiploma.dto.StudentDTO;
import com.digitalhouse.obtenerdiploma.dto.CertificateDTO;
import com.digitalhouse.obtenerdiploma.dto.SubjectDTO;
import org.springframework.stereotype.Service;

@Service
public class CertificateServiceImpl implements CertificateService {
  public CertificateDTO analyzeNotes(StudentDTO notes) {
    CertificateDTO response = new CertificateDTO(notes);
    response.setAverage(calculateAverage(notes));
    response.setMessage(writeDiploma(notes));
    return response;
  }

  public Double calculateAverage(StudentDTO notes) {
    double sum = notes.getSubjects().stream().mapToDouble(SubjectDTO::getNote).sum();
    return sum / notes.getSubjects().size();
  }

  public String writeDiploma(StudentDTO notes) {
    Double localAverage = calculateAverage(notes);
    String studentName = notes.getName();
    String message = studentName + " usted ha conseguido el promedio de " + localAverage;
    if(localAverage > 9)
      message = withHonors(localAverage, studentName);
    return message;
  }

  public String withHonors(Double localAverage, String localStudent) {
    return "¡Felicitaciones " + localStudent + "! Usted tiene el gran promedio de " + localAverage;
  }
}
