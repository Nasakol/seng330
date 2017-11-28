//package org.model;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.samples.petclinic.model.Student;
//import org.springframework.samples.petclinic.repository.StudentRepository;
//import org.springframework.samples.petclinic.repository.jpa.StudentJpaConfig;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.support.AnnotationConfigContextLoader;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.annotation.Resource;
//
//import static org.junit.Assert.assertEquals;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(
//    classes = { StudentJpaConfig.class },
//    loader = AnnotationConfigContextLoader.class)
//@Transactional
//public class InMemoryDBTest {
//
//    @Resource
//    private StudentRepository studentRepository;
//
//    @Test
//    public void givenStudent_whenSave_thenGetOk() {
//        Student student = new Student(1, "john");
//        studentRepository.save(student);
//
//        Student student2 = studentRepository.findOne(1);
//        assertEquals("john", student2.getName());
//    }
//}
