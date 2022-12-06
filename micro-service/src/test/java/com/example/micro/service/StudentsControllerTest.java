package com.example.micro.service;

import static java.util.Collections.singletonList;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.micro.service.controller.StudentsController;
import com.example.micro.service.model.Students;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentsController.class)
public class StudentsControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private StudentsController studentsController;

	@Test
	public void getAllStudents() throws Exception {
		Students students = new Students();
		students.setStudentName("Adi");

		List allAStudents = singletonList(students);

		given(studentsController.getAllStudents()).willReturn(allAStudents);

		mvc.perform(get("/api/v1/students").contentType(APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].studentName", is(students.getStudentName())));
	}

	@Test
	public void getStudentsById() throws Exception {
		Students students = new Students();
		students.setStudentName("Adi");

		given(studentsController.getStudentsById(1L)).willReturn(new ResponseEntity<Students>(students, HttpStatus.OK));

		mvc.perform(get("/api/v1/students/{id}", 1).contentType(APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void createStudents() throws Exception {
		Students students = new Students();
		students.setStudentName("Adi");

		given(studentsController.createStudents(students)).willReturn(students);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(students);

		mvc.perform(post("/api/v1/students").contentType(APPLICATION_JSON).content(json).accept(APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void updateStudents() throws Exception {
		Students students = new Students();
		students.setStudentName("Adi");

		given(studentsController.updateStudents(1L, students))
				.willReturn(new ResponseEntity<Students>(students, HttpStatus.OK));
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(students);

		mvc.perform(put("/api/v1/students/{id}", 1).contentType(APPLICATION_JSON).content(json).accept(APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void deleteStudents() throws Exception {
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);

		given(studentsController.deleteStudents(1L)).willReturn(response);

		mvc.perform(delete("/api/v1/students/{id}", 1).contentType(APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}
