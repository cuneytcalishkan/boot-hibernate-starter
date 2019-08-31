package com.amadeus.tch.service;

import java.util.Collection;

import com.amadeus.tch.model.dto.SampleDTO;

public interface ISampleService {

	Collection<SampleDTO> listServices();

	SampleDTO getSample(Long id);

	SampleDTO deleteSample(Long id);

	SampleDTO updateSample(SampleDTO sample);

	SampleDTO createSample(SampleDTO newSample);

}
