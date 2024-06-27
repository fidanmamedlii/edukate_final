package com.example.edukate.service;

import com.example.edukate.dtos.testimonialdto.TestimonialCreateDto;
import com.example.edukate.dtos.testimonialdto.TestimonialDto;

import java.util.List;

public interface TestimonialService {
    List<TestimonialDto> getTestimonials();
    TestimonialDto getTestimonial(Long id);
    void addTestimonial(TestimonialCreateDto testimonialCreateDto);
    void deleteTestimonial(Long id);
}


