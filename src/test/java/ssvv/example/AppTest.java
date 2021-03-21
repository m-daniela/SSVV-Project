package ssvv.example;

import org.junit.*;
import ssvv.example.domain.Student;
import ssvv.example.repository.NotaXMLRepo;
import ssvv.example.repository.StudentXMLRepo;
import ssvv.example.repository.TemaXMLRepo;
import ssvv.example.service.Service;
import ssvv.example.validation.NotaValidator;
import ssvv.example.validation.StudentValidator;
import ssvv.example.validation.TemaValidator;


public class AppTest {
    public static Service service;

    @BeforeClass
    public static void setup() {
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

    @AfterClass
    public static void tearDown(){
        service = null;
    }

    private Student getValidStudent() {
        return new Student("id1", "John", 935, "john@gmail.com", "A");
    }

    private Student getStudentWithEmptyName() {
        return new Student("id2", "", 935, "john@gmail.com", "A");
    }
    private Student getStudentWithInvalidTeacherName(){
        return new Student("id8","Jack",925,"john@gmail.com", null);
    }
    private Student getStudentWithInvalidId(){
        return new Student("","Jim",925,"john@gmail.com", "A");
    }

    private Student getStudentWithEmptyGroup() {
        return new Student("id2", "John", 0, "john@gmail.com", "A");
    }

    private Student getStudentWithInvalidGroup() {
        return new Student("id2", "John", -2, "john@gmail.com", "A");
    }

    private Student getStudentWithEmptyEmail() {
        return new Student("id2", "John", 2, "", "A");
    }

    private Student getStudentWithNullEmail() {
        return new Student("id2", "John", 2, null, "A");
    }

    private Student getStudentWithNullName() {
        return new Student("id2", null, 2, "john@gmail.com", "A");
    }

    @Test
    public void addValidStudent_ShouldReturnTrue() {
        Student student = getValidStudent();
        try {
            Student addedStudent = service.addStudent(student);
            assert (addedStudent == student);
        } catch (Exception ex) {
            assert (false);
        }
    }

    @Test
    public void addStudentWithInvalidName_ShouldReturnFalse() {
        Student student = getStudentWithEmptyName();
        try {
            service.addStudent(student);
            assert (false);
        } catch (Exception ex) {
            assert (true);
        }
    }

    @Test
    public void addStudentWithNullName_ShouldReturnFalse() {
        Student student = getStudentWithNullName();
        try {
            service.addStudent(student);
            assert (false);
        } catch (Exception ex) {
            assert (true);
        }
    }

    @Test
    public void addStudentWithNoGroup_ShouldReturnFalse() {
        Student student = getStudentWithEmptyGroup();
        try {
            Student addedStudent = service.addStudent(student);
            System.out.println(addedStudent);
            assert (false);
        } catch (Exception ex) {
            assert (true);
        }
    }

    @Test
    public void addStudentWithInvalidGroup_ShouldReturnFalse() {
        Student student = getStudentWithInvalidGroup();
        try {
            service.addStudent(student);
            assert (false);
        } catch (Exception ex) {
            assert (true);
        }
    }

    @Test
    public void addStudentWithEmptyEmail_ShouldReturnFalse() {
        Student student = getStudentWithEmptyEmail();
        try {
            service.addStudent(student);
            assert (false);
        } catch (Exception ex) {
            assert (true);
        }
    }

    @Test
    public void addStudentWithNullEmail_ShouldReturnFalse() {
        Student student = getStudentWithNullEmail();
        try {
            service.addStudent(student);
            assert (false);
        } catch (Exception ex) {
            assert (true);
        }
    }

    //BVA for group
    @Test
    public void addStudentWithValidBV0_ShouldReturnFalse() {
        Student student = new Student("id2", "John", 0, "john@gmail.com", "A");
        try {
            service.addStudent(student);
            assert (false);
        } catch (Exception ex) {
            assert (true);
        }
    }

    @Test
    public void addStudentWithValidBV_1_ShouldReturnFalse() {
        Student student = new Student("id2", "John", -1, "john@gmail.com", "A");
        try {
            service.addStudent(student);
            assert (false);
        } catch (Exception ex) {
            assert (true);
        }
    }

    @Test
    public void addStudentWithValidBV_2_ShouldReturnFalse() {
        Student student = new Student("id2", "John", -2, "john@gmail.com", "A");
        try {
            service.addStudent(student);
            assert (false);
        } catch (Exception ex) {
            assert (true);
        }
    }

    @Test
    public void addStudentWithValidBV3_ShouldReturnTrue() {
        Student student = new Student("id2", "John", 3, "john@gmail.com", "A");
        try {
            Student addedStudent = service.addStudent(student);
            assert (student == addedStudent);
        } catch (Exception ex) {
            assert (false);
        }
    }

    @Test
    public void addStudentWithValidBV922_ShouldReturnTrue() {
        Student student = new Student("id2", "John", 922, "john@gmail.com", "A");
        try {
            Student addedStudent = service.addStudent(student);
            assert (student == addedStudent);
        } catch (Exception ex) {
            assert (false);
        }
    }

    @Test
    public void addStudentWithValidBV500_ShouldReturnTrue() {
        Student student = new Student("id2", "John", 500, "john@gmail.com", "A");
        try {
            Student addedStudent = service.addStudent(student);
            assert (addedStudent == student);
        } catch (Exception ex) {
            assert (false);
        }
    }


    @Test
    public void addStudentWithValidTeacherName_ShouldReturnTrue()
    {
        Student student = getValidStudent();
        try{
            Student addedStudent = service.addStudent(student);
            assert(addedStudent == student);
        }catch (Exception ex){
            assert(false);
        }
    }

    @Test
    public void addStudentWithInvalidTeacherName_ShouldReturnFalse()
    {
        Student student = getStudentWithInvalidTeacherName();
        try{
            service.addStudent(student);
            assert(false);
        }catch (Exception ex){
            assert(true);
        }
    }

    @Test
    public void addStudentWithValidId_ShouldReturnTrue()
    {
        Student student = getValidStudent();
        try{
            Student addedStudent = service.addStudent(student);

            assert(addedStudent == student);
        }catch (Exception ex){
            assert(false);
        }
    }

    @Test
    public void addStudentWithInvalidId_ShouldReturnFalse()
    {
        Student student = getStudentWithInvalidId();
        try{
            service.addStudent(student);
            assert(false);
        }catch (Exception ex){
            assert(true);
        }
    }

}
