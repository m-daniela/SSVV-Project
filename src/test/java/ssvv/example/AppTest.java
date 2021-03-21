package ssvv.example;

import org.junit.Test;
import ssvv.example.domain.Student;
import ssvv.example.repository.NotaXMLRepo;
import ssvv.example.repository.StudentXMLRepo;
import ssvv.example.repository.TemaXMLRepo;
import ssvv.example.service.Service;
import ssvv.example.validation.NotaValidator;
import ssvv.example.validation.StudentValidator;
import ssvv.example.validation.TemaValidator;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    Service service;

    private void initialize(){
        if(service==null) {
            StudentValidator studentValidator = new StudentValidator();
            TemaValidator temaValidator = new TemaValidator();
            String filenameStudent = "src/test/java/ssvv/example/fisiere/Studenti.xml";
            String filenameTema = "src/test/java/ssvv/example/fisiere/Teme.xml";
            String filenameNota = "src/test/java/ssvv/example/fisiere/Note.xml";

            StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
            TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
            NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
            NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
            service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
        }
    }

    private Student getValidStudent(){
        return new Student("id1","John",935,"john@gmail.com", "A");
    }

    private Student getStudentWithEmptyName(){
        return new Student("id2","",935,"john@gmail.com", "A");
    }
    @Test
    public void addStudentWithValidName_ShouldReturnTrue()
    {
        initialize();
        // before each
        Student student = getValidStudent();
        try{
            Student addedStudent = service.addStudent(student);
            assert(addedStudent == student);
        }catch (Exception ex){
            assert(false);
        }
    }

    @Test
    public void addStudentWithInvalidName_ShouldReturnFalse()
    {
        initialize();
        Student student = getStudentWithEmptyName();
        try{
            service.addStudent(student);
            assert(false);
        }catch (Exception ex){
            assert(true);
        }
    }

}
