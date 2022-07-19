package com.example.demo;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "Scripts")
public class Scripts {
    @Id
//    @SequenceGenerator(
//            name = "scripts_sequence",
//            sequenceName ="scripts_sequence",
//            allocationSize = 1)
    @GeneratedValue
            (
            strategy = GenerationType.IDENTITY
//            generator = "scripts_sequence"
    )
    @Column(
            name = "ID",
            updatable = false
    )
    private Integer id;
    @Column(
            name = "SCRIPT",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String script;
    @Column(
            name = "NAME",
            nullable = false
//            unique = true
    )
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Scripts(String script, String name) {
        this.script = script;
        this.name = name;
    }
    public Scripts() {
    }


    @Override
    public String toString() {
        return "Scripts{" +
                "id=" + id +
                ", script='" + script + '\'' +
                ", name='" + name + '\'' +
                '}';
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scripts scripts = (Scripts) o;
        return id.equals(scripts.id) && script.equals(scripts.script) && name.equals(scripts.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, script, name);
    }
}
