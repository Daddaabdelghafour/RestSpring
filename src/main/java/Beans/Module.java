package Beans;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @ManyToOne
    @JoinColumn(name="prof_id")
    private Prof prof;

    @ManyToMany(mappedBy = "modules")
    private Set<Student> students = new HashSet<>();

    public Module() {
        // Default constructor
    }

    public Module(String name, Prof prof) {
        this.name = name;
        this.prof = prof;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Prof getProf() {
        return prof;
    }

    public void setProf(Prof prof) {
        this.prof = prof;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}