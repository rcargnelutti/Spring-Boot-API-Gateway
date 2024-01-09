package com.example.springboot.gateway;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ServiceController {

    @Autowired
    ServiceRepository serviceRepository;

    @PostMapping("/services")
    public ResponseEntity<ServiceModel> saveService(@RequestBody @Valid ServiceRecordDto serviceRecordDto) {
        var serviceModel = new ServiceModel();
        BeanUtils.copyProperties(serviceRecordDto, serviceModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceRepository.save(serviceModel));
    }

    @GetMapping("/services/{id}")
    public ResponseEntity<Object> getOneService(@PathVariable(value="id") UUID id) {
        Optional<ServiceModel> serviceO = serviceRepository.findById(id);
        if (serviceO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Service not found.");
        }
        serviceO.get().add(linkTo(methodOn(ServiceController.class).getAllServices()).withRel("Service List"));
        return ResponseEntity.status(HttpStatus.OK).body(serviceO.get());
    }

    @GetMapping("/services")
    public ResponseEntity<List<ServiceModel>> getAllServices(){
        List<ServiceModel> servicesList = serviceRepository.findAll();
        if(!servicesList.isEmpty()) {
            for(ServiceModel service : servicesList) {
                UUID id = service.getIdService();
                service.add(linkTo(methodOn(ServiceController.class).getOneService(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(servicesList);
    }

    @PutMapping("/services/{id}")
    public ResponseEntity<Object> updateService(@PathVariable(value="id") UUID id, @RequestBody @Valid ServiceRecordDto serviceRecordDto) {
        Optional<ServiceModel> serviceO = serviceRepository.findById(id);
        if(serviceO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Service not found.");
        }
        var serviceModel = serviceO.get();
        BeanUtils.copyProperties(serviceRecordDto, serviceModel);
        return ResponseEntity.status(HttpStatus.OK).body(serviceRepository.save(serviceModel));
    }

    @DeleteMapping("/services/{id}")
    public ResponseEntity<Object> deleteService(@PathVariable(value="id") UUID id) {
        Optional<ServiceModel> serviceO = serviceRepository.findById(id);
        if(serviceO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Service not found.");
        }
        serviceRepository.delete(serviceO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Service deleted successfully.");
    }

}
