package com.example.edukate.service.impl;

import com.example.edukate.dtos.testimonialdto.TestimonialCreateDto;
import com.example.edukate.dtos.testimonialdto.TestimonialDto;
import com.example.edukate.models.Testimonial;
import com.example.edukate.repositories.StudentRepository;
import com.example.edukate.repositories.TestimonialRepository;
import com.example.edukate.service.TestimonialService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TestimonialServiceImpl implements TestimonialService
{
    private final TestimonialRepository testimonialRepository;
    private final StudentRepository studentRepository;
    private final ModelMapper mapper;

    @Autowired
    public TestimonialServiceImpl(TestimonialRepository testimonialRepository, StudentRepository studentRepository, ModelMapper mapper) {
        this.testimonialRepository = testimonialRepository;
        this.studentRepository = studentRepository;
        this.mapper = mapper;
    }

    @Override
    public List<TestimonialDto> getTestimonials() {
        return testimonialRepository.findAll().stream()
                .filter(testimonial -> !testimonial.isDeleted())
                .map(testimonial -> mapper.map(testimonial,TestimonialDto.class))
                .toList();
    }

    @Override
    public TestimonialDto getTestimonial(Long id) {
        Testimonial testimonial=testimonialRepository.findById(id).orElseThrow(RuntimeException::new);
        TestimonialDto testimonialDto=mapper.map(testimonial,TestimonialDto.class);

        return testimonialDto;
    }

    @Override
    @Transactional
    public void addTestimonial(TestimonialCreateDto testimonialCreateDto) {
        testimonialRepository.save(mapper.map(testimonialCreateDto,Testimonial.class));
    }

    @Override
    @Transactional
    public void deleteTestimonial(Long id) {
        Testimonial testimonial=testimonialRepository.findById(id).orElseThrow(RuntimeException::new);
        testimonial.setDeleted(true);

        testimonialRepository.save(testimonial);
    }
}
