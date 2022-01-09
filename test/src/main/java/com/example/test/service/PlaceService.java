package com.example.test.service;

import com.example.test.entity.Customer;
import com.example.test.entity.Place;
import com.example.test.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    public void add(String name, Long price) {
        Place place = new Place();
        place.setPlace(name);
        place.setPrice(price);
        placeRepository.save(place);
    }

    public List<Place> getAll(){
        return placeRepository.findAll();
    }

}
