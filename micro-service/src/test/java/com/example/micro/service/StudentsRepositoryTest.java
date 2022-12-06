package com.example.micro.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.micro.service.model.Students;
import com.example.micro.service.repository.StudentsRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class StudentsRepositoryTest {

	@Autowired
	   private TestEntityManager entityManager;

	   @Autowired
	   private StudentsRepository studentsRepository;

	   @Test
	   public void whenFindAll() {
	       //given
		   Students firstStudent = new Students();
		   firstStudent.setStudentName("Adi");
	       entityManager.persist(firstStudent);
	       entityManager.flush();

	       Students secondStudent = new Students();
	       secondStudent.setStudentName("Abi");
	       entityManager.persist(secondStudent);
	       entityManager.flush();

	       //when
	       List arrivals = studentsRepository.findAll();

	       //then
	       assertThat(arrivals.size()).isEqualTo(2);
	       assertThat(arrivals.get(0)).isEqualTo(firstStudent);
	       assertThat(arrivals.get(1)).isEqualTo(secondStudent);
	   }

	   @Test
	   public void whenFindAllById() {
	       //given
		   Students student = new Students();
		   student.setStudentName("Adi");
	       entityManager.persist(student);
	       entityManager.flush();

	       //when
	       Optional<Students> testStudent = studentsRepository.findById(student.getEntryId());

	       //then
	       assertThat(testStudent.get().getStudentName()).isEqualTo(student.getStudentName());
	   }
}
