package fox.alex.votingsystem.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by fox on 11.08.16.
 */
@MappedSuperclass
@Access(AccessType.FIELD)
public class BaseEntity {

    public static final int START_INDEX = 1;

    @Id
    @SequenceGenerator(name = "start_index", sequenceName = "start_index", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "start_index")
    protected Integer id;

    @NotEmpty
    @Column(name = "name", nullable = false)
    protected String name;

    public BaseEntity() {}

    protected BaseEntity(Integer id, String name){
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNew () {
        return (this.id == null);
    }

    @Override
    public int hashCode() {
        return (this.id == null) ? 0 : this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity other = (BaseEntity) o;
        if (this.id == null || other.id == null) return false;
        return this.id.equals(other.id);
    }
}
