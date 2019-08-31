package com.amadeus.tch.service;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amadeus.tch.manager.ISampleManager;
import com.amadeus.tch.model.Sample;
import com.amadeus.tch.model.dto.SampleDTO;

@Service
public class SampleService implements ISampleService {

	@Autowired
	private ISampleManager sampleManager;

	@Transactional(readOnly = true)
	@Override
	public Collection<SampleDTO> listServices() {
		return sampleManager.findAll().stream().map(s -> s.toDTO()).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	@Override
	public SampleDTO getSample(Long id) {
		return sampleManager.findByID(id).orElseThrow().toDTO();
	}

	@Transactional
	@Override
	public SampleDTO deleteSample(Long id) {
		Optional<Sample> deleted = sampleManager.findByID(id);
		deleted.ifPresent(s -> sampleManager.delete(s));
		return deleted.orElseThrow().toDTO();
	}

	@Transactional
	@Override
	public SampleDTO updateSample(SampleDTO sample) {
		Optional<Sample> updated = sampleManager.findByID(sample.getId());
		if (updated.isPresent()) {
			Sample u = updated.get();
			u.setName(sample.getName());
			u.setCode(sample.getCode());
			sampleManager.update(u);
			return u.toDTO();
		}
		throw new NoSuchElementException();
	}

	@Transactional
	@Override
	public SampleDTO createSample(SampleDTO newSample) {
		Sample s = new Sample();
		s.setName(newSample.getName());
		s.setCode(newSample.getCode());
		sampleManager.save(s);
		return s.toDTO();
	}

}
