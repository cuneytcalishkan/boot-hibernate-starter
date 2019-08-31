package com.amadeus.tch.controller;

import java.util.Collection;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amadeus.tch.model.ResponseMessage;
import com.amadeus.tch.model.dto.SampleDTO;
import com.amadeus.tch.service.ISampleService;

@RestController
public class SampleController {

	@Autowired
	private ISampleService sampleService;

	@GetMapping(path = "/samples")
	public Callable<ResponseMessage> listSamples() {
		return () -> {
			Collection<SampleDTO> samples = sampleService.listServices();
			ResponseMessage rsp = new ResponseMessage();
			rsp.setData(samples);
			return rsp;
		};
	}

	@GetMapping(path = "/samples/{id}")
	public Callable<ResponseMessage> getSample(@PathVariable(name = "id") Long id) {
		return () -> {
			SampleDTO sample = sampleService.getSample(id);
			ResponseMessage rsp = new ResponseMessage();
			rsp.setData(sample);
			return rsp;
		};
	}

	@PostMapping(path = "/samples")
	public Callable<ResponseMessage> createSample(@RequestBody @Validated SampleDTO newSample) {
		return () -> {
			SampleDTO sample = sampleService.createSample(newSample);
			ResponseMessage rsp = new ResponseMessage();
			rsp.setData(sample);
			return rsp;
		};
	}

}
