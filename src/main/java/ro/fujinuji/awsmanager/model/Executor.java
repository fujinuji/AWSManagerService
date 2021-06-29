package ro.fujinuji.awsmanager.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "executor")
public class Executor {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String executorId;
    private String name;

    @ManyToOne
    private User user;

    @OneToMany(cascade=CascadeType.ALL)
    private List<ExecutionUnit> executionUnits;
}
