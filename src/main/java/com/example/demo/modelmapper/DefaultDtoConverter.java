package com.example.demo.modelmapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class DefaultDtoConverter {

    private ModelMapper modelMapper;

    public DefaultDtoConverter() {
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
    }

    public ModelMapper getModelMapper() {
        return modelMapper;
    }
}
