package com.amadeus.tch.manager;

import org.springframework.stereotype.Repository;

import com.amadeus.tch.model.Sample;

@Repository
public class SampleManager extends AbstractEntityManager<Sample> implements ISampleManager {

	public SampleManager() {
		setClass(Sample.class);
	}

}
