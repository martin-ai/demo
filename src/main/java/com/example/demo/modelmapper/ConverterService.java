package com.example.demo.modelmapper;

import com.google.common.collect.Lists;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Locale;
import java.util.stream.IntStream;

@Service
public class ConverterService {

    public void convert() {
        Student student = new Student();
        student.setCode("01");
        student.setName("xx");
        student.setDate("2019-04-02");
        Address address = new Address();
        address.setCity("1");
        address.setProvince("2");
        address.setCounty("3");
        student.setAddress(address);
        ModelMapper modelMapper = new DefaultDtoConverter().getModelMapper();
//        List<StudentDto> studentDtoList = Lists.newArrayList();
//        IntStream.range(0, 3).forEach(num -> {
//            StudentDto dto = modelMapper.map(student, StudentDto.class);
//            dto.setCode(String.format("%daa", num));
//            studentDtoList.add(dto);
//        });
        List<StudentDto> studentDtoList2 = Lists.newArrayList();
        IntStream.range(0, 3).forEach(num -> {
            StudentDto dto = new StudentDto();
            try {
                ConvertUtils.register(new DateLocaleConverter(Locale.CHINA,"yyyy-MM-dd"), java.util.Date.class);
                BeanUtils.copyProperties(dto, student);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            dto.setCode(String.format("%daa", num));
            studentDtoList2.add(dto);
        });

        System.out.println(studentDtoList2);
    }

}
