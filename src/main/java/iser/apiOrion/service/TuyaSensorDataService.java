package iser.apiOrion.service;

import iser.apiOrion.collection.TuyaSensorData;
import iser.apiOrion.repository.TuyaSensorDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TuyaSensorDataService {

    @Autowired
    private TuyaSensorDataRepository repository;

    public TuyaSensorData save(TuyaSensorData data) {
        return repository.save(data);
    }

    public List<TuyaSensorData> findAll() {
        return repository.findAll();
    }

    public Optional<TuyaSensorData> findById(String id) {
        return repository.findById(id);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

     public Optional<TuyaSensorData> findByNombre(String nombre) { 
        return repository.findByNombre(nombre);
    }

    public TuyaSensorData update(String id, TuyaSensorData newData) {
        return repository.findById(id)
                .map(data -> {
                    data.setPh(newData.getPh());
                    data.setOrp(newData.getOrp());
                    data.setEc(newData.getEc());
                    data.setTds(newData.getTds());
                    data.setSalinidad(newData.getSalinidad());
                    data.setTemperatura(newData.getTemperatura());
                    return repository.save(data);
                }).orElse(null);
    }
}

