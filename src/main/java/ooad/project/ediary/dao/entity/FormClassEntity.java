package ooad.project.ediary.dao.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "form_class")
public class FormClassEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "year")
    private Long year;

    @Column(name = "identifier")
    private String identifier;

    @OneToOne
    @JoinColumn(name = "form_tutor")
    @ToString.Exclude
    private UserEntity formTutor;

    @Column(name = "room_number")
    private Long roomNumber;

    @ManyToOne
    @JoinColumn(name = "school_id", referencedColumnName = "id")
    @JsonBackReference
    private SchoolEntity school;

    @Column(name = "timetable_id")
    private Long timetableId;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
