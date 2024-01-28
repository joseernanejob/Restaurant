package com.restaurant.mappers;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    static ModelMapper mapper = new ModelMapper();

    public static <O, D> D parseObject(O origin, Class<D> destination){
        return mapper.map(origin, destination);
    }

    public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination){
        ArrayList<D> list = new ArrayList<D>();

        origin.forEach(o -> list.add(mapper.map(o, destination)));

        return list;
    }
}
