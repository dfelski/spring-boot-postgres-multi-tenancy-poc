package com.example.web;

import com.example.db.TestData;
import com.example.db.TestDataRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
class TestDataController {

    private final TestDataRepository testDataRepository;

    TestDataController(TestDataRepository testDataRepository) {
        this.testDataRepository = testDataRepository;
    }

    @GetMapping("/data")
    public List<TestDataDTO> getTestData(){
        return testDataRepository.findAll().stream()
                .map(td -> new TestDataDTO(td.getId(), td.getValue()))
                .toList();
    }

    @GetMapping("/data/{id}")
    public TestDataDTO getTestData(@PathVariable("id") UUID id){
        TestData testData = testDataRepository.findById(id)
                .orElseThrow(TestDataNotFoundException::new);
        return new TestDataDTO(testData.getId(), testData.getValue());
    }

    @PostMapping("/data")
    public TestDataDTO createTestData(@RequestBody CreateTestDataDTO createTestDataDTO){
        TestData testData = testDataRepository.save(new TestData(createTestDataDTO.value));
        return new TestDataDTO(testData.getId(), testData.getValue());
    }

    record CreateTestDataDTO(String value){}
    record TestDataDTO(UUID id, String value){}

    @ResponseStatus(HttpStatus.NOT_FOUND)
    static class TestDataNotFoundException extends IllegalArgumentException{ }
}
